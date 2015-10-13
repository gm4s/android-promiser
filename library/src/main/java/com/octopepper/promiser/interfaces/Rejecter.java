package com.octopepper.promiser.interfaces;

public interface Rejecter<U> {
    void run(U u);
}
