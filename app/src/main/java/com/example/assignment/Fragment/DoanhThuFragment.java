package com.example.assignment.Fragment;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.DAO.DAO_THONGKE;
import com.example.assignment.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoanhThuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoanhThuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DoanhThuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DoanhThuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DoanhThuFragment newInstance(String param1, String param2) {
        DoanhThuFragment fragment = new DoanhThuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doanh_thu, container, false);
    }
    TextView tv_doanhthu;
    EditText ed_tungay,ed_denNgay;
    Button btn_doanhThu,btn_tungay,btn_denNgay;
    String tuNgay,denNgay;
    int mYear,mMonth,mDay;
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public void onViewCreated(@NonNull  View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_doanhThu=view.findViewById(R.id.btn_doanhthu);
        btn_denNgay=view.findViewById(R.id.btn_denNgay);
        btn_tungay=view.findViewById(R.id.btn_tungay);
        tv_doanhthu=view.findViewById(R.id.txtDoangThu);
        ed_tungay=view.findViewById(R.id.ed_tungay);
        ed_denNgay=view.findViewById(R.id.ed_denNgay);
        btn_tungay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                mYear=c.get(Calendar.YEAR);
                mMonth=c.get(Calendar.MONTH);
                mDay=c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d=new DatePickerDialog(getActivity(),
                        0,mDateTuNgay,mYear,mMonth,mDay);
                d.show();

            }
        });
        btn_denNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                mYear=c.get(Calendar.YEAR);
                mMonth=c.get(Calendar.MONTH);
                mDay=c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d=new DatePickerDialog(getActivity(),
                        0,mDatedenNgay,mYear,mMonth,mDay);
                d.show();
            }
        });
        btn_doanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    DAO_THONGKE dtk=new DAO_THONGKE(getContext());
                    tv_doanhthu.setText(dtk.DoanhThu(ed_tungay.getText().toString(),ed_denNgay.getText().toString())+"");

            }
        });
    }
    DatePickerDialog.OnDateSetListener mDateTuNgay=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear=year;
            mMonth=month;
            mDay=dayOfMonth;
            GregorianCalendar c=new GregorianCalendar(mYear,mMonth,mDay);
            ed_tungay.setText(sdf.format(c.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener mDatedenNgay=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear=year;
            mMonth=month;
            mDay=dayOfMonth;
            GregorianCalendar c=new GregorianCalendar(mYear,mMonth,mDay);
            ed_denNgay.setText(sdf.format(c.getTime()));
        }
    };
}