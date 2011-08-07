package br.edu.unoesc.tcc.context.impl;

import br.edu.unoesc.tcc.TenantOwner;
import br.edu.unoesc.tcc.context.TenantHolderStrategy;

public class TenantHolder {

	public static final String nameATTR = "Tenant_key";
	private static TenantHolderStrategy strategy;

	static {
		strategy = new TenantHolderThreadLocalStrategy();
	}

	public static void setTenantOwner(TenantOwner to) {
		strategy.setTenantOwner(to);
	}

	public static TenantOwner getTenantOwner() {
		if (strategy != null) {
			return strategy.getTenantOwner();
		}
		return null;
	}

}
