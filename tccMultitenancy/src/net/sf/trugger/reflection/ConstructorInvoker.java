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

import net.sf.trugger.Invoker;

/**
 * Interface that defines a class capable of invoking a {@link Constructor}.
 * <p>
 * Invoking a constructor is quite simple, below is an example:
 * 
 * <pre>
 * MyType instance = {@link Reflection#invoke(Constructor)}.withArgs(argument1, argument2);
 * </pre>
 * 
 * @author Marcelo Varella Barca Guimarães
 */
public interface ConstructorInvoker extends Invoker {

}
