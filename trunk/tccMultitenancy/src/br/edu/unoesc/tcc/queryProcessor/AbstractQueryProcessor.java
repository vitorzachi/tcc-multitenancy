package br.edu.unoesc.tcc.queryProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.edu.unoesc.tcc.AbstractTenantModel;
import br.edu.unoesc.tcc.context.TenantContext;

public abstract class AbstractQueryProcessor implements QueryProcessor {

	public Set<Class<? extends AbstractTenantModel>> getEntitiesInQuery(String sql) {
		return getMapModelAndAlias(sql).keySet();
	}

	public Set<QueryParameter> getParamsToAddInQuery(String sql) {
		Set<QueryParameter> qryParams = new HashSet<QueryParameter>();
		for (String aliasName : getMapModelAndAlias(sql).values()) {
			qryParams.add(new DefaultQueryParameterImpl(aliasName + QueryProcessor.TENANT_ID_FIELD,
					QueryProcessor.PARAM_IDENTIFIER_NAME));
		}
		return qryParams;
	}

	public String processQuery(String sql) {
		if (getMapModelAndAlias(sql).size() > 0) {
			StringBuilder sb = new StringBuilder(sql);
			List<QueryParameter> qryParams = new ArrayList<QueryParameter>(
					getParamsToAddInQuery(sql));

			// select e from Entidade e where e.field=:param
			// add AND at end of query
			if (hasWhereClause(sql) && !(hasOrderByClause(sql) || hasGroupByClause(sql))) {
				sb.append(AND_CLAUSE);
				for (int i = 0; i < qryParams.size(); i++) {
					sb.append(qryParams.get(i).getWhereRestriction());
					if (!(i == qryParams.size() - 1)) {
						sb.append(AND_CLAUSE);
					}
				}
			}
			// select e from Entidade e
			// add WHERE at end of query
			if (!hasWhereClause(sql) && !(hasOrderByClause(sql) || hasGroupByClause(sql))) {
				sb.append(WHERE_CLAUSE);
				for (int i = 0; i < qryParams.size(); i++) {
					sb.append(qryParams.get(i).getWhereRestriction());
					if (!(i == qryParams.size() - 1)) {
						sb.append(AND_CLAUSE);
					}
				}
			}
			// select e from Entidade e order by e.field
			// add WHERE before ORDER BY
			if (!hasWhereClause(sql) && (hasOrderByClause(sql) || hasGroupByClause(sql))) {
				int separator = 0;
				if (hasOrderByClause(sql)) {
					separator = positionOfOrderByClause(sql);
				}
				if (hasGroupByClause(sql)) {
					separator = positionOfGroupByClause(sql);
				}

				String ini = sql.substring(0, separator);
				String fim = sql.substring(separator);

				sb = new StringBuilder(ini);

				sb.append(WHERE_CLAUSE);
				for (int i = 0; i < qryParams.size(); i++) {
					sb.append(qryParams.get(i).getWhereRestriction());
					if (!(i == qryParams.size() - 1)) {
						sb.append(AND_CLAUSE);
					}
				}
				sb.append(fim);
			}

			// select e from Entidade e where e.field=:param order by e.field
			// add AND before ORDER BY
			if (hasWhereClause(sql) && (hasOrderByClause(sql) || hasGroupByClause(sql))) {
				int separator = 0;
				if (hasOrderByClause(sql)) {
					separator = positionOfOrderByClause(sql);
				}
				if (hasGroupByClause(sql)) {
					separator = positionOfGroupByClause(sql);
				}
				String ini = sql.substring(0, separator);
				String fim = sql.substring(separator);

				sb = new StringBuilder(ini);

				sb.append(AND_CLAUSE);
				for (int i = 0; i < qryParams.size(); i++) {
					sb.append(qryParams.get(i).getWhereRestriction());
					if (!(i == qryParams.size() - 1)) {
						sb.append(AND_CLAUSE);
					}
				}
				sb.append(fim);
			}
			return sb.toString();
		} else {
			return sql;
		}
	}

	public Map<Class<? extends AbstractTenantModel>, String> getMapModelAndAlias(String sql) {
		Map<Class<? extends AbstractTenantModel>, String> map = new HashMap<Class<? extends AbstractTenantModel>, String>();
		List<String> queryWords = new ArrayList<String>();
		sql = sql.substring(sql.indexOf(QueryProcessor.FROM_CLAUSE)
				+ (QueryProcessor.FROM_CLAUSE.trim().length() + 1));
		String[] strSplit = sql.split("[ ,]");
		for (String s : strSplit) {
			if (!QueryUtils.isReservedWord(s) && !s.equals(" ") && !s.equals("")) {
				queryWords.add(s);
			}
		}
		for (int i = 0; i < queryWords.size(); i++) {
			if (TenantContext.isEntityInContext(queryWords.get(i))) {
				map.put(TenantContext.getEntityClass(queryWords.get(i)), queryWords.get(i + 1));
			}
		}
		return map;
	}

	protected boolean hasWhereClause(String sql) {
		return sql.toLowerCase().contains(WHERE_CLAUSE) || sql.toUpperCase().contains(WHERE_CLAUSE);
	}

	protected boolean hasOrderByClause(String sql) {
		return sql.toLowerCase().contains(ORDER_BY_CLAUSE)
				|| sql.toUpperCase().contains(ORDER_BY_CLAUSE);
	}

	protected boolean hasGroupByClause(String sql) {
		return sql.toLowerCase().contains(GROUP_BY_CLAUSE)
				|| sql.toUpperCase().contains(GROUP_BY_CLAUSE);
	}

	protected int positionOfOrderByClause(String sql) {
		return sql.toLowerCase().indexOf(ORDER_BY_CLAUSE);
	}

	protected int positionOfGroupByClause(String sql) {
		return sql.toLowerCase().indexOf(GROUP_BY_CLAUSE);
	}
}
