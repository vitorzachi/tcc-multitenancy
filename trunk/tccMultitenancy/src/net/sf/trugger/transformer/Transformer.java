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
package net.sf.trugger.transformer;

/**
 * Interface that defines a component for transforming an Object into another
 * one.
 * 
 * @author Marcelo Varella Barca Guimarães
 * @param <From>
 *          The source object type
 * @param <To>
 *          The transformed object type
 */
public interface Transformer<To, From> {
  
  /**
   * Transforms the source object.
   * 
   * @param object
   *          the source object.
   * @return the transformed object.
   */
  To transform(From object);
  
}
