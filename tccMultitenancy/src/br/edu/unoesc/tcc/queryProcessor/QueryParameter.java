package br.edu.unoesc.tcc.queryProcessor;

/**
 * 
 * @author vitor
 */
public class QueryParameter {

	private String paramName;
	private String paramValue;

	public QueryParameter(String paramName, String paramValue) {
		this.paramName = paramName;
		this.paramValue = paramValue;
	}

	public QueryParameter() {
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
		final QueryParameter other = (QueryParameter) obj;
		if ((this.paramName == null) ? (other.paramName != null)
				: !this.paramName.equals(other.paramName)) {
			return false;
		}
		if (this.paramValue != other.paramValue
				&& (this.paramValue == null || !this.paramValue
						.equals(other.paramValue))) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 23 * hash
				+ (this.paramName != null ? this.paramName.hashCode() : 0);
		hash = 23 * hash
				+ (this.paramValue != null ? this.paramValue.hashCode() : 0);
		return hash;
	}
}
