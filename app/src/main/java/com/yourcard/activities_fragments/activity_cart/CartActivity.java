package com.yourcard.activities_fragments.activity_cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;


import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.yourcard.R;
import com.yourcard.activities_fragments.activity_bill.BillActivity;
import com.yourcard.activities_fragments.activity_recharge.RechargeActivity;
import com.yourcard.adapters.Cart_Adapter;
import com.yourcard.databinding.ActivityCartBinding;
import com.yourcard.interfaces.Listeners;
import com.yourcard.language.Language;
import com.yourcard.models.Add_Order_Model;
import com.yourcard.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class CartActivity extends AppCompatActivity implements Listeners.BackListener {
    private ActivityCartBinding binding;

    private Preferences preferences;
    private String lang;
    private List<Add_Order_Model.Details> order_details;
    private Cart_Adapter cart_adapter;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(Language.updateResources(base, Language.getLanguage(base)));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart);

        initView();
        YoYo.with(Techniques.ZoomIn)
                .duration(900)
                .repeat(0)
                .playOn(binding.getRoot());
    }


    @SuppressLint("RestrictedApi")
    private void initView() {
        preferences = Preferences.getInstance();
        order_details = new ArrayList<>();
        cart_adapter = new Cart_Adapter(order_details, this);
        Paper.init(this);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setLang(lang);
        binding.setBackListener(this);
        binding.recView.setLayoutManager(new GridLayoutManager(this, 1));
        binding.recView.setAdapter(cart_adapter);
        setdtat();

        binding.btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, BillActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setdtat() {
        order_details.add(new Add_Order_Model.Details());
        order_details.add(new Add_Order_Model.Details());
        order_details.add(new Add_Order_Model.Details());
        order_details.add(new Add_Order_Model.Details());
        order_details.add(new Add_Order_Model.Details());
        order_details.add(new Add_Order_Model.Details());
        order_details.add(new Add_Order_Model.Details());

        cart_adapter.notifyDataSetChanged();
    }


    @Override
    public void back() {
        finish();
    }

    public void removeitem(int layoutPosition) {


    }

    public void additem(int layoutPosition) {

    }

    public void minusitem(int layoutPosition) {


    }

    private void gettotal() {

        double total = 0;
        for (int i = 0; i < order_details.size(); i++) {
            total += order_details.get(i).getPrice();

        }


    }
}
