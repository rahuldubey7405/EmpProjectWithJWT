package com.springBootProject.security.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.springBootProject.model.AuthUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenHelper {
//	private static Category log = FleetLogger.getLogger();
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	private final String secret = "jwtTokenKeyjwtTokenKeyjwtTokenKeyjwtTokenKeyjwtTokenKey";

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);

	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	public Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		boolean isExpired = expiration.before(new Date());
		return isExpired;
	}

	public String generateToken(AuthUser user) {
		Map<String, Object> claims = new HashMap<>();

		return doGenerateToken(claims, user.getEmail());
	}

	public String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		System.out.println("VelidateToken: " + isTokenExpired(token));
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}