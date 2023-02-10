package com.example.travelguide;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    TextView txtSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        DataBaseHelper dataBaseHelper =new
                DataBaseHelper(SignUpActivity.this,"TRAVEL",null,1);

        String[] options = { "Asia","Africa","Europe",
                "North America", "South America","Australia" };
        final Spinner continentSpinner =(Spinner)
                findViewById(R.id.spinner_continent);
        ArrayAdapter<String> objContinentArr = new
                ArrayAdapter<>(this,android.R.layout.simple_spinner_item, options);
        continentSpinner.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        objContinentArr,
                        R.layout.contact_spinner_row_nothing_selected,
                        this));
        txtSignIn = findViewById(R.id.txtSignIn);

        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Define text boxes
        final EditText firstName =
                (EditText)findViewById(R.id.first);
        final EditText lastName =
                (EditText)findViewById(R.id.last);
        final EditText email = (EditText)findViewById(R.id.email);
        final EditText password =
                (EditText)findViewById(R.id.edtSignUpPassword);
        final EditText confirmPassword =
                (EditText)findViewById(R.id.edtSignUpConfirmPassword);

        //Define text boxes layouts
        final TextInputLayout fname_layout= (TextInputLayout) findViewById(R.id.f_layout);
        final TextInputLayout lname_layout= (TextInputLayout) findViewById(R.id.l_layout);
        final TextInputLayout email_layout= (TextInputLayout) findViewById(R.id.e_layout);
        final TextInputLayout password_layout= (TextInputLayout) findViewById(R.id.p_layout);
        final TextInputLayout cPassword_layout= (TextInputLayout) findViewById(R.id.c_layout);

        //Sign up button
        Button signUpButton = (Button) findViewById(R.id.btnSignUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";
            //Compile regular expression to get the pattern
            Pattern pattern = Pattern.compile(regex);
            @Override
            public void onClick(View view) {
                String TOAST_TEXT = "Error in :  ";
                int flag =0;
                User newUser =new User();
                //check first name box
                if(firstName.getText().toString().isEmpty() || firstName.getText().toString().length() <3) {
                    TOAST_TEXT = TOAST_TEXT + "first name ";
                    fname_layout.setBoxBackgroundColor(getResources().getColor(R.color.faded));
                    fname_layout.setBoxStrokeColor(getResources().getColor(R.color.red));
                    fname_layout.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                    flag =1;
                }
                else{
                    newUser.setFirstname(firstName.getText().toString());
                    fname_layout.setBoxBackgroundColor(getResources().getColor(R.color.white));
                    fname_layout.setBoxStrokeColor(getResources().getColor(R.color.black));
                    fname_layout.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.black)));
                }

                //check last name box
                if(lastName.getText().toString().isEmpty()|| lastName.getText().toString().length() <3){
                    TOAST_TEXT = TOAST_TEXT + "last name ";
                    flag =1;
                    lname_layout.setBoxBackgroundColor(getResources().getColor(R.color.faded));
                    lname_layout.setBoxStrokeColor(getResources().getColor(R.color.red));
                    lname_layout.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                }
                else {
                    newUser.setLastname(lastName.getText().toString());
                    lname_layout.setBoxBackgroundColor(getResources().getColor(R.color.white));
                    lname_layout.setBoxStrokeColor(getResources().getColor(R.color.black));
                    lname_layout.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.black)));
                }

                //check email box
                if(email.getText().toString().isEmpty()  ||  ! pattern.matcher(email.getText().toString()).matches()) {
                    TOAST_TEXT = TOAST_TEXT + "email ";
                    flag =1;
                    email_layout.setBoxBackgroundColor(getResources().getColor(R.color.faded));
                    email_layout.setBoxStrokeColor(getResources().getColor(R.color.red));
                    email_layout.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                }
                else {
                    newUser.setEmail(email.getText().toString());
                    email_layout.setBoxBackgroundColor(getResources().getColor(R.color.white));
                    email_layout.setBoxStrokeColor(getResources().getColor(R.color.black));
                    email_layout.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.black)));
                }

                //check Password box
                if(password.getText().toString().isEmpty() ||
                        password.getText().toString().length() <8||
                        !password.getText().toString().matches(".*[0-9].*") ||
                        !password.getText().toString().matches(".*[a-z].*") ||
                        !password.getText().toString().matches(".*[A-Z].*")){
                    TOAST_TEXT = TOAST_TEXT + "password ";
                    flag =1;
                    password_layout.setBoxBackgroundColor(getResources().getColor(R.color.faded));
                    password_layout.setBoxStrokeColor(getResources().getColor(R.color.red));
                    password_layout.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                }
                else {
                    newUser.setPassword(password.getText().toString());
                    password_layout.setBoxBackgroundColor(getResources().getColor(R.color.white));
                    password_layout.setBoxStrokeColor(getResources().getColor(R.color.black));
                    password_layout.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.black)));
                }

                //check confirm Password box
                if(!password.getText().toString().equals(confirmPassword.getText().toString())) {
                    TOAST_TEXT = TOAST_TEXT + "confirm password ";
                    flag =1;
                    cPassword_layout.setBoxBackgroundColor(getResources().getColor(R.color.faded));
                    cPassword_layout.setBoxStrokeColor(getResources().getColor(R.color.red));
                    cPassword_layout.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.red)));

                }
                else {
                    cPassword_layout.setBoxBackgroundColor(getResources().getColor(R.color.white));
                    cPassword_layout.setBoxStrokeColor(getResources().getColor(R.color.black));
                    cPassword_layout.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.black)));

                }
                //check preferred destination selection
                if(continentSpinner.getSelectedItem()==null) {
                    TOAST_TEXT = TOAST_TEXT +"preferred destination";
                    flag =1;
                    continentSpinner.setBackgroundColor(getResources().getColor(R.color.red));

                }
                else {
                    newUser.setpDestination(continentSpinner.getSelectedItem().toString());
                    continentSpinner.setBackgroundColor(getResources().getColor(R.color.white));

                }
                //Check if there are errors
                //If there isn't any error, the user will be signed uo and his/her info will be stored in the db
                if(flag ==0) {
                    dataBaseHelper.insertUser(newUser);
                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                    SignUpActivity.this.startActivity(intent);
                    Toast toast = Toast.makeText(SignUpActivity.this,
                            "Successful", Toast.LENGTH_SHORT);
                    toast.show();
                    finish();
                }
                //else toast notification will notify the user of the errors
                else{
                    Toast toast =Toast.makeText(SignUpActivity.this,
                            TOAST_TEXT,Toast.LENGTH_SHORT);
                    toast.show();
                }}
        });
    }
}