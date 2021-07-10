package com.example.madpropertypal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //   NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


    }


    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(getBaseContext(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    public void uploadJSON() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST
                , JSonClass.POST_URL, response -> {
            try {

                JSONObject jsonObject = new JSONObject(response);

                if (jsonObject.getString("uploadResponseCode").equals("successful")) {

                    String message = jsonObject.getString("message");
                    String userid = jsonObject.getString("userid");
                    String names = jsonObject.getString("names");
                    int number = jsonObject.getInt("number");


                    Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getBaseContext(), HomeActivity.class));

                } else {

                    Toast.makeText(getBaseContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();

                }

                //startActivity(new Intent(getBaseContext()Context(),HomeActivity.class));

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }, error -> {

            error.printStackTrace();

        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();

                return map;
            }


        };


        RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());
        requestQueue.add(stringRequest);


    }
}
