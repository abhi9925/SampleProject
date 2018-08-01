package com.example.abhishek.campus;

/**
 * Created by Abhishek on 7/16/2018.
 */

public class RunnerData {

    private String Location;
    private String Time;
    //private String mUid;

    public RunnerData() {}

    public RunnerData(String location, String time) {
        Location = location;
        Time = time;
      //  mUid = uid;
    }

    public String getLocation() { return Location; }

    public void setLocation(String location) { Location = location; }

    public String getTime() { return Time; }

    public void setTime(String time) { Time = time; }

   /* public String getUid() { return mUid; }

    public void setUid(String uid) { mUid = uid; }
*/
}

