package com.example.dostavka.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.dostavka.R;
import com.example.dostavka.databinding.ActivityPagerBinding;
import com.example.dostavka.ui.DB.DBHelper;
import com.example.dostavka.ui.login.signIn.SignInActivity;
import com.example.dostavka.ui.login.adapters.StartPagerAdapter;
import com.example.dostavka.ui.login.models.StartPagerModel;
import com.example.dostavka.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class PagerActivity extends AppCompatActivity {

    List<StartPagerModel> pagerModels = new ArrayList<>();

    ActivityPagerBinding binding;
    PagerViewModel model;
    int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.query("user", null, null, null, null, null, null);
        c.moveToFirst();
        if (c.getCount() > 0){
            Intent intent = new Intent(PagerActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        c.close();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pager);

        model = new ViewModelProvider(this).get(PagerViewModel.class);
        binding.setViewmodel(model);
        initPager();

    }

    private void initPager() {
        pagerModels.add(new StartPagerModel(R.drawable.fst, "Hello it its header", "Hello it is description"));
        pagerModels.add(new StartPagerModel(R.drawable.snd, "Hello it its header1", "Hello it is description1"));
        pagerModels.add(new StartPagerModel(R.drawable.trd, "Hello it its header2", "Hello it is description2"));
        binding.pager.setAdapter(new StartPagerAdapter(this, pagerModels));
        binding.indicator.setViewPager(binding.pager);

        binding.pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == pagerModels.size() - 1) {
                    binding.buttonPager.setText("Войти");
                } else {
                    binding.buttonPager.setText("Далее");
                }
            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        binding.buttonPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (currentPage != pagerModels.size() - 1) {
                    currentPage++;
                } else {
                    Intent intent = new Intent(PagerActivity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();
                }
                binding.pager.setCurrentItem(currentPage);

            }
        });
    }
}
