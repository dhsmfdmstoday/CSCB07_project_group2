package com.example.project1;

// A class to describe a session by its season + year;
public class ExactSession {
    public Session session;
    public int year;

    public ExactSession(Session session, int year) {
        this.session = session;
        this.year = year;
    }

    public String toString() {
        switch(session) {
            case Fall:
                return "Fall " + year;
            case Winter:
                return "Winter " + year;
            case Summer:
                return "Summer " + year;
        }
        return "Session toString() error.";
    }

}
