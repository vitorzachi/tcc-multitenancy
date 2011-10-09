package br.edu.unoesc.alligator.queryProcessor;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author vitor
 */
public class DefaultQueryParameterImpl implements QueryParameter {

	private Logger log = Logger.getLogger(DefaultQueryParameterImpl.class.getName());
	private String paramName;
	private String paramValue = QueryProcessor.PARAM_IDENTIFIER_NAME;

	public DefaultQueryParameterImpl(String paramName, String paramValue) {
		this.paramName = paramName;
		this.paramValue = paramValue;
	}

	public DefaultQueryParameterImpl() {
	}

	public String getParamName() {
		return paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final DefaultQueryParameterImpl other = (DefaultQueryParameterImpl) obj;
		if ((this.paramName == null) ? (other.paramName != null) : !this.paramName
				.equals(other.paramName)) {
			return false;
		}
		if (this.paramValue != other.paramValue
				&& (this.paramValue == null || !this.paramValue.equals(other.paramValue))) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 23 * hash + (this.paramName != null ? this.paramName.hashCode() : 0);
		hash = 23 * hash + (this.paramValue != null ? this.paramValue.hashCode() : 0);
		return hash;
	}

	@Override
	public String getWhereRestriction() {
		if (paramName == null || paramValue == null) {
			log.log(Level.WARNING,
					"paramName ou paramValue não pode ser null...retornando parâmetro vazio");
			return "";
		}
		return getParamName() + QueryProcessor.EQUALS_CLAUSE
				+ QueryProcessor.PARAM_IDENTIFIER_SYMBOL + getParamValue();
	}
}
