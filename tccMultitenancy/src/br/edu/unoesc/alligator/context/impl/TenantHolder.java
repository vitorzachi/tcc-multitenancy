package br.edu.unoesc.alligator.context.impl;

import br.edu.unoesc.alligator.TenantOwner;
import br.edu.unoesc.alligator.context.TenantHolderStrategy;

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
