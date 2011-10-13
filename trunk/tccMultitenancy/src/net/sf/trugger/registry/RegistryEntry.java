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
package net.sf.trugger.registry;

import net.sf.trugger.registry.Registry.Entry;

/**
 * Represents an entry in a registry.
 *
 * @author Marcelo Varella Barca Guimarães
 * @since 2.3
 */
public class RegistryEntry<K, V> implements Entry<K, V> {

  private final K key;
  private final V registry;

  public RegistryEntry(K key, V registry) {
    this.key = key;
    this.registry = registry;
  }

  public K key() {
    return key;
  }

  public V registry() {
    return registry;
  }

}
