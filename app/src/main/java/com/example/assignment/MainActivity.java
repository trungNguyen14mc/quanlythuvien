package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.assignment.Fragment.DoanhThuFragment;
import com.example.assignment.Fragment.DoiMatKhauFragment;
import com.example.assignment.Fragment.QuanLyLoaiSachFragment;
import com.example.assignment.Fragment.QuanLySachFragment;
import com.example.assignment.Fragment.QuanLyThanhVienFragment;
import com.example.assignment.Fragment.QuanlyPmFragment;
import com.example.assignment.Fragment.ThemNgDungFragment;
import com.example.assignment.Fragment.Top10Fragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout_main;
    MaterialToolbar toolbar_main;
    FragmentContainerView fragmentContainerView_main;
    NavigationView navigationView_main;
    FragmentManager fragmentManager_main;
    //fragment
    QuanlyPmFragment quanlyPmFragment;
    QuanLyLoaiSachFragment quanLyLoaiSachFragment;
    QuanLySachFragment quanLySachFragment;
    QuanLyThanhVienFragment quanLyThanhVienFragment;
    DoanhThuFragment doanhThuFragment;
    Top10Fragment top10Fragment;
    DoiMatKhauFragment doiMatKhauFragment;
    ThemNgDungFragment themNgDungFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //anh xa

        drawerLayout_main =  findViewById(R.id.drawer_layout_main);
        toolbar_main = findViewById(R.id.toolbar_main);
        fragmentContainerView_main =  findViewById(R.id.fragment_container_view_main);
        navigationView_main  =findViewById(R.id.nav_view_main);

        //------//
        setSupportActionBar(toolbar_main);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.outline_menu_black_24);
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar_main.setNavigationOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                drawerLayout_main.openDrawer (GravityCompat.START);
            }
        });
        // đk fragment
        doiMatKhauFragment = new DoiMatKhauFragment();

        top10Fragment = new Top10Fragment();
        quanlyPmFragment =new QuanlyPmFragment();
        quanLyLoaiSachFragment = new QuanLyLoaiSachFragment();
        quanLySachFragment = new QuanLySachFragment();
        quanLyThanhVienFragment = new QuanLyThanhVienFragment();
        fragmentManager_main = getSupportFragmentManager();
        doanhThuFragment = new DoanhThuFragment();
        themNgDungFragment = new ThemNgDungFragment();
        fragmentManager_main.beginTransaction().add(R.id.fragment_container_view_main,quanlyPmFragment).commit();
        Intent i=getIntent();
        String user=i.getStringExtra("user");
        if(user.equalsIgnoreCase("admin")){
            navigationView_main.getMenu().findItem(R.id.mnu_add_acc).setVisible(true);
        }
        View mv=navigationView_main.getHeaderView(0);
        TextView tv_helo=mv.findViewById(R.id.tv_hello);
        tv_helo.setText("Xin chào "+user+"!");
        navigationView_main.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.mnu_qlpm:
                        fragmentManager_main.beginTransaction().replace(R.id.fragment_container_view_main,quanlyPmFragment).commit();
                        break;
                    case R.id.mnu_qlls:
                        fragmentManager_main.beginTransaction().replace(R.id.fragment_container_view_main,quanLyLoaiSachFragment).commit();
                        break;
                    case R.id.mnu_qlsach:
                        fragmentManager_main.beginTransaction().replace(R.id.fragment_container_view_main,quanLySachFragment).commit();
                        break;
                    case R.id.mnu_qltv:
                        fragmentManager_main.beginTransaction().replace(R.id.fragment_container_view_main,quanLyThanhVienFragment).commit();
                        break;
                    case R.id.mnu_top10:
                        fragmentManager_main.beginTransaction().replace(R.id.fragment_container_view_main,top10Fragment).commit();
                        break;
                    case R.id.mnu_doanhthu:
                        fragmentManager_main.beginTransaction().replace(R.id.fragment_container_view_main,doanhThuFragment).commit();
                        break;
                    case R.id.mnu_add_acc:
                        fragmentManager_main.beginTransaction().replace(R.id.fragment_container_view_main,themNgDungFragment).commit();
                        break;
                    case R.id.mnu_doimk:
                        fragmentManager_main.beginTransaction().replace(R.id.fragment_container_view_main,doiMatKhauFragment).commit();
                        break;
                    case R.id.mnu_logout:
                        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                    default:
                }
                drawerLayout_main.closeDrawer(navigationView_main);
                return false;
            }
        });
    }
}