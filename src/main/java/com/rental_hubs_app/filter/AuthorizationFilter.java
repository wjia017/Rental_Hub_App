package com.rental_hubs_app.filter;

import java.io.IOException;

import com.rental_hubs_app.utils.SessionUtil;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {
	    "/admin-dashboard",
	    "/clothes",
	    "/add-cloth",
	    "/edit-cloth",
	    "/delete-cloth",
	    "/admin-rentals",
	    "/update-rental-status",
	    "/manage-users",
	    "/update-user-status",
	    "/update-user-role",
	    "/delete-user"
	})
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String role = (String) SessionUtil.getAttribute(req, "role");

        if (role == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        if (!role.equalsIgnoreCase("admin")) {
        	req.getRequestDispatcher("/WEB-INF/pages/access_denied.jsp")
        	   .forward(req, res);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}