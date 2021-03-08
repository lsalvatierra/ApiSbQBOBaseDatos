package com.qbo.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

public class FiltroJWTAutorizacion extends OncePerRequestFilter {
	
	private final String HEADER = "Authorization";
	private final String PREFIJO = "Bearer ";
	private final String CLAVESECRETA = "@IDAT2021";

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			if(existeJWTToken(request, response)) {
				Claims claims = validarToken(request);
				if(claims.get("authorities") != null) {
					crearAutenticacion(claims);
				}else {
					SecurityContextHolder.clearContext();
				}
			}else {
				SecurityContextHolder.clearContext();
			}
			filterChain.doFilter(request, response);
		} catch (ExpiredJwtException | UnsupportedJwtException 
				| MalformedJwtException ex) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			((HttpServletResponse)response)
			.sendError(HttpServletResponse.SC_FORBIDDEN, ex.getMessage());
			return;
		}
	}
	
	private void crearAutenticacion(Claims claims) {
		@SuppressWarnings("unchecked")
		List<String> autorizaciones = (List<String>)claims.get("authorities");
		UsernamePasswordAuthenticationToken autenticaciontoken = 
				new UsernamePasswordAuthenticationToken(
						claims.getSubject(),
						null,
						autorizaciones.stream()
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList())
						);
		SecurityContextHolder.getContext().setAuthentication(autenticaciontoken);
	}
		
	private Claims validarToken(HttpServletRequest peticion) {
		String jwtToken = peticion.getHeader(HEADER).replace(PREFIJO, "");
		return Jwts.parser().setSigningKey(CLAVESECRETA.getBytes())
				.parseClaimsJws(jwtToken).getBody();
	}	
	
	private boolean existeJWTToken(HttpServletRequest peticion,
			HttpServletResponse respuesta) {
		String autenticacion = peticion.getHeader(HEADER);
		if(autenticacion == null || !autenticacion.startsWith(PREFIJO)) {
			return false;
		}
		return true;
	}

}
