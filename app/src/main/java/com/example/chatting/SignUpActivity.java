package com.example.chatting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private EditText editTextUsername; // Declare the EditText variable for username
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignUp;
    private TextView textViewGoToLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        editTextUsername = findViewById(R.id.editTextUsername); // Initialize the username variable
        editTextEmail = findViewById(R.id.editTextSignUpEmail);
        editTextPassword = findViewById(R.id.editTextSignUpPassword);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        textViewGoToLogin = findViewById(R.id.textViewGoToLogin);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim(); // Get the username input string
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Username, Email, and Password are required!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Here, you can use the 'username' variable where you need it, e.g., to store it in the Firebase database.
                // You would typically store it along with the user's other information after the user has been created.

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // If you want to store the username in Firebase, do it here
                                    Toast.makeText(SignUpActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(SignUpActivity.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        textViewGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            }
        });
    }
}
