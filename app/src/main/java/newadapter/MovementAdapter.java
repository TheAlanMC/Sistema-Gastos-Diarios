package newadapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemagestiongastos.R;

import java.util.ArrayList;

import models.MovementModel;

public class MovementAdapter extends RecyclerView.Adapter<MovementAdapter.ViewDataHolder> {

    ArrayList<MovementModel> lista = new ArrayList<>();

    public MovementAdapter(ArrayList<MovementModel> lista) {
        this.lista = lista;
    }

    public ArrayList<MovementModel> getLista() {
        return lista;
    }

    public void setLista(ArrayList<MovementModel> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public MovementAdapter.ViewDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_movimiento, parent, false);
        return new ViewDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovementAdapter.ViewDataHolder holder, int position) {
        String[] fechahora = lista.get(position).getFechaHoraMovimiento().split(" ");
        String[] fecha = fechahora[0].split("-");
        String[] hora = fechahora[1].split(":");
        String[] mes = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        holder.tvfechahora.setText(fecha[2] + " de " + mes[Integer.parseInt(fecha[1]) - 1] + " del " + fecha[0] + " a las " + hora[0] + ":" + hora[1]);

        int tipo = lista.get(position).getTipoMovimiento();

        String[] textArray = {"Tarjeta", "Efectivo", "Ahorros"};
        String[] textArrayCatInc = {"Prestamo", "Salario", "Ventas"};
        Integer[] imageArrayCatInc = {R.drawable.loan, R.drawable.salary, R.drawable.sales};

        String[] textArrayCatExp = {"Otros", "Ropa", "Bebidas", "Educacion", "Comida", "Combustible", "Diversion", "Salud", "Viaje", "Hotel",
                "Mercaderia", "Personal", "Mascotas", "Restaurante", "Propinas", "Transporte"};
        Integer[] imageArrayCatExp = {R.drawable.question, R.drawable.clothes, R.drawable.drink, R.drawable.education, R.drawable.food,
                R.drawable.fuel, R.drawable.fun, R.drawable.healthcare, R.drawable.road, R.drawable.hotel, R.drawable.box,
                R.drawable.hands, R.drawable.paw, R.drawable.restaurant, R.drawable.tip, R.drawable.taxi};

        String descripcion = "";
        if (tipo == 1) {
            holder.tvcategoria.setText(textArrayCatInc[lista.get(position).getCategoriaIdMovimiento() - 1]);
            holder.ivimagen.setImageResource(imageArrayCatInc[lista.get(position).getCategoriaIdMovimiento() - 1]);
            holder.tvingresogasto.setText("+");
            holder.tvingresogasto.setTextColor(Color.parseColor("#4CAF50"));
            holder.tvdescripcion.setText(lista.get(position).getDescripcionMovimiento());
        } else if (tipo == 2) {
            holder.tvcategoria.setText(textArrayCatExp[lista.get(position).getCategoriaIdMovimiento() - 1]);
            holder.ivimagen.setImageResource(imageArrayCatExp[lista.get(position).getCategoriaIdMovimiento() - 1]);
            holder.tvingresogasto.setText("-");
            holder.tvingresogasto.setTextColor(Color.parseColor("#F44336"));
            holder.tvdescripcion.setText(lista.get(position).getDescripcionMovimiento());
        } else if (tipo == 3) {
            holder.tvcategoria.setText("Transferencia");
            holder.ivimagen.setImageResource(R.drawable.left_right);
            holder.tvingresogasto.setText("-");
            holder.tvingresogasto.setTextColor(Color.parseColor("#F44336"));
            descripcion = lista.get(position).getDescripcionMovimiento() + (" (Transferencia de ") +
                    textArray[lista.get(position).getFuenteIdMovimiento() - 1] + (" a ") +
                    textArray[lista.get(position).getCategoriaIdMovimiento() - 1] + (")");
            holder.tvdescripcion.setText(descripcion);
        } else if (tipo == 4) {
            holder.tvcategoria.setText("Transferencia");
            holder.ivimagen.setImageResource(R.drawable.left_right);
            holder.tvingresogasto.setText("+");
            holder.tvingresogasto.setTextColor(Color.parseColor("#4CAF50"));
            descripcion= lista.get(position).getDescripcionMovimiento() + (" (Transferencia de ") +
                    textArray[lista.get(position).getFuenteIdMovimiento() - 1] + (" a ") +
                    textArray[lista.get(position).getCategoriaIdMovimiento() - 1] + (")");
            holder.tvdescripcion.setText(descripcion);
        }
        holder.tvmonto.setText("Bs." + lista.get(position).getMontoMovimiento());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewDataHolder extends RecyclerView.ViewHolder {
        TextView tvfechahora, tvcategoria, tvmonto, tvdescripcion, tvingresogasto;
        ImageView ivimagen;

        public ViewDataHolder(@NonNull View itemView) {
            super(itemView);
            tvfechahora = itemView.findViewById(R.id.tvFechaHora);
            tvcategoria = itemView.findViewById(R.id.tvCategoria);
            tvmonto = itemView.findViewById(R.id.tvMonto);
            tvdescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvingresogasto = itemView.findViewById(R.id.tvIngresoGasto);
            ivimagen = itemView.findViewById(R.id.ivMovimiento);
        }
    }
}
