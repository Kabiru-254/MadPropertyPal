package com.example.madpropertypal.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.madpropertypal.JSonClass;
import com.example.madpropertypal.adapters.RecyclerViewAdapter;
import com.example.madpropertypal.models.PropertyModel;
import com.example.madpropertypal.R;
import com.example.madpropertypal.sqlite.SQLiteHelper;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {



    RecyclerView recyclerView;
    List<PropertyModel> list=new ArrayList<>();
    RecyclerView.Adapter mAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fetchItems();



        return root;
    }



    @Override
    public void onPause() {
        list.clear();
        super.onPause();


    }


    @Override
    public void onResume() {
        fetchItems();
        super.onResume();

    }

    private void fetchItems() {

        if (!list.isEmpty()){
            list.clear();
        }

        SQLiteHelper sqLiteHelpar = new SQLiteHelper(getContext(), "HOUSES_AVAILABLE");
        list = sqLiteHelpar.fetchHouses();
        uploadJSON(list);
        mAdapter = new RecyclerViewAdapter(getContext(),list);
        recyclerView.setAdapter(mAdapter);
    }






    public void uploadJSON(List<PropertyModel> listq) {

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        int i = 0;
        for (PropertyModel propertyModel : listq) {

            JSONObject jsonObj= new JSONObject();
            try {
                jsonObj.put("id", propertyModel.getId());
                jsonObj.put("name", propertyModel.getPropertyName());
                jsonObj.put("type", propertyModel.getPropertyType());
                jsonObj.put("location", propertyModel.getLocation());
                jsonObj.put("Number of bedrooms", propertyModel.getBedroomNumber());
                jsonObj.put("number of bathrooms", propertyModel.getBathroomNumber());
                jsonObj.put("price", propertyModel.getAskingPrice());
                jsonObj.put("rooms", propertyModel.getBedroomNumber());
                jsonObj.put("size", propertyModel.getSize());
                jsonObj.put("amenities", propertyModel.getLocalAmenities());
                jsonObj.put("description", propertyModel.getDescription());

                jsonArray.put(jsonObj);
                jsonObject.put("userId", JSonClass.USER_ID);
                jsonObject.put("detailList",jsonArray);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


          StringRequest stringRequest = new StringRequest(Request.Method.POST
                , JSonClass.POST_URL, response -> {
            try {

               // Log.i("RESPONSE",response.toString());

                JSONObject jsonObject1 = new JSONObject(response);
                if (jsonObject1.getString("uploadResponseCode").equals("SUCCESS")) {
                    String message = jsonObject1.getString("message");
                    String userid = jsonObject1.getString("userid");
                    String names = jsonObject1.getString("names");
                    int number = jsonObject1.getInt("number");
                    Toast.makeText(getContext(), "Success: Successful "
                            +"\n userId: "+ userid
                            +"\n Names of Uploaded Properties: "+names
                            +"\n Uploaded Properties:"+ number
                            +"\n Message"+ message, Toast.LENGTH_SHORT).show();

                }else {

                    String message = jsonObject1.getString("message");
                    Toast.makeText(getContext(), "Success: Failed" +" \n Message: "+ message, Toast.LENGTH_SHORT).show();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }, error -> {

            error.printStackTrace();

        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap map = new HashMap<>();
                map.put("jsonpayload",jsonObject.toString());

                return map;
            }


        };


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

}