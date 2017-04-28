package com.example.arushi.fireapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {

    private Button logoutbtn;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth= FirebaseAuth.getInstance();
        logoutbtn= (Button) findViewById(R.id.logoutbutton);

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuthListener= new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(FirebaseAuth firebaseAuth) {

                        if(firebaseAuth.getCurrentUser()== null){
                            startActivity(new Intent(Profile.this, MainActivity.class));
                        }
                    }
                };

            }
        });


    }
}
