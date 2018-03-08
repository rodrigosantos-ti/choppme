package rodrigosantos.com.choppme;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText usernameField;
    private EditText passwordField;
    private Button signinButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Views
        usernameField = (EditText) findViewById(R.id.usernameId);
        passwordField = (EditText) findViewById(R.id.passwordId);

        // Buttons
        signinButton = (Button) findViewById(R.id.signInButtonId);

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();

                mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){//Success on validating user!
                            Log.i("signIn", "Sucesso ao fazer o login do usuário!");
                            /*Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();*/
                        }else{
                            Log.i("signIn", "Erro ao fazer o login do usuário. " + task.getException());
                        }
                    }
                });

            }
        });

        mAuth = FirebaseAuth.getInstance();
//        mAuth.signOut();
        if(mAuth.getCurrentUser() != null){
            Log.i("verifyUser", "Usuário está logado!!!");
            Intent intentAlreadyLogged = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intentAlreadyLogged);
        }else{
            Log.i("verifyUser", "Usuário não está logado!");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }





    private void updateUI(FirebaseUser currentUser) {
        //hideProgressDialog();
        /*if (currentUser != null) {
            mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));
            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            findViewById(R.id.email_password_buttons).setVisibility(View.GONE);
            findViewById(R.id.email_password_fields).setVisibility(View.GONE);
            findViewById(R.id.signed_in_buttons).setVisibility(View.VISIBLE);

            findViewById(R.id.verify_email_button).setEnabled(!user.isEmailVerified());
        } else {
            mStatusTextView.setText(R.string.signed_out);
            mDetailTextView.setText(null);

            findViewById(R.id.email_password_buttons).setVisibility(View.VISIBLE);
            findViewById(R.id.email_password_fields).setVisibility(View.VISIBLE);
            findViewById(R.id.signed_in_buttons).setVisibility(View.GONE);
        }*/
    }
}
