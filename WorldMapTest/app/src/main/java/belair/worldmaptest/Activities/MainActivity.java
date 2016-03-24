package belair.worldmaptest.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.SurfaceView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import belair.worldmaptest.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button buttonForest;
    Button buttonSwamp;
    Button buttonCave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        buttonForest = (Button)findViewById(R.id.ForestButton);
        buttonSwamp = (Button)findViewById(R.id.SwampButton);
        buttonCave = (Button)findViewById(R.id.CaveButton);

        buttonForest.setOnClickListener(this);
        buttonSwamp.setOnClickListener(this);
        buttonCave.setOnClickListener(this);

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void ForestButtonClick(){

        startActivity(new Intent("ForestActivity"));
    }

    private void SwampButtonClick(){

        startActivity(new Intent("SwampActivity"));
    }

    private void CaveButtonClick(){

        startActivity(new Intent("CaveActivity"));
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.ForestButton:
                ForestButtonClick();
                break;
            case R.id.SwampButton:
                SwampButtonClick();
                break;
            case R.id.CaveButton:
                CaveButtonClick();
                break;
            default:
                break;
        }
    }
}
