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
package net.sf.trugger.predicate;

import net.sf.trugger.loader.ImplementationLoader;

/**
 * An utility class to handle {@link Predicate} objects.
 * 
 * @author Marcelo Varella Barca Guimarães
 */
public final class Predicates {

	private static PredicateFactory factory;

	private Predicates() {
	}

	static {
		factory = ImplementationLoader.getInstance().get(PredicateFactory.class);
	}

	/**
	 * A predicate that always returns <code>true</code>.
	 */
	public static final CompositePredicate ALWAYS_TRUE = newComposition(new ConstantPredicate(true));
	/**
	 * A predicate that always returns <code>false</code>.
	 */
	public static final CompositePredicate ALWAYS_FALSE = newComposition(new ConstantPredicate(false));

	/**
	 * Note: This method returns the {@link #ALWAYS_TRUE} field, but with type
	 * safety.
	 * 
	 * @return A predicate that always returns <code>true</code>.
	 */
	public static <T> CompositePredicate<T> alwaysTrue() {
		return ALWAYS_TRUE;
	}

	/**
	 * Note: This method returns the {@link #ALWAYS_FALSE} field, but with type
	 * safety.
	 * 
	 * @return A predicate that always returns <code>false</code>.
	 */
	public static <T> CompositePredicate<T> alwaysFalse() {
		return ALWAYS_FALSE;
	}

	/**
	 * @param value
	 *            the value that must be returned by the predicate.
	 * @return a predicate that always return the given value.
	 */
	public static <T> CompositePredicate<T> valueOf(boolean value) {
		return value ? ALWAYS_TRUE : ALWAYS_FALSE;
	}

	/**
	 * @return a predicate that returns the negation of the given one.
	 */
	public static <T> CompositePredicate<T> not(Predicate<? super T> predicate) {
		return Predicates.<T> newComposition(predicate).negate();
	}

	/**
	 * @return a predicate that returns the negation of the given one.
	 */
	public static <T> CompositePredicate<T> not(CompositePredicate<T> predicate) {
		return predicate.negate();
	}

	/**
	 * Creates a new {@link CompositePredicate} using a {@link PredicateFactory}
	 * to create the instance.
	 * 
	 * @param predicate
	 *            the initial predicate of the composition.
	 * @return the created predicate.
	 */
	public static <T> CompositePredicate<T> newComposition(Predicate<? super T> predicate) {
		return factory.createCompositePredicate(predicate);
	}

	private static class ConstantPredicate implements Predicate<Object> {

		private final boolean returnValue;

		public ConstantPredicate(boolean returnValue) {
			super();
			this.returnValue = returnValue;
		}

		public boolean evaluate(Object element) {
			return returnValue;
		}

		@Override
		public String toString() {
			return String.valueOf(returnValue);
		}
	}

}
