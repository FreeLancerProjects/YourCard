package com.yourcard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yourcard.R;
import com.yourcard.activities_fragments.activity_home.HomeActivity;
import com.yourcard.databinding.CardRowBinding;
import com.yourcard.databinding.CategoryRowBinding;
import com.yourcard.models.SingleRestaurantModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SingleRestaurantModel> list;
    private Context context;
    private LayoutInflater inflater;
    private int lastPosition = -1;

    public CategoryAdapter(List<SingleRestaurantModel> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        CategoryRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.category_row, parent, false);
        return new MyHolder(binding);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MyHolder myHolder = (MyHolder) holder;
        CardAdapter cardAdapter = new CardAdapter(list.get(position).getMenu_images(), context);
        myHolder.binding.recViewAccessories.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
        myHolder.binding.recViewAccessories.setAdapter(cardAdapter);
        Runnable startAnimation = new Runnable() {
            @Override
            public void run() {
                setAnimation(myHolder.itemView, position);

            }
        };
        myHolder.itemView.postDelayed(startAnimation, 200);
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.top_down_anim);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public CategoryRowBinding binding;

        public MyHolder(@NonNull CategoryRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }


}
