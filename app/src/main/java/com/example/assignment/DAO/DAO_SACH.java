package com.example.assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.assignment.Database.Dbhelper;
import com.example.assignment.Model.Sach;
import com.example.assignment.Database.Dbhelper;

public class DAO_SACH {
    Dbhelper dbhelper;
    public DAO_SACH(Context context) {
        dbhelper = new Dbhelper (context);
        SQLiteDatabase db = dbhelper.getWritableDatabase ();
    }

    public void ThemSach(Sach sach){
        SQLiteDatabase db = dbhelper.getWritableDatabase ();
        ContentValues values = new ContentValues ();
        values.put ("MASACH",sach.getMaSach ());
        values.put ("TENSACH", sach.getGiaThue ());
        values.put ("GIATHUE",sach.getGiaThue ());
        values.put ("MALOAI",sach.getMaLoai ());
        db.insert ("Sach",null,values);

    }
    public void UpdateSach(Sach sach){
        SQLiteDatabase db = dbhelper.getWritableDatabase ();
        ContentValues values = new ContentValues ();
        values.put ("TENSACH",sach.getTenSach ());
        values.put ("GIATHUE",sach.getGiaThue ());
        db.update ("Sach",values,"MASACH = " + sach.getMaSach (),null);
    }

    public void DeleteSach(int id){
        SQLiteDatabase db = dbhelper.getWritableDatabase ();
        db.delete ("Sach","MASACH = " + id,null);
    }
    public List<Sach> GetSach(){
        ArrayList<Sach> arrayList = new ArrayList<> ();
        SQLiteDatabase db = dbhelper.getReadableDatabase ();
        Cursor cursor = db.rawQuery ("select * from Sach",null);

        cursor.moveToFirst ();

        while (!cursor.isAfterLast ()){
            Sach sach = new Sach ();
            sach.setMaSach (Integer.parseInt (cursor.getString (cursor.getColumnIndex ("MASACH"))));
            sach.setTenSach (cursor.getString (cursor.getColumnIndex ("TENSACH")));
            sach.setGiaThue (Integer.parseInt (cursor.getString (cursor.getColumnIndex ("GIATHUE"))));
//            sach.setMaLoai (Integer.parseInt (cursor.getString (cursor.getColumnIndex ("MALOAI"))));
            arrayList.add (sach);
            cursor.moveToNext ();
        }
        cursor.close ();
        return arrayList;
    }
}
