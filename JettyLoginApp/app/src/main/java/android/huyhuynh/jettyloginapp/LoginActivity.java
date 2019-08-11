package android.huyhuynh.jettyloginapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    ImageButton imgRegister;
    EditText edtMail, edtPass;
    String urlServer = "http://192.168.1.102:7070/jetty/android/login";
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

                    //Intent intent = new Intent(LoginActivity.this,DetailUser.class);
                    //startActivity(intent);
                    Log.d("Login","Đã bấm");
                    requestLogin(email,pass);
                }
            }
        });
    }

    private void requestLogin(String email, String pass) {
        HashMap data = new HashMap();
        data.put("email",email);
        data.put("pass",pass);
        JSONObject jsonObject = new JSONObject(data);
        Log.d("Login",jsonObject.toString());
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlServer,
                jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String name = response.getString("name");
                    String email = response.getString("email");
                    String phone = response.getString("phone");
                    if (!email.equals("Không tồn tại tài khoản!")) {
                        Bundle bundle = new Bundle();
                        bundle.putString("name", name);
                        bundle.putString("email", email);
                        bundle.putString("phone", phone);
                        Intent intent = new Intent(LoginActivity.this, DetailUser.class);
                        intent.putExtra("dataBundle", bundle);
                        startActivity(intent);
                    } else {
                        thongBao(email);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Login",error.toString());
            }
        });

        requestQueue.add(jsonObjectRequest);
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
    private void thongBao(String mess) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Messages");
        builder.setMessage(mess);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}
