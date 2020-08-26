package com.yourcard.activities_fragments.activity_my_favorite;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.yourcard.R;
import com.yourcard.adapters.FavouriteProduct_Adapter;
import com.yourcard.databinding.ActivityMyFavoriteBinding;
import com.yourcard.interfaces.Listeners;
import com.yourcard.language.Language;

import com.yourcard.models.SingleRestaurantModel;
import com.yourcard.models.UserModel;
import com.yourcard.preferences.Preferences;
import com.yourcard.remote.Api;
import com.yourcard.tags.Tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFavoriteActivity extends AppCompatActivity implements Listeners.BackListener {
    private ActivityMyFavoriteBinding binding;
    private String lang;
    private boolean isLoading = false;
    private int current_page = 1;

    private LinearLayoutManager manager;
    private UserModel userModel;
    private Preferences preferences;
 
    private List<SingleRestaurantModel.MenuImages> favouriteDataList;
    private FavouriteProduct_Adapter favouriteProduct_adapter;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(Language.updateResources(base, Language.getLanguage(base)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_favorite);
        YoYo.with(Techniques.ZoomIn)
                .duration(1000)
                .repeat(0)
                .playOn(binding.getRoot());
        initView();
    }


    private void initView() {
        favouriteDataList = new ArrayList<>();
        Paper.init(this);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setBackListener(this);
        binding.setLang(lang);
        binding.progBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);

        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);


        manager = new GridLayoutManager(this, 1);
        binding.recView.setLayoutManager(manager);
        favouriteProduct_adapter = new FavouriteProduct_Adapter(favouriteDataList, this);
        binding.recView.setAdapter(favouriteProduct_adapter);
//        binding.recView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (dy > 0) {
//                    int total = binding.recView.getAdapter().getItemCount();
//
//                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
//
//
//                    if (total > 6 && (total - lastVisibleItem) == 2 && !isLoading) {
//                        isLoading = true;
//                        int page = current_page + 1;
//                        productModelList.add(null);
//                        adapter.notifyDataSetChanged();
//                        loadMore(page);
//                    }
//                }
//            }
//        });*/
        getData();
        updateUI();
    }

    private void updateUI() {
        favouriteDataList.add(new SingleRestaurantModel.MenuImages());
        favouriteDataList.add(new SingleRestaurantModel.MenuImages());
        favouriteDataList.add(new SingleRestaurantModel.MenuImages());
        favouriteDataList.add(new SingleRestaurantModel.MenuImages());
        favouriteDataList.add(new SingleRestaurantModel.MenuImages());
        favouriteDataList.add(new SingleRestaurantModel.MenuImages());
        favouriteProduct_adapter.notifyDataSetChanged();

    }


    public void getData() {
//
//        try {
//
//            String token;
//            if (userModel == null) {
//                token = "";
//            } else {
//                token = userModel.getUser().getToken();
//            }
//            Api.getService(Tags.base_url)
//                    .getMyFavoriteProducts(userModel.getUser().getToken(), "off")
//                    .enqueue(new Callback<FavouriteDataModel>() {
//                        @Override
//                        public void onResponse(Call<FavouriteDataModel> call, Response<FavouriteDataModel> response) {
//                            binding.progBar.setVisibility(View.GONE);
//                            if (response.isSuccessful() && response.body() != null && response.body().getData() != null) {
//                                favouriteDataList.clear();
//                                favouriteDataList.addAll(response.body().getData());
//                                if (favouriteDataList.size() > 0) {
//                                    favouriteProduct_adapter.notifyDataSetChanged();
//                                    binding.tvNoData.setVisibility(View.GONE);
//                                } else {
//                                    binding.tvNoData.setVisibility(View.VISIBLE);
//
//                                }
//                            } else {
//                                if (response.code() == 500) {
//                                    Toast.makeText(MyFavoriteActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
//
//
//                                } else {
//                                    Toast.makeText(MyFavoriteActivity.this, getString(R.string.failed), Toast.LENGTH_SHORT).show();
//
//                                    try {
//
//                                        Log.e("errorsss", response.code() + "_" + response.errorBody().string());
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<FavouriteDataModel> call, Throwable t) {
//                            try {
//                                binding.progBar.setVisibility(View.GONE);
//
//                                if (t.getMessage() != null) {
//                                    Log.e("error", t.getMessage());
//                                    if (t.getMessage().toLowerCase().contains("failed to connect") || t.getMessage().toLowerCase().contains("unable to resolve host")) {
//                                        Toast.makeText(MyFavoriteActivity.this, R.string.something, Toast.LENGTH_SHORT).show();
//                                    } else {
//                                        Toast.makeText(MyFavoriteActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
    public void onBackPressed() {
        back();
    }

}
