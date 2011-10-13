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

import net.sf.trugger.predicate.Predicate;
import net.sf.trugger.reflection.Access;
import net.sf.trugger.scan.ClassScanResult;

import java.lang.annotation.Annotation;

/**
 * Interface that defines a selector for a single {@link Class} objects.
 *
 * @author Marcelo Varella Barca Guimarães
 * @since 2.5
 */
public interface ClassSelector extends ClassSpecifier, ClassScanResult<Class> {

  ClassSelector withAccess(Access access);

  ClassSelector thatMatches(Predicate<? super Class> predicate);

  ClassSelector annotatedWith(Class<? extends Annotation> type);

  ClassSelector notAnnotatedWith(Class<? extends Annotation> type);

  ClassSelector annotated();

  ClassSelector notAnnotated();

  ClassSelector anonymous();

  ClassSelector nonAnonymous();

  ClassSelector recursively();

  ClassSelector assignableTo(Class type);
}
