package com.example.assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.assignment.Model.LoaiSach;
import com.example.assignment.Database.Dbhelper;

public class DAO_LS {

    Dbhelper dbhelper;

    public DAO_LS(Context context) {
        dbhelper = new Dbhelper (context);
        SQLiteDatabase dbDatabase = dbhelper.getWritableDatabase ();
    }

    public void ThemLS(LoaiSach ls){
        SQLiteDatabase db = dbhelper.getWritableDatabase ();
        ContentValues values = new ContentValues ();
        values.put ("MALOAI",ls.getMaLoai ());
        values.put ("TENLOAI",ls.getTenLoai ());
        db.insert ("LoaiSach",null,values);
    }
    public void UpdateLS(LoaiSach ls){
        SQLiteDatabase db = dbhelper.getWritableDatabase ();
        ContentValues values = new ContentValues ();
        values.put ("TENLOAI",ls.getTenLoai ());
        db.update ("LoaiSach",values,"MALOAI = " + ls.getMaLoai (),null);
    }
    public void DeleteLS(int id){
        SQLiteDatabase db = dbhelper.getWritableDatabase ();
        db.delete ("LoaiSach","MALOAI = " + id , null);
    }

    public List<LoaiSach> GetLS(){
        ArrayList<LoaiSach> arrayList= new ArrayList<> ();
        SQLiteDatabase db = dbhelper.getReadableDatabase ();
        Cursor cursor = db.rawQuery ("select * from LoaiSach",null);
        cursor.moveToFirst ();
        while (!cursor.isAfterLast ()){
            int maLoai = Integer.parseInt (cursor.getString (cursor.getColumnIndex ("MALOAI")));
            String tenLoai = cursor.getString (cursor.getColumnIndex ("TENLOAI"));
            arrayList.add (new LoaiSach (maLoai,tenLoai));
            cursor.moveToNext ();
        }
        cursor.close ();
        return arrayList;
    }
}
