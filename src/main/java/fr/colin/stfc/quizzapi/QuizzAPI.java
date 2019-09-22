package fr.colin.stfc.quizzapi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import fr.colin.stfc.quizzapi.objects.Category;
import fr.colin.stfc.quizzapi.objects.CompletedQuizz;
import fr.colin.stfc.quizzapi.objects.Questions;
import fr.colin.stfc.quizzapi.objects.Quizz;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class QuizzAPI {
    public static QuizzAPI DEFAULT_INSTANCE = new QuizzAPI("http://localhost:6767");
    private String baseURL;
    public static OkHttpClient HTTP_CLIENT = new OkHttpClient();
    private MediaType JSON = MediaType.parse("application/json");

    public QuizzAPI(String baseURL) {
        this.baseURL = baseURL;
    }

    public ArrayList<Category> getCategories() {
        Type t = new TypeToken<ArrayList<Category>>() {
        }.getType();
        ArrayList<Category> categories;
        Request r = new Request.Builder().get().url(baseURL + "/get_categories").build();
        String answer;
        try {
            answer = HTTP_CLIENT.newCall(r).execute().body().string();
        } catch (IOException e) {
            categories = new ArrayList<>();
            return categories;
        }
        categories = new Gson().fromJson(answer, t);
        return categories;
    }

    public HashMap<String, ArrayList<Questions>> getQuestions() {
        Type t = new TypeToken<HashMap<String, ArrayList<Questions>>>() {
        }.getType();
        HashMap<String, ArrayList<Questions>> questions;
        Request r = new Request.Builder().get().url(baseURL + "/get_questions").build();
        String answer;
        try {
            answer = HTTP_CLIENT.newCall(r).execute().body().string();
        } catch (IOException e) {
            questions = new HashMap<>();
            return questions;
        }
        questions = new Gson().fromJson(answer, t);
        return questions;
    }

    public Quizz fetchQuizz(String uuid) {

        Request r = new Request.Builder().get().url(baseURL + "/fetch_quizz?uuid=" + uuid).build();
        try {
            return new Gson().fromJson(HTTP_CLIENT.newCall(r).execute().body().string(), Quizz.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public Quizz getRandomQuizz(String category, int noq) {
        if (noq < 0) {
            noq = 3;
        }
        Quizz z;
        Request r = new Request.Builder().get().url(baseURL + "/request_quizz?category=" + category + "&noq=" + noq).build();
        String answer;
        try {
            answer = HTTP_CLIENT.newCall(r).execute().body().string();
        } catch (IOException e) {
            return null;
        }
        z = new Gson().fromJson(answer, Quizz.class);
        return z;
    }

    public String addCategory(String name, String token) {
        Request r = new Request.Builder().url(baseURL + "/add_category?token=" + token + "&name=" + name).get().build();
        try {
            return HTTP_CLIENT.newCall(r).execute().body().string();
        } catch (IOException e) {
            return "Error";
        }
    }

    public String addQuestion(Questions questions, String token) {
        Request r = new Request.Builder().url(baseURL + "/add_question?token=" + token).post(RequestBody.create(JSON, new Gson().toJson(questions))).build();
        try {
            return HTTP_CLIENT.newCall(r).execute().body().string();
        } catch (Exception e) {
            return "Error";
        }
    }

    public String removeCategory(String uuid, String token) {
        Request r = new Request.Builder().url(baseURL + "/remove_category?token=" + token + "&uuid=" + uuid).get().build();
        try {
            return HTTP_CLIENT.newCall(r).execute().body().string();
        } catch (IOException e) {
            return "Error";
        }
    }

    public String removeQuestion(String uuid, String token) {
        Request r = new Request.Builder().url(baseURL + "/remove_category?token=" + token + "&uuid=" + uuid).get().build();
        try {
            return HTTP_CLIENT.newCall(r).execute().body().string();
        } catch (IOException e) {
            return "Error";
        }
    }

    public boolean checkToken(String token) {
        Request r = new Request.Builder().url(baseURL + "/check_token?token=" + token).get().build();
        try {
            return Boolean.parseBoolean(HTTP_CLIENT.newCall(r).execute().body().string());
        } catch (Exception e) {
            return false;
        }
    }

    public String sendCompletedQuizz(String dest, CompletedQuizz quizz) {
        Request r = new Request.Builder().url(baseURL + "/send_completed_quizz?dest=" + dest).post(RequestBody.create(JSON, new Gson().toJson(quizz))).build();
        try {
            return HTTP_CLIENT.newCall(r).execute().body().string();
        } catch (IOException e) {
            return "Error";
        }
    }

    public String addQuestionBulk(ArrayList<Questions> questions, String token) {
        Request r = new Request.Builder().url(baseURL + "/add_question_bulk?token=" + token).post(RequestBody.create(JSON, new Gson().toJson(questions))).build();
        try {
            return HTTP_CLIENT.newCall(r).execute().body().string();
        } catch (IOException e) {
            return "Error";
        }
    }

    public String removeCategoriesBulk(ArrayList<String> cats, String token) {
        Request r = new Request.Builder().url(baseURL + "/remove_category_bulk?token=" + token).post(RequestBody.create(JSON, new Gson().toJson(cats))).build();
        try {
            return HTTP_CLIENT.newCall(r).execute().body().string();
        } catch (IOException e) {
            return "Error";
        }
    }

    public String removeQuestionsBulk(ArrayList<String> cats, String token) {
        Request r = new Request.Builder().url(baseURL + "/remove_question_bulk?token=" + token).post(RequestBody.create(JSON, new Gson().toJson(cats))).build();
        try {
            return HTTP_CLIENT.newCall(r).execute().body().string();
        } catch (IOException e) {
            return "Error";
        }
    }

    public static void main(String[] args) {
        System.out.println(new Gson().toJson(DEFAULT_INSTANCE.fetchQuizz("4D5EA06F")));
    }
}
