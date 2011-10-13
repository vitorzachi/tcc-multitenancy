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
package net.sf.trugger.iteration.impl;

import java.util.Collection;
import java.util.Iterator;

import net.sf.trugger.iteration.IterationFactory;
import net.sf.trugger.iteration.IterationSearchOperation;
import net.sf.trugger.iteration.SrcIteration;
import net.sf.trugger.iteration.SrcToDestIteration;
import net.sf.trugger.iteration.TransformingIteration;
import net.sf.trugger.transformer.Transformer;

/**
 * The default implementation for {@link IterationFactory}.
 *
 * @author Marcelo Varella Barca Guimarães
 */
public class TruggerIterationFactory implements IterationFactory {

  @Override
  public <E> TransformingIteration<E> createCopyOperation(Collection<E> collection) {
    return new TruggerTransformingIteration<E>(collection) {

      protected SrcToDestIteration<E> create(Collection to) {
        return new TruggerCopyIteration<E, E>(to);
      }

      protected <From> SrcToDestIteration<From> create(Collection to, Transformer<E, From> transformer) {
        return new TruggerCopyIteration<E, From>(to, transformer);
      }
    };
  }

  @Override
  public <E> TransformingIteration<E> createMoveOperation(Collection<E> collection) {
    return new TruggerTransformingIteration<E>(collection) {

      protected SrcToDestIteration<E> create(Collection to) {
        return new TruggerMoveIteration<E, E>(to);
      }

      protected <From> SrcToDestIteration<From> create(Collection to, Transformer<E, From> transformer) {
        return new TruggerMoveIteration<E, From>(to, transformer);
      }
    };
  }

  @Override
  public <E> SrcIteration<E> createCountOperation(Iterator<? extends E> iterator) {
    return new TruggerCountIteration<E>(iterator);
  }

  @Override
  public <E> SrcIteration<E> createRemoveOperation(Iterator<? extends E> iterator) {
    return new TruggerRemoveIteration<E>(iterator);
  }

  @Override
  public <E> SrcIteration<E> createRetainOperation(Iterator<? extends E> iterator) {
    return new TruggerRetainIteration<E>(iterator);
  }

  @Override
  public <E> IterationSearchOperation<E> createSearchOperation(Iterator<E> iterator) {
    return new TruggerSearchIteration<E>(iterator);
  }

}
