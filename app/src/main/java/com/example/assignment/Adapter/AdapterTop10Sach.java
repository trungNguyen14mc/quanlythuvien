package com.example.assignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.assignment.Model.Top;
import com.example.assignment.R;

public class AdapterTop10Sach extends RecyclerView.Adapter<AdapterTop10Sach.Top10SachViewHodel>{
    private Context context;
    private List<Top> list;

    public AdapterTop10Sach(Context context, List<Top> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Top10SachViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.item_recy_top10_sach,null);
        return new Top10SachViewHodel (view);
    }

    @Override
    public void onBindViewHolder(@NonNull Top10SachViewHodel holder, int position) {
        Top top = list.get (position);
        holder.tv_ten_sach.setText ("Tên Sách: "+top.getTenSach ());
        holder.tv_so_luong.setText ("Số Lượng: "+top.getSoLuong ());
    }

    @Override
    public int getItemCount() {
        return list.size ();
    }

    public class Top10SachViewHodel extends RecyclerView.ViewHolder {
        TextView tv_ten_sach,tv_so_luong;
        public Top10SachViewHodel(@NonNull View itemView) {
            super (itemView);
            tv_ten_sach = itemView.findViewById (R.id.tv_ten_sach_top10);
            tv_so_luong = itemView.findViewById (R.id.tv_soluong);
        }
    }
}
