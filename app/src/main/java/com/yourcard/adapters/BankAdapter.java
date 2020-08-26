package com.yourcard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.yourcard.R;
import com.yourcard.databinding.BankRowBinding;
import com.yourcard.models.SingleRestaurantModel;

import java.util.List;

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.MyHolder> {

    private List<SingleRestaurantModel.MenuImages> bankDataModelList;
    private Context context;

    public BankAdapter(List<SingleRestaurantModel.MenuImages> bankDataModelList, Context context) {
        this.bankDataModelList = bankDataModelList;
        this.context = context;


    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BankRowBinding bankRowBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.bank_row, parent, false);
        return new MyHolder(bankRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return bankDataModelList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private BankRowBinding bankRowBinding;

        public MyHolder(BankRowBinding bankRowBinding) {
            super(bankRowBinding.getRoot());
            this.bankRowBinding = bankRowBinding;


        }


    }
}
