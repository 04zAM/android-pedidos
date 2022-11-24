package com.example.utnpedidos001;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.utnpedidos001.classes.Producto;

import java.util.List;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.MyViewHolder> {

    private List<Producto> listaProductos;

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public AdaptadorProductos(List<Producto> productos) {
        this.listaProductos = productos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View filaProducto = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fila_recycler, viewGroup, false);
        return new MyViewHolder(filaProducto);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Producto producto = listaProductos.get(position);

        // Obtener los datos de la lista
        String nombre = producto.descripcion;
        float detalle = producto.precio;
        // Y poner a los TextView los datos con setText
        holder.nombre.setText(nombre);
        holder.detalle.setText(String.valueOf(detalle));
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, detalle;

        MyViewHolder(View itemView) {
            super(itemView);
            this.nombre = itemView.findViewById(R.id.tvNombre);
            this.detalle = itemView.findViewById(R.id.tvDetalle);
        }
    }
}
