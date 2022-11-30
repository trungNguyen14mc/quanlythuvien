package com.example.assignment.Model;

public class ThanhVien {
    private int maTV;
    private String name;
    private String namSinh;

    public ThanhVien() {
    }

    public ThanhVien(int maTV, String name, String namSinh) {
        this.maTV = maTV;
        this.name = name;
        this.namSinh = namSinh;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }
}
