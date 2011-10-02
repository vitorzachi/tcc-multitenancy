package javaapplication30;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Cidade cidade;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Pedido> pedidos;

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

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public String toString() {
		return this.getNome() + " " + super.getTenantId();
	}
}
