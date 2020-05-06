package com.example.android.quizapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private int score = 0;
    private int scoreMail;
    public boolean contiBoolean = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Extract the NAME of the person and store it in a String
        EditText name = findViewById(R.id.name_field);
        name.getText();
    }

    public void submitButton(View view) {
        //Extract the answer for Map and store it in a Boolean
        RadioButton correctMap = findViewById(R.id.up);
        boolean mapBoolean = correctMap.isChecked();


        //Extract the answer for River and store it in a String
        EditText nameRiver = findViewById(R.id.river);
        String riverName = nameRiver.getText().toString();

        //Extract the answer for Continent and store it in a Boolean
        RadioButton correctConti = findViewById(R.id.conti_7);
        contiBoolean = correctConti.isChecked();
        // Inflate the CheckBox method
        CheckBox nameTownOne = findViewById(R.id.townBucharest);
        CheckBox nameTownTwo = findViewById(R.id.townVlad);
        CheckBox nameTownThree = findViewById(R.id.townParis);
        CheckBox nameTownFour = findViewById(R.id.townCairo);
        //call the method for calculating the score
        score = calculateScore(contiBoolean, mapBoolean, riverName, nameTownOne, nameTownTwo, nameTownThree, nameTownFour);
        scoreMail = score;
        Log.v("MainActivity", "Score 2 " + score);
        TextView result = findViewById(R.id.text_result);


       Toast.makeText(this, "Your result is: " + score + " points ", Toast.LENGTH_SHORT).show();

        // Show result as a Text below the Submit button
        result.setText("Your result is: " + score + " points");
        // Evaluate the niveau of the student
        if ( score == 4) {
            Toast.makeText(this, "You are vey good at Geography", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, " You have to learn more... or you can try againg...", Toast.LENGTH_SHORT).show();
        }


        score = 0;
        Log.v("MainActivity", "Score 3 " + scoreMail);

    }

    /**
     * CALCULATES THE SCORE of the QUIZ
     *
     * @return total SCORE
     */
    private int calculateScore(boolean contiBoolean, boolean mapBoolean, String riverName,CheckBox nameTownOne, CheckBox nameTownTwo , CheckBox nameTownThree, CheckBox nameTownFour ) {

        // Figure out if Continent answer is OK
        if (contiBoolean) {
            score++;
        }
        // Figure out if the radio button " up"  is checked
        if (mapBoolean) {
            score++;
        }
        //Check the NAME of the river
        if (riverName.equalsIgnoreCase("Danube")) {
            score ++;
        }
        // Figure out if town checkbox answer  is OK
        if (nameTownOne.isChecked() && nameTownThree.isChecked() && !nameTownTwo.isChecked () && !nameTownFour.isChecked()) {
            score++;
        }

        return score;
    }


    public void sendButton(View view) {
        Log.v("MainActivity", "Score 4 " + scoreMail);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "How good is your geography  ");
        intent.putExtra(Intent.EXTRA_TEXT, "Your geograpy niveau is " + scoreMail);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * RESET BUTTON.
     */
    public void resetButton(View v) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
