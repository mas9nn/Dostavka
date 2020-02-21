package com.example.dostavka.ui.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.dostavka.R;
import com.example.dostavka.databinding.ActivityPagerBinding;
import com.example.dostavka.ui.DB.DBHelper;
import com.example.dostavka.ui.Util;
import com.example.dostavka.ui.login.models.UserResponse;
import com.example.dostavka.ui.login.signIn.SignInActivity;
import com.example.dostavka.ui.login.adapters.StartPagerAdapter;
import com.example.dostavka.ui.login.models.StartPagerModel;
import com.example.dostavka.ui.login.signIn.SignInListener;
import com.example.dostavka.ui.main.MainActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PagerActivity extends AppCompatActivity implements SignInListener {

    List<StartPagerModel> pagerModels = new ArrayList<>();

    ActivityPagerBinding binding;
    PagerViewModel model;
    int currentPage = 0;
    private CallbackManager callbackManager;
    int RC_SIGN_IN = 0;
    GoogleSignInClient mGoogleSignInClient;
    DBHelper dbHelper;
    Util util = new Util();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.query("user", null, null, null, null, null, null);
        c.moveToFirst();
        if (c.getCount() > 0) {
            Intent intent = new Intent(PagerActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        c.close();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        final SignInListener listener = this;

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pager);

        model = new ViewModelProvider(this).get(PagerViewModel.class);
        binding.setViewmodel(model);
        model.listener = listener;

        initPager();

        callbackManager = CallbackManager.Factory.create();

        binding.loginButton.setPermissions("email", "public_profile", "user_friends");

        binding.facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.loginButton.callOnClick();
            }
        });

        binding.loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.wtf("asdas", "asdas");
            }

            @Override
            public void onCancel() {
                Log.wtf("Asdasd", "cancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.wtf("asdas", error.toString());
            }
        });
        binding.google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInGoogle();
            }
        });
        model.user.observe(this, new Observer<UserResponse>() {
            @Override
            public void onChanged(UserResponse user_params) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("id", user_params.getData().getId());
                cv.put("token", user_params.getData().getToken());
                cv.put("name", user_params.getData().getName());
                db.insert("user", null, cv);
                db.close();
                dbHelper.close();
                listener.Success();
            }
        });
    }

    private void signInGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Log.wtf("asda", account.getEmail() + " " + account.getDisplayName());
            model.clickedBtnGoogle(account.getDisplayName(), account.getEmail());
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.wtf("error", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(PagerActivity.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            Log.wtf("sadasd", "Loged In");
        }
        super.onStart();
    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken == null) {

            } else {
                loadUserProfile(currentAccessToken);
            }
        }
    };

    private void checkLoginStatus() {
        if (AccessToken.getCurrentAccessToken() != null) {
            loadUserProfile(AccessToken.getCurrentAccessToken());
        }
    }

    private void loadUserProfile(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
                    String email = object.getString("email");
                    String id = object.getString("id");
                    String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";

                    Log.wtf("asdas", first_name + " " + last_name + " " + email + " " + id + " " + image_url);
                    model.clickedBtnGoogle(first_name + " " + last_name, email);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parametrs = new Bundle();
        parametrs.putString("fields", "first_name,last_name,email,id");
        request.setParameters(parametrs);
        request.executeAsync();
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

    @Override
    public void Success() {
        Log.wtf("adsads","Success");
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void Failed(String message) {
        util.hideProgress(binding.progressBarPager);
        util.showToast(this, message);
    }

    @Override
    public void Finish() {
        util.hideProgress(binding.progressBarPager);
        finish();
    }

    @Override
    public void Start() {
        util.showProgress(binding.progressBarPager);
    }
}
