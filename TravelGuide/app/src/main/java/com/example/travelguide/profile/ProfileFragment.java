package com.example.travelguide.profile;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelguide.DataBaseHelper;
import com.example.travelguide.NothingSelectedSpinnerAdapter;
import com.example.travelguide.R;
import com.example.travelguide.SignInActivity;
import com.example.travelguide.SignUpActivity;
import com.example.travelguide.User;

import java.util.regex.Pattern;

public class ProfileFragment extends Fragment {


    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        String[] options = { "Asia","Africa","Europe",
                "North America", "South America","Australia" };
        final Spinner continentSpinner =(Spinner)view.findViewById(R.id.editdest);
        ArrayAdapter<String> objContinentArr = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item, options);
        continentSpinner.setAdapter(objContinentArr);


        //Get database
        DataBaseHelper dataBaseHelper =new DataBaseHelper(getContext(),"TRAVEL", null,1);
        Cursor userCursor = dataBaseHelper.getUser(User.currentEmail);

        // Define text boxes
        final EditText firstName = (EditText)view.findViewById(R.id.editfirst);
        final EditText lastName = (EditText)view.findViewById(R.id.editlast);
        final TextView email = (TextView)view.findViewById(R.id.emailView);
        final EditText password = (EditText)view.findViewById(R.id.editpassword);

        if(userCursor != null && userCursor.moveToFirst() ){
            email.setText(userCursor.getString(0));
            firstName.setText(userCursor.getString(1));
            lastName.setText(userCursor.getString(2));
            password.setText(userCursor.getString(3));
            continentSpinner.setSelection(objContinentArr.getPosition(userCursor.getString(4)));
        }
        Button edit = (Button) view.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User newUser =new User();
                int flag =0;
                //check first name box
                if(firstName.getText().toString().isEmpty() || firstName.getText().toString().length() <3) {
                    flag =1;
                }
                else{
                    newUser.setFirstname(firstName.getText().toString());
                }

                //check last name box
                if(lastName.getText().toString().isEmpty()|| lastName.getText().toString().length() <3){
                    flag =1;
                }
                else {
                    newUser.setLastname(lastName.getText().toString());
                }


                //check Password box
                if(password.getText().toString().isEmpty() ||
                        password.getText().toString().length() <8||
                        !password.getText().toString().matches(".*[0-9].*") ||
                        !password.getText().toString().matches(".*[a-z].*") ||
                        !password.getText().toString().matches(".*[A-Z].*")){

                    flag =1;
                }
                else {
                    newUser.setPassword(password.getText().toString());
                }


                //check preferred destination selection
                if(continentSpinner.getSelectedItem()==null) {
                    flag =1;

                }
                else {
                    if(!userCursor.getString(4).equals(continentSpinner.getSelectedItem().toString()))User.flag=0;
                    newUser.setpDestination(continentSpinner.getSelectedItem().toString());
                }
                //Check if there are errors
                //If there isn't any error, the user will be signed uo and his/her info will be stored in the db
                if(flag ==0) {
                    newUser.setEmail(User.currentEmail);
                    if(dataBaseHelper.editUser(newUser) >0) {

                        Toast toast = Toast.makeText(getContext(),
                                "success", Toast.LENGTH_SHORT);
                        toast.show();

                    }
                    else {
                        Toast toast = Toast.makeText(getContext(),
                                "failed", Toast.LENGTH_SHORT);
                        toast.show();

                    }
                }
                //else toast notification will notify the user of the errors
                else{
                    Toast toast =Toast.makeText(getContext(),
                            "failed",Toast.LENGTH_SHORT);
                    toast.show();
                }}
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}