/*
 * Copyright 2009-2010 Marcelo Varella Barca Guimarães
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sf.trugger.reflection.impl;

import static net.sf.trugger.reflection.ReflectionPredicates.STATIC;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.trugger.Result;
import net.sf.trugger.reflection.ReflectionException;
import net.sf.trugger.reflection.Reflector;
import net.sf.trugger.selector.ConstructorSelector;
import net.sf.trugger.selector.ConstructorsSelector;
import net.sf.trugger.selector.FieldGetterMethodSelector;
import net.sf.trugger.selector.FieldSelector;
import net.sf.trugger.selector.FieldSetterMethodSelector;
import net.sf.trugger.selector.FieldsSelector;
import net.sf.trugger.selector.GetterMethodSelector;
import net.sf.trugger.selector.MethodSelector;
import net.sf.trugger.selector.MethodsSelector;
import net.sf.trugger.selector.SetterMethodSelector;
import net.sf.trugger.util.Utils;

/**
 * An implementation of the reflection operations.
 *
 * @author Marcelo Varella Barca Guimarães
 */
public class TruggerReflector implements Reflector {

  public GetterMethodSelector getterFor(String name) {
    return new TruggerGetterMethodSelector(name);
  }

  public FieldGetterMethodSelector getterFor(Field field) {
    return new TruggerFieldGetterMethodSelector(field);
  }

  public SetterMethodSelector setterFor(String name) {
    return new TruggerSetterMethodSelector(name);
  }

  public FieldSetterMethodSelector setterFor(Field field) {
    return new TruggerFieldSetterMethodSelector(field);
  }

  public ConstructorSelector constructor() {
    return new TruggerConstructorSelector();
  }

  public ConstructorsSelector constructors() {
    return new TruggerConstructorsSelector();
  }

  public FieldSelector field(String name) {
    return new TruggerFieldSelector(name);
  }

  public FieldSelector field() {
    return new TruggerNoNamedFieldSelector();
  }

  public FieldsSelector fields() {
    return new TruggerFieldsSelector();
  }

  public MethodSelector method(String name) {
    return new TruggerMethodSelector(name);
  }

  public MethodSelector method() {
    return new TruggerNoNamedMethodSelector();
  }

  public MethodsSelector methods() {
    return new TruggerMethodsSelector();
  }

  public Result<Set<Class<?>>, Object> interfaces() {
    return new Result<Set<Class<?>>, Object>() {

      private void loop(Class<?> interf, Set<Class<?>> set) {
        set.add(interf);
        for (Class<?> extendedInterfaces : interf.getInterfaces()) {
          loop(extendedInterfaces, set);
        }
      }

      public Set<Class<?>> in(Object target) {
        Class<?> objectClass = Utils.resolveType(target);
        Set<Class<?>> set = new HashSet<Class<?>>(30);
        for (Class<?> c = objectClass ; (c != null) && !Object.class.equals(c) ; c = c.getSuperclass()) {
          for (Class<?> interf : c.getInterfaces()) {
            loop(interf, set);
          }
        }
        return set;
      }
    };
  }

  public Result<Class, Object> genericType(final String parameterName) {
    return new Result<Class, Object>() {

      public Class in(Object target) {
        return TruggerGenericTypeResolver.resolveParameterName(parameterName, Utils.resolveType(target));
      }
    };
  }

  public Result<Class, Object> genericType() {
    return new Result<Class, Object>() {

      public Class in(Object target) {
        Map<Type, Type> typeVariableMap = TruggerGenericTypeResolver.getTypeVariableMap(Utils.resolveType(target));
        Set<Type> keySet = typeVariableMap.keySet();
        Set<String> paramNames = new HashSet<String>(keySet.size());
        for (Type type : keySet) {
          paramNames.add(type.toString());
        }
        if(paramNames.isEmpty()) {
          throw new ReflectionException("No generic type found.");
        } else if(paramNames.size() > 1) {
          throw new ReflectionException("More than one generic type found.");
        }
        String name = paramNames.iterator().next();
        return genericType(name).in(target);
      }
    };
  }

  public Method bridgedMethodFor(Method bridgeMethod) {
    return new TruggerBridgeMethodResolver(bridgeMethod).findBridgedMethod();
  }

  @Override
  public FieldSelector staticField() {
    return field().thatMatches(STATIC);
  }

  @Override
  public FieldSelector staticField(String name) {
    return field(name).thatMatches(STATIC);
  }

  @Override
  public FieldsSelector staticFields() {
    return fields().thatMatches(STATIC);
  }

  @Override
  public MethodSelector staticMethod() {
    return method().thatMatches(STATIC);
  }

  @Override
  public MethodSelector staticMethod(String name) {
    return method(name).thatMatches(STATIC);
  }

  @Override
  public MethodsSelector staticMethods() {
    return methods().thatMatches(STATIC);
  }

}
