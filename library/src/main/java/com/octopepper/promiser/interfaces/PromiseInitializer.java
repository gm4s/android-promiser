package com.octopepper.promiser.interfaces;

public interface PromiseInitializer<T, U> {
    void run(Resolver<T> resolve, Rejecter<U> reject);
}
