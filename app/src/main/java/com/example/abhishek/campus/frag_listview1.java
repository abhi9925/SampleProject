package com.example.abhishek.campus;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class frag_listview1 extends Fragment {


    public frag_listview1() {
        // Required empty public constructor
    }

    DatabaseReference dref;
    ListView listview;
    //SwipeRefreshLayout pullToRefresh;
    ArrayList<RunnerData> list = new ArrayList<RunnerData>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_frag_listview1, container, false);

        dref=FirebaseDatabase.getInstance().getReference("Runner");
        String key =  dref.push().getKey();
        // dref.child(key).child("Location");

        listview=(ListView) v.findViewById(R.id.listview1);
        //pullToRefresh = (SwipeRefreshLayout) v.findViewById(R.id.pullToRefresh);

        final UsersAdapters adapter = new UsersAdapters(getActivity(), list);

        listview.setAdapter(adapter);

        dref.addChildEventListener(new ChildEventListener() {
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                RunnerData runnerData = dataSnapshot.getValue(RunnerData.class);
                adapter.add(runnerData);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
             public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
                Intent appInfo = new Intent(getActivity(), ChatWindowActivity.class);
                startActivity(appInfo);
            }
        });




        return v;
    }


}
