package br.edu.unoesc.tcc.context.impl;

import br.edu.unoesc.tcc.TenantOwner;
import br.edu.unoesc.tcc.context.TenantHolderStrategy;

public class TenantHolderThreadLocalStrategy implements TenantHolderStrategy {

	private static ThreadLocal<TenantOwner> ctx = new ThreadLocal<TenantOwner>();

	@Override
	public TenantOwner getTenantOwner() {
		return ctx.get();
	}

	@Override
	public void setTenantOwner(TenantOwner tenantOwner) {
		ctx.set(tenantOwner);
	}

}
