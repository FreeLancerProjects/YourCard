package com.yourcard.activities_fragments.activity_card_details;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.yourcard.R;

import com.yourcard.activities_fragments.activity_card_type.CardTypeActivity;
import com.yourcard.activities_fragments.activity_home.HomeActivity;
import com.yourcard.adapters.SlidingMenuImage_Adapter;
import com.yourcard.adapters.TypeAdapter;
import com.yourcard.databinding.ActivityCardDetialsBinding;
import com.yourcard.interfaces.Listeners;
import com.yourcard.language.Language;

import com.yourcard.models.SingleRestaurantModel;
import com.yourcard.models.UserModel;
import com.yourcard.preferences.Preferences;
import com.yourcard.remote.Api;
import com.yourcard.share.Common;
import com.yourcard.tags.Tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.yourcard.Animate.CircleAnimationUtil;

public class CardDetailsActivity extends AppCompatActivity implements Listeners.BackListener, Listeners.CardDetialsAction {
    private ActivityCardDetialsBinding binding;
    private String lang;
    private BottomSheetBehavior behavior;
    private ImageView imclose;
    private RecyclerView reccardtypes;
    private List<SingleRestaurantModel.MenuImages> menuImagesList;
    private TypeAdapter typeAdapter;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(Language.updateResources(base, Language.getLanguage(base)));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_card_detials);
        YoYo.with(Techniques.SlideInUp)
                .duration(1500)
                .repeat(0)
                .playOn(binding.getRoot());
        initView();


        setUpBottomSheet();
    }

    private void setUpBottomSheet() {

        behavior = BottomSheetBehavior.from(findViewById(R.id.root));

    }


    private void initView() {

        Paper.init(this);
        menuImagesList = new ArrayList<>();
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setBackListener(this);
        binding.setCardListener(this);
        binding.setLang(lang);
        imclose = findViewById(R.id.imclose);
        reccardtypes = findViewById(R.id.recViewcardtype);
        typeAdapter = new TypeAdapter(menuImagesList, this);
        reccardtypes.setLayoutManager(new LinearLayoutManager(this));
        reccardtypes.setAdapter(typeAdapter);
        imclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

            }
        });
initData();
    }

    private void initData() {
        menuImagesList.add(new SingleRestaurantModel.MenuImages());
        menuImagesList.add(new SingleRestaurantModel.MenuImages());
        menuImagesList.add(new SingleRestaurantModel.MenuImages());
        menuImagesList.add(new SingleRestaurantModel.MenuImages());
        menuImagesList.add(new SingleRestaurantModel.MenuImages());
        menuImagesList.add(new SingleRestaurantModel.MenuImages());


        typeAdapter.notifyDataSetChanged();


    }

    @Override
    public void back() {
//        if (isDataAdded) {
//            setResult(RESULT_OK);
//        }
        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {

            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            finish();
        }
    }


    @Override
    public void onBackPressed() {
        back();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
//            createOrderModel = preferences.getCartData(this);
//            if (createOrderModel == null) {
//                createOrderModel = new CreateOrderModel();
//                createOrderModel.setMarkter_id(market.getId());
//                binding.setCartCount(0);
//                isDataAdded = true;
//
//            } else {
//
//                binding.setCartCount(createOrderModel.getProducts().size());
//            }
//        }
        }
    }


    @Override
    public void choosecard() {
//        Intent intent = new Intent(CardDetailsActivity.this, CardTypeActivity.class);
//        startActivity(intent);

        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);

    }
}
