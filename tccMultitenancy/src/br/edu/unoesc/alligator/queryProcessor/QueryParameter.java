package br.edu.unoesc.alligator.queryProcessor;

/**
 * Interface que representa um parâmetro a ser adicionado na cláusula "where" de
 * um SQL.
 * 
 * @author vitor
 */
public interface QueryParameter {

	String getWhereRestriction();
}
