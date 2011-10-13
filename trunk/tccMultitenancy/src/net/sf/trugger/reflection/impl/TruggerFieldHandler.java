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

import java.lang.reflect.Field;

import net.sf.trugger.HandlingException;
import net.sf.trugger.ValueHandler;
import net.sf.trugger.reflection.FieldHandler;
import net.sf.trugger.reflection.Reflection;

/**
 * Implementation for the FieldHandler interface.
 * 
 * @author Marcelo Varella Barca Guimarães
 */
public class TruggerFieldHandler implements FieldHandler {
  
  private final Field field;
  
  private final Object instance;
  
  /**
   * Constructs a new handler for a static field.
   * 
   * @param field
   *          the field to be handled
   */
  public TruggerFieldHandler(Field field) {
    if (!field.isAccessible()) {
      Reflection.setAccessible(field);
    }
    this.field = field;
    this.instance = null; //for static access
  }
  
  /**
   * Constructs a new handler for a non-static field.
   * 
   * @param field
   *          the field to be handled
   * @param instance
   *          an instance that declares this field
   */
  private TruggerFieldHandler(Field field, Object instance) {
    super();
    this.field = field;
    this.instance = instance;
  }
  
  public <E> E value() throws HandlingException {
    try {
      return (E) field.get(instance);
    } catch (IllegalAccessException e) {
      throw new HandlingException(e);
    }
  }
  
  public void value(Object value) throws HandlingException {
    try {
      field.set(instance, value);
    } catch (IllegalAccessException e) {
      throw new HandlingException(e);
    }
  }
  
  public ValueHandler in(Object source) {
    return new TruggerFieldHandler(field, source);
  }
  
}
