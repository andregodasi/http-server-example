package com.example.httpjsonserver;

import androidx.appcompat.app.AppCompatActivity;

import com.example.httpjsonserver.model.CEP;
import com.example.httpjsonserver.service.HttpService;
import com.google.gson.Gson;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (android.os.Build.VERSION.SDK_INT > 16)
//        {
//            StrictMode.ThreadPolicy policy = new
//                    StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }
        final EditText cep = findViewById(R.id.et_cep);
        final TextView resposta = findViewById(R.id.tv_res);

        Button btnBuscarCep = findViewById(R.id.bt_buscar);
        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CEP retorno =  new HttpService(cep.getText().toString()).execute().get();
                    resposta.setText(retorno.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }



    public static CEP getAddress(String cep) throws Exception {
        StringBuilder resposta = new StringBuilder();
        try {
            URL url = new URL("http://viacep.com.br/ws/" + cep + "/json/");
            System.out.println(url.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            con.setConnectTimeout(5000);
            con.connect();

            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                resposta.append(scanner.next());
            }
        } catch (Exception e) {
            return null;
        }

        return new Gson().fromJson(resposta.toString(), CEP.class);
    }

}
