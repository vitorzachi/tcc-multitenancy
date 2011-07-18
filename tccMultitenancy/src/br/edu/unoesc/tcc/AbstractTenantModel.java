package br.edu.unoesc.tcc;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

/**
 * 
 * @author vitor
 */

@MappedSuperclass
public abstract class AbstractTenantModel implements Serializable {

	private static final long serialVersionUID = 4116789534529581137L;
	@javax.persistence.Column(name = "tenantId", nullable = false)
	private Long tenantId;

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

}
