package com.example.assignment.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {

    public Dbhelper(@Nullable Context context) {
        super (context, "SachPN1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_ThuThu = "create table if not exists ThuThu(MATT text primary key , HOTEN text , MATKHAU text)";
        db.execSQL (table_ThuThu);
        String INSERT_THUTHU = "insert into ThuThu values" +
                "('00', 'admin', 'admin') ," +
                "('01', 'Nguyễn Văn A', '1231') ," +
                "('02', 'Nguyễn Văn B', '1232'), " +
                "('03', 'Nguyễn Văn C', '1233'), " +
                "('04', 'Nguyễn Văn D', '1234'), " +
                "('05', 'Nguyễn Văn E', '1235')";
        db.execSQL(INSERT_THUTHU);
        String table_LoaiSach = "create table if not exists LoaiSach(MALOAI integer primary key , TENLOAI text )";
        db.execSQL (table_LoaiSach);
        String INSERT_LOAISACH = "insert into LoaiSach values" +
                "(1, 'Tiểu thuyết'), " +
                "(2, 'Trinh Thám')," +
                "(3, 'Khoa học')," +
                "(4, 'Xã hội')," +
                "(5, 'Kinh dị')";
        db.execSQL(INSERT_LOAISACH);

        String table_ThanhVien = "create table if not exists ThanhVien(MATV integer primary key , TENTV text , NAMSINH text)";
        db.execSQL (table_ThanhVien);
        String INSERT_THANHVIEN = "insert into ThanhVien values" +
                "(1, 'Nguyễn văn  A', '2002'), " +
                "(2, 'Nguyễn văn B', '2001'), " +
                "(3, 'Trần Hoài C', '2000'), " +
                "(4, 'Trần Thị D', '2001'), " +
                "(5, 'Trần Thế E', '2002')";
        db.execSQL(INSERT_THANHVIEN);
        String table_Sach = "create table if not exists Sach (MASACH integer primary key , " +
                "TENSACH text ," +
                " GIATHUE integer ," +
                " MALOAI integer REFERENCES LoaiSach(MALOAI))";
        db.execSQL (table_Sach);
        String insert_sach = "insert into Sach values " +
                "(1,'Toán',30000,1)," +
                "(2,'Văn',20000,2)," +
                "(3,'địa lý',3000,3)";
        db.execSQL (insert_sach);
        String table_phieuMuon = "create table if not exists PhieuMuon(MAPM integer primary key  ," +
                " MATT text REFERENCES ThuThu(MATT) ," +
                " MATV integer REFERENCES ThanhVien(MATV), " +
                "MASACH integer REFERENCES Sach (MASACH), " +
                "NGAY DATE , TRASACH text , TIENTHUE integer)";
        db.execSQL (table_phieuMuon);
        String INSERT_PHIEUMUON = "insert into PhieuMuon values" +
                "(4, '2',1, 1, '2022-01-16', 'đã trả', 10000)," +
                "(5, '3',2, 2 , '2022-01-19',  'chưa trả', 20000)," +
                "(6, '4',3, 3 , '2022-01-10', 'đã trả', 30000)," +
                "(7, '5',1, 1 , '2022-02-11', 'chưa trả', 40000)," +
                "(8, '6',1, 1 , '2022-01-12', 'chưa trả', 50000)";
        db.execSQL(INSERT_PHIEUMUON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String xoa_tt = "drop table if exists ThuThu";
        db.execSQL (xoa_tt);
        String xoa_loaisach = "drop table if exists LoaiSach";
        db.execSQL (xoa_loaisach);
        String xoa_tv = "drop table if exists ThanhVien";
        db.execSQL (xoa_tv);
        String xoa_sach = "drop table if exists Sach";
        db.execSQL (xoa_sach);
        String xoa_pm = "drop table if exists PhieuMuon";
        db.execSQL (xoa_pm);
        onCreate (db);
    }
}
