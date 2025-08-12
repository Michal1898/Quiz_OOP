package Quiz;

import Answers.Answer;
import Questions.Question;

import java.io.*;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
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
                    System.out.println(questionText);

                } else if (line.equals(ANSWER_END)) {
                    possibleAnswers.add(answerText);
                    //System.out.println();
                    //System.out.println(possibleAnswers);
                } else {
                    if (answerRead) {
                        answerText += line;
                    } else if (questionRead) {
                        questionText += line;
                    } else {
                        // this case should never occur
                        // but better save then sorry!
                        System.out.println("neco je spatne");
                    }
                }

            }
            // System.out.println(questionText);
            // System.out.println(possibleAnswers);
            Question load_question = new Question(questionText, "single", 1);
            questions.add(load_question);
            for (String answer : possibleAnswers) {
                Answer load_answer = new Answer(load_question.hashCode(), answer, true);
                answers.add(load_answer);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }

        for (Question question : questions) {


            for (Answer answer : answers) {
                if (question.hashCode() == answer.getHashCode()) {
                    System.out.println(answer);
                }
            }

        }



                // How to write a file using Java (4 popular options)

                // FileWriter = Good for small or medium-sized text files
                // BufferedWriter = Better performance for large amounts of text
                // PrintWriter = Best for structured data, like reports or logs
                // FileOutputStream = Best for binary files (e.g., images, audio files)
                filePath = "src\\FileManagement\\first_output.txt";
                String textContent = """
                        Roses are Red
                        Violets are Blue
                        BOOTY BOOTY BOOTY
                        ROCKIN' EVERWHERE!
                        """;

                try (FileWriter writer = new FileWriter(filePath)) {
                    writer.write(questions);
                    System.out.println("File has been written");
                } catch (FileNotFoundException e) {
                    System.out.println("Could not locate file location");
                } catch (IOException e) {
                    System.out.println("Could not write file");
                }
            }
        }


