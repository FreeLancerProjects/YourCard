package com.yourcard.activities_fragments.activity_home.fragments;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.appbar.AppBarLayout;
import com.yourcard.R;
import com.yourcard.activities_fragments.activity_edit_profile.EditProfileActivity;
import com.yourcard.activities_fragments.activity_home.HomeActivity;
import com.yourcard.activities_fragments.activity_my_favorite.MyFavoriteActivity;
import com.yourcard.activities_fragments.activity_recharge.RechargeActivity;
import com.yourcard.activities_fragments.bank_activity.BanksActivity;
import com.yourcard.adapters.CardAdapter;
import com.yourcard.adapters.SlidingMenuImage_Adapter;
import com.yourcard.databinding.FragmentMainBinding;
import com.yourcard.databinding.FragmentProfileBinding;
import com.yourcard.interfaces.Listeners;
import com.yourcard.models.SingleRestaurantModel;
import com.yourcard.models.UserModel;
import com.yourcard.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import io.paperdb.Paper;

public class Fragment_profile extends Fragment implements Listeners.ProfileActions {
    private HomeActivity activity;
    private FragmentProfileBinding binding;
    private Preferences preferences;
    private String lang;

    public static Fragment_profile newInstance() {
        return new Fragment_profile();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        YoYo.with(Techniques.SlideInUp)
                .duration(1500)
                .repeat(0)
                .playOn(binding.getRoot());
        initView();


    }


    private void initView() {
        activity = (HomeActivity) getActivity();
        preferences = Preferences.getInstance();
        Paper.init(activity);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setLang(lang);
        binding.setListener(this);
//        binding.appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                //  Vertical offset == 0 indicates appBar is fully expanded.
//                Log.e("l;ll",Math.abs(verticalOffset)+"");
//                if (Math.abs(verticalOffset) > 200) {
//                    binding.llshow.setVisibility(View.VISIBLE);
//                    binding.llshow2.setVisibility(View.GONE);
//
//                } else {
//                    binding.llshow.setVisibility(View.GONE);
//                    binding.llshow2.setVisibility(View.VISIBLE);
//
//
//                }
//            }
//        });
    }


    @Override
    public void order() {

    }

    @Override
    public void charge() {
        Intent intent = new Intent(activity, RechargeActivity.class);
        startActivity(intent);
    }

    @Override
    public void aboutApp() {

    }

    @Override
    public void logout() {

    }

    @Override
    public void favorite() {
        Intent intent = new Intent(activity, MyFavoriteActivity.class);
        startActivity(intent);
    }

    @Override
    public void bankAccount() {
        Intent intent = new Intent(activity, BanksActivity.class);
        startActivity(intent);
    }

    @Override
    public void profile() {
        Intent intent = new Intent(activity, EditProfileActivity.class);
        startActivity(intent);
    }
}
