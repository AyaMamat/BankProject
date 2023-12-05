package com.laba.solvd.bankhierarchy.people;

import com.laba.solvd.bankhierarchy.enums.JobTitle;

public class Position {

    private JobTitle jobTitle;


    public Position(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public JobTitle getTitle() {
        return jobTitle;
    }

    public void setTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "'" + jobTitle;
    }
}
