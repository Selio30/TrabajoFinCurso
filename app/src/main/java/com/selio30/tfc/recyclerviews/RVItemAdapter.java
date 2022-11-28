package com.selio30.tfc.recyclerviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.selio30.tfc.R;
import com.selio30.tfc.entity.Producto;
import com.selio30.tfc.entity.Ubicacion;
import com.selio30.tfc.utils.GestionProductos;
import com.selio30.tfc.viewmodel.ProductoViewModel;

import java.util.ArrayList;
import java.util.List;

public class RVItemAdapter extends RecyclerView.Adapter<RVItemAdapter.ItemViewHolder> {
    //Create variable
    private List<Ubicacion> mList;
    private List<Producto> list = new ArrayList<>();
    private Context mCntext;
    private int size;
    private LayoutInflater mInflater;
    private ProductoViewModel model;

    //generate constructor
    public RVItemAdapter(Context mCntext, List<Ubicacion> mList, List<Producto> list) {
        this.mCntext = mCntext;
        this.mList = mList;
        this.list = list;
        this.mInflater = LayoutInflater.from(mCntext);
        this.model = new ViewModelProvider(((FragmentActivity) mCntext)).get(ProductoViewModel.class);
    }

    public RVItemAdapter(Context mCntext, List<Ubicacion> mList, int size) {
        this.mCntext = mCntext;
        this.mList = mList;
        this.size = size;
        this.mInflater = LayoutInflater.from(mCntext);
        this.model = new ViewModelProvider(((FragmentActivity) mCntext)).get(ProductoViewModel.class);
    }

    public RVItemAdapter(Context mCntext, List<Ubicacion> mList) {
        this.mCntext = mCntext;
        this.mList = mList;
        this.mInflater = LayoutInflater.from(mCntext);
        this.model = new ViewModelProvider(((FragmentActivity) mCntext)).get(ProductoViewModel.class);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Layout inflater
        View view = mInflater.inflate(R.layout.each_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        //get position
        GestionProductos gestionProductos = new GestionProductos();
        Ubicacion ubicacion = mList.get(position);
        holder.mTextView.setText(ubicacion.getName());

        boolean isExpandable = ubicacion.isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

        if (isExpandable) {
            holder.mArrowImage.setImageResource(R.drawable.arrow_up);
        } else {
            holder.mArrowImage.setImageResource(R.drawable.arrow_down);
        }

        RVRetrofitAdapter adapter = new RVRetrofitAdapter(mCntext, list, size);
        holder.listRecyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.listRecyclerView.setHasFixedSize(true);
        holder.listRecyclerView.setAdapter(adapter);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ubicacion.setExpandable(!ubicacion.isExpandable());
                list = ubicacion.getProductos();
                notifyItemChanged(holder.getLayoutPosition());
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
        return mList.size();
    }

    /**
     * Clase que define cada uno de los holders
     */

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        private RelativeLayout expandableLayout;
        private TextView mTextView;
        private ImageView mArrowImage;
        private RecyclerView listRecyclerView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.linear_layout);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);
            mTextView = itemView.findViewById(R.id.itemTv);
            mArrowImage = itemView.findViewById(R.id.arro_imageview);
            listRecyclerView = itemView.findViewById(R.id.child_rv);
        }
    }
}
