/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication30;

import br.edu.unoesc.tcc.AbstractTenantModel;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author vitor
 */
@Entity
public class Cliente extends AbstractTenantModel implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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
}
