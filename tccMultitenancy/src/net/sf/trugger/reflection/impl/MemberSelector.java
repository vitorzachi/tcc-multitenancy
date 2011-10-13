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

import java.lang.reflect.Member;

import net.sf.trugger.PredicableResult;
import net.sf.trugger.predicate.CompositePredicate;
import net.sf.trugger.reflection.ClassHierarchyFinder;
import net.sf.trugger.reflection.ReflectionException;
import net.sf.trugger.util.Utils;

/**
 * A base class for selecting a single {@link Member} object.
 * 
 * @author Marcelo Varella Barca Guimarães
 * @param <T>
 *          The member type.
 */
public class MemberSelector<T extends Member> implements PredicableResult<T, Object> {
  
  private final MemberFinder<T> finder;
  private final CompositePredicate<T> predicate;
  private final boolean useHierarchy;
  
  public MemberSelector(MemberFinder<T> finder, CompositePredicate<T> predicate, boolean useHierarchy) {
    this.finder = finder;
    this.predicate = predicate;
    this.useHierarchy = useHierarchy;
  }
  
  public MemberSelector(MemberFinder<T> finder, CompositePredicate<T> predicate) {
    this(finder, predicate, false);
  }
  
  private T findMember(Class<?> type) {
    try {
      T element = finder.find(type);
      if (predicate.evaluate(element)) {
        return element;
      }
      return null;
    } catch (NoSuchMethodException e) {
      return null;
    } catch (NoSuchFieldException e) {
      return null;
    } catch (Exception e) {
      throw new ReflectionException(e);
    }
  }
  
  public final T in(Object target) {
    if (useHierarchy) {
      return new ClassHierarchyFinder<T>() {
        
        protected T findObject(Class<?> clazz) {
          return findMember(clazz);
        }
        
      }.find(target);
    }
    Class<?> type = Utils.resolveType(target);
    return findMember(type);
  }
  
  @Override
  public CompositePredicate<T> toPredicate() {
    return predicate;
  }
  
}
