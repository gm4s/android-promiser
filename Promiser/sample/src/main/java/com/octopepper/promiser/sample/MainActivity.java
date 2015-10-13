package com.octopepper.promiser.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.octopepper.promiser.Promiser;
import com.octopepper.promiser.interfaces.Rejecter;
import com.octopepper.promiser.interfaces.Resolver;

import java.util.Timer;
import java.util.TimerTask;

/*
 * Created by Yannick & Guillaume on 13/10/2015.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        launch();
    }

    private void launch() {
        Promiser<String, Integer> p = new Promiser<>((Resolver<String> resolve, Rejecter<Integer> reject) -> {
            Log.e("TEST", "BEGIN");
            // Place your asynchronous process here, and make sure to trigger resolve.run() or reject.run() when needed.
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Log.e("TEST", "END");
                    resolve.run("JSON");
                }
            }, 500);

        });
    }

}
