package com.octopepper.promiser;

import com.octopepper.promiser.enums.PromiseState;
import com.octopepper.promiser.interfaces.PromiseInitializer;
import com.octopepper.promiser.interfaces.Rejecter;
import com.octopepper.promiser.interfaces.Resolver;
import com.octopepper.promiser.interfaces.Thenable;

public class Promiser<T, U> {

    private PromiseState state;
    private Resolver<T> _success = null;
    private Rejecter<U> _error = null;
    private PromiseInitializer<T, U> _init;

    private T resolveResult;
    private U rejectError;
    private Resolver<T> resolve = (T res) -> {
        state = PromiseState.FULFILLED;
        resolveResult = res;
        next();
    };
    private Rejecter<U> reject = (U err) -> {
        state = PromiseState.REJECTED;
        rejectError = err;
        next();
    };

    public Promiser(PromiseInitializer<T, U> init) {
        this._init = init;
        state = PromiseState.PENDING;
        this.go();
    }

    private void next() {
        if (state == PromiseState.FULFILLED && this._success != null) {
            this._success.run(resolveResult);

        } else if (state == PromiseState.REJECTED && this._error != null) {
            this._error.run(rejectError);
        }
    }

    public <X> Promiser<X, U> then(Thenable<T, X> pThen) {
        if (resolveResult instanceof Promiser) {
            return new Promiser<>((res, rej) -> {
//                then(t -> res.run(pThen.run(resolveResult)));
            });
        } else {
            return new Promiser<>((res, rej) -> {
                if (state == PromiseState.FULFILLED) {
                    res.run(pThen.run(resolveResult));
                } else if (state == PromiseState.REJECTED) {
                    // Do nothing for the moment
                } else {
                    this._success = t -> res.run(pThen.run(resolveResult));
                }
            });
        }
    }

    public Promiser<T, U> success(Resolver<T> pSuccess) {
        this._success = pSuccess;
        if (state == PromiseState.FULFILLED) {
            this._success.run(resolveResult);
        }
        return this;
    }

    public Promiser<T, U> error(Rejecter<U> pError) {
        this._error = pError;
        if (state == PromiseState.REJECTED) {
            this._error.run(rejectError);
        }
        return this;
    }

    private Promiser<T, U> go() {
        _init.run(this.resolve, this.reject);
        return this;
    }
}
