package com.example.activity_login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    private EditText Name;
    private EditText Password;
    private Button Login;
    private ProgressDialog progressDialog;
    private Button SignUp;

    //defining firebaseAuth object

    private FirebaseAuth firebaseAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //initializing firebaseAuth object
        firebaseAuth = FirebaseAuth.getInstance();

//        @Override
//        public void onStart() {
//            super.onStart();
//            // Check if user is signed in (non-null) and update UI accordingly.
//            FirebaseUser currentUser = firebaseAuth.getCurrentUser();
//            updateUI(currentUser);
//        }





        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Login = (Button) findViewById(R.id.btnLogin);
        SignUp = (Button)findViewById(R.id.SignUp);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //validate(Name.getText().toString(), Password.getText().toString());
                loginUser();

            }
        });

        progressDialog = new ProgressDialog(this);


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
                Intent i = new Intent(MainActivity.this, homePage.class);
                startActivity(i);
            }
        });



//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }
    private void registerUser(){

        //getting email and password from edit texts

        String email = Name.getText().toString().trim();
        String pword = Password.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(pword)){
            Toast.makeText(this, "Please enter password",Toast.LENGTH_LONG).show();
            return;
        }


        //if the email and password are empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering please wait....");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email,pword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                //checking if success
                if(task.isSuccessful()){
                    //display some message here
                    Toast.makeText(MainActivity.this, "Successfully registered", Toast.LENGTH_LONG).show();

                }else {
                    //display some message here
                    Toast.makeText(MainActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();



            }
        });

    }
    private void loginUser(){
        String email = Name.getText().toString().trim();
        String pword = Password.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(pword)){
            Toast.makeText(this, "Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email,pword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                //checking if success
                if(task.isSuccessful()){
                    //display some message here
                    Toast.makeText(MainActivity.this, "Successfully Signed", Toast.LENGTH_LONG).show();

                }else {
                    //display some message here
                    Toast.makeText(MainActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();



            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    private void validate(String userName, String userPassword){
//        if((userName.equals("student@gc")) && (userPassword .equals("1234"))){
//            Snackbar.make(findViewById(R.id.root), "Welcome", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
//
//        }
//        else{
//            Snackbar.make(findViewById(R.id.root), "Try agian", Snackbar.LENGTH_LONG)
//                   .setAction("Action", null).show();
//
//
//        }
//    }




}



