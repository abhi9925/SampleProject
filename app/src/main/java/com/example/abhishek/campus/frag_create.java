package com.example.abhishek.campus;


import android.app.Activity;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


public class frag_create extends Fragment {

    Spinner location, fees;
    EditText time;
    Button submit;
    DatabaseReference newRef;

    public frag_create() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frag_create, container, false);

        location =  v.findViewById(R.id.spinner_location);
        time = v.findViewById(R.id.time_picker);
        fees =  v.findViewById(R.id.spinner_fees);
        submit = v.findViewById(R.id.submit_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Submit();
            }
        });

        String[] Type_List =  getResources().getStringArray(R.array.select_location_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,Type_List );
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        location.setAdapter(adapter);


        time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });


        String[] Type_List1 =  getResources().getStringArray(R.array.select_fees_array);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,Type_List1 );
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        fees.setAdapter(adapter1);



        return v;
    }

    private void Submit(){
      /*  final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Uploading...");
        progressDialog.show();
*/

        newRef = FirebaseDatabase.getInstance().getReference("Runner").push();
        String Time = time.getText().toString();
        String Location = location.getSelectedItem().toString();
        String Fees = fees.getSelectedItem().toString();


        newRef.child("Time").setValue(Time);
        newRef.child("Location").setValue(Location);
        newRef.child("Fees").setValue(Fees);

  //      progressDialog.dismiss();
        Toast.makeText(getActivity(), "Uploaded", Toast.LENGTH_SHORT).show();


        time.setText("");
        location.setSelection(0);
        fees.setSelection(0);

        }
}
