package com.example.travelguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {

    TextView txtSignUp;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        sharedPrefManager =SharedPrefManager.getInstance(this);
        txtSignUp = findViewById(R.id.tSignUp);
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        final EditText email =
                (EditText)findViewById(R.id.edtSignInEmail);
        final EditText password =
                (EditText)findViewById(R.id.edtSignInPassword);
        email.setText(sharedPrefManager.readString("email","noValue"));
        final CheckBox check = (CheckBox) findViewById(R.id.checkBox);

        Button signInButton = (Button) findViewById(R.id.btnSignIn);
        signInButton.setOnClickListener(new View.OnClickListener() {
            String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";
            //Compile regular expression to get the pattern
            Pattern pattern = Pattern.compile(regex);
            DataBaseHelper dataBaseHelper =new DataBaseHelper(SignInActivity.this,"TRAVEL", null,1);
            @Override
            public void onClick(View view) {
                if(email.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(SignInActivity.this,
                            "Missing email", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    Cursor userCursor = dataBaseHelper.getUser(email.getText().toString());
                    if(userCursor != null && userCursor.moveToFirst() ){
                        if(password.getText().toString().isEmpty()){
                            Toast toast = Toast.makeText(SignInActivity.this,
                                    "Missing password", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else{
                            if(password.getText().toString().equals(userCursor.getString(3)))
                            {
                                if(check.isChecked())
                                    sharedPrefManager.writeString("email",email.getText().toString());

                                User.currentEmail = email.getText().toString();

                                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                //intent.putExtra("email", email.getText().toString());
                                SignInActivity.this.startActivity(intent);
                                User.currentEmail = email.getText().toString();
                                Toast toast = Toast.makeText(SignInActivity.this,
                                        "Successful", Toast.LENGTH_SHORT);
                                toast.show();
                                finish();
                            }
                            else{
                                Toast toast = Toast.makeText(SignInActivity.this,
                                        "Wrong password", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                    }
                    else{
                        Toast toast = Toast.makeText(SignInActivity.this,
                                "Wrong email", Toast.LENGTH_SHORT);
                        toast.show();

                    }


                }

            }
        });
    }

}