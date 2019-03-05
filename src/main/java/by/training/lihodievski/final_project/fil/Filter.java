package by.training.lihodievski.final_project.fil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class Filter implements javax.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding ("utf-8");
        servletResponse.setContentType("text/html; charset=UTF-8");

        filterChain.doFilter (servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}