package com.example.moveuitemplate.ui;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.moveuitemplate.R;
import com.example.moveuitemplate.utils.CheckConnection;
import com.example.moveuitemplate.utils.HttpParse;
import com.example.moveuitemplate.utils.Server;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    EditText edtUserLogin, edtPasswordLogin;
    Button btnLogin2, btnSignUp2;

    String urlLogin = Server.urlLogin;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    ProgressDialog progressDialog;
    Boolean CheckEditText ;
    public static final String UserString = "";
    String finalResult ;
    public String UserHolder, PasswordHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AndXa();

        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            EventButton();
        } else {
            CheckConnection.ShowToast(getApplicationContext(), "Kiểm tra lại kết nối!");
        }
    }

    private void EventButton() {
        btnLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){
                    UserLoginFunction(UserHolder, PasswordHolder);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Nhập đầy đủ thông tin đăng nhập", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSignUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);

            }
        });

    }

    public void CheckEditTextIsEmptyOrNot(){

        UserHolder = edtUserLogin.getText().toString();
        PasswordHolder = edtPasswordLogin.getText().toString();

        if(TextUtils.isEmpty(UserHolder) || TextUtils.isEmpty(PasswordHolder))
        {
            CheckEditText = false;
        }
        else {

            CheckEditText = true ;
        }
    }


    public void UserLoginFunction(final String user, final String password){

        class UserLoginClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(LoginActivity.this,"Loading Data...",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                if(httpResponseMsg.equalsIgnoreCase("Data Matched")){
                    finish();
                    //Truyền ID user từ LoginActivity -> DashBoardActivity
                    Intent intent = new Intent(LoginActivity.this, DashBoardActivity.class);
                    intent.putExtra("UserString", user);
                    startActivity(intent);
                    //Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                }
                else if (httpResponseMsg.equalsIgnoreCase("Wrong Data")) {
                    Log.d(" - Kiem tra login: ", "*** Error at method onPostExecute - LoginActivity! " +
                            "\nSai thong tin dang nhap!!!");

                    Toast.makeText(LoginActivity.this, "Sai thông tin đăng nhập!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.d(" - Kiem tra login: ", "*** Error at method onPostExecute - LoginActivity! " +
                            "\nKhong co ket noi!!!");
                    Toast.makeText(LoginActivity.this, "Kiểm tra lại kết nối!!!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("tendangnhap",params[0]);
                hashMap.put("matkhau",params[1]);
//                hashMap.put("tendangnhap",user);
//                hashMap.put("matkhau",password);

                finalResult = httpParse.postRequest(hashMap, urlLogin);

                return finalResult;
            }
        }

        UserLoginClass userLoginClass = new UserLoginClass();

        userLoginClass.execute(user,password);
    }

    private void AndXa() {
        edtUserLogin = findViewById(R.id.edt_userLogin);
        edtPasswordLogin = findViewById(R.id.edt_passwordLogin);
        btnLogin2 = findViewById(R.id.btn_login2);
        btnSignUp2 = findViewById(R.id.btn_signUp2);
    }
}