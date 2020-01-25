package com.example.httpjsonserver;

import androidx.appcompat.app.AppCompatActivity;

import com.example.httpjsonserver.model.Post;
import com.example.httpjsonserver.model.User;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {
    EditText etUser;
    EditText etPost;
    TextView tvJson;
    Button btnSubmitUser;
    Button btnSubmitPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser = findViewById(R.id.et_user);
        tvJson = findViewById(R.id.tv_json);

        btnSubmitUser = findViewById(R.id.bt_submit_user);
        btnSubmitUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    get(etUser.getText().toString(), "users", User.class, tvJson);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        etPost = findViewById(R.id.et_post);
        tvJson = findViewById(R.id.tv_json);

        btnSubmitPost = findViewById(R.id.bt_submit_post);
        btnSubmitPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    get(etPost.getText().toString(), "posts", Post.class, tvJson);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void get(String id, String pathParam, final Class className, final TextView res) {
        String url = "http://10.0.0.103:3000/" + pathParam + "/" + id;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("jsonresponse", response.toString());

                res.setText(new Gson().fromJson(response.toString(), className).toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

            }
        });
    }


}
