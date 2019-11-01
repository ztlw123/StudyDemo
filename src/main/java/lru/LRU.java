package lru;

import java.util.*;

/**
 * @Author zjh
 * @Date 2019/08/26,22:47
 */
public class LRU<K, V> {
    private final int MAX_CACHE_SIZE;
    private final float DEAFAULT_LOAD_FACTORY = 0.75F;

    LinkedHashMap<K, V> map;

    public LRU(int cacheSize) {
        MAX_CACHE_SIZE = cacheSize;
        int capacity = (int)Math.ceil(MAX_CACHE_SIZE / DEAFAULT_LOAD_FACTORY) + 1;

        map = new LinkedHashMap<K, V>(capacity, DEAFAULT_LOAD_FACTORY, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > MAX_CACHE_SIZE;
            }
        };
    }

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    public synchronized void remove(K key) {
        map.remove(key);
    }

}
