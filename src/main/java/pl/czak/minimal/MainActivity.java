package pl.czak.minimal;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.btn1);
        TextView textView = findViewById(R.id.text1);
        btn1.setOnClickListener(view -> textView.setText("Button clicked!"));
    }
}
