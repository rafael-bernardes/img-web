package br.gov.bom_destino.img_web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ImgWebFilter implements Filter {

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpSession session = req.getSession(true);
		
		if(!req.getRequestURI().contains("login") && session.getAttribute("usuario-logado") == null) {
			HttpServletResponse res = (HttpServletResponse) arg1;
			
			res.sendRedirect("/img-web/login.xhtml");
		}else {
			arg2.doFilter(arg0, arg1);
		}
	}

}
