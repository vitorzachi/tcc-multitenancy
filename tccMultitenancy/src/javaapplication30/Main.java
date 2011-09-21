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

	public static void main(String[] args) {

		// inicializando TenantContext
		TenantContext.setUp("JavaApplication30PU", "javaapplication30");

		// criando empresa
		Empresa e = new Empresa();
		e.setNome("TESTE " + System.currentTimeMillis());

		// salvando a empresa no banco de dados
		TenantContext.getEntityManager().getTransaction().begin();
		TenantContext.getEntityManager().persist(e);
		System.out.println("salvei empresa nome:" + e.getNome());
		TenantContext.getEntityManager().getTransaction().commit();

		// setando a empresa criada anteriormente como sendo a proprietária dos
		// registros a partir de agora
		TenantContext.setTenantOwner(e);

		// selecionando todas as empresas existentes.
		// não pode inserir cláusula where pois ela não estende
		// AbstractTenantModel
		System.out.println(TenantContext.getEntityManager().createQuery("select e from Empresa e")
				.getResultList());

		// criando um cliente
		Cliente c = new Cliente();
		c.setNome("cliente");
		// salvando o cliente no banco de dados
		TenantContext.getEntityManager().getTransaction().begin();
		TenantContext.getEntityManager().persist(c);
		TenantContext.getEntityManager().getTransaction().commit();

		// listando clientes(da empresa que foi setada anteriormente como sendo
		// a proprietária dos registros)
		System.out.println(TenantContext.getEntityManager().createQuery("select e from Cliente e")
				.getResultList());
	}

}
