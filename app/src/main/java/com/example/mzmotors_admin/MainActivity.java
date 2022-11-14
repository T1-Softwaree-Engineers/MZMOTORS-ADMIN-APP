package com.example.mzmotors_admin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Intent i;
    Button btnlogin;
    EditText editEmail, editPassword;
    String email, password, key;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTheme(R.style.Theme_MZMOTORSADMIN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editEmail = findViewById(R.id.emailUser);
        editPassword = findViewById(R.id.passwordUser);

        btnlogin = findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = editEmail.getText().toString();
                password = editPassword.getText().toString();
                key = "9137462850";

                if (email.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Ingrese un email valido",
                            Toast.LENGTH_SHORT).show();
                }
                else if (password.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Ingrese una contraseña",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    validarUsuario("https://ochoarealestateservices.com/mzmotors/admin.php?email="+email+"&pwd="+password+"&key="+key);
                }
            }
        });

    }

    private void validarUsuario(String URL)
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL ,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = response.getJSONObject(0);
                    String uName = jsonObject.getString("nombre");
                    String uEmail = jsonObject.getString("email");
                    String uPhone = jsonObject.getString("contacto");
                    Toast.makeText(MainActivity.this, "Inicio Exitoso", Toast.LENGTH_SHORT).show();
                    guardarSession(uName,uEmail,uPhone);
                    startActivity(new Intent(MainActivity.this, HomeAdmin.class));
                    finish();
                }catch (JSONException e){
                    Toast.makeText(MainActivity.this, "Usuario o Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue2 = Volley.newRequestQueue(this);
        requestQueue2.add(jsonArrayRequest);

    }

    private void guardarSession(String uName, String uEmail, String uPhone) {
        SharedPreferences datosU = getSharedPreferences("sessionUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=datosU.edit();
        editor.putString("nombre",uName);
        editor.putString("email",uEmail);
        editor.putString("phone",uPhone);
        editor.putBoolean("session",true);
        editor.commit();
    }
}