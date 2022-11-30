package com.example.assignment.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.example.assignment.Adapter.AdapterPhieuMuon;
import com.example.assignment.DAO.DAO_PM;
import com.example.assignment.Model.PhieuMuon;
import com.example.assignment.R;


public class QuanlyPmFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    AdapterPhieuMuon adapterPhieuMuon;
    DAO_PM cn;
    ArrayList<PhieuMuon> arrayList;
    public QuanlyPmFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate (R.layout.fragment_quan_ly_phieu_muon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        recyclerView = view.findViewById (R.id.recy_phieuMuon);
        floatingActionButton = view.findViewById (R.id.fl_them_phieuMuon);
        arrayList = new ArrayList<> ();
        cn = new DAO_PM (getContext ());
        adapterPhieuMuon = new AdapterPhieuMuon (getContext (),arrayList,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager (getContext (),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager (layoutManager);
        recyclerView.setAdapter (adapterPhieuMuon);

        floatingActionButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                DiaLogThem ();
            }
        });
    }
    public void DiaLogThem(){
        AlertDialog.Builder builder = new AlertDialog.Builder (getContext ());
        View view = LayoutInflater.from (getContext ()).inflate (R.layout.dia_log_them_phieu_muon,null);
        builder.setView (view);

        TextInputEditText ed_ma_pm = view.findViewById (R.id.ed_ma_phieu_muon);
        TextInputEditText ed_ngay = view.findViewById (R.id.ed_ngay_tra);
        Button btn_chon_ngay = view.findViewById (R.id.btn_chon_ngay_pm);
        RadioButton rb_da_tra = view.findViewById (R.id.rb_daTra);
        RadioButton rb_chua_tra = view.findViewById (R.id.rb_chua_);
        TextInputEditText ed_tien_thue = view.findViewById (R.id.ed_tien_thue);
        TextInputEditText ed_ma_Sach = view.findViewById (R.id.ed_ma_sach_pm);
        Button btn_them_pm = view.findViewById (R.id.btn_them_pm);
        Button btn_thoat = view.findViewById (R.id.btn_thoat_pm);

        Dialog dg = builder.create ();

        Calendar calendar = Calendar.getInstance();//Lay time
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(calendar.DAY_OF_MONTH);
        btn_chon_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        ed_ngay.setText( y+"-" + (m + 1) + "-"+d );
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        btn_them_pm.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (ed_ma_pm.getText ().toString ().isEmpty () && ed_ma_Sach.getText ().toString ().isEmpty ()){
                    Toast.makeText (getActivity (), "Vui lòng nhập mã phiếu mượn", Toast.LENGTH_SHORT).show ();
                }else if(ed_ngay.getText ().toString ().isEmpty ()){
                    Toast.makeText (getActivity (), "Vui lòng nhập ngày", Toast.LENGTH_SHORT).show ();
                }else if(rb_da_tra.isChecked () != true && rb_chua_tra.isChecked () != true ){
                    Toast.makeText (getActivity (), "Vui lòng trọn trạng thái", Toast.LENGTH_SHORT).show ();
                }else if(ed_tien_thue.getText ().toString ().isEmpty ()){
                    Toast.makeText (getActivity (), "Vui lòng nhập tiền thuê", Toast.LENGTH_SHORT).show ();
                    return;
                }
                PhieuMuon pm = new PhieuMuon ();
                pm.setMaPM (Integer.parseInt (ed_ma_pm.getText ().toString ()));
                pm.setNgay (ed_ngay.getText ().toString ());
                pm.setMaSach (Integer.parseInt (ed_ma_Sach.getText ().toString ()));
                pm.setTraSach (rb_da_tra.isChecked () ? "Đã trả" : "Chưa trả");
                pm.setTienThue (Integer.parseInt (ed_tien_thue.getText ().toString ()));
                arrayList.add (pm);
                adapterPhieuMuon.notifyDataSetChanged ();
                cn.ThemPM (pm);
                Toast.makeText (getActivity (),"Thêm thành công",Toast.LENGTH_LONG).show ();

            }
        });
        btn_thoat.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                dg.dismiss ();
            }
        });

        dg.show ();
    }

    public void Delete(int id){
        cn.Delete (id);
    }
    public void Update(PhieuMuon pm){
        cn.UpdatePM (pm);
    }
    @Override
    public void onResume() {
        super.onResume ();
        arrayList.clear ();
        arrayList.addAll (cn.GetPM());
        if(arrayList != null){
            adapterPhieuMuon.notifyDataSetChanged ();
        }
    }
}