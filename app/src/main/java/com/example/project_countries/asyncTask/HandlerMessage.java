package com.example.project_countries.asyncTask;

public class HandlerMessage<R> implements Runnable {
    private final R result; //de trimis catre activitatea principala
    private final Callback<R> mainThreadOperation;

    public HandlerMessage(R result, Callback<R> mainThreadOperation) {
        this.result = result;
        this.mainThreadOperation = mainThreadOperation;
    }

    @Override
    public void run() {
        mainThreadOperation.runResultOnUiTread(result); //trimitem mesajul primit catre referinta din MainThread
    }
}

