package com.course.api.gateway.util;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class SecurityUtil {

    public static final String ROLE_PREFIX="ROLE_";

    public static final String AUTH_HEADER="Authorization";

    public static final String AUTH_TOKEN_TYPE="Bearer";

    public static final String AUTH_TOKEN_PREFIX=AUTH_TOKEN_TYPE+" ";


    public  static SimpleGrantedAuthority convertToAuthority(String role)
    {

        String formattedRole =role.startsWith(ROLE_PREFIX)?role:ROLE_PREFIX+role;

        return  new SimpleGrantedAuthority(formattedRole);
    }


    public static String exactractAuthTokenFromRequest(HttpServletRequest httpServletRequest)
    {
         String bearerToken=httpServletRequest.getHeader(AUTH_HEADER);

         if (StringUtils.hasLength(bearerToken)&&bearerToken.startsWith(AUTH_TOKEN_PREFIX))
         {

             return bearerToken.substring(7);
         }

         return null;

    }

}
