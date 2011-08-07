package br.edu.unoesc.tcc.context;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;

import net.sf.trugger.scan.ClassScan;
import br.edu.unoesc.tcc.AbstractTenantModel;
import br.edu.unoesc.tcc.TenantOwner;
import br.edu.unoesc.tcc.entityManager.EntityManager;
import br.edu.unoesc.tcc.exceptions.TenantContextException;

/**
 * 
 * @author vitor
 */
public abstract class TenantContext {

	private static EntityManager entityManager;
	private static TenantOwner tenantOwner;
	private static Set<Class> entities = new HashSet<Class>();
	private static Map<String, NamedQuery> mapNamedQueries = new HashMap<String, NamedQuery>();
	private static boolean isSetUp = false;

	/**
	 * set up the TenantContext with the persistenceUnitName and the package of
	 * the entities of the application.
	 * 
	 * @param persistenceUnitName
	 */
	public static void setUp(final String persistenceUnitName, final String... modelPackageNames) {
		entityManager = createEntityManager(persistenceUnitName);
		for (String modelPackageName : modelPackageNames) {
			entities.addAll(ClassScan.findAll().annotatedWith(Entity.class)
					.assignableTo(AbstractTenantModel.class).recursively().in(modelPackageName));
		}
		isSetUp = true;
		scanNamedQueries();
	}

	/**
	 * set up the TenantContext with the javax.persistence.EntityManager
	 * instance and the package of the entities of the application.
	 * 
	 * @param persistenceUnitName
	 */
	public static void setUp(javax.persistence.EntityManager javaxEntityManager,
			final String... modelPackageNames) {
		entityManager = new br.edu.unoesc.tcc.entityManager.EntityManager(javaxEntityManager);
		for (String modelPackageName : modelPackageNames) {
			entities.addAll(ClassScan.findAll().annotatedWith(Entity.class)
					.assignableTo(AbstractTenantModel.class).recursively().in(modelPackageName));
		}
		isSetUp = true;
		scanNamedQueries();
	}

	/**
	 * set up the TenantContext with the package of the entities of the
	 * application. EntityManager will be injected by @PersistenceContext
	 * 
	 * @param modelPackageNames
	 */
	public static void setUp(final String[] modelPackageNames) {
		for (String modelPackageName : modelPackageNames) {
			entities.addAll(ClassScan.findAll().annotatedWith(Entity.class)
					.assignableTo(AbstractTenantModel.class).recursively().in(modelPackageName));
		}
		isSetUp = true;
		scanNamedQueries();
	}

	/**
	 * create a entityManager based in persistenceUnitName.
	 * 
	 * @param persistenceUnitName
	 * @return a new br.edu.unoesc.tcc.EntityManager instance
	 */
	private static br.edu.unoesc.tcc.entityManager.EntityManager createEntityManager(
			final String persistenceUnitName) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		javax.persistence.EntityManager em = emf.createEntityManager();
		return new br.edu.unoesc.tcc.entityManager.EntityManager(em);
	}

	/**
	 * @return a br.edu.unoesc.tcc.EntityManager for the JPA operations
	 */
	public static EntityManager getEntityManager() {
		verifyIsSetUp();
		return entityManager;
	}

	/**
	 * 
	 * @return a TenantOwner of current data
	 */
	public static TenantOwner getTenantOwner() {
		verifyIsSetUp();
		return tenantOwner;
	}

	public static void setTenantOwner(TenantOwner tenantOwner) {
		verifyIsSetUp();
		TenantContext.tenantOwner = tenantOwner;
	}

	public static Set<Class> getEntities() {
		verifyIsSetUp();
		return Collections.unmodifiableSet(entities);
	}

	public static boolean isEntityInContext(Class clazz) {
		verifyIsSetUp();
		if (entities != null) {
			return entities.contains(clazz);
		} else {
			return false;
		}
	}

	public static boolean isEntityInContext(String className) {
		verifyIsSetUp();
		if (entities != null) {
			for (Class c : entities) {
				String s = c.getName();
				if (!className.contains(".") && s.contains(".")) {
					s = s.substring(s.lastIndexOf(".") + 1);
				}
				if (s.equals(className)) {
					return true;
				}
			}
		}
		return false;
	}

	public static Class<AbstractTenantModel> getEntityClass(String className) {
		verifyIsSetUp();
		if (entities != null) {
			for (Class c : entities) {
				String s = c.getName();
				if (!className.contains(".") && s.contains(".")) {
					s = s.substring(s.lastIndexOf(".") + 1);
				}
				if (s.equalsIgnoreCase(className)) {
					return c;
				}
			}
		}
		return null;
	}

	public static boolean isSetUp() {
		return isSetUp;
	}

	private static void verifyIsSetUp() {
		if (!isSetUp) {
			throw new TenantContextException(
					"TenantContext is not set up. Please set up the TenantContext.");
		}
	}

	private static void addNamedQuery(String nome, NamedQuery namedQry) {
		mapNamedQueries.put(nome, namedQry);
	}

	private static void bindNamedQueries(Class entityClass) {
		if (entityClass.isAnnotationPresent(NamedQuery.class)) {
			NamedQuery nq = (NamedQuery) entityClass.getAnnotation(NamedQuery.class);
			addNamedQuery(nq.name(), nq);
		}

		if (entityClass.isAnnotationPresent(NamedQueries.class)) {
			NamedQueries nqs = (NamedQueries) entityClass.getAnnotation(NamedQueries.class);
			for (NamedQuery nm : nqs.value()) {
				addNamedQuery(nm.name(), nm);
			}
		}
	}

	private static void scanNamedQueries() {
		if (isSetUp()) {
			for (Class cl : entities) {
				bindNamedQueries(cl);
			}
		}
	}

	public static String getNamedQuery(String nome) {
		return ((NamedQuery) mapNamedQueries.get(nome)).query();
	}
}
