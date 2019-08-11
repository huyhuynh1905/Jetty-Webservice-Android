package android.huyhuynh.jettyloginapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    EditText edtEmailRg, edtNamerg, edtPhoneRg, edtPassRg;
    Button btnRegisterRg;
    String urlRegister = "http://192.168.1.102:7070/jetty/android/register";
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
                    dangKiTaiKhoan(email,name,phone,pass);
                }
            }
        });
    }

    private void dangKiTaiKhoan(String email, String name, String phone, String pass) {
        HashMap data = new HashMap();
        data.put("id",0);
        data.put("email",email);
        data.put("name",name);
        data.put("phone",phone);
        data.put("pass",pass);
        JSONObject jsonObject = new JSONObject(data);
        RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlRegister,
                jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String mess = response.getString("email");
                    thongBao(mess);
                } catch (JSONException e) {
                    Toast.makeText(RegisterActivity.this,"Not OK!"
                            ,Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this,error.toString()
                        ,Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void thongBao(String mess) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Messages");
        builder.setMessage(mess);
        builder.setNegativeButton("Login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setCancelable(false);
        builder.show();
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
