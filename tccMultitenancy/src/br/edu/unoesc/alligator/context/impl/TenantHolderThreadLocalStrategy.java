package br.edu.unoesc.alligator.context.impl;

import br.edu.unoesc.alligator.TenantOwner;
import br.edu.unoesc.alligator.context.TenantHolderStrategy;

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
