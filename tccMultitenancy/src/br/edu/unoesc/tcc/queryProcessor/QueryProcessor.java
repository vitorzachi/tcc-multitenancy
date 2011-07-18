package br.edu.unoesc.tcc.queryProcessor;

import java.util.Map;
import java.util.Set;

import br.edu.unoesc.tcc.AbstractTenantModel;

/**
 * 
 * @author vitor
 */
public interface QueryProcessor {

	public static final String WHERE_CLAUSE = " where ";
	public static final String AND_CLAUSE = " and ";
	public static final String FROM_CLAUSE = " from ";
	public static final String AS_CLAUSE = " as ";
	public static final String NEW_CLAUSE = " new ";
	public static final String ORDER_BY_CLAUSE = " order by ";
	public static final String GROUP_BY_CLAUSE = " group by ";
	public static final String INNER_CLAUSE = " inner ";
	public static final String JOIN_CLAUSE = " join ";
	public static final String LEFT_CLAUSE = " left ";
	public static final String RIGHT_CLAUSE = " right ";
	public static final String EQUALS_CLAUSE = "=";
	public static final String PARAM_IDENTIFIER_SYMBOL = ":";
	public static final String PARAM_IDENTIFIER_NAME = "tenantId";
	public static final String TENANT_ID_FIELD = ".tenantId";

	Set<Class<? extends AbstractTenantModel>> getEntitiesInQuery(String sql);

	Set<QueryParameter> getParamsToAddInQuery(String sql);

	String processQuery(String sql);

	Map<Class<? extends AbstractTenantModel>, String> getMapModelAndAlias(
			String sql);
}
