package edu.ucr.TabTest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ArtistsActivity extends Activity {
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);
        textView.setText("This is the Artists Tab");
        setContentView(textView);
    }
}