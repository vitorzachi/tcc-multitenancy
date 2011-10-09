package br.edu.unoesc.alligator.webmodule.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.unoesc.alligator.TenantOwner;
import br.edu.unoesc.alligator.context.impl.TenantHolder;

/**
 * filtro responsável por propagar o TenantOwner na sessão http
 * 
 * @author vitor
 * 
 */
public class TenantOwnerPropagationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// do nothing
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession httpSession = httpRequest.getSession();

		try {
			TenantOwner tenantOwner = (TenantOwner) httpSession.getAttribute(TenantHolder.nameATTR);
			TenantHolder.setTenantOwner(tenantOwner);
			chain.doFilter(httpRequest, httpResponse);

		} finally {
			TenantOwner tenantOwner = TenantHolder.getTenantOwner();
			httpSession = httpRequest.getSession(false);
			if (httpSession == null) {
				httpSession = httpRequest.getSession(true);
			}
			httpSession.setAttribute(TenantHolder.nameATTR, tenantOwner);
		}
	}

	@Override
	public void destroy() {
		// do nothing
	}
}
