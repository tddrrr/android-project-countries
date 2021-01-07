package com.example.proiect_bd_tema.asyncTask;

public interface Callback<R> {
    void runResultOnUiTread(R result);
}
