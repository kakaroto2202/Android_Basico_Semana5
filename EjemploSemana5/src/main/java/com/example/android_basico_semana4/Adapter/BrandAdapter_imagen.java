package com.example.android_basico_semana4.Adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_basico_semana4.R;
import com.example.android_basico_semana4.dm.Carro;

import java.util.List;

public class BrandAdapter_imagen extends BaseAdapter {
    private Context context;
    private List<Carro> lista;
    private LayoutInflater inflater;

    private ImageView imageView2;

    public BrandAdapter_imagen(Context context, List<Carro> lista) {
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
            view = inflater.inflate(R.layout.item_marca_imagen, parent, false);
        }

        TextView tvMarca = view.findViewById(R.id.tvMarca);
        tvMarca.setText(lista.get(position).getFabricante());

        imageView2 = view.findViewById(R.id.imageView2);

        cargarImagenPorNombre(lista.get(position).getFabricante().toLowerCase());

        return view;
    }

    private void cargarImagenPorNombre(String imageName) {
        if (imageName.isEmpty()) {
          //  Toast.makeText(this, "Introduce un nombre de imagen", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtiene el identificador: R.drawable.imageName
        int resId = ((Activity)inflater.getContext()).getResources().getIdentifier(
                imageName,
                "drawable",
                ((Activity)inflater.getContext()).getPackageName()
        );

        if (resId != 0) {
            imageView2.setImageResource(resId);
        } else {
         /*   Toast.makeText(this,
                    "Imagen \"" + imageName + "\" no encontrada",
                    Toast.LENGTH_SHORT).show();*/
        }
    }
}