package com.example.assignment.Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import com.example.assignment.Adapter.AdapterSach;
import com.example.assignment.DAO.DAO_SACH;
import com.example.assignment.Model.Sach;
import com.example.assignment.R;

public class QuanLySachFragment extends Fragment {


    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    ArrayList<Sach> arrayList ;
    AdapterSach adapterSach;
    DAO_SACH cn;
    public QuanLySachFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate (R.layout.fragment_quan_ly_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        recyclerView = view.findViewById (R.id.recy_view_sach);
        floatingActionButton = view.findViewById (R.id.fl_btn_them_sach);
        arrayList = new ArrayList<> ();
        cn = new DAO_SACH (getContext ());
        adapterSach = new AdapterSach (getContext (),arrayList,this);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext (),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager (layoutManager);
        recyclerView.setAdapter (adapterSach);

        floatingActionButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                DiaLogThem ();
            }
        });

    }

    public void DiaLogThem(){
        AlertDialog.Builder builder = new AlertDialog.Builder (getContext ());
        View view = LayoutInflater.from (getContext ()).inflate (R.layout.dia_log_them_sach,null);
        builder.setView (view);

        TextInputEditText ed_masach = view.findViewById (R.id.ed_ma_sach);
        TextInputEditText ed_ten_sach = view.findViewById (R.id.ed_ten_Sach);
        TextInputEditText ed_gia_thue = view.findViewById (R.id.ed_gia_thue);
        Button btn_them_sach = view.findViewById (R.id.btn_them_sach);
        Button btn_huy = view.findViewById (R.id.btn_huy_sach);
        Dialog dialog = builder.create ();

        btn_them_sach.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if(ed_masach.getText ().toString ().isEmpty ()){
                    Toast.makeText (getActivity (), "Vui lòng nhập mã sách", Toast.LENGTH_SHORT).show ();
                }else if(ed_ten_sach.getText ().toString ().isEmpty ()){
                    Toast.makeText (getActivity (), "Vui lòng nhập tên sách", Toast.LENGTH_SHORT).show ();
                }else if(ed_gia_thue.getText ().toString ().isEmpty ()){
                    Toast.makeText (getActivity (), "Vui lòng nhập giá thuê", Toast.LENGTH_SHORT).show ();
                    return;
                }
                Sach sach = new Sach ();
                sach.setMaSach (Integer.parseInt (ed_masach.getText ().toString ()));
                sach.setTenSach (ed_ten_sach.getText ().toString ());
                sach.setGiaThue (Integer.parseInt (ed_gia_thue.getText ().toString ()));
                arrayList.add (sach);
                adapterSach.notifyDataSetChanged ();
                cn.ThemSach(sach);
                Toast.makeText (getActivity (), "Thêm thành công", Toast.LENGTH_SHORT).show ();

            }
        });
        btn_huy.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                dialog.dismiss ();
            }
        });

        dialog.show ();
    }

    public void Delete(int id){
        cn.DeleteSach (id);
    }
    public void Update(Sach sach){
        cn.UpdateSach (sach);
    }
    @Override
    public void onResume() {
        super.onResume ();
        arrayList.clear ();
        arrayList.addAll (cn.GetSach ());
        if(arrayList != null){
            adapterSach.notifyDataSetChanged ();
        }
    }
}