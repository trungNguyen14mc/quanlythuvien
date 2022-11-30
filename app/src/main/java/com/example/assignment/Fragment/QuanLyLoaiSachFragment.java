package com.example.assignment.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import com.example.assignment.Adapter.AdapterLoaiSach;
import com.example.assignment.DAO.DAO_LS;
import com.example.assignment.Model.LoaiSach;
import com.example.assignment.R;

public class QuanLyLoaiSachFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton fl_btn_them_ls;
    ArrayList<LoaiSach> arrayList;
    AdapterLoaiSach adapterLoaiSach;
    DAO_LS cn;

    public QuanLyLoaiSachFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate (R.layout.fragment_quan_ly_loai_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        recyclerView = view.findViewById (R.id.recy_loai_sach);
        fl_btn_them_ls = view.findViewById (R.id.fl_btn_them_ls);
        arrayList = new ArrayList<> ();
        cn = new DAO_LS (getActivity ());
        adapterLoaiSach = new AdapterLoaiSach (getActivity (),arrayList,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager (getActivity (),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager (layoutManager);
        recyclerView.setAdapter (adapterLoaiSach);



        fl_btn_them_ls.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                DialogThemLS ();
            }
        });
    }

    public void DialogThemLS(){
        Dialog dialog = new Dialog (getActivity ());
        dialog.setTitle ("Thêm loại sách");
        dialog.setContentView (R.layout.dia_log_them_loai_sach);
        dialog.show ();

        EditText ed_ma_loai = dialog.findViewById (R.id.ed_ma_loai);
        EditText ed_ten_loai = dialog.findViewById (R.id.ed_ten_loai);
        ImageView img_them_ls = dialog.findViewById (R.id.img_them_ls);
        ImageView img_huy_ls = dialog.findViewById (R.id.img_huy_ls);


        img_huy_ls.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                dialog.dismiss ();
            }
        });
        img_them_ls.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if(ed_ma_loai.getText ().toString ().isEmpty ()){
                    Toast.makeText (getActivity (), "Vui lòng nhập mã loại", Toast.LENGTH_SHORT).show ();
                }else if(ed_ten_loai.getText ().toString ().isEmpty ()){
                    Toast.makeText (getActivity (), "Vui lòng nhập tên loại", Toast.LENGTH_SHORT).show ();
                    return;
                }

                int maloai = Integer.parseInt (ed_ma_loai.getText ().toString ());
                String ten_loai = ed_ten_loai.getText ().toString ();
                LoaiSach ls = new LoaiSach (maloai,ten_loai);
                arrayList.add (ls);
                adapterLoaiSach.notifyDataSetChanged ();
                cn.ThemLS (ls);
                Toast.makeText (getActivity (), "Thêm thành công ", Toast.LENGTH_SHORT).show ();
            }
        });

    }
    public void Delete(int id){
        cn.DeleteLS (id);
    }
    public void Update(LoaiSach ls){
        cn.UpdateLS (ls);
    }

    @Override
    public void onResume() {
        super.onResume ();
        arrayList.clear ();
        arrayList.addAll (cn.GetLS ());
        if(arrayList != null){
            adapterLoaiSach.notifyDataSetChanged ();
        }
    }
}