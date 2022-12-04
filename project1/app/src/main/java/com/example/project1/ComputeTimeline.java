/* WAS CODED WITH THE FOLLOWING COURSE DATA STRUCTURE IN MIND:
public class Course {
    private String course_name;
    private String course_code;
    private List<Session> offering_session;
    private List<String> prerequisites;
} */

// How come the data structure was changed? Need to adapt this code to the new
// course data structure

package com.example.project1;

import java.util.ArrayList;
import java.util.List;

public class ComputeTimeline {

    private static final ExactSession current_session = new ExactSession(Session.Fall, 2022);
    // Storing current session because it will impact when the first courses can be taken by the student
    private final List<Course> all_courses;
    private final List<String> plan_courses;          // Course codes
    private final List<String> taken_courses;         // Course codes


    public ComputeTimeline(List<Course> all_courses, List<String> plan_courses, List<String> taken_courses) {
        this.all_courses = all_courses;
        this.plan_courses = plan_courses;
        this.taken_courses = taken_courses;
    }

    // Method to generate the next session when given an ExactSession object. Example: Fall 2022 -> Winter 2023
    public static ExactSession genNextSession(ExactSession exact) {
        switch (exact.session) {
            case Fall:
                return new ExactSession(Session.Winter, exact.year + 1);
            case Winter:
                return new ExactSession(Session.Summer, exact.year);
        }
        return new ExactSession(Session.Fall, exact.year);
    }

    public static ExactSession idToSession(int id) {
        ExactSession x = current_session;
        for (int i = 0; i < id; i++) {
            x = genNextSession(x);
        }
        return x;
    }

    private Course findCourse(String code) {
        for (int i = 0; i < all_courses.size(); i++) {
            if (all_courses.get(i).getCourse_code().equals(code)) {
                return all_courses.get(i);
            }
        }
        return null; // Couldn't find course.
    }

    private void addCourses(Course to_take,
                               ArrayList<String> course_codes,
                               ArrayList<Integer> session_indices,
                               Integer earliest) {
        boolean unnecessary = false;
        for (int i = 0; i < course_codes.size(); i++) {     // If course has already been added
            if (course_codes.get(i).equals(to_take.getCourse_code())) {
                unnecessary = true;
            }
        }
        for (int i = 0; i < taken_courses.size(); i++) {    // If course has already been taken
            if (taken_courses.get(i).equals(to_take.getCourse_code())) {
                unnecessary = true;
            }
        }
        if (unnecessary) return;        // This means there is no need to add this course
                                        // If we reach this line, we know the course needs to be added
        Integer earliest_session = 0;   // Session that we can add our course to
        if (to_take.getPrerequisites().size() > 0) {   // If pre-req required, add the pre-reqs and shift earliest session to the new earliest session
            for (int j = 0; j < to_take.getPrerequisites().size(); j++) {   // Loop through prereqs
                addCourses(findCourse(to_take.getPrerequisites().get(j)), course_codes, session_indices, earliest_session);
            }
        }
        // Loop 3 times to find at least one session where the course is offered
        for (int i = 0; i < 3; i++) {
            // This condition means that course we are trying to take is offered in session we are currently looking at
            if (to_take.getOffering_session().contains(idToSession(earliest_session).session)) {
                course_codes.add(to_take.getCourse_code());
                session_indices.add(earliest_session);
                if (earliest <= earliest_session) {
                    earliest = earliest_session + 1;
                }
                return;
            } else {
                earliest_session++;
                if (earliest <= earliest_session) {
                    earliest = earliest_session + 1;
                }
            }
        }
    }

    public List<List<String>> timeline() {
        ArrayList<String> course_codes = new ArrayList<String>();
        ArrayList<Integer> session_indices = new ArrayList<Integer>();
        // Loop through the wanted courses
        for (int i = 0; i < plan_courses.size(); i++) {
            // Recursively adding courses here
            addCourses(findCourse(plan_courses.get(i)), course_codes, session_indices, 0);
        }
        int min_num_sessions = 0;
        // Finding size for future list of lists
        for (int i = 0; i < course_codes.size(); i++) {
            if (min_num_sessions < session_indices.get(i)) {
                min_num_sessions = session_indices.get(i);
            }
        }
        // Create list of lists that will include course details for every session until all the
        // planned courses are taken
        List<List<String>> timeline = new ArrayList<List<String>>();
        for (int i = 0; i < min_num_sessions; i++) {
            List<String> courses = new ArrayList<String>();
            courses.add("Empty");
            timeline.add(courses);
        }

        // Add to the lists
        for (int i = 0; i < course_codes.size(); i++) {
            List<String> sublist = timeline.get(session_indices.get(i));
            if (sublist.get(0).equals("Empty")) {
                sublist.set(0, course_codes.get(i));
            } else {
                sublist.add(course_codes.get(i));
            }
        }
        return timeline;
    }
}
