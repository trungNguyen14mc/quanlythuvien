package com.example.assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignment.Model.ThuThu;
import com.example.assignment.Database.Dbhelper;

public class DAO_TT {

    Dbhelper dbhelper;

    public DAO_TT(Context context) {
        dbhelper = new Dbhelper (context);
        SQLiteDatabase db =  dbhelper.getWritableDatabase ();
    }

    public DAO_TT() {

    }

    public void ThemTT(ThuThu tt){
        SQLiteDatabase db = dbhelper.getWritableDatabase ();
        ContentValues values = new ContentValues ();
        values.put ("MATT", tt.getMaTT ());
        values.put ("HOTEN" , tt.getHoTen ());
        values.put ("MATKHAU",tt.getMatKhau ());
        db.insert ("ThuThu",null,values);
    }

    public void UpdateTT(ThuThu tt ){
        SQLiteDatabase db = dbhelper.getWritableDatabase ();
        ContentValues values = new ContentValues ();
        values.put ("MATKHAU",tt.getMatKhau ());
        db.update ("ThuThu",values,"MATT = " + tt.getMaTT (),null);
    }
    public void DelteTT(int id){
        SQLiteDatabase db = dbhelper.getWritableDatabase ();
        db.delete ("ThuThu","MATT = " + id , null);
    }

    public boolean kiemTraLogin(String hoTen , String mk){
        SQLiteDatabase db = dbhelper.getReadableDatabase ();
        String kiemtra = "select * from ThuThu where HOTEN = '"+hoTen+"' and MATKHAU = '"+mk+"'";
        Cursor cursor = db.rawQuery (kiemtra,null);
        if(cursor.getCount ()!=0){
            return true;
        }else{
            return false;
        }

    }
    public boolean kiemTraMk(String mk){
        SQLiteDatabase db = dbhelper.getReadableDatabase ();
        String kiemtramk = "select * from ThuThu where MATKHAU = '"+mk+"' ";
        Cursor cursor = db.rawQuery (kiemtramk,null);
        if(cursor.getCount () != 0){
            return true;
        }else {
            return false;
        }
    }

}
