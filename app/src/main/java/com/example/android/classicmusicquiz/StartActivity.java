package com.example.android.classicmusicquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_start.xml
        setContentView(R.layout.activity_start);
        // Locate the button in activity_start.xml
        Button next = (Button) findViewById(R.id.start_button);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                EditText userNameField = findViewById(R.id.user_name_edit_text);
                String userName = userNameField.getText().toString();

                if (userName.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.no_input_name), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                } else {
                    Intent myIntent = new Intent(new  Intent(StartActivity.this,
                            MainActivity.class));
                    myIntent.putExtra("userName", userName);
                    startActivityForResult(myIntent, 0);
                    finish();
                }


            }
        });
    }

    /**
     * When this activity is resumed this method clears the EditText
     */
    @Override
    protected void onResume() {
        super.onResume();
        EditText userNameField = findViewById(R.id.user_name_edit_text);
        userNameField.setText("");
    }

}