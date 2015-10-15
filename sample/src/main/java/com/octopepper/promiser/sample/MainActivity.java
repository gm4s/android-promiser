package com.octopepper.promiser.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.octopepper.promiser.Promiser;
import com.octopepper.promiser.interfaces.Rejecter;
import com.octopepper.promiser.interfaces.Resolver;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getResult(init());
    }

    private Promiser<String, Integer> init() {
        return new Promiser<>((Resolver<String> resolve, Rejecter<Integer> reject) -> {
            Log.e("TEST", "BEGIN");
            // Place your asynchronous process here, and make sure to trigger resolve.run() or reject.run() when needed.
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Log.e("TEST", "END");
                    int i = randomNumber();
                    if (i % 2 == 0)
                        resolve.run("JSON");
                    else
                        reject.run(i);
                }
            }, 500);
        });
    }

    private void getResult(Promiser<String, Integer> p) {
        p.success(this::resultSucceeded)
                .error(this::resultError);
    }

    private void resultSucceeded(String s) {
        Log.e("TEST", "SUCCESS : " + s);
    }

    private void resultError(Integer code) {
        Log.e("TEST", "ERROR : " + code);
    }

    private int randomNumber() {
        Random r = new Random();
        return r.nextInt(9 - 1) + 1;
    }

}
