package com.example.project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CourseListAdapter extends ArrayAdapter {

    private int resource;

    public CourseListAdapter(@NonNull Context context, int resource, ListView lst_course) {
        super(context, resource, (List) lst_course);
        this.resource = resource;
    }

    @Override
    public View getView(int pos, View Convert, ViewGroup group) {
        Course courselist = (Course) getItem(pos);

        if (Convert == null) {
            Convert = LayoutInflater.from(getContext()).inflate(resource, group, false);
        }

        TextView courseName = (TextView) Convert.findViewById(R.id.CourseName);
        TextView courseCode = (TextView) Convert.findViewById(R.id.CourseCode);
        TextView courseOffering = (TextView) Convert.findViewById(R.id.OfferingSessions);
        TextView coursePre = (TextView) Convert.findViewById(R.id.Prerequisites);

        courseName.setText(courselist.getCourse_name());
        courseCode.setText(courselist.getCourse_code());
        courseOffering.setText((CharSequence) courselist.getOffering_session());
        coursePre.setText((CharSequence) courselist.getPrerequisites());

        return Convert;
    }
}
