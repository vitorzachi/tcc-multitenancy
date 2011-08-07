package br.edu.unoesc.tcc.context;

import br.edu.unoesc.tcc.TenantOwner;

public interface TenantHolderStrategy {

	TenantOwner getTenantOwner();

	void setTenantOwner(TenantOwner tenantOwner);
}
