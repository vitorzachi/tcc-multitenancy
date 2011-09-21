package br.edu.unoesc.tcc;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

/**
 * Classe abstrata contendo o campo com o ID do Tenant possuidor do registro. As
 * classes de entidade devem estender dessa classe para possibilitar a injeção
 * automática da cláusula "where" em todos os SQL.
 * 
 * @author vitor
 */

@MappedSuperclass
public abstract class AbstractTenantModel implements Serializable {

	private static final long serialVersionUID = 4116789534529581137L;
	@javax.persistence.Column(name = "tenantId")
	private Long tenantId;

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

}
