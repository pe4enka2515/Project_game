package com.samoylov.gameproject.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.samoylov.gameproject.Data;
import com.samoylov.gameproject.Test2;
import com.samoylov.gameproject.adapters.ExpListAdapter;
import com.samoylov.gameproject.R;
import com.samoylov.gameproject.locations.Location;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentLocation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLocation extends Fragment implements ExpListAdapter.OnCardClickListener {
    private Location location;

    private ExpListAdapter adapter;
    private LinearLayout linearLayout;
    private ExpandableListView listView;
    private TextView lName, lDescription;
    private FragmentProfile fragmentProfile;
    private FragmentMyProfile fragmentMyProfile;
    FragmentManager fragmentManager;
    private Button add1, add2, add3;


//    public interface onFragmentSelected {
//        public void onSelected(String tag,int i);
//    }

    private Test2 listener;

    public FragmentLocation() {
    }

    public static FragmentLocation newInstance(FragmentManager fragmentManager) {
        FragmentLocation fragment = new FragmentLocation();
        fragment.fragmentManager = fragmentManager;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_location, container, false);
        listView = (ExpandableListView) v.findViewById(R.id.expanded_menu);
        add1 = v.findViewById(R.id.add1);
        add2 = v.findViewById(R.id.add2);
        add3 = v.findViewById(R.id.add3);
        linearLayout = (LinearLayout) v.findViewById(R.id.titleLoc);
        linearLayout.setBackgroundResource(R.drawable.krugliye_ugli);

        lName = (TextView) v.findViewById(R.id.lName);
        lDescription = (TextView) v.findViewById(R.id.lDescription);
        location = Data.bdLocations.get(Data.bdHeros.get(0).getLocationId());
        startLoc();

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Test2) {
            listener = (Test2) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement listener");
        }
    }

    @Override
    public void onCardClick(View view, String name, int pos, String tag) {
        switch (name) {
            case "Замкадье":
                location = Data.bdLocations.get(1);
                Data.bdHeros.get(0).setLocation(location.getLocName());
                location.addPlayersOnLocationList(location.getLocName());
                location.addOnLocation();
                lName.setText(location.getLocName());
                lDescription.setText(location.getLocDescription());
                //Создаем адаптер и передаем context и список с данными
                adapter = new ExpListAdapter(getContext(), location.getOnLocation());
                listView.setAdapter(adapter);
                break;
            case "Москва":
                Data.bdHeros.get(0).setLocation(name);

                location = Data.bdLocations.get(Data.bdHeros.get(0).getLocationId());
                startLoc();
            default:
                break;
        }
//        if(view== view.findViewById(R.id.buttonChild)){
//
//        }
        if (pos == 1 && tag == null) {
            for (int i = 0; i < Data.bdHeros.size(); i++) {
                if (name == Data.bdHeros.get(i).getName()) {
                    listener.onSelected("null", i);

                }
            }
        }
        if (pos == 1 && tag == "b") {
            for (int i = 0; i < Data.bdHeros.size(); i++) {
                if (name == Data.bdHeros.get(0).getName()) {
                    listener.onSelected(tag, 0);
                    i = Data.bdHeros.size();
                } else {
                    if (name == Data.bdHeros.get(i).getName()) {
                        listener.onSelected("a", i);
                    }
                }
            }
        }
    }


    public void startLoc() {
//        location = Data.bdLocations.get(Data.bdHeros.get(0).getLocationId());
//        if (Data.bdHeros.get(0).getLocation() == "Москва") {

//        }

        location.addPlayersOnLocationList(location.getLocName());
        location.addOnLocation();
        lName.setText(location.getLocName());
        lDescription.setText(location.getLocDescription());
        //Создаем адаптер и передаем context и список с данными
        adapter = new ExpListAdapter(getContext(), location.getOnLocation());
        adapter.setOnCardClickListener(this);
        listView.setAdapter(adapter);

    }

    public void add1(View view) {

    }

    public void setOnClick(){
        add3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        add3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
