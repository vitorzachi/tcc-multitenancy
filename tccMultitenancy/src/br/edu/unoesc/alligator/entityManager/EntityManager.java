package br.edu.unoesc.alligator.entityManager;

import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.Metamodel;

import br.edu.unoesc.alligator.AbstractTenantModel;
import br.edu.unoesc.alligator.context.TenantContext;
import br.edu.unoesc.alligator.context.impl.TenantHolder;
import br.edu.unoesc.alligator.exceptions.EntityManagerException;
import br.edu.unoesc.alligator.queryProcessor.QueryProcessor;
import br.edu.unoesc.alligator.queryProcessor.impl.DefaultQueryProcessorImpl;
import br.edu.unoesc.alligator.reflectionUtils.CascadeIntrospector;

/**
 * EntityManager sobrescrito e modificado para interceptar as query e injetar os
 * parâmetros necessários.
 * 
 * @author vitor
 */
public class EntityManager implements javax.persistence.EntityManager {

	@PersistenceContext
	private javax.persistence.EntityManager entityManager;

	public EntityManager(javax.persistence.EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager() {
	}

	public void persist(Object o) {
		resolvePersist(o);
	}

	public <T> T merge(T t) {
		return entityManager.merge(t);
	}

	public void remove(Object o) {
		entityManager.remove(o);
	}

	public <T> T find(Class<T> type, Object o) {
		return entityManager.find(type, o);
	}

	public <T> T getReference(Class<T> type, Object o) {
		return entityManager.getReference(type, o);
	}

	public void flush() {
		entityManager.flush();
	}

	public void setFlushMode(FlushModeType fmt) {
		entityManager.setFlushMode(fmt);
	}

	public FlushModeType getFlushMode() {
		return entityManager.getFlushMode();
	}

	public void lock(Object o, LockModeType lmt) {
		entityManager.lock(o, lmt);
	}

	public void refresh(Object o) {
		entityManager.refresh(o);
	}

	public void clear() {
		entityManager.clear();
	}

	public boolean contains(Object o) {
		return entityManager.contains(o);
	}

	public Query createQuery(String stringSQL) {
		QueryProcessor qryProc = new DefaultQueryProcessorImpl();

		javax.persistence.Query qry = entityManager.createQuery(qryProc.processQuery(stringSQL));
		if (qryProc.getMapModelAndAlias(stringSQL).size() > 0) {
			qry.setParameter(QueryProcessor.PARAM_IDENTIFIER_NAME, TenantHolder.getTenantOwner().getTenantId());
		}
		return qry;
	}

	public Query createNamedQuery(String stringSQL) {
		String sql = TenantContext.getNamedQuery(stringSQL);
		if (sql != null) {
			return this.createQuery(sql);
		} else {
			return entityManager.createNamedQuery(stringSQL);
		}
	}

	public Query createNativeQuery(String string) {
		return entityManager.createNativeQuery(string);
	}

	public Query createNativeQuery(String string, Class type) {
		return entityManager.createNativeQuery(string, type);
	}

	public Query createNativeQuery(String string, String string1) {
		return entityManager.createNativeQuery(string, string1);
	}

	public void joinTransaction() {
		entityManager.joinTransaction();
	}

	public Object getDelegate() {
		return entityManager.getDelegate();
	}

	public void close() {
		entityManager.close();
	}

	public boolean isOpen() {
		return entityManager.isOpen();
	}

	public EntityTransaction getTransaction() {
		return entityManager.getTransaction();
	}

	/**
	 * decide the strategy to persist an Object
	 * 
	 * @param o
	 * @throws EntityManagerException
	 */
	private void resolvePersist(Object o) throws EntityManagerException {
		if (o instanceof AbstractTenantModel) {
			persistTenantModel((AbstractTenantModel) o);
		} else {
			entityManager.persist(o);
		}
	}

	/**
	 * persist an AbstractTenantModel
	 * 
	 * @param atm
	 * @throws EntityManagerException
	 */
	private void persistTenantModel(AbstractTenantModel atm) throws EntityManagerException {
		if (TenantHolder.getTenantOwner() == null) {
			throw new EntityManagerException("Could not find TenantOwner for this operation. TenantOwner is null.");
		}
		if (TenantHolder.getTenantOwner().getTenantId() == null) {
			throw new EntityManagerException("\"Id\" of TenantOwner is required for persist a TenantModel. \"Id\" of TenantOwner is null.");
		}
		Long codigo = TenantHolder.getTenantOwner().getTenantId();
		atm.setTenantId(codigo);
		introspect(atm, codigo);
		entityManager.persist(atm);
	}

	private void introspect(AbstractTenantModel atm, Long codigo) {
		try {
			new CascadeIntrospector(atm, codigo).introspect();
		} catch (Exception e) {
			throw new EntityManagerException(e);
		}
	}

	@Override
	public <T> TypedQuery<T> createNamedQuery(String sql, Class<T> clazz) {
		return entityManager.createNamedQuery(sql, clazz);
	}

	@Override
	public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteria) {
		return entityManager.createQuery(criteria);
	}

	@Override
	public <T> TypedQuery<T> createQuery(String sql, Class<T> clazz) {
		QueryProcessor qryProc = new DefaultQueryProcessorImpl();

		javax.persistence.TypedQuery<T> qry = entityManager.createQuery(qryProc.processQuery(sql), clazz);
		if (qryProc.getMapModelAndAlias(sql).size() > 0) {
			qry.setParameter(QueryProcessor.PARAM_IDENTIFIER_NAME, TenantHolder.getTenantOwner().getTenantId());
		}
		return qry;
	}

	@Override
	public void detach(Object arg0) {
		entityManager.detach(arg0);
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1, Map<String, Object> arg2) {
		return entityManager.find(arg0, arg1, arg2);
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1, LockModeType arg2) {
		return entityManager.find(arg0, arg1, arg2);
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1, LockModeType arg2, Map<String, Object> arg3) {
		return entityManager.find(arg0, arg1, arg2, arg3);
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		return entityManager.getCriteriaBuilder();
	}

	@Override
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManager.getEntityManagerFactory();
	}

	@Override
	public LockModeType getLockMode(Object arg0) {
		return entityManager.getLockMode(arg0);
	}

	@Override
	public Metamodel getMetamodel() {
		return entityManager.getMetamodel();
	}

	@Override
	public Map<String, Object> getProperties() {
		return entityManager.getProperties();
	}

	@Override
	public void lock(Object arg0, LockModeType arg1, Map<String, Object> arg2) {
		entityManager.lock(arg0, arg1, arg2);
	}

	@Override
	public void refresh(Object arg0, Map<String, Object> arg1) {
		entityManager.refresh(arg0, arg1);
	}

	@Override
	public void refresh(Object arg0, LockModeType arg1) {
		entityManager.refresh(arg0, arg1);
	}

	@Override
	public void refresh(Object arg0, LockModeType arg1, Map<String, Object> arg2) {
		entityManager.refresh(arg0, arg1, arg2);
	}

	@Override
	public void setProperty(String arg0, Object arg1) {
		entityManager.setProperty(arg0, arg1);
	}

	@Override
	public <T> T unwrap(Class<T> arg0) {
		return entityManager.unwrap(arg0);
	}
}
