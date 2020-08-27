package com.yourcard.activities_fragments.activity_my_orders;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.yourcard.R;
import com.yourcard.databinding.ActivityMyOrdersBinding;
import com.yourcard.interfaces.Listeners;
import com.yourcard.language.Language;
import com.yourcard.models.UserModel;
import com.yourcard.preferences.Preferences;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrderActivity extends AppCompatActivity implements Listeners.BackListener {
    private ActivityMyOrdersBinding binding;
    private String lang;

    //    private OrderAdapter adapter;
//    private List<SingleOrderModel> orderModelList;
    private boolean isLoading = false;
    private int current_page = 1;
    private Preferences preferences;
    private UserModel userModel;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(Language.updateResources(base, Language.getLanguage(base)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_orders);
        YoYo.with(Techniques.SlideInUp)
                .duration(900)
                .repeat(0)
                .playOn(binding.getRoot());
        initView();


    }

    private void initView() {
        //orderModelList = new ArrayList<>();
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);
        Paper.init(this);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setLang(lang);
        binding.setBackListener(this);
        binding.progBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        binding.recView.setLayoutManager(new LinearLayoutManager(this));

//        adapter = new OrderAdapter(this, orderModelList);
//        binding.recView.setAdapter(adapter);


        getOrders();
    }


    public void getOrders() {
//        try {
//
//            Api.getService(Tags.base_url)
//                    .getOrders(userModel.getUser().getToken(), String.valueOf(userModel.getUser().getId()), "on", 1, 20)
//                    .enqueue(new Callback<OrderDataModel>() {
//                        @Override
//                        public void onResponse(Call<OrderDataModel> call, Response<OrderDataModel> response) {
//                            binding.progBar.setVisibility(View.GONE);
//
//                            if (response.isSuccessful() && response.body() != null && response.body().getData() != null) {
//                                orderModelList.clear();
//                                orderModelList.addAll(response.body().getData());
//                                if (orderModelList.size() > 0) {
//                                    adapter.notifyDataSetChanged();
//                                    binding.tvNoData.setVisibility(View.GONE);
//                                } else {
//                                    binding.tvNoData.setVisibility(View.VISIBLE);
//
//                                }
//                            } else {
//                                if (response.code() == 500) {
//                                    Toast.makeText(MyOrderActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
//
//
//                                } else {
//                                    Toast.makeText(MyOrderActivity.this, getString(R.string.failed), Toast.LENGTH_SHORT).show();
//
//                                    try {
//
//                                        Log.e("error", response.code() + "_" + response.errorBody().string());
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<OrderDataModel> call, Throwable t) {
//                            try {
//
//                                binding.progBar.setVisibility(View.GONE);
//
//                                if (t.getMessage() != null) {
//                                    Log.e("error", t.getMessage());
//                                    if (t.getMessage().toLowerCase().contains("failed to connect") || t.getMessage().toLowerCase().contains("unable to resolve host")) {
//                                        Toast.makeText(MyOrderActivity.this, R.string.something, Toast.LENGTH_SHORT).show();
//                                    } else {
//                                        Toast.makeText(MyOrderActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//
//                            } catch (Exception e) {
//                            }
//                        }
//                    });
//        } catch (Exception e) {
//
//        }

    }


    @Override
    public void back() {
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (preferences != null) {
            getOrders();
        }
    }
}
