package javaapplication30;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.edu.unoesc.tcc.AbstractTenantModel;

/**
 * 
 * @author vitor
 */
@Entity
public class Cliente extends AbstractTenantModel implements Serializable {

	private static final long serialVersionUID = 4512935312027448695L;
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

	@Override
	public String toString() {
		return this.getNome();
	}
}
