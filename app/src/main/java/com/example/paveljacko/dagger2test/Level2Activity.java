package com.example.paveljacko.dagger2test;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.paveljacko.library.Level1Application;
import com.example.paveljacko.library.Level1Class;
import com.example.paveljacko.library.Level2Class;
import com.example.paveljacko.library.Level2Module;

import javax.inject.Inject;

public class Level2Activity extends AppCompatActivity {

    @Inject
    Level1Class level1Class;
    @Inject
    Level2Class level2Class;

    private Level2ExtendComponent level2ExtendComponent;

    public Level2Activity() {
        Log.d("LIFECYCLE", getClass().getSimpleName() + " Created");
    }

    public Level2ExtendComponent level2ExtendComponent() {
        return level2ExtendComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Level1Application level1Application = (Level1Application) getApplication();
        level2ExtendComponent = DaggerLevel2ExtendComponent.builder()
                .level1Component(level1Application.level1Component())
                .level2Module(new Level2Module())
                .build();

        level2ExtendComponent.inject(this);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Level2Activity.this, Level2Activity.class));
            }
        });

        Log.d("BUM", getClass().getSimpleName() + " try access: " + level1Class);
        Log.d("BUM", getClass().getSimpleName() + " try access: " + level2Class);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent intent = new Intent(this, Level2ActivityCopy.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
