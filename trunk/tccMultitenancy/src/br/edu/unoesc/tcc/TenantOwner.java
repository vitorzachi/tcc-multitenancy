package br.edu.unoesc.tcc;

import java.io.Serializable;

/**
 *
 * @author vitor
 */
public interface TenantOwner extends Serializable{

    void setTenantId(Long tenantId);

    Long getTenantId();
}
