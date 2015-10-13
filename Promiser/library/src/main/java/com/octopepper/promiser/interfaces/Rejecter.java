package com.octopepper.promiser.interfaces;

/*
 * Created by Yannick & Guillaume on 13/10/2015.
 */
public interface Rejecter<U> {
    void run(U u);
}