package belair.worldmaptest.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import belair.worldmaptest.R;

public class CaveActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cave);
        buttonBack = (Button)findViewById(R.id.backButton);

        buttonBack.setOnClickListener(this);
    }

    private void BackButtonClick() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.backButton:
                BackButtonClick();
                break;
            default:
                break;
        }
    }
}
