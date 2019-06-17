package com.teo.cakes.view.adaptor;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import com.teo.cakes.R;
import com.teo.cakes.databinding.CakeListItemBinding;
import com.teo.cakes.service.model.Cake;
import com.teo.cakes.view.callback.CakeClickCallback;

import java.util.List;

public class CakeAdapter extends RecyclerView.Adapter<CakeAdapter.CakeViewHolder> {

        private Context context;
        private List<Cake> list;

        public CakeAdapter(Context context, List<Cake> list) {
            //super(context, R.layout.cake_list_item, list);
            this.context = context;
            this.list = list;
        }

        @Override
        public CakeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //View v = LayoutInflater.from(context).inflate(R.layout.cake_list_item, parent, false);
            CakeListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.cake_list_item,
                        parent, false);

        binding.setCallback(new CakeClickCallback());

        return new CakeViewHolder(binding);
           // return new ViewHolder(binding);
        }



    @Override
        public void onBindViewHolder(CakeViewHolder holder, int position) {
            Cake cake = list.get(position);

            holder.binding.title.setText(cake.getTitle());

            Picasso.with(context).load(cake.getImage())
                    .error(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(holder.binding.image);

            holder.binding.setCake(cake);


        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView title;
            public ImageView image;

            public ViewHolder(View itemView) {
                super(itemView);

                title = itemView.findViewById(R.id.title);
                image = itemView.findViewById(R.id.image);



            }
        }


    static class CakeViewHolder extends RecyclerView.ViewHolder {

        final CakeListItemBinding binding;

        public CakeViewHolder(CakeListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
    }

