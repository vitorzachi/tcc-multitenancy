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
package net.sf.trugger.scan.impl;

import net.sf.trugger.reflection.ReflectionPredicates;
import net.sf.trugger.scan.ClassScanner;
import net.sf.trugger.scan.ClassScannerFactory;
import net.sf.trugger.scan.ResourceFinder;
import net.sf.trugger.scan.ScanLevel;
import net.sf.trugger.selector.ClassSelector;
import net.sf.trugger.selector.ClassesSelector;

/**
 * A default class finder.
 * <p>
 * This class uses the {@link ScanLevel#PACKAGE} as the default scan level and
 * uses the {@link ResourceFinder finders} below:
 * <ul>
 * <li> {@link FileResourceFinder} - for classes in the file system.
 * <li> {@link JarResourceFinder} - for classes in jar files.
 * <li> {@link BundleResourceFinder} - for classes in the eclipse platform.
 * </ul>
 *
 * @author Marcelo Varella Barca Guimarães
 */
public class TruggerClassScanner implements ClassScanner {

  private final Scanner scanner;

  /**
   * Creates a new finder using the context class loader in the current Thread.
   */
  public TruggerClassScanner(ClassScannerFactory factory) {
    this(Thread.currentThread().getContextClassLoader(), factory);
  }

  public TruggerClassScanner(ClassLoader classLoader, ClassScannerFactory factory) {
    this.scanner = new TruggerScanner(factory, classLoader);
  }

  public void setClassLoader(ClassLoader classLoader) {
    this.scanner.setClassLoader(classLoader);
  }

  public ClassesSelector findAnnotations() {
    return new TruggerClassesSelector(scanner).thatMatches(ReflectionPredicates.ANNOTATION);
  }

  public ClassesSelector findEnums() {
    return new TruggerClassesSelector(scanner).thatMatches(ReflectionPredicates.ENUM);
  }

  public ClassesSelector findClasses() {
    return new TruggerClassesSelector(scanner).thatMatches(ReflectionPredicates.CLASS);
  }

  public ClassesSelector findInterfaces() {
    return new TruggerClassesSelector(scanner).thatMatches(ReflectionPredicates.INTERFACE);
  }

  public ClassesSelector findAll() {
    return new TruggerClassesSelector(scanner);
  }

  public ClassSelector findClass() {
    return new TruggerClassSelector(scanner).thatMatches(ReflectionPredicates.CLASS);
  }

}
