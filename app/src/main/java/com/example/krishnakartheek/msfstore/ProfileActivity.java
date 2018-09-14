package com.example.krishnakartheek.msfstore;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textName, textEmail;
    FirebaseAuth mAuth;
    Button btn_logout,btn_upload;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        imageView = findViewById(R.id.imageView);
        textName = findViewById(R.id.textViewName);
        textEmail = findViewById(R.id.textViewEmail);
        btn_logout=(Button)findViewById(R.id.btn_logout);

        btn_upload=(Button)findViewById(R.id.btn_upload);


        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()==null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));

        }

        FirebaseUser firebaseUser=mAuth.getCurrentUser();

        Glide.with(this)
                .load(firebaseUser.getPhotoUrl())
                .into(imageView);

        textName.setText(firebaseUser.getDisplayName());
        textEmail.setText(firebaseUser.getEmail());


        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Write a message to the database
               /* FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Dhan");

                myRef.setValue("Dhan, World!");*/





            }
        });



        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();


                // Configure Google Sign In
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();

                GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(ProfileActivity.this, gso);
                mGoogleSignInClient.signOut();

                if(mAuth.getCurrentUser()==null){
                    startActivity(new Intent(ProfileActivity.this,MainActivity.class));
                    Toast.makeText(ProfileActivity.this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    }

