package com.r00t.ai.models;

public class Pair<T, V> {
    private T a;
    private V b;

    public Pair() {
    }

    public Pair(T a, V b) {
        this.a = a;
        this.b = b;
    }

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    public V getB() {
        return b;
    }

    public void setB(V b) {
        this.b = b;
    }
}
