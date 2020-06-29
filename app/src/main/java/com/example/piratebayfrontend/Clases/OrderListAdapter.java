package com.example.piratebayfrontend.Clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piratebayfrontend.Model.OrderModel;
import com.example.piratebayfrontend.R;

import java.util.ArrayList;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolderDatos> implements View.OnClickListener{

    Context context;
    ArrayList<OrderModel> orders;
    private View.OnClickListener listener;

    public OrderListAdapter(Context context, ArrayList<OrderModel> orders) {
        this.context = context;
        this.orders = orders;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;

    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order,parent,false);
        view.setOnClickListener(this);
        return new OrderListAdapter.ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.tvOrderId.setText("ID orden: "+orders.get(position).getOrderId().toString());
        holder.tvOrderSupplierAndReceipt.setText("Proveedor: "+orders.get(position).getProviderName()+" | Recibo: "+orders.get(position).getReceipt());
        holder.tvQttyRequested.setText("Cantidad ordenada: "+orders.get(position).getQttyOrdered().toString());
        holder.tvQttyCommited.setText("Cantidad Confirmada: "+orders.get(position).getQttyCommit().toString());
        if(orders.get(position).getQttyReceived()!=null && orders.get(position).getQttyReceived()>0){
            holder.tvQttyReceived.setText("Cantidad recibida: "+orders.get(position).getQttyReceived().toString());
        }
        else {
            holder.tvQttyReceived.setText("Cantidad recibida: 0");
        }
        holder.tvDateOrdered.setText("Fecha ordenada: "+changeDateFormat(orders.get(position).getDateRequested()));
        holder.tvDateReceived.setText("Fecha ordenada: "+changeDateFormat(orders.get(position).getDateReceived()));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView tvOrderId;
        TextView tvOrderSupplierAndReceipt;
        TextView tvQttyRequested;
        TextView tvQttyCommited;
        TextView tvQttyReceived;
        TextView tvDateOrdered;
        TextView tvDateReceived;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.tvOrderId);
            tvOrderSupplierAndReceipt = itemView.findViewById(R.id.tvOrderSupplierAndReceipt);
            tvQttyRequested = itemView.findViewById(R.id.tvQttyRequested);
            tvQttyCommited = itemView.findViewById(R.id.tvQttyCommited);
            tvQttyReceived = itemView.findViewById(R.id.tvQttyReceived);
            tvDateOrdered = itemView.findViewById(R.id.tvDateOrdered);
            tvDateReceived = itemView.findViewById(R.id.tvDateReceived);
        }
    }
    private String changeDateFormat(String date){
        String [] aux = date.split("-");
        return aux[2]+"-"+aux[1]+"-"+aux[0];
    }
}
