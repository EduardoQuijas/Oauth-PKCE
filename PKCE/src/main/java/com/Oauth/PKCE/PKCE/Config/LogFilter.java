package com.Oauth.PKCE.PKCE.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Enumeration;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class LogFilter implements Filter {
    @Override
    public boolean isLoggable(LogRecord record) {

        public void doFilter ;(ServletRequest, ServletResponse, FilterChain)
			throws IOException, ServletException {

            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            LocalDateTime date = LocalDateTime.now();
            System.err.println("LogFilter: " + date + " - " + httpRequest.getLocalAddr() + ":" + httpRequest.getLocalPort() + httpRequest.getServletPath());
            System.out.println("Request:");
            Enumeration<String> headers = httpRequest.getHeaderNames();
            while(headers.hasMoreElements()) {
                String headerName = (String)headers.nextElement();
                System.out.println("\tHeader: " + headerName + ":" + httpRequest.getHeader(headerName));
            }
            System.out.println("\n");
            Enumeration<String> parameters = httpRequest.getParameterNames();
            while(parameters.hasMoreElements()) {
                String parameterName = (String)parameters.nextElement();
                System.out.println("\tParameter: " + parameterName + ": " + httpRequest.getParameter(parameterName));
            }
            System.out.println("\nResponse:");
            chain.doFilter(request, response);
            Collection<String> responseHeaders = httpResponse.getHeaderNames();
            responseHeaders.forEach(x -> System.out.println("\tHeader: " + x + ": " + httpResponse.getHeader(x)));
            System.out.println("\n\n");
        }
    }
}
