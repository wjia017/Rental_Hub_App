package com.rental_hub_app.filter;

import java.io.IOException;

import com.rental_hub_app.utils.SessionUtil;

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
	    "/user-dashboard",
	    "/clothes",
	    "/add-cloth",
	    "/edit-cloth",
	    "/delete-cloth",
	    "/browse-clothes",
	    "/rent-cloth",
	    "/my-rentals",
	    "/admin-rentals",
	    "/update-rental-status",
	    "/profile",
	    "/manage-users",
	    "/update-user-status",
	    "/update-user-role",
	    "/delete-user"
	})
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        Object loggedUser = SessionUtil.getAttribute(req, "loggedUser");

        if (loggedUser == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}