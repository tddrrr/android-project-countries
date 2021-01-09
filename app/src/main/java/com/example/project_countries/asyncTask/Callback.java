package com.example.project_countries.asyncTask;

public interface Callback<R> {
    void runResultOnUiTread(R result);
}
