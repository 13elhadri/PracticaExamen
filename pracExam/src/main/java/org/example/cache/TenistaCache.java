package org.example.cache;

import org.example.models.Tenista;

public class TenistaCache implements Cache<Integer, Tenista>{
    @Override
    public Tenista get(Integer key) {
        return null;
    }

    @Override
    public void put(Integer key, Tenista value) {

    }

    @Override
    public void remove(Integer key) {

    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }
}
