package com.course.api.gateway.security.jwt;

import com.course.api.gateway.model.User;
import com.course.api.gateway.security.CustomUserDetails;
import com.course.api.gateway.util.SecurityUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JWTTokenProviderImpl extends JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String JWT_SECRET;

    @Value("${app.jwt.expiration-in-ms}")
    private Long JWT_EXPIRATION_IN_MS;





    @Override
    public String generateToken(CustomUserDetails customUserDetails) {

        String authorities = customUserDetails.getAuthorities().stream().map(grantedAuthority -> grantedAuthority.getAuthority()).
                collect(Collectors.joining(","));

        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder().setSubject(customUserDetails.getUsername())
                .claim("roles", authorities)
                .claim("userID", customUserDetails.getId())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

    }


    @Override
    public Authentication getauthentication(HttpServletRequest httpServletRequest) {

        Claims claims = extractClaims(httpServletRequest);
        if (claims == null) {
            return null;
        }


        String username = claims.getSubject();
        Long UserId = claims.get("userID", Long.class);

        Set<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(",")).map(SecurityUtil::convertToAuthority).collect(Collectors.toSet());

        CustomUserDetails principal = new CustomUserDetails();

        principal.setUsername(username);
        principal.setAuthorities(authorities);
        principal.setId(UserId);
        User user=new User();
        user.setId(UserId);
        user.setUsername(username);
        principal.setUser(user);


        if (username == null) {
            return null;
        }


        return new UsernamePasswordAuthenticationToken(principal, null, authorities);

    }

    private Claims extractClaims(HttpServletRequest httpServletRequest) {
        String token = SecurityUtil.exactractAuthTokenFromRequest(httpServletRequest);
        if (token == null) {
            return null;
        }
        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }



@Override
    public Boolean isTokenValid (HttpServletRequest httpServletRequest)
    {
        Claims claims=extractClaims(httpServletRequest);

        if (claims==null)
        {

            return false;

        }


        if (claims.getExpiration().before(new Date()))
        {
            return false;
        }

        return true;


    }
}
