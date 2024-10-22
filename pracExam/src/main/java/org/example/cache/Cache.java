package org.example.cache;


public interface Cache<K, T> {
    T get(K key);

    void put(K key, T value);

    void remove(K key);

    void clear();

    int size();
}