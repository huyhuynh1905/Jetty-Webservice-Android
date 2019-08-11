package android.huyhuynh.jettyloginapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

public class DetailUser extends AppCompatActivity {
    TextView txtHoten, txtEmail, txtPhone;
    Button btnDel, btnLogout;
    String deleteUrl = "http://192.168.1.102:7070/jetty/android/delete";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        //Ánh xạ
        txtHoten = findViewById(R.id.txthoten);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        btnDel = findViewById(R.id.btnDel);
        btnLogout = findViewById(R.id.btnLogout);

        final Bundle bundle = getIntent().getBundleExtra("dataBundle");
        txtHoten.setText(bundle.getString("name"));
        txtEmail.setText(bundle.getString("email"));
        txtPhone.setText(bundle.getString("phone"));

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoaTaiKhoan(bundle.getString("email"));
            }
        });
    }

    private void xoaTaiKhoan(String email) {
        HashMap data = new HashMap();
        data.put("id",0);
        data.put("email",email);
        data.put("name","");
        data.put("phone","");
        data.put("pass","");
        JSONObject jsonObject = new JSONObject(data);
        RequestQueue requestQueue = Volley.newRequestQueue(DetailUser.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, deleteUrl,
                jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String mess = response.getString("email");
                    thongBao(mess);
                } catch (JSONException e) {
                    Toast.makeText(DetailUser.this,"Error Json!"
                            ,Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    private void thongBao(String mess) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Messages");
        builder.setMessage(mess);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(DetailUser.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}
