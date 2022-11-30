package com.example.assignment.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import com.example.assignment.DAO.DAO_TT;
import com.example.assignment.Model.ThuThu;
import com.example.assignment.R;


public class DoiMatKhauFragment extends Fragment {


    TextInputEditText ed_mk_cu,ed_mk_moi, ed_nhap_lai_mk_moi;
    Button btn_doi_mk;
    DAO_TT cn;

    public DoiMatKhauFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate (R.layout.fragment_doi_mat_khau, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        ed_mk_cu = view.findViewById (R.id.ed_mk_cu);
        ed_mk_moi = view.findViewById (R.id.ed_update_mk_moi);
        ed_nhap_lai_mk_moi = view.findViewById (R.id.ed_nhap_lai_mk_moi);
        btn_doi_mk = view.findViewById (R.id.btn_doi_mk_tt);
        cn = new DAO_TT (getContext ());
        btn_doi_mk.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if(ed_mk_cu.getText ().toString ().isEmpty ()){
                    Toast.makeText (getActivity (), "Vui lòng nhập mật khẩu cũ", Toast.LENGTH_SHORT).show ();
                }else if(ed_mk_moi.getText ().toString ().isEmpty ()){
                    Toast.makeText (getActivity (),"Vui lòng nhập mật khẩu mới ",Toast.LENGTH_LONG).show ();
                }
                else if(ed_nhap_lai_mk_moi.getText ().toString ().isEmpty ())
                {
                    Toast.makeText (getActivity (), "Vui lòng xác nhận mât khẩu mới", Toast.LENGTH_SHORT).show ();
                }else {
                    String mkcu = ed_mk_cu.getText ().toString ();
                    String mkmoi = ed_mk_moi.getText ().toString ();
                    String nhap_lai_mkmoi = ed_nhap_lai_mk_moi.getText ().toString ();
                    if(nhap_lai_mkmoi.equals (mkmoi)){
                        boolean kt = cn.kiemTraMk (mkcu);
                        if(kt){
                            ThuThu tt = new ThuThu ();
                            tt.setMatKhau (mkmoi);
                            cn.UpdateTT (tt);
                            Toast.makeText (getActivity (), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show ();
                            ed_mk_cu.setText ("");
                            ed_mk_moi.setText ("");
                            ed_nhap_lai_mk_moi.setText ("");
                        }

                        }
                }
            }
        });
    }
}