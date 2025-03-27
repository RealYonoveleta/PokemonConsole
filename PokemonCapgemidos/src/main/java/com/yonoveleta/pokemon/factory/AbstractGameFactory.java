package com.yonoveleta.pokemon.factory;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yonoveleta.pokemon.io.log.CentralLogger;

public abstract class AbstractGameFactory<K, V> implements GameFactory<K, V> {

	protected Map<K, V> cache = new HashMap<>();

	public AbstractGameFactory() {
		loadCache();
	}

	protected abstract List<V> loadData() throws FileNotFoundException;

	private void loadCache() {
		try {
			List<V> items = loadData();
			for (V item : items) {
				if (item != null)
					cache.put(getKey(item), item);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected abstract K getKey(V item); // Each subclass defines how to extract the key

	public V get(K key) {
		K formattedKey = getKeyFormat(key);
		V item = cache.containsKey(formattedKey) ? createCopy(cache.get(formattedKey)) : null;
		if(item == null) CentralLogger.logError(String.format("%s doesnt exist", key), null);
		return item;
	}

	protected abstract K getKeyFormat(K key);

	protected abstract V createCopy(V item); // Ensures a fresh instance is returned

	public void add(K key, V item) {
		cache.put(key, item);
	}

	public boolean has(K key) {
		return cache.containsKey(key);
	}

	public Collection<V> getAll() {
		return new ArrayList<>(cache.values());
	}

}
