package com.ats_prototype.atsprototype.config;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String redirectUrl = "/ATS.in/";

        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_CANDIDATE")) {
                redirectUrl = "/ATS.in/candidate/dashboard";
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_EMPLOYER")) {
                redirectUrl = "/ATS.in/employer/dashboard";
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_RECRUITER")) {
                redirectUrl = "/ATS.in/recruiter/dashboard";
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_COORDINATOR")) {
                redirectUrl = "/ATS.in/coordinator/dashboard";
                break;
            }
        }

        response.sendRedirect(redirectUrl);
    }
}