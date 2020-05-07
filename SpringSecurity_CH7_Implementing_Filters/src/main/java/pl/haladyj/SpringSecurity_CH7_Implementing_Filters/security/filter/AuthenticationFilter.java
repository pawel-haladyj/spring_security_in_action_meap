package pl.haladyj.SpringSecurity_CH7_Implementing_Filters.security.filter;

import javax.servlet.*;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

    }
}
