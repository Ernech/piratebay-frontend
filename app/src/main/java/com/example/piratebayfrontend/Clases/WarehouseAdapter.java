package com.example.piratebayfrontend.Clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piratebayfrontend.Model.WarehouseModel;
import com.example.piratebayfrontend.R;

import java.util.ArrayList;

public class WarehouseAdapter extends RecyclerView.Adapter<WarehouseAdapter.ViewHolderDatos> implements View.OnClickListener {
    Context context;
    ArrayList<WarehouseModel> warehouseList;
    private View.OnClickListener miListener;

    public WarehouseAdapter(Context context, ArrayList<WarehouseModel> warehouseList) {
        this.context = context;
        this.warehouseList = warehouseList;
    }

    public void setOnClickListener(View.OnClickListener miListener){
        this.miListener=miListener;

    }
    @Override
    public void onClick(View view) {
        if(miListener!=null){
            miListener.onClick(view);
        }
    }

    @NonNull
    @Override
    public WarehouseAdapter.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_warehouse,parent,false);
        view.setOnClickListener(this);
        return new WarehouseAdapter.ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WarehouseAdapter.ViewHolderDatos holder, int position) {
        holder.tvWareHouseName.setText(warehouseList.get(position).getWarehouseName());
    }

    @Override
    public int getItemCount() {
        return warehouseList.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView tvWareHouseName;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            tvWareHouseName=itemView.findViewById(R.id.tvWarehouseName);
        }
    }
}
