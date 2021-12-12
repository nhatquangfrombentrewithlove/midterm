package com.n05.Adapter;

import android.content.Context;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.n05.g701.MainActivity;
import com.n05.g701.R;
import com.n05.model.Product;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    MainActivity context;
    int item_layout;
    List<Product> products;

    public ProductAdapter(MainActivity context, int item_layout, List<Product> products) {
        this.context = context;
        this.item_layout = item_layout;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, null);
            holder.txtMaSP = view.findViewById(R.id.txtMaSP);
            holder.txtTenSP = view.findViewById(R.id.txtTenSP);
            holder.txtHSX = view.findViewById(R.id.txtHSX);
            holder.txtGia = view.findViewById(R.id.txtGia);
            holder.imvThumb = view.findViewById(R.id.imvThumb);
            holder.btnSua = view.findViewById(R.id.btnSua);
            holder.btnXoa = view.findViewById(R.id.btnXoa);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        //Binding Data
        Product p = products.get(i);
        holder.txtMaSP.setText(p.getTxtMaSP());
        holder.txtTenSP.setText(p.getTxtTenSP());
        holder.txtHSX.setText(p.getTxtHSX());
        holder.txtGia.setText(String.valueOf(p.getTxtGia()));

        holder.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.openEditDialog(p);
            }
        });

        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.deleteProduct(p);
            }
        });
        return view;
    }
    private class ViewHolder{
        TextView txtMaSP, txtTenSP, txtHSX, txtGia;
        ImageView imvThumb;
        Button btnSua, btnXoa;
    }
}
