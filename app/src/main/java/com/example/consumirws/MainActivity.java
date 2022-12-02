package com.example.consumirws;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText txtUser, txtTitle, txtBody;
    Button btnEnviar, btnObtener, btnActualizar,btnLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUser = findViewById(R.id.txtUser);
        txtTitle = findViewById(R.id.txtTitle);
        txtBody = findViewById(R.id.txtBody);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnObtener = findViewById(R.id.btnObtener);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        btnActualizar = findViewById(R.id.btnActualizar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarWS(txtTitle.getText().toString(), txtBody.getText().toString(), txtUser.getText().toString());
            }
        });

        btnObtener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leerWS();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar(txtTitle.getText().toString(), txtBody.getText().toString(), txtUser.getText().toString());
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });

    }

    private void leerWS(){

        String url = getString(R.string.urlGET);
        //Definicion de la peticion
        StringRequest postRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    txtUser.setText(jsonObject.getString("userId"));
                    //puedo recibirlo en variables, dependiendo de como lo maneje
                    String title = jsonObject.getString("title");
                    txtTitle.setText(title);
                    txtBody.setText(jsonObject.getString("body"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        //Envio de la peticion
        Volley.newRequestQueue(this).add(postRequest);

    }

    private void enviarWS(final String title, final String body, final String userId){

        String url = getString(R.string.urlPOST);
        //Definicion de la peticion
        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    /*
                    txtUser.setText(jsonObject.getString("userId"));
                    //puedo recibirlo en variables, dependiendo de como lo maneje
                    String title = jsonObject.getString("title");
                    txtTitle.setText(title);
                    txtBody.setText(jsonObject.getString("body"));
                    */
                    Toast.makeText(MainActivity.this, "Resultado = " + response, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })

        {
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("title", title);
                params.put("body", body);
                params.put("userId", userId);
                return params;
            }
        };

        //Envio de la peticion
        Volley.newRequestQueue(this).add(postRequest);

    }

    private void actualizar(final String title, final String body, final String userId){
        String url = getString(R.string.urlUPDATE);
        //Definicion de la peticion
        StringRequest postRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    /*
                    txtUser.setText(jsonObject.getString("userId"));
                    //puedo recibirlo en variables, dependiendo de como lo maneje
                    String title = jsonObject.getString("title");
                    txtTitle.setText(title);
                    txtBody.setText(jsonObject.getString("body"));
                    */
                    Toast.makeText(MainActivity.this, "Resultado = " + response, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })

        {
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("id", "1");
                params.put("title", title);
                params.put("body", body);
                params.put("userId", userId);
                return params;
            }
        };

        //Envio de la peticion
        Volley.newRequestQueue(this).add(postRequest);
    }

    private void limpiar(){
        txtUser.setText("");
        txtTitle.setText("");
        txtBody.setText("");
    }

}