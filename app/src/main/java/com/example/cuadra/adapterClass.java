package com.example.cuadra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapterClass extends RecyclerView.Adapter<adapterClass.modelViewHolder> {
    List<ModelClass> myList;
    Context context;
    public adapterClass(List<ModelClass> myList, Context context){
        this.myList = myList;
        this.context = context;
    }
    @NonNull
    @Override
    public modelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.recycler_layout,viewGroup,false);
        modelViewHolder obj= new modelViewHolder(view);
        return obj;
    }

    @Override
    public void onBindViewHolder(@NonNull modelViewHolder modelViewHolder, int i) {
        modelViewHolder.numero.setText(myList.get(i).getNumero());
        modelViewHolder.fecha.setText(myList.get(i).getFecha());
        modelViewHolder.cuenta.setText(myList.get(i).getCuenta());
        modelViewHolder.monto.setText(myList.get(i).getMonto());
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class modelViewHolder extends RecyclerView.ViewHolder {
        TextView numero,fecha,cuenta,monto;
        public modelViewHolder(@NonNull View itemView) {
            super(itemView);
            numero=itemView.findViewById(R.id.numero);
            fecha=itemView.findViewById(R.id.fecha);
            cuenta=itemView.findViewById(R.id.cuenta);
            monto=itemView.findViewById(R.id.monto);
            numero.setTextSize(9);
            numero.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            fecha.setTextSize(9);
            fecha.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            cuenta.setTextSize(9);
            cuenta.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            monto.setTextSize(11);
            monto.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);

        }
    }
}
