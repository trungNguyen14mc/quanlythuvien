package com.example.assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.assignment.Model.ThanhVien;
import com.example.assignment.Database.Dbhelper;

public class DAO_TV {
    Dbhelper dbhelper;
    public DAO_TV(Context context) {
        dbhelper = new Dbhelper (context);
        SQLiteDatabase db = dbhelper.getWritableDatabase ();
    }
    public void ThemTV(ThanhVien tv){
        SQLiteDatabase db = dbhelper.getWritableDatabase ();
        ContentValues values =  new ContentValues ();
        values.put ("MATV",tv.getMaTV ());
        values.put ("TENTV",tv.getName ());
        values.put ("NAMSINH",tv.getNamSinh ());
        db.insert ("ThanhVien",null,values);
    }
    public void UpdateTV(ThanhVien tv){
        SQLiteDatabase db = dbhelper.getWritableDatabase ();
        ContentValues values =  new ContentValues ();
        values.put ("TENTV",tv.getName ());
        values.put ("NAMSINH",tv.getNamSinh ());
        db.update ("ThanhVien",values,"MATV = " + tv.getMaTV (),null);
    }
    public void DeleteTV(int id){
        SQLiteDatabase db = dbhelper.getWritableDatabase ();
        db.delete ("ThanhVien","MATV = " + id, null);
    }

    public List<ThanhVien> GetTV(){
        ArrayList<ThanhVien> arrayList = new ArrayList<> ();
        SQLiteDatabase db = dbhelper.getReadableDatabase ();
        Cursor cursor = db.rawQuery ("select * from ThanhVien",null);
        cursor.moveToFirst ();

        while (!cursor.isAfterLast ()){
            int matv = Integer.parseInt (cursor.getString (cursor.getColumnIndex ("MATV")));
            String tentv = cursor.getString (cursor.getColumnIndex ("TENTV"));
            String namsinh = cursor.getString (cursor.getColumnIndex ("NAMSINH"));
            arrayList.add (new ThanhVien (matv,tentv,namsinh));
            cursor.moveToNext ();
        }
        cursor.close ();
        return arrayList;
    }
}
