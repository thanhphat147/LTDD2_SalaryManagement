package com.example.kiemtra;



import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText txtMaNV, txtTenNV, txtNgaySinh, txtMaPB, txtLuong;
    Button btnThem, btnXoa, btnSua, btnClear;
    ListView lvNhanVien;
    
    ArrayList<NhanVien> data_NhanVien;
    CustomAdapter adapter_NhanVien;

    
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
                
    }

    private void setEvent() {
        data_NhanVien = new ArrayList<>();
        adapter_NhanVien = new CustomAdapter(this, R.layout.layout_nhanvien, data_NhanVien);
        lvNhanVien.setAdapter(adapter_NhanVien);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien nhanVien = new NhanVien();
                if(txtMaNV.getText().toString().isEmpty())
                {
                    txtMaNV.setError("Bắt buộc phải nhập");
                    txtMaNV.isFocused();
                }else if(txtTenNV.getText().toString().isEmpty())
                {
                    txtTenNV.setError("Bắt buộc phải nhập");
                    txtTenNV.isFocused();
                }else if(txtNgaySinh.getText().toString().isEmpty())
                {
                    txtNgaySinh.setError("Bắt buộc phải nhập");
                    txtNgaySinh.isFocused();
                }else if(txtMaPB.getText().toString().isEmpty())
                {
                    txtMaPB.setError("Bắt buộc phải nhập");
                    txtMaPB.isFocused();
                }else if(txtLuong.getText().toString().isEmpty())
                {
                    txtLuong.setError("Bắt buộc phải nhập");
                    txtLuong.isFocused();
                }else {
                    nhanVien.setMaNV(txtMaNV.getText().toString());
                    nhanVien.setTenNV(txtTenNV.getText().toString());
                    nhanVien.setNgaySinh(txtNgaySinh.getText().toString());
                    nhanVien.setMaPB(txtMaPB.getText().toString());
                    nhanVien.setTienLuong(txtLuong.getText().toString());


                }
                data_NhanVien.add(nhanVien);
                adapter_NhanVien.notifyDataSetChanged();


            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông báo xóa");
                builder.setMessage("Bạn có muốn xóa thông tin nhân viên đã chọn?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data_NhanVien.remove(index);
                        adapter_NhanVien.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien nhanVien = data_NhanVien.get(index);
                nhanVien.setMaNV(txtMaNV.getText().toString());
                nhanVien.setTenNV(txtTenNV.getText().toString());
                nhanVien.setNgaySinh(txtNgaySinh.getText().toString());
                nhanVien.setMaPB(txtMaPB.getText().toString());
                nhanVien.setTienLuong(txtLuong.getText().toString());

                adapter_NhanVien.notifyDataSetChanged();

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMaNV.setText("");
                txtTenNV.setText("");
                txtNgaySinh.setText("");
                txtMaPB.setText("");
                txtLuong.setText("");

            }
        });

        lvNhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NhanVien nhanVien = data_NhanVien.get(position);
                txtMaNV.setText(nhanVien.getMaNV());
                txtTenNV.setText(nhanVien.getTenNV());
                txtNgaySinh.setText(nhanVien.getNgaySinh());
                txtMaPB.setText(nhanVien.getMaPB());
                txtLuong.setText(nhanVien.getTienLuong());
                index = position;
            }
        });

//        lvNhanVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                data_NhanVien.remove(position);
//                adapter_NhanVien.notifyDataSetChanged();
//                return false;
//            }
//        });

    }


    private void setControl() {
        txtMaNV = findViewById(R.id.txtMaNV);
        txtTenNV = findViewById(R.id.txtTenNV);
        txtNgaySinh = findViewById(R.id.txtNgaySinh);
        txtMaPB = findViewById(R.id.txtMaPB);
        txtLuong = findViewById(R.id.txtLuong);

        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnClear = findViewById(R.id.btnClear);
        lvNhanVien = findViewById(R.id.lvNhanVien);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.luu:
//                DBnhanVien dbnhanVien = new DBnhanVien(getApplicationContext());
//                nhanVien nhanVien = new nhanVien();
//                nhanVien.setSoPT(txtSoPT.getText().toString());
//                nhanVien.setNgay(txtNgay.getText().toString());
//                nhanVien.setSoTien(txtSoTien.getText().toString());
//                nhanVien.setTenKH(spKhachHang.getSelectedItem().toString());
//                nhanVien.setDichVu(spDichVu.getSelectedItem().toString());
//                dbnhanVien.save(nhanVien);
                DBNhanVien dbnhanVien = new DBNhanVien(getApplicationContext());
                dbnhanVien.save1(data_NhanVien);
                Toast.makeText(MainActivity.this, "Save thành công", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mo:
                Intent intent = new Intent(MainActivity.this, DanhSach.class);
                startActivity(intent);
                break;
            case R.id.thoat:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setTitle("Thông báo");
                builder1.setMessage("Bạn có muốn thoát không ?");
                builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog1 = builder1.create();
                alertDialog1.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
