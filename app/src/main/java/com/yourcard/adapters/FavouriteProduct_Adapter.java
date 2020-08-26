package com.yourcard.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.yourcard.R;
import com.yourcard.databinding.FavouriteRowBinding;
import com.yourcard.models.SingleRestaurantModel;

import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class FavouriteProduct_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SingleRestaurantModel.MenuImages> list;
    private Context context;
    private LayoutInflater inflater;
    private String lang;
    private int i = 0;

    public FavouriteProduct_Adapter(List<SingleRestaurantModel.MenuImages> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        Paper.init(context);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        FavouriteRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.favourite_row, parent, false);
        return new EventHolder(binding);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder eventHohlder, int position) {

        EventHolder myHolder = (EventHolder) eventHohlder;


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EventHolder extends RecyclerView.ViewHolder {
        public FavouriteRowBinding binding;

        public EventHolder(@NonNull FavouriteRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }
    }


}
