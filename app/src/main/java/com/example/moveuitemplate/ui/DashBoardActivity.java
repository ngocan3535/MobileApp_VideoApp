package com.example.moveuitemplate.ui;


import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moveuitemplate.R;

public class DashBoardActivity extends AppCompatActivity {
    Button LogOut, HomePage;
    TextView UserShow;
    String UserHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        anhxa();
        nhanDuLieu();
        EventButton();
    }

    private void EventButton() {
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(DashBoardActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(DashBoardActivity.this, "Đăng xuất thành công!", Toast.LENGTH_LONG).show();
            }
        });

        HomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Truyền id_user từ dashboar -> mainactivity
                Intent intent = new Intent(DashBoardActivity.this, MainActivity.class);
                intent.putExtra("UserString", UserHolder);
                startActivity(intent);
            }
        });
    }


    private void nhanDuLieu() {
        //Nhận ID user từ LoginActivity -> DashBoardActivity
        Intent loginToDashboard = getIntent();
        UserHolder = loginToDashboard.getStringExtra("UserString");
        UserShow.setText(UserHolder);

        //Nhận ID user từ MainActivity -> DashBoardActivity
        Intent mainActivityToDashboard = getIntent();
        UserHolder = mainActivityToDashboard.getStringExtra("UserString");
        UserShow.setText(UserHolder);

    }

    private void anhxa() {
        LogOut = findViewById(R.id.bt_logout);
        UserShow = findViewById(R.id.UserShow);
        HomePage = findViewById(R.id.bt_homePage);
    }
}