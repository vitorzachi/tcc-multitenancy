package testes.reflection;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.edu.unoesc.alligator.AbstractTenantModel;

public class DonoColecao extends AbstractTenantModel {

	@ManyToOne(cascade = { CascadeType.PERSIST })
	private FilhoColecao filhoRetornar;

	@OneToOne(cascade = { CascadeType.PERSIST })
	private FilhoColecao filho2Retornar;

	@OneToMany(cascade = { CascadeType.PERSIST })
	private Set<FilhoColecao> listaFilhoRetornar;

	@OneToMany()
	private Set<FilhoColecao> listaFilhoNaoRetornar;

	public FilhoColecao getFilhoRetornar() {
		return filhoRetornar;
	}

	public void setFilhoRetornar(FilhoColecao filhoRetornar) {
		this.filhoRetornar = filhoRetornar;
	}

	public FilhoColecao getFilho2Retornar() {
		return filho2Retornar;
	}

	public void setFilho2Retornar(FilhoColecao filho2Retornar) {
		this.filho2Retornar = filho2Retornar;
	}

	public Set<FilhoColecao> getListaFilhoRetornar() {
		return listaFilhoRetornar;
	}

	public void setListaFilhoRetornar(Set<FilhoColecao> listaFilhoRetornar) {
		this.listaFilhoRetornar = listaFilhoRetornar;
	}

	public Set<FilhoColecao> getListaFilhoNaoRetornar() {
		return listaFilhoNaoRetornar;
	}

	public void setListaFilhoNaoRetornar(Set<FilhoColecao> listaFilhoNaoRetornar) {
		this.listaFilhoNaoRetornar = listaFilhoNaoRetornar;
	}

}
