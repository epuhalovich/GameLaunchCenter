package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupSignInListener();
        setupSignUpListener();

    }
    public void setupSignInListener(){
        Button signIn = findViewById(R.id.signIn);
        signIn.setOnClickListener((v) -> {
            Intent tmp = new Intent(this, LogInActivity.class);
            startActivity(tmp);
        });

    }

    public void setupSignUpListener(){
        Button signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener((v) -> {
            Intent tmp = new Intent(this, SignUpActivity.class);
            startActivity(tmp);
        });

    }
}