package com.example.project_countries.fragmente;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_countries.Login;
import com.example.project_countries.R;
import com.example.project_countries.database.entities.Question;
import com.example.project_countries.database.entities.ResultQuestion;
import com.example.project_countries.database.operations.QuestionOperations;
import com.example.project_countries.database.operations.ResultOperations;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class FragmentTesteazaCunostintele extends Fragment {

    private QuestionOperations questionOperations;
    private ResultOperations resultOperations;
    private SharedPreferences sharedPreferences;
    private List<Question> questions;
    private TextView tvQuestion;
    private RadioButton rbAnswer1;
    private RadioButton rbAnswer2;
    private RadioButton rbAnswer3;
    private RadioButton rbAnswer4;
    private Button nextQuestion;
    private RadioGroup rgAnswers;
    private View view;
    int position = 0;

    public FragmentTesteazaCunostintele() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        questions = new ArrayList<>();
        sharedPreferences = getActivity().getSharedPreferences(Login.LOGIN_SHARED_PREF, Context.MODE_PRIVATE);
        questionOperations = new QuestionOperations(getContext().getApplicationContext());
        resultOperations = new ResultOperations(getContext().getApplicationContext());
        getQuestionsFromDB();
        view = inflater.inflate(R.layout.fragment_testeaza_cunostintele, container, false);
        initComponents(view);
        nextQuestion.setOnClickListener(v -> {
            onClickNext();
        });
        return view;
    }

    private void setQuestionOnScreen(int index) {
        Question objectQuestion = questions.get(index);
        tvQuestion.setText(objectQuestion.getQuestion());
        rbAnswer1.setText(objectQuestion.getWanswer1());
        rbAnswer2.setText(objectQuestion.getWanswer2());
        rbAnswer3.setText(objectQuestion.getWanswer3());
        rbAnswer4.setText(objectQuestion.getWanswer4());
    }

    private void getQuestionsFromDB() {
        questionOperations.getAll(result -> {
            if (result != null) {
                questions.addAll(result);
                setQuestionOnScreen(0);
            }
        });
    }

    private void initComponents(View view) {
        tvQuestion = view.findViewById(R.id.question);
        rbAnswer1 = view.findViewById(R.id.answer1);
        rbAnswer2 = view.findViewById(R.id.answer2);
        rbAnswer3 = view.findViewById(R.id.answer3);
        rbAnswer4 = view.findViewById(R.id.answer4);
        nextQuestion = view.findViewById(R.id.button_next);
        rgAnswers = view.findViewById(R.id.radioGroup);
    }

    private String checkIfCorrect() {
        Question objectQuestion = questions.get(position);
        String correctAnswer = objectQuestion.getCorrectAnswer();
        Integer correctId = rgAnswers.getCheckedRadioButtonId();
        RadioButton radioButton = view.findViewById(correctId);
        if (radioButton.getText().toString().equals(correctAnswer)) {
            return "true";
        } else return "false";
    }

    private void onClickNext() {
        if (position < questions.size()) {
            ResultQuestion resultQuestion = new ResultQuestion(sharedPreferences.getInt(Login.id_user, -1),
                    questions.get(position).getQuestionId(), checkIfCorrect());
            resultOperations.insert(result -> {
            },resultQuestion);
            setQuestionOnScreen(position++);
        } else {
            nextQuestion.setEnabled(false);
            Toast.makeText(getContext().getApplicationContext(), R.string.endOfQuestions, Toast.LENGTH_SHORT).show();
        }
    }
}