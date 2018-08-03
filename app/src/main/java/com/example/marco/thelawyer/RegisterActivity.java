package com.example.marco.thelawyer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText mnome;
    EditText memail;
    EditText mpassword;
    EditText mconfpassword;

    Button regbutton;
    static  final String CHAT_PREF ="chat_pref";
    static final String NOME_KEY="username";



    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);


    }

    private void updateUI(FirebaseUser currentUser) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        initUI();




        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=memail.getText().toString();
                String password=mpassword.getText().toString();

                createFirebaseUser(email,password);
            }
        });



    }



    private void initUI() {

        regbutton=findViewById(R.id.regbutton);
        mnome=findViewById(R.id.name);
        memail=findViewById(R.id.email);
        mpassword=findViewById(R.id.password);
        mconfpassword=findViewById(R.id.confpassword);


    }
    private void createFirebaseUser(String email,String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            saveName();
                            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                            finish();
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("RegistrationNo", "createUserWithEmail:failure", task.getException());

                        }

                        // ...
                    }
                });




    }


    private void saveName(){

        String email= memail.getText().toString();

        SharedPreferences pref= getSharedPreferences(CHAT_PREF,0);

        pref.edit().putString(NOME_KEY,email).apply();



    }
}

