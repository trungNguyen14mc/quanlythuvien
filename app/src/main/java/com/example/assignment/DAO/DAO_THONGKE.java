package com.example.assignment.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignment.Database.Dbhelper;
import com.example.assignment.Model.Top;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DAO_THONGKE {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    public DAO_THONGKE(Context context){
        this.context=context;
        Dbhelper dbhelper=new Dbhelper(context);
        db=dbhelper.getWritableDatabase();
    }
    public List<Top> getTop(){
        ArrayList<Top> arrayList = new ArrayList<> ();
        String sql = "select  TENSACH , count(PhieuMuon.MASACH) as soluong from PhieuMuon INNER JOIN Sach on PhieuMuon.MASACH = Sach.MASACH GROUP BY TENSACH ORDER BY soluong DESC LIMIT 10";
        Cursor cursor = db.rawQuery (sql,null);
        while (cursor.moveToNext ()){
            Top t = new Top ();
            t.setTenSach (cursor.getString (cursor.getColumnIndex ("TENSACH")));
            t.setSoLuong (Integer.parseInt (cursor.getString (cursor.getColumnIndex ("soluong"))));
            arrayList.add (t);
        }
        cursor.close ();
        return arrayList;
    }
    public int DoanhThu(String tuNgay,String denNgay){
        String getDT="select sum(TIENTHUE) as doanhThu from PhieuMuon where NGAY between ? and ?";
        Cursor cursor=db.rawQuery(getDT,new String[]{tuNgay,denNgay});
        List<Integer> list=new ArrayList<>();
        while (cursor.moveToNext()){
            try {
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));
            }
            catch (Exception e){

            }
        }
        return list.get(0);
    }
}
