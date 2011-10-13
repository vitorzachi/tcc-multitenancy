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
package net.sf.trugger.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Interface that defines a factory for objects using in the {@link Reflection}
 * class.
 * 
 * @author Marcelo Varella Barca Guimarães
 */
public interface ReflectionFactory {
  
  /**
   * @return an implementation of the Reflector interface.
   */
  Reflector createReflector();
  
  /**
   * @param method
   *          the method to invoke.
   * @return an implementation of the MethodInvoker interface.
   */
  MethodInvoker createInvoker(Method method);
  
  /**
   * @param constructor
   *          the constructor to invoke.
   * @return an implementation of the ConstructorInvoker interface.
   */
  ConstructorInvoker createInvoker(Constructor<?> constructor);
  
  /**
   * @param field
   *          the field to handle.
   * @return an implementation of the FieldHandler interface.
   */
  FieldHandler createHandler(Field field);
}
