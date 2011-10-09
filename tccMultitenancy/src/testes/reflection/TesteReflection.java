package testes.reflection;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

import br.edu.unoesc.alligator.reflectionUtils.CascadeIntrospector;
import br.edu.unoesc.alligator.reflectionUtils.ReflectionUtils;

public class TesteReflection {

	public static void main(String args[]) {
		DonoColecao dono = new DonoColecao();
		FilhoColecao filho = new FilhoColecao();
		FilhoColecao filho2 = new FilhoColecao();
		dono.setListaFilhoRetornar(new HashSet<FilhoColecao>());
		dono.getListaFilhoRetornar().add(filho);
		dono.getListaFilhoRetornar().add(filho2);

		CascadeIntrospector ci = new CascadeIntrospector(dono, 1l);

		try {
			ci.introspect();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		for (FilhoColecao f : dono.getListaFilhoRetornar()) {
			System.out.println(f.getTenantId());
		}
		// testeCampos();
	}

	public static void testeCampos() {
		DonoColecao dono = new DonoColecao();
		int size = ReflectionUtils.getFieldsToIntrospect(dono).size();
		System.out.println("Deve retornar 3: ==> " + size);
	}
}
