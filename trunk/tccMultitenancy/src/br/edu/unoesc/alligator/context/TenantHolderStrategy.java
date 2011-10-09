package br.edu.unoesc.alligator.context;

import br.edu.unoesc.alligator.TenantOwner;

public interface TenantHolderStrategy {

	TenantOwner getTenantOwner();

	void setTenantOwner(TenantOwner tenantOwner);
}
