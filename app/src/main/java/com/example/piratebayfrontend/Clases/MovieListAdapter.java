package com.example.piratebayfrontend.Clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piratebayfrontend.Model.MovieModel;
import com.example.piratebayfrontend.R;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolderDatos> implements View.OnClickListener{
    ArrayList<MovieModel> movieList;
    Context context;
    private View.OnClickListener listener;
    public MovieListAdapter(ArrayList<MovieModel> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent,false);
        view.setOnClickListener(this);
        return new MovieListAdapter.ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.txTitle.setText("Título: "+movieList.get(position).getProductName());
        holder.txIDAndFormat.setText("Código película: "+movieList.get(position).getProductCode() +" | Formato: "+movieList.get(position).getFormat());
        holder.txSupplier.setText( "Proveedor: "+movieList.get(position).getProviderName());
        holder.txDate.setText("Fecha de creación: "+changeDateFormat(movieList.get(position).getCreationDate()));
        holder.txQuantity.setText(movieList.get(position).getQttyReceived()+"");
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;

    }
    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }

    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView txTitle;
        TextView txIDAndFormat;
        TextView txSupplier;
        TextView txDate;
        TextView txQuantity;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            txTitle = (TextView) itemView.findViewById(R.id.tvTitulo);
            txIDAndFormat = (TextView) itemView.findViewById(R.id.tvIDAndFormat);
            txSupplier = (TextView) itemView.findViewById(R.id.tvSupplier);
            txDate =(TextView) itemView.findViewById(R.id.tvDate);
            txQuantity = (TextView) itemView.findViewById(R.id.tvQuantity);

        }
    }
    private String changeDateFormat(String date){
        String [] aux = date.split("-");
        return aux[2]+"-"+aux[1]+"-"+aux[0];
    }
}
