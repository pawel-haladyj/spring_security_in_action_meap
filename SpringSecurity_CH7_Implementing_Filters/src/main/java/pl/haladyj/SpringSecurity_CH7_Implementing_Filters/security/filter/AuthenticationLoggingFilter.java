package pl.haladyj.SpringSecurity_CH7_Implementing_Filters.security.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Logger;

public class AuthenticationLoggingFilter implements Filter {

    private final Logger logger = Logger.getLogger(AuthenticationLoggingFilter.class.getName());

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        var httpRequest = (HttpServletRequest) request;
        String requestId = httpRequest.getHeader("Request-id");

        logger.info("Succesfullty authenticated with id: " + requestId);

        chain.doFilter(request, response);
    }
}
