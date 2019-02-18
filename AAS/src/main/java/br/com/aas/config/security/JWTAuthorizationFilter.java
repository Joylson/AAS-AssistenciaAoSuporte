package br.com.aas.config.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import br.com.aas.config.SecurityConstants;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authManager) {

		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		String header = req.getHeader("Authorization");

		if (header == null || !header.startsWith("Bearer ")) {
			chain.doFilter(req, res);
			return;
		}

		try {
			UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			chain.doFilter(req, res);
		} catch (TokenExpiredException e) {
			res.setStatus(401);
			res.setContentType("application/json");
			res.getWriter().append(json("token Expirado"));
		} catch (JWTDecodeException e) {
			res.setStatus(401);
			res.setContentType("application/json");
			res.getWriter().append(json("token Invalido"));
		}
	}

	private String json(String mensage) {
		long date = new Date().getTime();
		return "{\"timestamp\": " + date + ", " + "\"status\": 401, " + "\"error\": \"Erro de Autentifição\", "
				+ "\"message\": \"" + mensage + "\", " + "\"path\": \"/login\"}";
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request)
			throws TokenExpiredException, JWTDecodeException {
		String token = request.getHeader("Authorization");
		if (token != null) {
			// parse the token.
			String user = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes())).build()
					.verify(token.replace(SecurityConstants.TOKEN_PREFIX, "")).getSubject();

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
			return null;
		}
		return null;
	}

}
