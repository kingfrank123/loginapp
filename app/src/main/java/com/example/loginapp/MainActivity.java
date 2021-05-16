package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    private EditText cUserName;
    private EditText cPassword;
    private Button cLogin;
    private TextView cAttempts;


    private String username = "Admin";
    private String password = "123456";

    private Button cGoogleLogin;
    private static int RC_SIGN_IN = 5000;

    GoogleSignInClient mGoogleSignInClient;

    boolean checkistrue = false;
    private int counter = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cUserName = findViewById(R.id.ctUserName);
        cPassword = findViewById(R.id.ctPassword);
        cLogin = findViewById(R.id.ctLoginBtn);
        cAttempts = findViewById(R.id.ctAttempts);


        cLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = cUserName.getText().toString();
                String inputPassword = cPassword.getText().toString();

                if(inputName.isEmpty() || inputPassword.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter your credentials", Toast.LENGTH_SHORT).show();
                }else{
                    checkistrue = check(inputName,inputPassword);
                    if(!checkistrue){
                        // decrement every unsuccessful login attempt
                        counter--;

                        Toast.makeText(MainActivity.this, "incorrect username or password", Toast.LENGTH_SHORT).show();

                        cAttempts.setText("# of Attempts remaining : " + counter);
                        if(counter == 0){
                            // disables button if no more attempts
                            cLogin.setEnabled(false);
                        }
                    }else{
                        // direct user to new page if login is successful
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        // go to homepage
                        Intent intent = new Intent(MainActivity.this, Homepage.class);
                        startActivity(intent);
                    }
                }
            }
        });

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        /* Set the dimensions of the sign-in button. */
        SignInButton signInButton = findViewById(R.id.googleSignIn);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        signInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switch (view.getId()) {
                    case R.id.googleSignIn:
                        signIn();
                        break;
                }
            }
        });
    }

    private void signIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();

                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                // go to homepage
                Intent intent = new Intent(MainActivity.this, Homepage.class);
                startActivity(intent);
            }

            // Signed in successfully, show authenticated UI.

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("signed in", e.toString());
        }
    }

    private boolean check(String n, String pw){
        if(n.equals(username) && pw.equals(password)){
            return true;
        }
        return false;
    }

}