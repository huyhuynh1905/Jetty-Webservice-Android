package android.huyhuynh.jettyloginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    ImageButton imgRegister;
    EditText edtMail, edtPass;
    final String email_pattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLoginLg);
        imgRegister = findViewById(R.id.btnRegisterLg);
        edtMail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);


        imgRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //
        Toolbar toolbar = findViewById(R.id.tbLogin);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        controlLogin();
    }

    private void controlLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtMail.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                if (email.length()==0 | pass.length()==0){
                    Toast.makeText(LoginActivity.this,"Vui lòng điền đầy đủ thông tin đăng nhập"
                    ,Toast.LENGTH_LONG).show();
                } else if (!email.matches(email_pattern)){
                    Toast.makeText(LoginActivity.this,"Bạn nhập sai định dạng email!",Toast.LENGTH_LONG).show();
                } else if (pass.length()<8){
                    Toast.makeText(LoginActivity.this,"Mật khẩu phải có ít nhất 8 kí tự!"
                            ,Toast.LENGTH_LONG).show();
                } else {
                    //Trường hợp đăng nhập thành công
                    Toast.makeText(LoginActivity.this,"OK!"
                            ,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this,DetailUser.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
