package com.example.assignment.Adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.Fragment.QuanlyPmFragment;
import com.example.assignment.Model.PhieuMuon;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.assignment.Fragment.QuanlyPmFragment;
import com.example.assignment.Model.PhieuMuon;
import com.example.assignment.R;

public class AdapterPhieuMuon extends RecyclerView.Adapter<AdapterPhieuMuon.PhieuMuonViewHodel>{
    private Context context;
    private List<PhieuMuon> list;
    private QuanlyPmFragment quanlyPmFragment;

    public AdapterPhieuMuon(Context context, ArrayList<PhieuMuon> list, QuanlyPmFragment quanlyPmFragment) {
        this.context = context;
        this.list = list;
        this.quanlyPmFragment = quanlyPmFragment;
    }
    @NonNull
    @Override
    public PhieuMuonViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.item_recy_view_phieu_muon,null);
        return new PhieuMuonViewHodel (view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhieuMuonViewHodel holder, int position) {
        PhieuMuon pm = list.get (position);
        holder.tv_ma_pm.setText ("Mã phiếu mượn : " + pm.getMaPM ()+"");
        holder.tv_ma_sachm.setText("Mã Sách Mượn: "+pm.getMaSach());
        holder.tv_ngay_tra.setText ("Ngày mượn : " + pm.getNgay ());
        holder.tv_trang_thai.setText ("Trạng thái : " + pm.getTraSach ());
        holder.tv_tien_thue.setText ("Tiền thuê : " + pm.getTienThue ());
//        holder.tv_masach.setText ("Mã sách : " + pm.getMaSach ()+"");
//        holder.tv_ma_thanh_vien.setText ("Mã thành viên : " + pm.getMaTV ()+"");
        holder.img_delte_pm.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder (context);
                builder.setTitle ("Delete");
                builder.setMessage ("Bạn có chắc chắn muốn xóa không");
                builder.setPositiveButton ("Yes", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        quanlyPmFragment.Delete (pm.getMaPM ());
                        quanlyPmFragment.onResume ();
                        Toast.makeText (context, "Xóa thành công", Toast.LENGTH_SHORT).show ();
                    }
                });
                builder.setNegativeButton ("No", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss ();
                    }
                });
                builder.create ().show ();
            }
        });
        holder.click_update.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder (context);
                View view = LayoutInflater.from (context).inflate (R.layout.dia_log_update_pm,null);
                builder.setView (view);
                TextView tv_ma_pm = view.findViewById (R.id.tv_update_maPm);
                TextInputEditText ed_ngay_thue =  view.findViewById (R.id.ed_update_ngaythue_pm);
                Button btn_chon_ngay = view.findViewById (R.id.btn_update_ngay);
                RadioButton rb_da_tra = view.findViewById (R.id.rb_da_tra_pm);
                TextInputEditText ed_tien_thue = view.findViewById (R.id.ed_tien_thue_pm);

                tv_ma_pm.setText ("Mã phiếu mượn : " + pm.getMaPM ()+"");
                ed_ngay_thue.setText (pm.getNgay ());
                ed_tien_thue.setText (pm.getTienThue ()+"");
                Calendar calendar = Calendar.getInstance ();
                btn_chon_ngay.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog dialog = new DatePickerDialog (context, new DatePickerDialog.OnDateSetListener () {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                month = month+1;
                                ed_ngay_thue.setText (year+"/" + month + "/" + dayOfMonth);
                            }
                        }, calendar.YEAR, calendar.MONTH,calendar.DATE);
                        dialog.show ();
                    }
                });
                builder.setPositiveButton ("Đồng ý", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pm.setNgay (ed_ngay_thue.getText ().toString ());
                        pm.setTraSach (rb_da_tra.isChecked () ? "Đã trả" : "Chưa trả" );
                        pm.setTienThue (Integer.parseInt (ed_tien_thue.getText ().toString ()));
                        list.add (pm);
                        quanlyPmFragment.Update (pm);
                        quanlyPmFragment.onResume ();
                        Toast.makeText (context, "Update thành công", Toast.LENGTH_SHORT).show ();
                    }
                });
                builder.setNegativeButton ("Hủy", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss ();
                    }
                });
                builder.create ().show ();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size ();
    }

    public class PhieuMuonViewHodel extends RecyclerView.ViewHolder {
        TextView tv_ma_pm, tv_ngay_tra , tv_trang_thai , tv_tien_thue,tv_ma_sachm;
        ImageView img_delte_pm;
        LinearLayout click_update;
        public PhieuMuonViewHodel(@NonNull View itemView) {
            super (itemView);
            tv_ma_pm = itemView.findViewById (R.id.tv_ma_pm);
            tv_ma_sachm=itemView.findViewById(R.id.tv_ma_sachm);
            tv_ngay_tra = itemView.findViewById (R.id.tv_ngay);
            tv_trang_thai = itemView.findViewById (R.id.tv_trang_thai);
            tv_tien_thue = itemView.findViewById (R.id.tv_tien_thue);
            img_delte_pm = itemView.findViewById (R.id.img_xoa_pm);
            click_update = itemView.findViewById (R.id.ll_update_pm);
//            tv_masach = itemView.findViewById (R.id.tv_masachaa);
//            tv_ma_thanh_vien = itemView.findViewById (R.id.tv_ma_thanh_vienaa);
        }
    }
}
