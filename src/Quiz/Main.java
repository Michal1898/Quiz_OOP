package Quiz;

import Answers.Answer;
import Questions.Question;

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
        Question question1 = new Question("Na pasece se pase bily kun. Jakou ma barvu?");
        Question question2 = new Question("Kolik je 2+2?");
        Question question3 = new Question("Kdo vynalezl parni stroj?");
        Answer answer1 = new Answer(question1.getHashCode(), "bily", true);
        Answer answer2 = new Answer(question1.getHashCode(), "cerveny", false);
        Answer answer3 = new Answer(question1.getHashCode(), "cerny", false);
        Answer answer4 = new Answer(question1.getHashCode(), "grosovany", false);

        Answer answer5 = new Answer(question2.getHashCode(), "0", false);
        Answer answer6 = new Answer(question2.getHashCode(), "2", false);
        Answer answer7 = new Answer(question2.getHashCode(), "-2", false);
        Answer answer8 = new Answer(question2.getHashCode(), "-2", true);

        Answer answer9 = new Answer(question3.getHashCode(), "Denis Papin", false);
        Answer answer10 = new Answer(question3.getHashCode(), "Thomas Newcomen", false);
        Answer answer11 = new Answer(question3.getHashCode(), "James Watt", false);
        Answer answer12 = new Answer(question3.getHashCode(), "Richard Threvithick", false);
        Answer answer13 = new Answer(question3.getHashCode(), "Nelze odpovedet. Parni stroj nema jedineho tvurce.", true);



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


        for (Question question : questions) {
            System.out.println(question);

            for (Answer answer : answers) {
                if (question.getHashCode() == answer.getHashCode()) {
                    System.out.println(answer);
                }
            }

        }

        }
    }
