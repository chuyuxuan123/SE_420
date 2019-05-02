package com.example.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

//        request.getRequestDispatcher("/login").forward(request, response);
//        System.out.println("login success");

//        SavedRequest savedRequest = (SavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
//        if(savedRequest != null) {
//            String redirectUrl =   savedRequest.getRedirectUrl();
//            request.getSession().removeAttribute("SPRING_SECURITY_SAVED_REQUEST");
//        }
////        response.sendRedirect(redirectUrl);
//        response.setStatus(200);
        response.addHeader("login", "success");


    }

}
