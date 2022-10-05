package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnXacNhan;
    EditText edtUsername, edtPassWord;
    CheckBox cbRemember;

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
        sharedPreferences = getSharedPreferences("dataLogin",MODE_PRIVATE);
//     lấy giá trị sharedPreferences
        edtUsername.setText(sharedPreferences.getString("taikhoan",""));
        edtPassWord.setText(sharedPreferences.getString("matkhau", ""));
        cbRemember.setChecked(sharedPreferences.getBoolean("checked", false));

        Anhxa();

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassWord.getText().toString().trim();

                if (username.equals("tuananh") && password.equals("123456"))
                {
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                    nếu có check
                    if (cbRemember.isChecked()){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("taikhoan", username);
                        editor.putString("matkhau", password);
                        editor.putBoolean("checked",true);
                        editor.commit();
                    }else{
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("taikhoan");
                        editor.remove("matkhau");
                        editor.remove("checked");
                        editor.commit();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Lỗi đang nhập", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private void  Anhxa(){
        btnXacNhan = (Button) findViewById(R.id.buttonXacNhan);
        edtPassWord = (EditText) findViewById(R.id.editTextPassword);
        edtUsername = (EditText) findViewById(R.id.editTextUsename);
        cbRemember = (CheckBox) findViewById(R.id.checkBoxRemember);
    }
}