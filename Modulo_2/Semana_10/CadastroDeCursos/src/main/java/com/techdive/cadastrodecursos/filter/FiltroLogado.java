package com.techdive.cadastrodecursos.filter;

import com.techdive.cadastrodecursos.bean.LoginBean;
import com.techdive.cadastrodecursos.model.Usuario;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/faces/secure/*")
public class FiltroLogado implements Filter {

    @Inject
    private LoginBean loginBean;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String URL = req.getRequestURL().toString();

        if (loginBean == null || loginBean.getUsuario() == null) {
            res.sendRedirect(req.getServletContext().getContextPath() + "/faces/login.xhtml");
        } else {
            chain.doFilter(request,response);
        }

    }

}