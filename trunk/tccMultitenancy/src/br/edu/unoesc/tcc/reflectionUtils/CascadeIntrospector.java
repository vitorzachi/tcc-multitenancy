package br.edu.unoesc.tcc.reflectionUtils;

import static java.util.Locale.ENGLISH;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

import br.edu.unoesc.tcc.AbstractTenantModel;

public class CascadeIntrospector {

	private final Class[] NO_PARAMETERS = new Class[0];
	private final Object[] NO_ARGS = new Object[0];
	private Object source;
	private Long valueToSet;

	public CascadeIntrospector(AbstractTenantModel source, Long tenantIdToSet) {
		if (source == null) {
			throw new IllegalArgumentException("Source could not be null");
		}
		if (tenantIdToSet == null) {
			throw new IllegalArgumentException("Value to set could not be null");
		}
		this.source = source;
		this.valueToSet = tenantIdToSet;
	}

	public void introspect() throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		if (source != null) {
			for (Field field : ReflectionUtils.getFieldsToIntrospect(source)) {
				if (AbstractTenantModel.class.isAssignableFrom(field.getType())) {
					introspectField(field);
				} else if (Collection.class.isAssignableFrom(field.getType())) {
					introspectCollection(field);
				}
			}
		}
	}

	private void introspectField(Field field) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		AbstractTenantModel atm = (AbstractTenantModel) invokeDynamic(getReadMethod(field));
		if (atm != null) {
			atm.setTenantId(valueToSet);
		}
	}

	private void introspectCollection(Field field) throws IllegalArgumentException, SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		Collection<AbstractTenantModel> collection = (Collection<AbstractTenantModel>) invokeDynamic(getReadMethod(field));
		if (collection != null) {
			for (AbstractTenantModel atm : collection) {
				atm.setTenantId(valueToSet);
			}
		}
	}

	private Method getReadMethod(Field field) throws SecurityException, NoSuchMethodException {
		return source.getClass().getMethod(getReadMethodNameByField(field), NO_PARAMETERS);
	}

	private String getReadMethodNameByField(Field field) {
		String name = field.getName();
		return "get" + name.substring(0, 1).toUpperCase(ENGLISH) + name.substring(1);
	}

	private Object invokeDynamic(Method method) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (!method.isAccessible()) {
			method.setAccessible(true);
		}
		return method.invoke(source, NO_ARGS);
	}
}
