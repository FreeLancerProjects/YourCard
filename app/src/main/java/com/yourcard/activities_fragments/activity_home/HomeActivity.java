package com.yourcard.activities_fragments.activity_home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.yourcard.R;

import com.yourcard.activities_fragments.activity_card_details.CardDetailsActivity;
import com.yourcard.activities_fragments.activity_chat.ChatActivity;
import com.yourcard.activities_fragments.activity_home.fragments.FragmentDiscountCoupons;
import com.yourcard.activities_fragments.activity_home.fragments.Fragment_Main;

import com.yourcard.activities_fragments.activity_home.fragments.Fragment_Favourite;
import com.yourcard.activities_fragments.activity_home.fragments.Fragment_profile;
import com.yourcard.activities_fragments.activity_login.LoginActivity;

import com.yourcard.activities_fragments.activity_signup.SignUpActivity;
import com.yourcard.activities_fragments.activtitycards.CardsActivity;
import com.yourcard.databinding.ActivityHomeBinding;
import com.yourcard.language.Language;

import com.yourcard.models.UserModel;
import com.yourcard.preferences.Preferences;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private Preferences preferences;
    private FragmentManager fragmentManager;
    private Fragment_Main fragment_main;
    private Fragment_profile fragment_profile;
    private FragmentDiscountCoupons fragmentdiscountcoupons;
    private Fragment_Favourite fragment_Favourite;
    private UserModel userModel;
    private String lang;
    private String token;
    private int back = 0;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(Language.updateResources(base, Language.getLanguage(base)));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        initView();


    }

    private void initView() {
        fragmentManager = getSupportFragmentManager();
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);
        Paper.init(this);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        setUpBottomNavigation();
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });

    }


    private void setUpBottomNavigation() {

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("", R.drawable.ic_home);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("", R.drawable.ic_checked);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("", R.drawable.ic_empty_heart);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("", R.drawable.ic_home);

        binding.ahBottomNav.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        binding.ahBottomNav.setDefaultBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
        binding.ahBottomNav.setTitleTextSizeInSp(13, 13);
        binding.ahBottomNav.setForceTint(true);
        binding.ahBottomNav.setAccentColor(ContextCompat.getColor(this, R.color.colorPrimary));
        binding.ahBottomNav.setInactiveColor(ContextCompat.getColor(this, R.color.gray9));

        binding.ahBottomNav.addItem(item1);
        binding.ahBottomNav.addItem(item2);
        binding.ahBottomNav.addItem(item3);
        binding.ahBottomNav.addItem(item4);


        binding.ahBottomNav.setOnTabSelectedListener((position, wasSelected) -> {
            switch (position) {
                case 0:
                    displayFragmentMain();
                    break;
                case 1:

                    displayFragmentProfile();

                    break;
                case 2:

                    displayFragmentFavourite();

                    break;
                case 3:
                    displayFragmentEditProfile();
                    break;

            }
            return false;
        });

        updateBottomNavigationPosition(0);
        displayFragmentMain();

    }

    public void updateBottomNavigationPosition(int pos) {

        binding.ahBottomNav.setCurrentItem(pos, false);

    }


    public void displayFragmentMain() {
        try {
            if (fragment_main == null) {
                fragment_main = Fragment_Main.newInstance();
            }


            if (fragment_Favourite != null && fragment_Favourite.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_Favourite).commit();
            }
            if (fragment_profile != null && fragment_profile.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_profile).commit();
            }

            if (fragmentdiscountcoupons != null && fragmentdiscountcoupons.isAdded()) {
                fragmentManager.beginTransaction().hide(fragmentdiscountcoupons).commit();
            }
            if (fragment_main.isAdded()) {
                fragmentManager.beginTransaction().show(fragment_main).commit();

            } else {
                fragmentManager.beginTransaction().add(R.id.fragment_app_container, fragment_main, "fragment_main").addToBackStack("fragment_main").commit();

            }
            //  binding.setTitle(getString(R.string.home));
            updateBottomNavigationPosition(0);
        } catch (Exception e) {
        }

    }

    public void displayFragmentProfile() {
        try {
            if (fragment_profile == null) {
                fragment_profile = Fragment_profile.newInstance();
            }


            if (fragment_Favourite != null && fragment_Favourite.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_Favourite).commit();
            }
            if (fragment_main != null && fragment_main.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_main).commit();
            }

            if (fragmentdiscountcoupons != null && fragmentdiscountcoupons.isAdded()) {
                fragmentManager.beginTransaction().hide(fragmentdiscountcoupons).commit();
            }
            if (fragment_profile.isAdded()) {
                fragmentManager.beginTransaction().show(fragment_profile).commit();

            } else {
                fragmentManager.beginTransaction().add(R.id.fragment_app_container, fragment_profile, "fragment_offer").addToBackStack("fragment_offer").commit();

            }
            //  binding.setTitle(getString(R.string.home));
            updateBottomNavigationPosition(1);
        } catch (Exception e) {
        }

    }

    public void displayFragmentFavourite() {
        try {
            if (fragment_Favourite == null) {
                fragment_Favourite = Fragment_Favourite.newInstance();
            }


            if (fragmentdiscountcoupons != null && fragmentdiscountcoupons.isAdded()) {
                fragmentManager.beginTransaction().hide(fragmentdiscountcoupons).commit();
            }
            if (fragment_main != null && fragment_main.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_main).commit();
            }

            if (fragment_profile != null && fragment_profile.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_profile).commit();
            }
            if (fragment_Favourite.isAdded()) {
                fragmentManager.beginTransaction().show(fragment_Favourite).commit();

            } else {
                fragmentManager.beginTransaction().add(R.id.fragment_app_container, fragment_Favourite, "fragment_Order").addToBackStack("fragment_Order").commit();

            }
            //  binding.setTitle(getString(R.string.home));
            updateBottomNavigationPosition(2);
        } catch (Exception e) {
        }

    }

    public void displayFragmentEditProfile() {
        try {
            if (fragmentdiscountcoupons == null) {
                fragmentdiscountcoupons = FragmentDiscountCoupons.newInstance();
            }


            if (fragment_Favourite != null && fragment_Favourite.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_Favourite).commit();
            }
            if (fragment_main != null && fragment_main.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_main).commit();
            }

            if (fragment_profile != null && fragment_profile.isAdded()) {
                fragmentManager.beginTransaction().hide(fragment_profile).commit();
            }
            if (fragmentdiscountcoupons.isAdded()) {
                fragmentManager.beginTransaction().show(fragmentdiscountcoupons).commit();

            } else {
                fragmentManager.beginTransaction().add(R.id.fragment_app_container, fragmentdiscountcoupons, "fragment_discount_copoun").addToBackStack("fragment_discount_copoun").commit();

            }
            //  binding.setTitle(getString(R.string.home));
            updateBottomNavigationPosition(3);
        } catch (Exception e) {
        }

    }

