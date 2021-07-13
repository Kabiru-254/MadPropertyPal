package com.example.madpropertypal.ui.myproperties;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.madpropertypal.JSonClass;
import com.example.madpropertypal.R;
import com.example.madpropertypal.adapters.EditorsRecyclerViewAdapter;
import com.example.madpropertypal.adapters.RecyclerViewAdapter;
import com.example.madpropertypal.models.PropertyModel;
import com.example.madpropertypal.sqlite.SQLiteHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertiesFragment extends Fragment {

    RecyclerView recyclerView;
    List<PropertyModel> list=new ArrayList<>();
    RecyclerView.Adapter mAdapter;
    private Button postBT;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         View root = inflater.inflate(R.layout.properties_fragment, container, false);

         postBT = root.findViewById(R.id.postBT);

         recyclerView = root.findViewById(R.id.recyclerView4);
         recyclerView.setHasFixedSize(true);
         recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        fetchItems();


        postBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadJSON(list);

            }
        });
         return root;
    }

    private void fetchItems() {

        if (!list.isEmpty()){
            list.clear();
        }

        SQLiteHelper sqLiteHelpar = new SQLiteHelper(getContext(), "HOUSES_AVAILABLE");
        list = sqLiteHelpar.fetchHouses();

        mAdapter = new EditorsRecyclerViewAdapter(getContext(),list);
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