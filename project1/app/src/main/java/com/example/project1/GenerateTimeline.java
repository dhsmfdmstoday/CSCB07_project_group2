package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

// GenerateTimeline will import the data for courses and the user from firebase, feed the data into
// ComputeTimeline, and then feed the output into the xml file running the UI.
public class GenerateTimeline extends AppCompatActivity {

    // TO DO: NEED TO IMPORT THESE FROM FIREBASE (COULD NOT DO YET BECAUSE FIREBASE DATA STRUCTURES
    // NOT FINALIZED AT THIS MOMENT)
    private List<Course> all_courses;
    private List<String> plan_courses;
    private List<String> taken_courses;

    public void displayTimeline(View view) {
        ComputeTimeline compute = new ComputeTimeline(all_courses, plan_courses, taken_courses);
        List<List<String>> computed = compute.timeline();

        // compute.timeline() will now provide a list of lists of strings where each sublist will
        // contain the names of courses that need to be taken in one specific session. The index of
        // the sublist within the parent list will indicate the session in chronological order, with
        // index 0 corresponding to final variable ExactSession current_session (Fall 2022). Index
        // 1 corresponding to Winter 2023 and so on.

        // Use methods ComputeTimeline.idToSession() and ExactSession.toString() to turn session id
        // integers into normal session names like "Winter 2024"
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_timeline);
    }
}