//    private void updateTokenFireBase() {
//
//        FirebaseInstanceId.getInstance()
//                .getInstanceId().addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                token = task.getResult().getToken();
//
//                try {
//                    Log.e("llll", userModel.getUser().getToken());
//                    Api.getService(Tags.base_url)
//                            .updatePhoneToken(userModel.getUser().getToken(), token, "android")
//                            .enqueue(new Callback<ResponseBody>() {
//                                @Override
//                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                                    if (response.isSuccessful() && response.body() != null) {
//                                        Log.e("token", "updated successfully");
//                                    } else {
//                                        try {
//
//                                            Log.e("errorToken", response.code() + "_" + response.errorBody().string());
//                                        } catch (IOException e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                                    try {
//
//                                        if (t.getMessage() != null) {
//                                            Log.e("errorToken2", t.getMessage());
//                                            if (t.getMessage().toLowerCase().contains("failed to connect") || t.getMessage().toLowerCase().contains("unable to resolve host")) {
//                                                Toast.makeText(HomeActivity.this, R.string.something, Toast.LENGTH_SHORT).show();
//                                            } else {
//                                                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                                            }
//                                        }
//
//                                    } catch (Exception e) {
//                                    }
//                                }
//                            });
//                } catch (Exception e) {
//
//
//                }
//
//            }
//        });
//    }


