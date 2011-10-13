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
package net.sf.trugger.element.impl;

import net.sf.trugger.HandlingException;
import net.sf.trugger.ValueHandler;
import net.sf.trugger.element.Element;

import java.util.Properties;

/**
 * @author Marcelo Varella Barca Guimarães
 */
public class PropertiesElement extends AbstractElement implements Element {

  public PropertiesElement(String name) {
    super(name);
  }

  @Override
  public ValueHandler in(Object target) {
    if (target instanceof Properties) {
      final Properties props = (Properties) target;
      return new ValueHandler() {

        @Override
        public void value(Object value) throws HandlingException {
          if (value != null) {
            try {
              props.setProperty(name, (String) value);
            } catch (ClassCastException e) {
              throw new HandlingException(e);
            }
          }
        }

        @Override
        public <E> E value() throws HandlingException {
          return (E) props.getProperty(name);
        }
      };
    }
    throw new IllegalArgumentException("Target is not a " + Properties.class);
  }

  @Override
  public Class<?> declaringClass() {
    return Properties.class;
  }

  @Override
  public Class<?> type() {
    return String.class;
  }

  @Override
  public boolean isReadable() {
    return true;
  }

  @Override
  public boolean isWritable() {
    return true;
  }

}
