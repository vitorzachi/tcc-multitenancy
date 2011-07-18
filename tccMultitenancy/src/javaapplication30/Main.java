/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication30;

import br.edu.unoesc.tcc.context.TenantContext;

/**
 * 
 * @author vitor
 */
public class Main {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		TenantContext.setUp("JavaApplication30PU", "javaapplication30");

		Empresa e = new Empresa();
		e.setNome("TESTE");

		TenantContext.getEntityManager().getTransaction().begin();
		TenantContext.getEntityManager().persist(e);
		TenantContext.getEntityManager().getTransaction().commit();

		TenantContext.setTenantOwner(e);

		System.out.println(TenantContext.getEntityManager()
				.createQuery("select e from Empresa e").getResultList());

		Cliente c = new Cliente();
		c.setNome("clt");

		TenantContext.getEntityManager().getTransaction().begin();
		TenantContext.getEntityManager().persist(c);
		TenantContext.getEntityManager().getTransaction().commit();

		System.out.println(TenantContext.getEntityManager()
				.createQuery("select e from Cliente e").getResultList());
	}

}
