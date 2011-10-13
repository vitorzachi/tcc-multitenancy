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
package net.sf.trugger.selector;

/**
 * Interface that defines a selector for typed elements.
 * 
 * @author Marcelo Varella Barca Guimarães
 */
public interface TypedElementSelector {
  
  /**
   * Selects only the element of the given type.
   * 
   * @return a reference to this object.
   */
  TypedElementSelector ofType(Class<?> type);
  
  /**
   * Selects only the element assignable to the given type.

   * @return a reference to this object.
   * @since 2.1
   */
  TypedElementSelector assignableTo(Class<?> type);
  
}
