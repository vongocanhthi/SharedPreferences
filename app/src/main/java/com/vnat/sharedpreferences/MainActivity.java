package com.vnat.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtTaiKhoan, edtMatKhau;
    CheckBox chkGhiNho;
    Button btnDangNhap;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        event();
    }

    private void event() {
        edtTaiKhoan.setText(sharedPreferences.getString("taikhoan",""));
        edtMatKhau.setText(sharedPreferences.getString("matkhau",""));
        chkGhiNho.setChecked(sharedPreferences.getBoolean("ghinho",false));

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtTaiKhoan.getText().toString().equals("admin") && edtMatKhau.getText().toString().equals("123")){
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    GhiNho();
                }else{
                    Toast.makeText(MainActivity.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void GhiNho() {
        String taikhoan = edtTaiKhoan.getText().toString();
        String matkhau = edtMatKhau.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (chkGhiNho.isChecked()){
            editor.putString("taikhoan", taikhoan);
            editor.putString("matkhau", matkhau);
            editor.putBoolean("ghinho", true);
            editor.commit();
        }else{
            editor.clear();
            editor.commit();
        }
    }

    private void init() {
        edtTaiKhoan = findViewById(R.id.edtTaiKhoan);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        chkGhiNho = findViewById(R.id.chkGhiNho);
        btnDangNhap = findViewById(R.id.btnDangNhap);

        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
    }
}