//    public void logout() {
//        if (userModel != null) {
//
//
//            ProgressDialog dialog = Common.createProgressDialog(this, getString(R.string.wait));
//            dialog.show();
//
//
//            FirebaseInstanceId.getInstance()
//                    .getInstanceId().addOnCompleteListener(task -> {
//                if (task.isSuccessful()) {
//                    token = task.getResult().getToken();
//
//                    Api.getService(Tags.base_url)
//                            .logout(userModel.getUser().getToken(), token, "android")
//                            .enqueue(new Callback<ResponseBody>() {
//                                @Override
//                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                                    dialog.dismiss();
//                                    if (response.isSuccessful()) {
//                                        Log.e("dd", "ddd");
//                                        preferences.clear(HomeActivity.this);
//                                        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                                        if (manager != null) {
//                                            manager.cancel(Tags.not_tag, Tags.not_id);
//                                        }
//                                        navigateToSignInActivity();
//
//
//                                    } else {
//                                        dialog.dismiss();
//                                        try {
//                                            Log.e("error", response.code() + "__" + response.errorBody().string());
//                                        } catch (IOException e) {
//                                            e.printStackTrace();
//                                        }
//
//                                        if (response.code() == 500) {
//                                            Toast.makeText(HomeActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
//                                        } else {
//                                            Toast.makeText(HomeActivity.this, getString(R.string.failed), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                                    try {
//                                        dialog.dismiss();
//                                        if (t.getMessage() != null) {
//                                            Log.e("error", t.getMessage() + "__");
//
//                                            if (t.getMessage().toLowerCase().contains("failed to connect") || t.getMessage().toLowerCase().contains("unable to resolve host")) {
//                                                Toast.makeText(HomeActivity.this, getString(R.string.something), Toast.LENGTH_SHORT).show();
//                                            } else {
//                                                Toast.makeText(HomeActivity.this, getString(R.string.failed), Toast.LENGTH_SHORT).show();
//                                            }
//                                        }
//                                    } catch (Exception e) {
//                                        Log.e("Error", e.getMessage() + "__");
//                                    }
//                                }
//                            });
//
//                }
//            });
//
//
//        } else {
//            navigateToSignInActivity();
//        }
//
//    }


    public void refreshActivity(String lang) {
        Paper.book().write("lang", lang);
        Language.setNewLocale(this, lang);
        new Handler()
                .postDelayed(() -> {

                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }, 1050);


    }


    @Override
    public void onBackPressed() {

        if (fragment_main != null && fragment_main.isAdded() && fragment_main.isVisible()) {
            if (userModel != null) {

                finish();
            } else {
                navigateToSignInActivity();
            }
        } else {
            displayFragmentMain();
        }
    }


    private void navigateToSignInActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Fragment> fragmentList = fragmentManager.getFragments();
        for (Fragment fragment : fragmentList) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<Fragment> fragmentList = fragmentManager.getFragments();
        for (Fragment fragment : fragmentList) {
            fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    public void updateCartCount(int count) {


        if (count == 0) {
            AHNotification notification = new AHNotification.Builder()
                    .setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
                    .setTextColor(ContextCompat.getColor(this, R.color.white))
                    .setText(null)
                    .build();
            binding.ahBottomNav.setNotification(notification, 3);

        } else {
            AHNotification notification = new AHNotification.Builder()
                    .setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
                    .setTextColor(ContextCompat.getColor(this, R.color.white))
                    .setText(String.valueOf(count))
                    .build();
            binding.ahBottomNav.setNotification(notification, 3);
        }
    }


    public void showite() {
        Intent intent = new Intent(HomeActivity.this, CardsActivity.class);
        startActivity(intent);
    }
}
