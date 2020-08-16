package com.yourcard.activities_fragments.activity_home.fragments;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yourcard.R;
import com.yourcard.activities_fragments.activity_home.HomeActivity;

import com.yourcard.adapters.SlidingMenuImage_Adapter;
import com.yourcard.databinding.FragmentMainBinding;

import com.yourcard.models.SingleRestaurantModel;
import com.yourcard.models.UserModel;
import com.yourcard.preferences.Preferences;
import com.yourcard.remote.Api;
import com.yourcard.share.Common;
import com.yourcard.tags.Tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.paperdb.Paper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class Fragment_Main extends Fragment {
    private HomeActivity activity;
    private FragmentMainBinding binding;
    private Preferences preferences;
    private String lang;
    private TimerTask timerTask;
    private Timer timer;
    private int current_page = 0, NUM_PAGES;
    private LinearLayoutManager manager;
    private boolean isLoading = false;
    private UserModel userModel;
    private SlidingMenuImage_Adapter slidingImage__adapter;
    private List<SingleRestaurantModel.MenuImages> menuImages;

    public static Fragment_Main newInstance() {
        return new Fragment_Main();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();

        change_slide_image();


    }

    private void initData() {
        menuImages.add(new SingleRestaurantModel.MenuImages());
        menuImages.add(new SingleRestaurantModel.MenuImages());
        menuImages.add(new SingleRestaurantModel.MenuImages());
        menuImages.add(new SingleRestaurantModel.MenuImages());
        menuImages.add(new SingleRestaurantModel.MenuImages());
        menuImages.add(new SingleRestaurantModel.MenuImages());
        slidingImage__adapter = new SlidingMenuImage_Adapter(activity, menuImages);
        binding.pager.setAdapter(slidingImage__adapter);
        binding.progBarSlider.setVisibility(View.GONE);
    }

    private void initView() {
        menuImages = new ArrayList<>();
        activity = (HomeActivity) getActivity();
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(activity);
        Paper.init(activity);
        lang = Paper.book().read("lang", "ar");

        binding.progBarSlider.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        binding.progBarOffer.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        binding.progBarAccessories.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);


    }


    private void change_slide_image() {
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (current_page == NUM_PAGES) {
                    current_page = 0;
                }
                binding.pager.setCurrentItem(current_page++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);
    }


}
