package com.example.assignment.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

import com.example.assignment.Adapter.AdapterThanhVien;
import com.example.assignment.DAO.DAO_TV;
import com.example.assignment.Model.ThanhVien;
import com.example.assignment.R;


public class QuanLyThanhVienFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton fl_btn_them;
    ArrayList<ThanhVien> arrayList;
    AdapterThanhVien adapterThanhVien;
    DAO_TV cn;
    public QuanLyThanhVienFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate (R.layout.fragment_quan_ly_thanh_vien, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        recyclerView = view.findViewById (R.id.recy_thanh_vien);
        fl_btn_them = view.findViewById (R.id.fl_btn_them);
        arrayList = new ArrayList<> ();
        cn = new DAO_TV (getContext ());
        adapterThanhVien = new AdapterThanhVien (getContext (),arrayList,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager (getContext (),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager (layoutManager);
        recyclerView.setAdapter (adapterThanhVien);
        fl_btn_them.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                DialogThem ();
            }
        });
    }
    public void DialogThem(){
        Dialog dialog = new Dialog (getContext ());
        dialog.setTitle ("Thêm Thành Viên");
        dialog.setContentView (R.layout.dia_log_them);
        dialog.show ();

        EditText ed_matv = dialog.findViewById (R.id.ed_ma_thanh_vien);
        EditText ed_ten_tv = dialog.findViewById (R.id.ed_ten_thanh_vien);
        EditText ed_namsinh = dialog.findViewById (R.id.ed_namsinh);
        MaterialButton btn_chon_ngay = dialog.findViewById (R.id.btn_chon_ngay_sinh);
        ImageView img_save = dialog.findViewById (R.id.img_save_tv);
        ImageView img_huy = dialog.findViewById (R.id.img_huy_tv);

        btn_chon_ngay.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance ();
                DatePickerDialog dialog1 = new DatePickerDialog (getActivity (), new DatePickerDialog.OnDateSetListener () {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        ed_namsinh.setText (year+"/" + month +"/" + dayOfMonth);

                    }
                },calendar.YEAR , calendar.MONTH, calendar.DATE);
                dialog1.show ();
            }
        });
        img_huy.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                dialog.dismiss ();
            }
        });
        img_save.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if(ed_matv.getText ().toString ().isEmpty ()){
                    Toast.makeText (getActivity (), "Vui lòng nhập mã thành viên", Toast.LENGTH_SHORT).show ();
                }else if(ed_ten_tv.getText ().toString ().isEmpty ()){
                    Toast.makeText (getActivity (), "Vui lòn nhập tên thành viên ", Toast.LENGTH_SHORT).show ();
                }else if(ed_namsinh.getText ().toString ().isEmpty ()){
                    Toast.makeText (getActivity (), "Vui lòng nhập ngày sinh", Toast.LENGTH_SHORT).show ();
                    return;
                }

                int matv = Integer.parseInt (ed_matv.getText ().toString ());
                String ten_tv = ed_ten_tv.getText ().toString ();
                String namSinh  = ed_namsinh.getText ().toString ();
                ThanhVien thanhVien = new ThanhVien (matv,ten_tv,namSinh);
                arrayList.add (thanhVien);
                adapterThanhVien.notifyDataSetChanged ();
                cn.ThemTV (thanhVien);
                Toast.makeText (getActivity (), "Thêm thành công", Toast.LENGTH_SHORT).show ();
            }
        });
    }
    public void Delete(int id){
        cn.DeleteTV (id);
    }
    public void Update(ThanhVien tv){
        cn.UpdateTV (tv);
    }

    @Override
    public void onResume() {
        super.onResume ();
        arrayList.clear ();
        arrayList.addAll (cn.GetTV ());
        if(arrayList != null){
            adapterThanhVien.notifyDataSetChanged ();
        }
    }
}