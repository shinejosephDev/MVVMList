package com.test.mvvmlist.presentation.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.test.loadimage.cache.DoubleCache;
import com.test.loadimage.LoadImage;
import com.test.mvvmlist.databinding.ActivityDetailBinding;
import com.test.mvvmlist.domain.model.ResultsItem;
import com.test.mvvmlist.util.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    protected ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent().getExtras().containsKey(Constants.INTENT_KEY_DETAIL)) {
            ResultsItem item = getIntent().getExtras().getParcelable(Constants.INTENT_KEY_DETAIL);
            Log.d("TAG", "onCreate: " + item.getName());

            binding.tvName.setText(item.getName());
            binding.tvPrice.setText(item.getPrice());
            binding.tvUID.setText(item.getUid());


            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault());
                SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                binding.tvCreatedAt.setText(simpleDateFormat.format(parser.parse(item.getCreated_at())));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            LoadImage.INSTANCE.setCache(new DoubleCache(getApplicationContext()));

            LoadImage.INSTANCE.displayImage(item.getImage_urls().get(0),binding.image);

//            Glide.with(this)
//                    .load(item.getImage_urls().get(0))
//                    .into(binding.image);
        }
    }
}