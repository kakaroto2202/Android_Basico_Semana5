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

public class BrandAdapter extends BaseAdapter {
    private Context context;
    private List<Carro> lista;
    private LayoutInflater inflater;

    public BrandAdapter(Context context, List<Carro> lista) {
        this.context = context;
        this.lista = lista;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position).getFabricante();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_marca, parent, false);
        }

        TextView tvMarca = view.findViewById(R.id.tvMarca);
        tvMarca.setText(lista.get(position).getFabricante());
        return view;
    }
}