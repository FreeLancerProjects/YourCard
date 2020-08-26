package com.yourcard.activities_fragments.activity_recharge;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.yourcard.R;
import com.yourcard.adapters.TypeAdapter;

import com.yourcard.databinding.ActivityRechargeBinding;
import com.yourcard.interfaces.Listeners;
import com.yourcard.language.Language;
import com.yourcard.models.SingleRestaurantModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

//import com.yourcard.Animate.CircleAnimationUtil;

public class RechargeActivity extends AppCompatActivity implements Listeners.BackListener {
    private ActivityRechargeBinding binding;
    private String lang;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(Language.updateResources(base, Language.getLanguage(base)));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recharge);
        YoYo.with(Techniques.ZoomInDown)
                .duration(1000)
                .repeat(0)
                .playOn(binding.getRoot());
        initView();


    }




    private void initView() {

        Paper.init(this);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setBackListener(this);

    }


    @Override
    public void back() {
//        if (isDataAdded) {
//            setResult(RESULT_OK);
//        }

            finish();

    }


    @Override
    public void onBackPressed() {
        back();
    }


}
