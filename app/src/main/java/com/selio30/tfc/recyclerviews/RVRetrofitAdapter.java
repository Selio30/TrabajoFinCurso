package com.selio30.tfc.recyclerviews;

import static android.view.View.FOCUS_FORWARD;

import android.app.AlertDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.selio30.tfc.R;
import com.selio30.tfc.entity.Producto;

import java.util.List;

public class RVRetrofitAdapter extends RecyclerView.Adapter<RVRetrofitAdapter.RVHolderRetrofit> {
    //Create variable
    List<Producto> productos;
    Context mCntext;
    int size;

    boolean isOnTextChanged = false;

    //generate constructor
    public RVRetrofitAdapter(Context mCntext, List<Producto> productos, int size) {
        this.mCntext = mCntext;
        this.productos = productos;
        this.size = size;
    }


    @NonNull
    @Override
    public RVHolderRetrofit onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Layout inflater
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_layout, parent, false);
        return new RVHolderRetrofit(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVHolderRetrofit holder, int position) {
        //get position
        Producto producto = productos.get(position);
        holder.prodName.setText(producto.getName());
        holder.prodStock.setHint(String.valueOf(producto.getStock()));

        //Si se cambia algo del editable se llama al metodo correspondiente
        holder.prodStock.addTextChangedListener(new TextWatcher() {
            //Se llama a este método para notificar que se ha cambiado el texto
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            //Se llama a este método para notificar que están a putno de reemplazar el texto por uno nuevo
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isOnTextChanged = true;
            }

            //Se llama a este método para notificar que se acaba de reemplazar el texto anterior
            @Override
            public void afterTextChanged(Editable editable) {
                if (isOnTextChanged) {
                    isOnTextChanged = false;
                    //Si el editable esta vacío no peta, sino que se pone a 0.
                    if (String.valueOf(holder.prodStock.getText()).equals("")) {
                        producto.setStock("0");
                    } else {
                        producto.setStock(String.valueOf(holder.prodStock.getText()));
                    }
                }
            }
        });

        //Definición de interfaz para invocar una devolución de llamada cuando se realiza una acción en el editor
        holder.prodStock.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    View view = textView.focusSearch(View.FOCUS_LEFT);
                    if (view != null) {
                        return !view.requestFocus(FOCUS_FORWARD);
                    }
                    return false;
                }
                return false;
            }
        });

        //Metodo para crear el dialogo popup
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                View dialogView = LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.producto_dialog, null);
                TextView tvName, tvDesc;
                ImageView imgProducto;
                tvName = dialogView.findViewById(R.id.tvname);
                tvDesc = dialogView.findViewById(R.id.tvdescri);

                //Tema de añadir imagen
//                Glide.with(mCntext).
//                        load(producto.getImageURL()).
//                        placeholder(R.drawable.logo).error(R.drawable.sin_imagen).into(holder.prodImg);

                tvName.setText(String.valueOf(producto.getName()));
//                tvDesc.setHint(String.valueOf(producto.getStock()));

                builder.setView(dialogView);
                builder.setCancelable(true);
                builder.show();
            }
        });
    }

    /**
     * Obtiene los elementos de la lista
     *
     * @return elementos de la lista
     */

    @Override
    public int getItemCount() {
        return productos.size();
    }

    /**
     * Clase que define cada uno de los holders
     */

    public static class RVHolderRetrofit extends RecyclerView.ViewHolder {

        TextView prodName;
        EditText prodStock;
        CardView cardView;

        public RVHolderRetrofit(@NonNull View itemView) {
            super(itemView);

            prodName = itemView.findViewById(R.id.tvname);
            prodStock = itemView.findViewById(R.id.etstock);
            cardView = itemView.findViewById(R.id.cv);
        }
    }
}
