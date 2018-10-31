package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LogInActivity extends AppCompatActivity{

    private UserManager users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        users = new UserManager(this);
        setupSignInListener();
    }

    public void setupSignInListener() {
        Button signIn = findViewById(R.id.signina);
        signIn.setOnClickListener((v) -> {
            String userName = ((EditText)findViewById(R.id.usernamea)).getText().toString();
            String passWord = ((EditText)findViewById(R.id.passworda)).getText().toString();
            TextView textBox = findViewById(R.id.textboxa);
            if(users.hasAccount(userName) == -1) {
                textBox.setText("Username is not found.");

            }
            else {
                if(users.signIn(userName, passWord)){
                    textBox.setText("Sign In Sucessfully!");
                    Intent tmp = new Intent(this, LaunchCenterActivity.class);
                    startActivity(tmp);
                }
                else{textBox.setText("Incorrect Password.");}
            }
        });
    }
}