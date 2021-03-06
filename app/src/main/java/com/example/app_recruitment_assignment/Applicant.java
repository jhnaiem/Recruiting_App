package com.example.app_recruitment_assignment;

import com.google.gson.JsonObject;

public class Applicant {
    private String tsync_id;
    private String name;
    private String email;
    private String phone;
    private String full_address;
    private String name_of_university;
    private int graduation_year;
    private float cgpa;
    private int experience_in_months;
    private String currrent_work_place_name;
    private String applying_in;
    private long expected_salary;
    private String field_buzz_reference;
    private String github_project_url;
    private JsonObject cv_file;
    //    @SerializedName("cv_file.tsync_id")
//    private String cv_file_tsync_id;
    private Long on_spot_update_time;
    private Long on_spot_creation_time;


    public Applicant(String tsync_id, String name, String email, String phone, String full_address, String name_of_university, int graduation_year, float cgpa, int experience_in_months, String currrent_work_place_name, String applying_in, long expected_salary, String field_buzz_reference, String github_project_url, JsonObject cv_file, Long on_spot_creation_time) {
        this.tsync_id = tsync_id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.full_address = full_address;
        this.name_of_university = name_of_university;
        this.graduation_year = graduation_year;
        this.cgpa = cgpa;
        this.experience_in_months = experience_in_months;
        this.currrent_work_place_name = currrent_work_place_name;
        this.applying_in = applying_in;
        this.expected_salary = expected_salary;
        this.field_buzz_reference = field_buzz_reference;
        this.github_project_url = github_project_url;
        this.cv_file = cv_file;
//        this.cv_file_tsync_id = cv_file_tsync_id;
        this.on_spot_update_time = on_spot_update_time;
        this.on_spot_creation_time = on_spot_creation_time;
    }

    public String getTsync_id() {
        return tsync_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFull_address() {
        return full_address;
    }

    public void setFull_address(String full_address) {
        this.full_address = full_address;
    }

    public String getName_of_university() {
        return name_of_university;
    }

    public void setName_of_university(String name_of_university) {
        this.name_of_university = name_of_university;
    }

    public int getGraduation_year() {
        return graduation_year;
    }

    public void setGraduation_year(int graduation_year) {
        this.graduation_year = graduation_year;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    public int getExperience_in_months() {
        return experience_in_months;
    }

    public void setExperience_in_months(int experience_in_months) {
        this.experience_in_months = experience_in_months;
    }

    public String getCurrrent_work_place_name() {
        return currrent_work_place_name;
    }

    public void setCurrrent_work_place_name(String currrent_work_place_name) {
        this.currrent_work_place_name = currrent_work_place_name;
    }

    public String getApplying_in() {
        return applying_in;
    }

    public void setApplying_in(String applying_in) {
        this.applying_in = applying_in;
    }

    public long getExpected_salary() {
        return expected_salary;
    }

    public void setExpected_salary(long expected_salary) {
        this.expected_salary = expected_salary;
    }

    public String getField_buzz_reference() {
        return field_buzz_reference;
    }

    public void setField_buzz_reference(String field_buzz_reference) {
        this.field_buzz_reference = field_buzz_reference;
    }

    public String getGithub_project_url() {
        return github_project_url;
    }

    public void setGithub_project_url(String github_project_url) {
        this.github_project_url = github_project_url;
    }

    public JsonObject getCv_file() {
        return cv_file;
    }

    public void setCv_file(JsonObject cv_file) {
        this.cv_file = cv_file;
    }

    public Long getOn_spot_update_time() {
        return on_spot_update_time;
    }

    public void setOn_spot_update_time(Long on_spot_update_time) {
        this.on_spot_update_time = on_spot_update_time;
    }

    public Long getOn_spot_creation_time() {
        return on_spot_creation_time;
    }

}
