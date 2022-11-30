package com.example.assignment.Adapter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.List;

import com.example.assignment.Fragment.QuanLyThanhVienFragment;
import com.example.assignment.Model.ThanhVien;
import com.example.assignment.R;

public class AdapterThanhVien extends RecyclerView.Adapter<AdapterThanhVien.ThanhVienHodel>{
    private Context context;
    private List<ThanhVien> list;
    private QuanLyThanhVienFragment quanLyThanhVienFragment;

    public AdapterThanhVien(Context context, List<ThanhVien> list, QuanLyThanhVienFragment quanLyThanhVienFragment) {
        this.context = context;
        this.list = list;
        this.quanLyThanhVienFragment = quanLyThanhVienFragment;
    }

    @NonNull
    @Override
    public ThanhVienHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.item_recy_view_thanh_vien,null);
        return new ThanhVienHodel (view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhVienHodel holder, int position) {
        ThanhVien tv = list.get (position);
        holder.tv_matv.setText ("Mã thành viên : " + tv.getMaTV ()+"");
        holder.tv_ten_tv.setText ("Name : " +  tv.getName ());
        holder.tv_nam_sinh.setText ("Năm sinh : "  + tv.getNamSinh ());
        holder.img_delete.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder (context);
                builder.setTitle ("Delete");
                builder.setMessage ("bạn có chắc chắn muốn xóa không");
                builder.setPositiveButton ("Yes", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        quanLyThanhVienFragment.Delete (tv.getMaTV ());
                        quanLyThanhVienFragment.onResume ();
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
        holder.clicktv.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog (context);
                dialog.setTitle ("Sửa thành viên");
                dialog.setContentView (R.layout.dia_log_sua_thanh_vien);
                dialog.show ();

                TextView tv_matv = dialog.findViewById (R.id.tv_sua_ma_tv);
                EditText ed_sua_ten_sv = dialog.findViewById (R.id.ed_sua_ten_tv);
                EditText ed_sua_nam_sinh = dialog.findViewById (R.id.ed_sua_nam_sinh);
                Button btn_sua_nam_sinh = dialog.findViewById (R.id.btn_sua_nam_sinh);
                ImageView img_sua_tv = dialog.findViewById (R.id.img_sua_tv);
                ImageView img_huy_tv = dialog.findViewById (R.id.img_huy_sua_tv);

                tv_matv.setText ("Mã thành viên :" + tv.getMaTV ()+"");
                ed_sua_ten_sv.setText (tv.getName ());
                ed_sua_nam_sinh.setText (tv.getNamSinh ());

                btn_sua_nam_sinh.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance ();
                        DatePickerDialog dialog1 = new DatePickerDialog (context, new DatePickerDialog.OnDateSetListener () {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                month = month + 1 ;
                                ed_sua_nam_sinh.setText (year + "/" + month + "/" + dayOfMonth);
                            }
                        },calendar.YEAR , calendar.MONTH ,calendar.DATE);
                    }
                });
                img_huy_tv.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss ();
                    }
                });
                img_sua_tv.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        tv.setName (ed_sua_ten_sv.getText ().toString ());
                        tv.setNamSinh (ed_sua_nam_sinh.getText ().toString ());
                        list.add (tv);
                        quanLyThanhVienFragment.Update (tv);
                        quanLyThanhVienFragment.onResume ();
                        Toast.makeText (context, "Sửa thành công", Toast.LENGTH_SHORT).show ();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size ();
    }

    public class ThanhVienHodel extends RecyclerView.ViewHolder {
        TextView tv_matv , tv_ten_tv , tv_nam_sinh;
        ImageView img_delete;
        LinearLayout clicktv;
        public ThanhVienHodel(@NonNull View itemView) {
            super (itemView);
            tv_matv = itemView.findViewById (R.id.tv_matv);
            tv_ten_tv = itemView.findViewById (R.id.tv_ten_tv);
            tv_nam_sinh = itemView.findViewById (R.id.tv_nam_sinh);
            img_delete = itemView.findViewById (R.id.img_xoa_tv);
            clicktv = itemView.findViewById (R.id.ll_dia_log_tv);
        }
    }
}
