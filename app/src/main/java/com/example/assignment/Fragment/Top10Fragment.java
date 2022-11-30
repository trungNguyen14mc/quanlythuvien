package com.example.assignment.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.assignment.Adapter.AdapterTop10Sach;
import com.example.assignment.DAO.DAO_PM;
import com.example.assignment.DAO.DAO_THONGKE;
import com.example.assignment.Model.Top;
import com.example.assignment.R;


public class Top10Fragment extends Fragment {

    RecyclerView recyclerView;

    DAO_THONGKE tk;
    AdapterTop10Sach adapterTop10Sach;
    ArrayList<Top>arrayList;

    public Top10Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate (R.layout.fragment_top10, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        recyclerView = view.findViewById (R.id.recy_view_top10sach);

        tk=new DAO_THONGKE(getContext());
        arrayList = (ArrayList<Top>) tk.getTop();
        adapterTop10Sach = new AdapterTop10Sach (getContext (),arrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager (getContext (),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager (layoutManager);
        recyclerView.setAdapter (adapterTop10Sach);
        adapterTop10Sach.notifyDataSetChanged ();
    }
    @Override
    public void onResume() {
        super.onResume ();
        arrayList.clear ();
        arrayList.addAll (tk.getTop());
        if(arrayList != null){
            adapterTop10Sach.notifyDataSetChanged ();
        }
    }
}