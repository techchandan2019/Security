package com.neosoft.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTService {

	private static final String SECRECT="29482B4D6251655468576D5A7134743777217A25432A462D4A614E635266556A";
	
	public String generateToken(String usn) {
		
		Map<String,Object> claims=new HashMap<>();
		return createToken(claims,usn);
		
	}

	private String createToken(Map<String, Object> claims, String usn) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(usn)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
				.signWith(getSignKey(),SignatureAlgorithm.HS256).compact();
	}

	private Key getSignKey() {
		byte[] keyByte=Decoders.BASE64.decode(SECRECT);
		return Keys.hmacShaKeyFor(keyByte);
	}
	
	  public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }

	    public Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }

	    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }
		private Claims extractAllClaims(String token) {
		    return Jwts.parserBuilder().setSigningKey(getSignKey()).build()
		    		.parseClaimsJws(token)
		    		.getBody();
		}

	    private Boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }
	    public Boolean validateToken(String token, UserDetails userDetails) {
	        final String username = extractUsername(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }

}
