package fall2018.csc2017.slidingtiles;

import android.accounts.AccountsException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    private UserManager users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        users = new UserManager(this);
        setupSignUpListener();
    }

    public void setupSignUpListener() {
        Button signUp = findViewById(R.id.signupa);
        signUp.setOnClickListener((v) -> {
            String userName = ((EditText)findViewById(R.id.usernamea)).getText().toString();
            String passWord = ((EditText)findViewById(R.id.passworda)).getText().toString();
            TextView textBox = findViewById(R.id.textboxa);
            try{
                users.signUp(userName, passWord);
            }
            catch (DuplicateException e) {
                textBox.setText("This username has been registered.");
                return;
            }
            catch (AccountsException e) {
                textBox.setText("Please fill in your username.");
                return;
            }
            catch (NoPassWordException e) {
                textBox.setText("Please fill in your password.");
                return;
            }
//
//            }
//            if(users.hasAccount(userName) != -1) {
//                textBox.setText("This username has been registered.");
//            }
//            else {
//                if (userName.length() < 4){
//                  textBox.setText("Username should have at least 4 characters.");
//                }
//                else if (passWord.length() < 5) {
//                    textBox.setText("Password should have at least 5 characters.");
//                }
//                else{
//                users.signUp(userName, passWord);
            textBox.setText("Sign up Successfully!");
            Intent tmp = new Intent(this, LogInActivity.class);
            startActivity(tmp);
        });

    }
}

