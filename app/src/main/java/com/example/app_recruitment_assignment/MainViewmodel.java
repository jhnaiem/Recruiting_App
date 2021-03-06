package com.example.app_recruitment_assignment;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainViewmodel extends ViewModel {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private String mtoken;
    Authentication authentication = new Authentication();

    public void setToken(String token) {
        this.mtoken = token;
    }

    public String getMtoken() {
        return mtoken;
    }


    Applicant applicant;
    MutableLiveData<String> validationMessageLiveData = new MutableLiveData<>();

    public void onSubmitClick(String name, String email, String phone, String address, String university, String graduation, String cgpa, String experience, String curWorkplace, String selectedDepartment, String expSalary, String reference, String gitUrl, String cvfile, ServerResponseLisenter serverResponseLisenter) {

        if (name.isEmpty()) {
            validationMessageLiveData.setValue("Name is required");
        } else if (email.isEmpty()) {
            validationMessageLiveData.setValue("Email is required");
        } else if (phone.isEmpty()) {
            validationMessageLiveData.setValue("Phone number is required");
        } else if (university.isEmpty()) {
            validationMessageLiveData.setValue("Phone number is required");
        } else if (graduation.isEmpty()) {
            validationMessageLiveData.setValue("Graduation year is required");
        } else if (Integer.parseInt(graduation) < 2015 && Integer.parseInt(graduation) > 2020) {
            validationMessageLiveData.setValue("Graduation must be between 2015 to 2020");
        } else if (Float.parseFloat(cgpa) < 2.0 && Integer.parseInt(cgpa) > 4.0) {
            validationMessageLiveData.setValue("CGPA must be between 2.0 to 4.0");
        } else if (Integer.parseInt(experience) < 0 && Integer.parseInt(experience) > 100) {
            validationMessageLiveData.setValue("Experience must be between 0 to 100 months");
        } else if (selectedDepartment.isEmpty()) {
            validationMessageLiveData.setValue("Applying department  is required");
        } else if (expSalary.isEmpty()) {
            validationMessageLiveData.setValue("Expected salary  is required");
        } else if (Long.parseLong(expSalary) < 15000 && Long.parseLong(expSalary) > 60000) {
            validationMessageLiveData.setValue("Expected salary  must be between 15000 to 60000");
        } else if (gitUrl.isEmpty()) {
            validationMessageLiveData.setValue("Github project URL  is required");
        } else if (cvfile.isEmpty()) {
            validationMessageLiveData.setValue("CV file  is required");
        } else {

            String idCV = UUID.randomUUID().toString();
            JsonObject cv_fileJSON = new JsonObject();
            cv_fileJSON.addProperty("tsync_id", idCV);

            if (applicant == null) {
                applicant = new Applicant(UUID.randomUUID().toString(), name, email, phone, address, university, Integer.parseInt(graduation), Float.parseFloat(cgpa), Integer.parseInt(experience), curWorkplace, selectedDepartment, Long.parseLong(expSalary), reference, gitUrl, cv_fileJSON, System.currentTimeMillis());
            } else {
                //When a user wants to update input
                applicant.setName(name);
                applicant.setEmail(email);
                applicant.setPhone(phone);
                applicant.setFull_address(address);
                applicant.setName_of_university(university);
                applicant.setGraduation_year(Integer.parseInt(graduation));
                applicant.setCgpa(Float.parseFloat(cgpa));
                applicant.setExperience_in_months(Integer.parseInt(experience));
                applicant.setCurrrent_work_place_name(curWorkplace);
                applicant.setApplying_in(selectedDepartment);
                applicant.setExpected_salary(Long.parseLong(expSalary));
                applicant.setField_buzz_reference(reference);
                applicant.setGithub_project_url(gitUrl);
                applicant.setCv_file(cv_fileJSON);
                applicant.setOn_spot_update_time(System.currentTimeMillis());


            }

            String postURL = "https://recruitment.fisdev.com/api/v1/recruiting-entities/";
            Gson gson = new Gson();

            String json = gson.toJson(applicant);

            RequestBody body = RequestBody.create(JSON, json);

            Log.d("Json", json);
            Request request = new Request.Builder()
                    .url(postURL)
                    .header("Authorization", "Token " + mtoken)
                    .post(body).build();


            authentication.client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                    try {
                        JSONObject bodyJson = new JSONObject(response.body().string());

                        // Get the (cv_file) json from the response
                        JSONObject cvObject = bodyJson.getJSONObject("cv_file");
                        String fileTokenId = cvObject.getString("id");

                        //Pass the token
                        serverResponseLisenter.onSuccess(fileTokenId);
                        serverResponseLisenter.onFailure(bodyJson.getString("message"));


                        Log.d("Message", bodyJson.getString("message"));
                        Log.d("===>", "Success:" + fileTokenId);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            });
        }


    }


    public void fetchToken() {

        ServerResponseLisenter serverResponseLisenter = new ServerResponseLisenter() {
            @Override
            public void onSuccess(String token) {

                // Set token to use it later in mainactivity for api 3.3 and for api 3.2
                setToken(token);
                Log.d("===>", " success: " + token);

            }

            @Override
            public void onFailure(String message) {
                Log.d("===>", " failure: " + message);

            }
        };

        try {
            authentication.fetchResponse(serverResponseLisenter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ;
    }


}
