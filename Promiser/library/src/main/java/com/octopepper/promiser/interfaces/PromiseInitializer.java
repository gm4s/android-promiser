package com.octopepper.promiser.interfaces;

/*
 * Created by Yannick & Guillaume on 13/10/2015.
 */
public interface PromiseInitializer<T, U> {
    public void run(Resolver<T> resolve, Rejecter<U> reject);
}