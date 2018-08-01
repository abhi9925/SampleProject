package com.example.abhishek.campus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.firebase.ui.auth.data.model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Abhishek on 7/19/2018.
 */

public class UsersAdapters extends ArrayAdapter<RunnerData> {
    public UsersAdapters(Context context, ArrayList<RunnerData> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        // Check if an existing view is being reused, otherwise inflate the view

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);

        // Lookup view for data population
        final  TextView loc = (TextView) convertView.findViewById(R.id.location_row);
        final  TextView tim = (TextView) convertView.findViewById(R.id.time_row);
        // Populate the data into the template view using the data object
       // loc.setText(RunnerData.getLocation());
        //tim.setText(RunnerData.getTime());
        // Return the completed view to render on screen

        final RunnerData runnerData = getItem(position);

        DatabaseReference dref;
        dref= FirebaseDatabase.getInstance().getReference("Runner");
        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               // RunnerData runnerData = dataSnapshot.getValue(RunnerData.class);
                loc.setText("Location: " + runnerData.getLocation());
                String tim1 = runnerData.getTime();
                tim.setText("Time: " + tim1);
                //adapter.add(runnerData);
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


        return convertView;
    }
}
