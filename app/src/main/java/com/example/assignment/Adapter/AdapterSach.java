package com.example.assignment.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import com.example.assignment.Fragment.QuanLySachFragment;
import com.example.assignment.Model.Sach;
import com.example.assignment.R;

public class AdapterSach extends RecyclerView.Adapter<AdapterSach.SachViewHodel>{
    private Context context;
    private List<Sach> list;
    private QuanLySachFragment quanLySachFragment;

    public AdapterSach(Context context, List<Sach> list, QuanLySachFragment quanLySachFragment) {
        this.context = context;
        this.list = list;
        this.quanLySachFragment = quanLySachFragment;
    }

    @NonNull
    @Override
    public SachViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.item_recy_view_sach,null);
        return new SachViewHodel (view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachViewHodel holder, int position) {
        Sach sach = list.get (position);
        holder.tv_ma_Sach.setText ("Mã sách : " + sach.getMaSach ()+"");
        holder.tv_ten_sach.setText ("Tên sách : " + sach.getTenSach ());
        holder.tv_gia_thue.setText ("Giá thuê : " + sach.getGiaThue ()+"");

        holder.img_delete_sach.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder (context);
                builder.setTitle ("Delete");
                builder.setMessage ("Bạn có chắc chắn muốn xóa không");
                builder.setPositiveButton ("Yes", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        quanLySachFragment.Delete (sach.getMaSach ());
                        quanLySachFragment.onResume ();
                        Toast.makeText (context, "Xóa Thành công ", Toast.LENGTH_SHORT).show ();
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

        holder.click_sua.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder (context);
                View view = LayoutInflater.from (context).inflate (R.layout.dia_log_sua_sach,null);
                builder.setView (view);

                TextView tv_ma_sach = view.findViewById (R.id.tv_update_ma_sach);
                TextInputEditText tv_up_ten_sach = view.findViewById (R.id.ed_update_ten_sach);
                TextInputEditText tv_up_gia_thue = view.findViewById (R.id.ed_update_gia_thue);

                tv_ma_sach.setText ("Mã sách : " + sach.getMaSach () + "");
                tv_up_ten_sach.setText (sach.getTenSach ());
                tv_up_gia_thue.setText (sach.getGiaThue ()+"");
                builder.setPositiveButton ("Đồng ý", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sach.setTenSach (tv_up_ten_sach.getText ().toString ());
                        sach.setGiaThue (Integer.parseInt (tv_up_gia_thue.getText ().toString ()));
                        list.add (sach);
                        quanLySachFragment.Update (sach);
                        quanLySachFragment.onResume ();
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

    public class SachViewHodel extends RecyclerView.ViewHolder {
        TextView tv_ma_Sach , tv_ten_sach , tv_gia_thue;
        ImageView img_delete_sach;
        LinearLayout click_sua;

        public SachViewHodel(@NonNull View itemView) {
            super (itemView);
            tv_ma_Sach = itemView.findViewById (R.id.tv_ma_Sach);
            tv_ten_sach = itemView.findViewById (R.id.tv_ten_Sach);
            tv_gia_thue = itemView.findViewById (R.id.tv_gia_thue);
            img_delete_sach = itemView.findViewById (R.id.img_xoa_sach);
            click_sua = itemView.findViewById (R.id.ll_click_sach);
        }
    }
}
