package com.example.assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignment.Database.Dbhelper;

import java.util.ArrayList;
import java.util.List;

import com.example.assignment.Model.PhieuMuon;
import com.example.assignment.Model.Top;
import com.example.assignment.Database.Dbhelper;

public class DAO_PM {
    Dbhelper dbhelper;
    private Context context;
    public DAO_PM(Context context) {
        dbhelper = new Dbhelper (context);
        SQLiteDatabase db = dbhelper.getWritableDatabase ();
    }
    public void ThemPM (PhieuMuon phieuMuon){
        SQLiteDatabase db = dbhelper.getWritableDatabase ();
        ContentValues values = new ContentValues ();
        values.put ("MAPM",phieuMuon.getMaPM ());
        values.put ("MATT",phieuMuon.getMaTT ());
        values.put ("MATV",phieuMuon.getMaTV ());
        values.put ("MASACH",phieuMuon.getMaSach ());
        values.put ("NGAY", phieuMuon.getNgay ());
        values.put ("TRASACH",phieuMuon.getTraSach ());
        values.put ("TIENTHUE",phieuMuon.getTienThue ());
        db.insert ("PhieuMuon",null,values);
    }
    public void UpdatePM(PhieuMuon pm){
        SQLiteDatabase db = dbhelper.getWritableDatabase ();
        ContentValues values = new ContentValues ();
        values.put ("NGAY", pm.getNgay ());
        values.put ("TRASACH",pm.getTraSach ());
        values.put ("TIENTHUE",pm.getTienThue ());
        db.update ("PhieuMuon", values, "MAPM = " + pm.getMaPM (),null);
    }
    public void Delete(int id){
        SQLiteDatabase db = dbhelper.getWritableDatabase ();
        db.delete ("PhieuMuon","MAPM = " + id, null);
    }

    public List<PhieuMuon> GetPM (){
        ArrayList<PhieuMuon> arrayList = new ArrayList<> ();
        SQLiteDatabase db = dbhelper.getReadableDatabase ();
        Cursor cursor = db.rawQuery ("select * from PhieuMuon",null);
        cursor.moveToFirst ();
        while (!cursor.isAfterLast ()){
            PhieuMuon pm = new PhieuMuon ();
            pm.setMaPM (Integer.parseInt (cursor.getString (cursor.getColumnIndex ("MAPM"))));
            pm.setMaTT (cursor.getColumnName (cursor.getColumnIndex ("MATT")));
            pm.setMaTV (Integer.parseInt (cursor.getString (cursor.getColumnIndex ("MATV"))));
            pm.setMaSach (Integer.parseInt (cursor.getString (cursor.getColumnIndex ("MASACH"))));
            pm.setNgay (cursor.getString(4));
            pm.setTraSach (cursor.getString (cursor.getColumnIndex ("TRASACH")));
            pm.setTienThue (Integer.parseInt (cursor.getString (cursor.getColumnIndex ("TIENTHUE"))));
            arrayList.add (pm);
            cursor.moveToNext ();
        }
        cursor.close ();
        return arrayList;

    }

}
