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
package net.sf.trugger;

/**
 * Interface that defines a class capable of invoking a object with the
 * possibility of passing arguments.
 * 
 * @author Marcelo Varella Barca Guimarães
 */
public interface Invoker {
  
  /**
   * Invokes the object passing the given arguments.
   * 
   * @param args
   *          the arguments to pass.
   * @return the invocation result.
   */
  <E> E withArgs(Object... args);
  
  /**
   * Invokes the object without passing any argument.
   * 
   * @return the invocation result.
   */
  <E> E withoutArgs();
  
}
