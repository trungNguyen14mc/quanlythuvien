package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.DAO.DAO_TT;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    Button btnSign;
    TextInputEditText ed_tk , ed_mk;
    CheckBox cb_savemk;
    DAO_TT cn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnSign = this.findViewById (R.id.btn_dang_nhap);
        ed_tk = this.findViewById (R.id.ed_tai_khoan_dn);
        ed_mk = this.findViewById (R.id.ed_mat_khau_dn);
        cb_savemk = this.findViewById (R.id.cb_save_mk);
        cn = new DAO_TT (this);
        Intent intent = new Intent (LoginActivity.this,MainActivity.class);
        SharedPreferences preferences=getSharedPreferences("USER_FILE",MODE_PRIVATE);
        ed_tk.setText(preferences.getString("username",""));
        ed_mk.setText(preferences.getString("pw",""));
        cb_savemk.setChecked(preferences.getBoolean("remember",false));
        //login
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tk = ed_tk.getText ().toString ();
                String mk = ed_mk.getText ().toString ();

                if(tk.equals("")||mk.equals("")){
                                        Toast.makeText (LoginActivity.this, "Vui lòng nhập tên tài khoản và mật khẩu", Toast.LENGTH_SHORT).show ();
                }
                else {
                    boolean kt = cn.kiemTraLogin (tk,mk);
                    if(kt){
                        remembeUser(tk,mk,cb_savemk.isChecked());
                        intent.putExtra("user",ed_tk.getText().toString());
                        startActivity (intent);
                        Toast.makeText (LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show ();
                    }else{
                        Toast.makeText (LoginActivity.this, "Vui lòng kiểm tra lại tài khoản và mật khẩu ", Toast.LENGTH_SHORT).show ();
                    }
                }
            }
        });

    }
    public void remembeUser(String u, String p, boolean status){
        SharedPreferences preferences=getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        if(!status){
            editor.clear();

        }else {
            editor.putString("username",u);
            editor.putString("pw",p);
            editor.putBoolean("remember",status);
        }
        editor.commit();
    }
}