package studio.geek.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 李文浩
 * @version 2017/2/21.
 */


//@WebFilter(urlPatterns = {"/back-end/back-end.html", "/candidate/*", "/member/*", "/production/*"},
//        filterName = "sessionFilter")
public class SessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getSession().getAttribute("manager") == null) {
            System.out.println("有人想当管理员");
            response.sendRedirect("/back-end/login.html");
        } else
            filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
