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

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

import net.sf.trugger.Result;
import net.sf.trugger.predicate.CompositePredicate;
import net.sf.trugger.predicate.Predicate;
import net.sf.trugger.reflection.ReflectionPredicates;
import net.sf.trugger.selector.SetterMethodSelector;

/**
 * Default implementation for the setter method selector.
 *
 * @author Marcelo Varella Barca Guimarães
 */
public class TruggerSetterMethodSelector implements SetterMethodSelector {

  private final MembersSelector<Method> selector;

  public TruggerSetterMethodSelector(String name) {
    this.selector = new MembersSelector<Method>(new SettersFinder(name));
  }

  public SetterMethodSelector annotated() {
    selector.builder().add(ReflectionPredicates.ANNOTATED);
    return this;
  }

  public SetterMethodSelector notAnnotated() {
    selector.builder().add(ReflectionPredicates.NOT_ANNOTATED);
    return this;
  }

  public SetterMethodSelector annotatedWith(Class<? extends Annotation> type) {
    selector.builder().add(ReflectionPredicates.annotatedWith(type));
    return this;
  }

  public SetterMethodSelector notAnnotatedWith(Class<? extends Annotation> type) {
    selector.builder().add(ReflectionPredicates.notAnnotatedWith(type));
    return this;
  }

  public SetterMethodSelector thatMatches(Predicate<? super Method> predicate) {
    selector.builder().add(predicate);
    return this;
  }

  public Set<Method> in(Object target) {
    return selector.in(target);
  }

  public SetterMethodSelector recursively() {
    selector.useHierarchy();
    return this;
  }

  public Result<Method, Object> forType(final Class<?> type) {
    return new SetterResult(selector, type);
  }

  public CompositePredicate<Method> toPredicate() {
    return selector.toPredicate();
  }

}
