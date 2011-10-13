package br.edu.unoesc.alligator.webmodule.filter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TenantContextLoaderListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// do nothing
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		String packageName = arg0.getServletContext().getInitParameter(WebModuleUtils.ARG_PACKAGE_NAME);
		String puName = arg0.getServletContext().getInitParameter(WebModuleUtils.ARG_PERSISTENCE_UNIT_NAME);

		// TODO iniciar o context

	}

}
