package com.n05.g701;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.n05.Adapter.ProductAdapter;
import com.n05.model.Product;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static MyDataBaseHelper db;
    ListView lvProduct;
    ProductAdapter adapter;
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkViews();
        loadData();
    }

    private void linkViews() {
        lvProduct.findViewById(R.id.lvProduct);
    }

    private void loadData() {
        adapter = new ProductAdapter(MainActivity.this, R.layout.item_layout,products);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnAddProduct){
            //Open dialog for adding task
            openAddDialog();
        }
        return super.onOptionsItemSelected(item);
    }
    private byte[] convertPhoto() {
        BitmapDrawable drawable = (BitmapDrawable) imvThumb.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();
    }


    public void deleteProduct(Product p) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm!");
        builder.setMessage("Are you sure you want to delete this task: " + p.getTxtTenSP() + "?");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.deleteData(p.getTxtMaSP());
                Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                loadData();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
    private void openAddDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_dialog);

        EditText edtAddTenSP, edtAddHSX, edtAddGia;
        edtAddTenSP = dialog.findViewById(R.id.edtAddTenSP);
        edtAddHSX = dialog.findViewById(R.id.edtAddHSX);
        edtAddGia = dialog.findViewById(R.id.edtAddGia);
        Button btnAddThem = dialog.findViewById(R.id.btnAddThem);
        btnAddThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Insert data
                String productName = edtAddTenSP.getText().toString();
                String productHSX = edtAddHSX.getText().toString();
                Double productGia = Double.parseDouble(edtAddGia.getText().toString());

                if(edtAddTenSP.equals("") || edtAddHSX.equals("") || edtAddGia.equals("")){
                    Toast.makeText(MainActivity.this,"Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    db.insertData(productName,productHSX,productGia,convertPhoto());
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this,"Success!", Toast.LENGTH_SHORT).show();
                    loadData();
                }
            }
        });
        Button btnAddHuy = dialog.findViewById(R.id.btnAddHuy);
        btnAddHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void openEditDialog(Product p){

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.edit_dialog);

        EditText edtAddTenSP, edtAddHSX, edtAddGia;
        edtAddTenSP = dialog.findViewById(R.id.edtAddTenSP);
        edtAddHSX = dialog.findViewById(R.id.edtAddHSX);
        edtAddGia = dialog.findViewById(R.id.edtAddGia);

        Button btnEditSua = dialog.findViewById(R.id.btnEditSua);
        btnEditSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productName = edtAddTenSP.getText().toString();
                String productHSX = edtAddHSX.getText().toString();
                Double productGia = Double.parseDouble(edtAddGia.getText().toString());

                db.updateData(p.getTxtMaSP(), productName, productHSX, productGia, convertPhoto());
                Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                loadData();
            }
        });
    }
}