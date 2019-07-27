package android.huyhuynh.jettyloginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    EditText edtEmailRg, edtNamerg, edtPhoneRg, edtPassRg;
    Button btnRegisterRg;

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
