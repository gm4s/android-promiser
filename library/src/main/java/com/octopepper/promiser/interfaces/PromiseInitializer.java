package com.octopepper.promiser.interfaces;

public interface PromiseInitializer<T, U> {
    public void run(Resolver<T> resolve, Rejecter<U> reject);
}
