package javaapplication30;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.edu.unoesc.tcc.AbstractTenantModel;

@Entity
public class Pedido extends AbstractTenantModel {
	private static final long serialVersionUID = 1825579189677489604L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;

	private String nome;

	public Pedido() {
		this.nome = String.valueOf(System.currentTimeMillis());
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return this.getNome() + " " + super.getTenantId();

	}

}
