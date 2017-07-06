package com.example.prakharjaiswal.attendance;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    firebaseAuth = FirebaseAuth.getInstance();
    buttonRegister = (Button) findViewById(R.id.buttonRegister);
    editTextEmail = (EditText) findViewById(R.id.editTextEmail);
    editTextPassword = (EditText) findViewById(R.id.editTextPassword);
    textViewSignin = (TextView) findViewById(R.id.textViewSignIn);
    progressDialog = new ProgressDialog(this);

    if (firebaseAuth.getCurrentUser()!=null)
    {
        //start profile activity
        finish();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }

    buttonRegister.setOnClickListener(this);
    textViewSignin.setOnClickListener(this);
}


    private void registerUser()
    {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Please Enter Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {

            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Register.this, "Successfully Registered",Toast.LENGTH_SHORT).show();
                            progressDialog.hide();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();


                        }else
                        {
                            Toast.makeText(Register.this, "Please try again",Toast.LENGTH_SHORT).show();
                            progressDialog.hide();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view == buttonRegister)
        {
            registerUser();
        }
        if(view == textViewSignin)
        {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

}
