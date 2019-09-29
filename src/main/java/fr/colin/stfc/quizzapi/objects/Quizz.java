package fr.colin.stfc.quizzapi.objects;

import fr.colin.stfc.quizzapi.QuizzAPI;

import java.util.ArrayList;
import java.util.UUID;

public class Quizz {

    private ArrayList<Questions> questions;
    private String category;
    private String uuid;
    private Long date;

    public Quizz(ArrayList<Questions> questions, String category, String uuid, Long date) {
        this.questions = questions;
        this.category = category;
        this.uuid = uuid;
        this.date = date;
    }

    public ArrayList<Questions> getQuestions() {
        return questions;
    }

    public Long getDate() {
        return date;
    }

    public String getUuid() {
        return uuid;
    }

    public static ArrayList<String> arrayOfQuestionToAnswer(ArrayList<Questions> questions) {
        ArrayList<String> answers = new ArrayList<>();
        questions.forEach(questions1 -> answers.add(questions1.getAnswer()));
        return answers;
    }

    public static void insertSillyQuestion() {
        QuizzAPI api = QuizzAPI.DEFAULT_INSTANCE;
        ArrayList<Category> cats = api.getCategories();
        for (Category c : cats) {
            for (int i = 0; i < 35; i++) {
                api.addQuestion(new Questions(UUID.randomUUID().toString(), "q" + i, "q" + i, "q" + i, c.getUuid()), "stfc2019");
            }
        }
    }

    public String getCategory() {
        return category;
    }
}
