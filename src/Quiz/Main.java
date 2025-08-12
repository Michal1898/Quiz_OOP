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
        Question question1 = new Question("Na pasece se pase bily kun. Jakou ma barvu?", "Single",1);
        Question question2 = new Question("Kolik je 2+2?", "multi",2);
        Question question3 = new Question("Kdo vynalezl parni stroj?", "Multi",3);
        Answer answer1 = new Answer(question1.hashCode(), "bily", true);
        Answer answer2 = new Answer(question1.hashCode(), "cerveny", false);
        Answer answer3 = new Answer(question1.hashCode(), "cerny", false);
        Answer answer4 = new Answer(question1.hashCode(), "grosovany", false);

        Answer answer5 = new Answer(question2.hashCode(), "0", false);
        Answer answer6 = new Answer(question2.hashCode(), "2", false);
        Answer answer7 = new Answer(question2.hashCode(), "-2", false);
        Answer answer8 = new Answer(question2.hashCode(), "4", true);

        Answer answer9 = new Answer(question3.hashCode(), "Denis Papin", false);
        Answer answer10 = new Answer(question3.hashCode(), "Thomas Newcomen", false);
        Answer answer11 = new Answer(question3.hashCode(), "James Watt", false);
        Answer answer12 = new Answer(question3.hashCode(), "Richard Threvithick", false);
        Answer answer13 = new Answer(question3.hashCode(), "Nelze odpovedet. Parni stroj nema jedineho tvurce.", true);



        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);

        answers.add(answer5);
        answers.add(answer6);
        answers.add(answer7);
        answers.add(answer8);

        answers.add(answer9);
        answers.add(answer10);
        answers.add(answer11);
        answers.add(answer12);
        answers.add(answer13);

        String filePath = "src\\FileManagement\\questions.txt";
        System.out.println("Present Project Directory : "+ System.getProperty("user.dir"));
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            System.out.println("Soubor otevren.");
            String line;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }

        }
        catch(FileNotFoundException e) {
            System.out.println("File not found!");
        }
        catch(IOException e) {
            System.out.println("Something went wrong!");
        }

        for (Question question : questions) {





            for (Answer answer : answers) {
                if (question.hashCode() == answer.getHashCode()) {
                    System.out.println(answer);
                }
            }

        }

        }
    }
