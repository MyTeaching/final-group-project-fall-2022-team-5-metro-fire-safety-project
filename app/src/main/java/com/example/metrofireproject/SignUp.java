package com.example.metrofireproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private TextInputLayout first_name, last_name, license, sign_up_code, email, phone, password;
    private String first_name_string, last_name_string, license_string, sign_up_code_string, email_string, phone_string, password_string;
    private String is_supervisor;
    private long phone_long ;
    private boolean form_validated;
    private Button sign_up_btn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        is_supervisor = "no";
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        // FIND ALL THE INPUT FIELDS
        first_name = findViewById(R.id.first_name_layout);
        last_name = findViewById(R.id.last_name_layout);
        license = findViewById(R.id.license_input_layout);
        sign_up_code = findViewById(R.id.sign_up_code_layout);
        phone = findViewById(R.id.phone_layout);
        email = findViewById(R.id.sign_up_email_layout);
        password = findViewById(R.id.sign_up_password_layout);
        // FIND THE BUTTON
        sign_up_btn = findViewById(R.id.sign_up_btn);



    }
    public void sign_up(View view){
        form_validated = true;
        validateForm(); //Checking If all Inputs Are Valid

        if(form_validated){
            //GRANT SUPERVISOR STATUS OR NOT
            if (sign_up_code_string.contains("sp")){
                is_supervisor = "yes";
            }
            fAuth.createUserWithEmailAndPassword(email_string,password_string).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {// WHEN A USER IS CREATED SUCCESSFULLY
                    FirebaseUser user = fAuth.getCurrentUser();
                    DocumentReference df = fStore.collection("Users").document(user.getUid());
                    Map<String,Object> userInfo = new HashMap<>();
                    userInfo.put("FirstName", first_name_string);
                    userInfo.put("LastName", last_name_string);
                    userInfo.put("License", license_string);
                    userInfo.put("Phone", phone_string);
                    userInfo.put("Email", email_string);
                    userInfo.put("SignUpCode", sign_up_code_string);
                    userInfo.put("Password", password_string);
                    userInfo.put("IsSupervisor", is_supervisor);
                    df.set(userInfo);
                    Toast.makeText(SignUp.this,"Account created successfully !", Toast.LENGTH_LONG).show();

                    Intent go_dashboard = new Intent(SignUp.this, FireguardDashboard.class);
                    startActivity(go_dashboard);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignUp.this,"Account creation unsuccessfully !", Toast.LENGTH_LONG).show();
                }
            });

        }

    }
    private void validateForm(){
        validateFirstName();
        validateLastName();
        validateLicense();
        validatePhone();
        validateEmail();
        validateSignUpCode();
        validatePassword();
    }
    //
    private void validateFirstName(){
        first_name_string = first_name.getEditText().getText().toString();
        if (first_name_string.isEmpty()){
            first_name.setError("Please add your glorious First name!");
            form_validated = false;
        }
        else {
            form_validated = true;
            first_name.setError(null);
            Toast.makeText(this,"First Name: " + first_name_string,Toast.LENGTH_LONG).show();
        }
    }
    private void validateLastName(){
        last_name_string = last_name.getEditText().getText().toString();
        if (last_name_string.isEmpty()){
            last_name.setError("Please add your glorious Last name!");
            form_validated = false;
        }
        else {
            form_validated = true;
            last_name.setError(null);
            Toast.makeText(this,"Last Name: " + last_name_string,Toast.LENGTH_LONG).show();
        }
    }
    private void validateLicense(){
        license_string = license.getEditText().getText().toString();
        if (license_string.isEmpty()){
            license.setError("Please add your License number!");
            form_validated = false;
        }
        else {
            form_validated = true;
            license.setError(null);
            Toast.makeText(this,"License: " + license_string,Toast.LENGTH_LONG).show();
        }
    }
    private void validatePhone(){
        phone_string = phone.getEditText().getText().toString();
        if (phone_string.isEmpty()){
            phone.setError("Please add your phone number");
            form_validated = false;
        }
        else {
            form_validated = true;
            phone.setError(null);
            Toast.makeText(this,"Phone: " + phone_string,Toast.LENGTH_LONG).show();
        }
    }
    private void validateEmail(){
        email_string = email.getEditText().getText().toString();
        if (email_string.isEmpty()){
            email.setError("Please add your email");
            form_validated = false;
        }
        else {
            form_validated = true;
            email.setError(null);
            Toast.makeText(this,"Email: " + email_string,Toast.LENGTH_LONG).show();
        }
    }
    private void validateSignUpCode(){
        sign_up_code_string = sign_up_code.getEditText().getText().toString();
        if (sign_up_code_string.isEmpty()){
            sign_up_code.setError("Please add your sign up code");
            form_validated = false;
        }
        else {
            form_validated = true;
            sign_up_code.setError(null);
            Toast.makeText(this,"Sign Up Code: " + sign_up_code_string,Toast.LENGTH_LONG).show();
        }
    }
    private void validatePassword(){
        password_string = password.getEditText().getText().toString();
        if (password_string.isEmpty()) {
            password.setError("Please add your Password");
            form_validated = false;
        }
        else {
            form_validated = true;
            password.setError(null);
            Toast.makeText(this,"Password: " + password_string,Toast.LENGTH_LONG).show();
        }
    }
}
