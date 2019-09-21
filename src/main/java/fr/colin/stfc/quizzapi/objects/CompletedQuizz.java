package fr.colin.stfc.quizzapi.objects;

import java.util.ArrayList;

public class CompletedQuizz {

    private Quizz quizz;
    private ArrayList<String> answers;
    public boolean corrected = false;
    private int score = 0;

    public void setScore(int score) {
        this.score = score;
    }
    public CompletedQuizz(Quizz quizz, ArrayList<String> answers){
        this.quizz = quizz;
        this.answers = answers;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public Quizz getQuizz() {
        return quizz;
    }

}
