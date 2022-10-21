package com.example.mzmotors_admin;

import android.content.Intent;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Intent i;
    Button btnlogin;
    EditText editEmail, editPassword;

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
                validarUsuario("https://ochoarealestateservices.com/mzmotors_api/db/validation_admin.php");
            }
        });

    }

    private void validarUsuario(String URL)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            if(response.equals("no_email"))
            {
                Toast.makeText(MainActivity.this, "Ingrese un email", Toast.LENGTH_SHORT).show();
            }
            if(response.equals("empty_password"))
            {
                Toast.makeText(MainActivity.this, "Ingrese una contraseña", Toast.LENGTH_SHORT).show();
            }
            else if(response.equals("empty_data"))
            {
                Toast.makeText(MainActivity.this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
            }

            else if(response.equals("1"))
            {
                Toast.makeText(MainActivity.this, "Inicio exitoso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), HomeAdmin.class);
                startActivity(intent);
            }
            else if(response.equals("no_verif"))
            {
                Toast.makeText(MainActivity.this, "Email o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            String text = error.toString();
            Log.e("error",text);
        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.put("email", editEmail.getText().toString());
            parametros.put("password", editPassword.getText().toString());
            return parametros;
        }
    };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}