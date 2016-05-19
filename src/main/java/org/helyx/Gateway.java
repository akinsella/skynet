package org.helyx;

public class Gateway {

    int exitIndice;

    public Gateway(int exitIndice) {
        this.exitIndice = exitIndice;
    }

    public int getExitIndice() {
        return exitIndice;
    }

    @Override
    public String toString() {
        return "Gateway{exitIndice=" + exitIndice + '}';
    }

}