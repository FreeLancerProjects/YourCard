package com.yourcard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.yourcard.R;
import com.yourcard.activities_fragments.activity_cart.CartActivity;
import com.yourcard.databinding.CartRowBinding;
import com.yourcard.models.Add_Order_Model;

import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class Cart_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Add_Order_Model.Details> orderlist;
    private Context context;
    private LayoutInflater inflater;
    private String lang;
    private CartActivity cartActivity;
    private int lastPosition=-1;

    public Cart_Adapter(List<Add_Order_Model.Details> orderlist, Context context) {
        this.orderlist = orderlist;
        this.context = context;
        inflater = LayoutInflater.from(context);
        Paper.init(context);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        cartActivity = (CartActivity) context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        CartRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.cart_row, parent, false);
        return new EventHolder(binding);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        EventHolder eventHolder = (EventHolder) holder;

//        eventHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cartActivity.Complete();
//            }
//        });
        eventHolder.binding.imageDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof CartActivity) {
                    cartActivity = (CartActivity) context;
                    cartActivity.removeitem(eventHolder.getLayoutPosition());
                }
            }
        });
        eventHolder.binding.imageIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof CartActivity) {
                    cartActivity = (CartActivity) context;
                    cartActivity.additem(eventHolder.getLayoutPosition());
                }
            }
        });
        eventHolder.binding.imageDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof CartActivity) {
                    cartActivity = (CartActivity) context;
                    cartActivity.minusitem(eventHolder.getLayoutPosition());
                }
            }
        });
        setAnimation(holder.itemView, position);

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
        return orderlist.size();
    }

    public class EventHolder extends RecyclerView.ViewHolder {
        public CartRowBinding binding;

        public EventHolder(@NonNull CartRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }


}
