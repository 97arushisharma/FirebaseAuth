package com.example.arushi.fireapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText ef;
    private EditText pf;
    private EditText uf;

    private Button signupbtn;
    //firebase object
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth= FirebaseAuth.getInstance();
        ef= (EditText)findViewById(R.id.emailField);
        pf= (EditText) findViewById(R.id.passlField);
        uf= (EditText) findViewById(R.id.userField);

        signupbtn= (Button) findViewById(R.id.signupButton);

        mAuthListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser()!= null){
                    startActivity(new Intent(MainActivity.this, Profile.class));
                }
            }
        };

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startsignup();

            }
        });
    }

    protected  void onStart(){
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startsignup(){

        String email= ef.getText().toString();
        String password= pf.getText().toString();

        if(TextUtils.isEmpty(email)|| TextUtils.isEmpty(password)){

            Toast.makeText(MainActivity.this,"Fields are Empty.", Toast.LENGTH_LONG).show();
        }
        else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(MainActivity.this,"Could not Sign Up.", Toast.LENGTH_LONG).show();
                    }
                }
            });


        }

    }
}
