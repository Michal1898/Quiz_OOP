package Quiz;

import Answers.Answer;
import Questions.Question;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello and welcome!");

        ArrayList<Question> questions = new ArrayList<Question>();
        ArrayList<Answer> answers = new ArrayList<Answer>();


        String filePath = "src\\FileManagement\\questions.txt";
        final String QUESTION_START = "QUESTION_START";
        final String QUESTION_END = "QUESTION_END";
        final String ANSWER_START = "ANSWER_START";
        final String ANSWER_END = "ANSWER_END";

        System.out.println("Present Project Directory : " + System.getProperty("user.dir"));
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            System.out.println("Soubor otevren.");
            String line;
            String questionText = "";
            String answerText = "";
            boolean questionRead = false;
            boolean answerRead = false;
            String testType = "";
            int difficulty = 0;
            int lastQuestionHash = 0;


            ArrayList<String> possibleAnswers = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                if (line.equals(QUESTION_START)) {
                    questionText = "";
                    questionRead = true;
                    answerRead = false;

                } else if (line.equals(ANSWER_START)) {
                    answerText = "";
                    answerRead = true;
                    questionRead = false;

                } else if (line.equals(QUESTION_END)) {
                    String[] splitQuestion = questionText.split(";");
                    difficulty = Integer.parseInt(splitQuestion[0]);
                    String questionType = splitQuestion[1];
                    questionText = splitQuestion[2];
                    Question load_question = new Question(questionText, questionType, difficulty);
                    questions.add(load_question);
                    //append question to the list
                    lastQuestionHash = load_question.hashCode();


                } else if (line.equals(ANSWER_END)) {
                    String[] splitAnswer = answerText.split(";");
                    boolean correct = splitAnswer[0].contains("1");
                    Answer load_answer = new Answer(lastQuestionHash, splitAnswer[1], correct);
                    answers.add(load_answer);
                    // append answer to the list
                } else {
                    if (answerRead) {
                        answerText += line + "\n";
                    } else if (questionRead) {
                        questionText += line + "\n";
                    } else {
                        // this case should never occur
                        // but better save then sorry!
                        System.out.println("neco je spatne");
                    }
                }

            }
            // end of file data processing
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }

        // Print list of all questions in quiz and their solution
        // only for testing
//        for (Question question : questions) {
//            System.out.println(question);
//            for (Answer answer : answers) {
//                if (question.hashCode() == answer.getHashCode()) {
//                    System.out.println(answer);
//                }
//            }
//
//        }
// Begin of test
        String[] paragraphNameCoder={"a", "b", "c", "d", "e", "f"};
        System.out.println("\n");
        System.out.println("Kviz zacina!");
        int questionCount = 7;
        int possiblePoints = 0;
        for(int a=0; a<questionCount; ++a){
            Collections.shuffle(questions);
//        for (Question question : questions) {
//            System.out.println(question);
//            for (Answer answer : answers) {
//                if (question.hashCode() == answer.getHashCode()) {
//                    System.out.println(answer);
//                }
//            }
//        }
            // Select random question
            Question randomQuestion = questions.get(0);
            // and remove it from pool
            questions.remove(0);
            int randomQuestionHash = randomQuestion.hashCode();
            ArrayList<Answer> randomAnswers = new ArrayList<Answer>();
            for (Answer answer : answers) {
                if (randomQuestionHash == answer.getHashCode()) {
                    randomAnswers.add(answer);
                }

            }
            // random shuffle of answer order
            Collections.shuffle(randomAnswers);
            System.out.println("\n");
            System.out.println("Otazka c: " + (a+1));
            System.out.println(randomQuestion);
            for (Answer answer : randomAnswers) {
                String paragraphName = paragraphNameCoder[randomAnswers.indexOf(answer)];
                System.out.println(paragraphName + " ) " + answer);
            }
        }

    }
}