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

public class MainActivity extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewGoToSignUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.editTextLoginEmail);
        editTextPassword = findViewById(R.id.editTextLoginPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewGoToSignUp = findViewById(R.id.textViewGoToSignUp);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();


                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Email and Password are required!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                    // Start ChatActivity
                                    Intent chatIntent = new Intent(MainActivity.this, ChatActivity.class);
                                    chatIntent.putExtra("USERNAME", email);
                                    startActivity(chatIntent);
                                } else {
                                    Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

        textViewGoToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });
    }
}
