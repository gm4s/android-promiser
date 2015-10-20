package com.octopepper.promiser.interfaces;


public interface Thenable<T, X> {
    X run(T t);
}
