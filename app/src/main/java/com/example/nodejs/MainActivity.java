package com.example.nodejs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText password,username;
    Button login;
    TextView newacc;
    String pass,user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init()
    {
        password=findViewById(R.id.password);
        username=findViewById(R.id.username);
        login=findViewById(R.id.login);
        newacc=findViewById(R.id.newacc);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass=password.getText().toString();
                user=username.getText().toString();
                String url="http://192.168.29.32:3000/show";
                Map <String,String> parmas=new HashMap<>();
                parmas.put("name",user);
                parmas.put("pass",pass);
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url,new JSONObject(parmas),new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText(MainActivity.this, response.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "no data", Toast.LENGTH_SHORT).show();
                            }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

               
                RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);


            }
        });
    }
}
