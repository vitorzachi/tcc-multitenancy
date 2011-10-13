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

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;

import net.sf.trugger.predicate.Predicate;
import net.sf.trugger.reflection.Access;

/**
 * Interface that defines a selector for {@link Member} objects.
 *
 * @author Marcelo Varella Barca Guimarães
 * @param <T>
 *          The element type.
 */
public interface MemberSelector<T extends Member> extends PredicateSelector<T>, AccessSelector, RecursionSelector,
    AnnotatedElementSelector {

  MemberSelector<T> annotated();
  
  MemberSelector<T> notAnnotated();
  
  MemberSelector<T> annotatedWith(Class<? extends Annotation> type);

  MemberSelector<T> notAnnotatedWith(Class<? extends Annotation> type);

  MemberSelector<T> thatMatches(Predicate<? super T> predicate);

  MemberSelector<T> withAccess(Access access);

  MemberSelector<T> recursively();

  /**
   * Selects the elements that are not <i>static</i>.
   *
   * @return a reference to this object.
   */
  MemberSelector<T> nonStatic();

  /**
   * Selects the elements that are not <i>final</i>.
   *
   * @return a reference to this object.
   * @since 1.2
   */
  MemberSelector<T> nonFinal();

}
