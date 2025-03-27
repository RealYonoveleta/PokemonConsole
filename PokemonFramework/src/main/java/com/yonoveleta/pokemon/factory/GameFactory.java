package com.yonoveleta.pokemon.factory;

import java.util.Collection;

public interface GameFactory<K, V> {
	
	public V get(K key);
	
	public void add(K key, V item);
	
	public boolean has(K key);
	
	public Collection<V> getAll();
	
}
