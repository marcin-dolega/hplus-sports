package pl.dolega.hplussports.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        // pre processing
        HttpServletRequest request = (HttpServletRequest) req;

        if (request.getRequestURI().startsWith("/hplus/orderHistory") || request.getRequestURI().startsWith("/hplus/getProfileDetails")) {

            HttpSession session = request.getSession();

            if (session.getAttribute("username") == null) {
                request.getRequestDispatcher("/html/login.jsp").forward(request, res);
            }

        }

        chain.doFilter(request, res);

        // post processing

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
