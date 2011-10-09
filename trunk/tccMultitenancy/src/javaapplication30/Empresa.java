/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication30;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.edu.unoesc.alligator.TenantOwner;

/**
 * 
 * @author vitor
 */
@Entity
public class Empresa implements Serializable, TenantOwner {

	private static final long serialVersionUID = -6379080267460359610L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTenantId(Long l) {
		setId(id);
	}

	public Long getTenantId() {
		return getId();
	}

	@Override
	public String toString() {
		return this.getNome();
	}

}
