package Quiz;

import Answers.Answer;
import Questions.Question;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

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
            String testType="";
            int difficulty=0;
            int lastQuestionHash=0;


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
                    String questionType =splitQuestion[1];
                    questionText = splitQuestion[2];
                    Question load_question=new Question(questionText, questionType,difficulty );
                    questions.add(load_question);
                    lastQuestionHash = load_question.hashCode();


                } else if (line.equals(ANSWER_END)) {
                    String[] splitAnswer = answerText.split(";");
                    boolean correct = splitAnswer[0].contains("1");
                    Answer load_answer = new Answer(lastQuestionHash, splitAnswer[1], correct);
                    answers.add(load_answer);
                } else {
                    if (answerRead) {
                        answerText += line+"\n";
                    } else if (questionRead) {
                        questionText += line+"\n";
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

        for (Question question : questions) {
            System.out.println(question);
            for (Answer answer : answers) {
                if (question.hashCode() == answer.getHashCode()) {
                    System.out.println(answer);
                }
            }
            System.out.println("\n");

        }



                // How to write a file using Java (4 popular options)

                // FileWriter = Good for small or medium-sized text files
                // BufferedWriter = Better performance for large amounts of text
                // PrintWriter = Best for structured data, like reports or logs
                // FileOutputStream = Best for binary files (e.g., images, audio files)
//                filePath = "src\\FileManagement\\first_output.txt";
//                String textContent = """
//                        Roses are Red
//                        Violets are Blue
//                        BOOTY BOOTY BOOTY
//                        ROCKIN' EVERWHERE!
//                        """;
//
//                try (FileWriter writer = new FileWriter(filePath)) {
//                    writer.write(textContent);
//                    System.out.println("File has been written");
//                } catch (FileNotFoundException e) {
//                    System.out.println("Could not locate file location");
//                } catch (IOException e) {
//                    System.out.println("Could not write file");
//                }
            }
        }


