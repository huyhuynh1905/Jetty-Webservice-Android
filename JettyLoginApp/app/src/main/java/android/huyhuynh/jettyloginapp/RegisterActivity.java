package android.huyhuynh.jettyloginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edtEmailRg, edtNamerg, edtPhoneRg, edtPassRg;
    Button btnRegisterRg;
    final String email_pattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtEmailRg = findViewById(R.id.edtEmailRg);
        edtNamerg = findViewById(R.id.edtNameRg);
        edtPhoneRg = findViewById(R.id.edtPhoneRg);
        edtPassRg = findViewById(R.id.edtPassRg);
        btnRegisterRg = findViewById(R.id.btnRegisterRg);

        //
        Toolbar toolbar = findViewById(R.id.bgHeadder);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        controlRegister();
    }

    private void controlRegister() {
        btnRegisterRg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email  = edtEmailRg.getText().toString().trim();
                String name = edtNamerg.getText().toString().trim();
                String phone = edtPhoneRg.getText().toString().trim();
                String pass = edtPassRg.getText().toString().trim();
                if (email.length()==0 | name.length()==0 | phone.length()==0 | pass.length()==0){
                    Toast.makeText(RegisterActivity.this,"Vui lòng điền đầy đủ thông tin đăng nhập"
                            ,Toast.LENGTH_LONG).show();
                } else if (!email.matches(email_pattern)){
                    Toast.makeText(RegisterActivity.this,"Vui lòng nhập đúng định dạng email"
                            ,Toast.LENGTH_LONG).show();
                } else if (!name.matches("^[a-z A-Z]{1,50}$")){
                    Toast.makeText(RegisterActivity.this,"Tên chỉ bao gồm chữ cái!"
                            ,Toast.LENGTH_LONG).show();
                } else if (!phone.matches("0+([0-9]{9})\\b")){
                    Toast.makeText(RegisterActivity.this,"Vui lòng nhập đúng định dạng số điện thoại"
                            ,Toast.LENGTH_LONG).show();
                } else if (pass.length()<8){
                    Toast.makeText(RegisterActivity.this,"Mật khẩu phải có ít nhất 8 kí tự"
                            ,Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegisterActivity.this,"OK!"
                            ,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
