package belair.worldmaptest.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import belair.worldmaptest.ForestView;

public class ForestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ForestView(this));

    }

    @Override
    public void onBackPressed(){
        GoBack();
    }

    public void GoBack(){

        Log.d("Fuck noise","Zoidberg");

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
