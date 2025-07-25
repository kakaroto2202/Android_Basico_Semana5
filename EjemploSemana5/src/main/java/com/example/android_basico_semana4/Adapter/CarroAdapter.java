package com.example.android_basico_semana4.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android_basico_semana4.R;
import com.example.android_basico_semana4.dm.Carro;

import java.util.List;

public class CarroAdapter extends BaseAdapter {

    private Context context;
    private List<Carro> listaCarros;
    private LayoutInflater inflater;

    // Constructor
    public CarroAdapter(Context context, List<Carro> listaCarros) {
        this.context = context;
        this.listaCarros = listaCarros;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listaCarros.size();
    }

    @Override
    public Object getItem(int position) {
        return listaCarros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_carro, parent, false);
            holder = new ViewHolder();
            holder.tvFabricante = convertView.findViewById(R.id.tvFabricante);
            //findViewById
            holder.tvModelo = convertView.findViewById(R.id.tvModelo);
            holder.tvAnio = convertView.findViewById(R.id.tvAnio);
            holder.tvGasolina = convertView.findViewById(R.id.tvGasolina);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Obtener el carro actual
        Carro carro = listaCarros.get(position);

        // Llenar los datos en la vista
        holder.tvFabricante.setText("Fabricante: " + carro.getFabricante());
        holder.tvModelo.setText("Modelo: " + carro.getModelo());
        holder.tvAnio.setText("Año: " + carro.getAnio());
        holder.tvGasolina.setText("Gasolina (lt): " + carro.getLtGasolina());

        return convertView;
    }


    // ViewHolder para optimizar el rendimiento
    private static class ViewHolder {
        TextView tvFabricante;
        TextView tvModelo;
        TextView tvAnio;
        TextView tvGasolina;
    }
}