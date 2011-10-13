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
package net.sf.trugger.property.impl;

import java.util.Set;

import net.sf.trugger.Finder;
import net.sf.trugger.Result;
import net.sf.trugger.element.Element;
import net.sf.trugger.element.impl.AnnotationElementFinder;

/**
 * The default implementation for the PropertyFinder. It uses the
 * {@link AnnotationElementFinder} and the {@link ObjectPropertyFinder} and
 * supports
 * <p>
 * This implementation also recognizes nested properties.
 * 
 * @author Marcelo Varella Barca Guimarães
 */
public class TruggerPropertyFinder implements Finder<Element> {
  
  private Finder<Element> finder;
  
  /**
   * Creates a new PropertyFinder
   */
  public TruggerPropertyFinder() {
    finder = new ObjectPropertyFinder();
  }
  
  public Result<Set<Element>, Object> findAll() {
    return new Result<Set<Element>, Object>() {
      
      public Set<Element> in(Object target) {
        return finder.findAll().in(target);
      }
    };
  }
  
  public Result<Element, Object> find(final String name) {
    return new Result<Element, Object>() {
      
      public Element in(Object target) {
        return finder.find(name).in(target);
      }
    };
  }
  
}
