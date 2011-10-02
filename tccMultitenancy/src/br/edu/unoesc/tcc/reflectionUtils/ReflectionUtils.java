package br.edu.unoesc.tcc.reflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.edu.unoesc.tcc.AbstractTenantModel;

public class ReflectionUtils {

	public static List<Field> getFieldsToIntrospect(Object object) {
		Field[] fields = object.getClass().getDeclaredFields();
		List<Field> lista = new ArrayList<Field>();

		for (Field field : fields) {
			if (isNeedIntrospectField(field)) {
				lista.add(field);
			}
		}
		return lista;
	}

	private static boolean isNeedIntrospectField(Field field) {
		boolean isAssignable = AbstractTenantModel.class.isAssignableFrom(field.getType());
		boolean isPresentRelationWithCascadePersist = false;

		if (field.isAnnotationPresent(ManyToOne.class)) {
			isPresentRelationWithCascadePersist = containsCascadePersist(field.getAnnotation(ManyToOne.class).cascade());
		}
		if (field.isAnnotationPresent(OneToOne.class)) {
			isPresentRelationWithCascadePersist = containsCascadePersist(field.getAnnotation(OneToOne.class).cascade());
		}
		if (field.isAnnotationPresent(OneToMany.class)) {
			isAssignable = resolveOneToManyAssignable(field);
			isPresentRelationWithCascadePersist = containsCascadePersist(field.getAnnotation(OneToMany.class).cascade());
		}
		return isAssignable && isPresentRelationWithCascadePersist;
	}

	private static boolean resolveOneToManyAssignable(Field field) {
		Type type = field.getGenericType();
		if (type != null) {
			try {
				ParameterizedType pType = (ParameterizedType) type;
				if (pType.getActualTypeArguments() != null && pType.getActualTypeArguments().length != 0) {
					Class parametrizedClass = (Class) pType.getActualTypeArguments()[0];
					return AbstractTenantModel.class.isAssignableFrom(parametrizedClass);
				}
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	private static boolean containsCascadePersist(CascadeType[] cascadeTypes) {
		if (cascadeTypes != null) {
			for (CascadeType cascade : cascadeTypes) {
				if (cascade.equals(CascadeType.PERSIST) || cascade.equals(CascadeType.ALL)) {
					return true;
				}
			}
		}
		return false;
	}

}
