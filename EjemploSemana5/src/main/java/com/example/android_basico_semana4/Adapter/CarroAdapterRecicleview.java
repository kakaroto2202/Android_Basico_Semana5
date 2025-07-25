package com.example.android_basico_semana4.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_basico_semana4.R;
import com.example.android_basico_semana4.dm.Carro;

import java.util.List;

public class CarroAdapterRecicleview extends RecyclerView.Adapter<CarroAdapterRecicleview.CarroViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(Carro carro);
    }

    private List<Carro> lista;
    private OnItemClickListener listener;
    private Context context;

    public CarroAdapterRecicleview(Context context, List<Carro> lista, OnItemClickListener listener) {
        this.context  = context;
        this.lista    = lista;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CarroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_carro_carview, parent, false);
        return new CarroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarroViewHolder holder, int position) {
        Carro carro = lista.get(position);
        holder.tvFabricante.setText(carro.getFabricante());
        holder.tvModelo.setText(carro.getModelo());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(carro));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class CarroViewHolder extends RecyclerView.ViewHolder {
        TextView tvFabricante, tvModelo;

        CarroViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFabricante = itemView.findViewById(R.id.tvFabricante);
            tvModelo     = itemView.findViewById(R.id.tvModelo);
        }
    }
}