package Quiz;

import Answers.Answer;
import Questions.Question;

import java.io.*;
import java.util.*;

public class Main {
    public static ArrayList<Question> questions = new ArrayList<Question>();
    public static ArrayList<Answer> answers = new ArrayList<Answer>();


    public static void main(String[] args) {


        ArrayList<String> paragraphNameCoder = new ArrayList<String>();
        paragraphNameCoder.add("a");
        paragraphNameCoder.add("b");
        paragraphNameCoder.add("c");
        paragraphNameCoder.add("d");
        paragraphNameCoder.add("e");
        paragraphNameCoder.add("f");
        paragraphNameCoder.add("g");

        printIntroduction();
        readQAfromFile();

        // Initialise coder od letters and numbers by answers


        // New quiz starts
        System.out.println("Kviz zacina!");
        final int questionCount = 7;

        do {
            int possiblePoints = 0;
            int earnedPoints = 0;
            for (int a = 0; a < questionCount; ++a) {
                Collections.shuffle(questions);
                // Select random question
                Question randomQuestion = questions.get(0);
                // and remove it from pool
                questions.remove(0);
                int randomQuestionHash = randomQuestion.hashCode();
                ArrayList<Answer> possibleAnswers = new ArrayList<Answer>();
                for (Answer answer : answers) {
                    if (randomQuestionHash == answer.getHashCode()) {
                        possibleAnswers.add(answer);
                    }

                }
                // random shuffle of answer order
                Collections.shuffle(possibleAnswers);
                System.out.println("\n");
                System.out.println("Otazka c: " + (a + 1));
                System.out.print(randomQuestion);

                for (Answer answer : possibleAnswers) {
                    int paragraphIndex = possibleAnswers.indexOf(answer);
                    String paragraphName = paragraphNameCoder.get(paragraphIndex);
                    System.out.print(paragraphName + " ) " + answer);
                }
                Scanner userInput = new Scanner(System.in);
                if (randomQuestion.getType().equals("Single choice")) {
                    // Evalution of SingleChoice answer
                    possiblePoints++;
                    String yourAnswer;
                    int answerIndex;
                    while (true) {
                        try {
                            System.out.println("Oznac spravnou odpoved: ");
                            yourAnswer = userInput.nextLine().toLowerCase().trim();
                            answerIndex = paragraphNameCoder.indexOf(yourAnswer);
                            if (yourAnswer.length() != 1) {
                                throw new Exception("Zadej prave 1 znak!");
                            } else if (!Character.isLetter(yourAnswer.charAt(0))) {
                                throw new Exception("Musis zadat pismeno!");
                            } else if (Integer.valueOf(yourAnswer.charAt(0) - 97) >= possibleAnswers.size()) {
                                throw new Exception("Musis si vybrat jednu z nabizenych moznosti!");
                            }
                            break;

                        } catch (Exception e) {
                            System.out.println("Invalid input!" + e.getMessage());
                        }

                    }


                    //earnedPoints

                    Answer markedAnswer = possibleAnswers.get(answerIndex);

                    System.out.println("Tvoje odpoved: \n" + markedAnswer);
                    if (markedAnswer.getAnswerCorrect()) {
                        earnedPoints++;
                        System.out.println("Uhodl jsi. Gratuluji. ");
                    } else {
                        System.out.println("Chybna odpoved. Cha Cha Chaa!");
                    }

                } else {
                    // evalution of multipleChoice answer
                    int thisQuestionPossiblePoints = 0;
                    int thisQuestionEarnedPoints = 0;
                    for (Answer answer : possibleAnswers) {

                        if (answer.getAnswerCorrect()) {
                            thisQuestionPossiblePoints++;
                        }
                    }
                    possiblePoints += thisQuestionPossiblePoints;

                    String yourAnswer;
                    String[] yourAnswers;
                    while (true) {
                        try {
                            System.out.println("Zadej svoji odpoved (mozna je vice odpovedi spravnych)");
                            yourAnswer = userInput.nextLine().toLowerCase().trim();
                            yourAnswers = yourAnswer.split(" ");
                            if (Arrays.stream(yourAnswers).count() > possibleAnswers.size()) {
                                throw new Exception("Zadej maximalne tolik znaku, kolik mas moznosti");
                            }
                            for (String oneAnswer : yourAnswers) {
                                if (oneAnswer.length() != 1) {
                                    throw new Exception("Zadej prave 1 znak!");
                                } else if (!Character.isLetter(oneAnswer.charAt(0))) {
                                    throw new Exception("Musis zadat pismeno!");
                                } else if (Integer.valueOf(oneAnswer.charAt(0) - 97) >= possibleAnswers.size()) {
                                    throw new Exception("Musis si vybrat jednu z nabizenych moznosti!");
                                }

                            }
                            break;

                        } catch (Exception e) {
                            System.out.println("Invalid input!" + e.getMessage());
                        }
                    }
                    for (String oneAnswer : yourAnswers) {
                        int answerIndex = paragraphNameCoder.indexOf(oneAnswer);
                        Answer markedAnswer = possibleAnswers.get(answerIndex);
                        if (markedAnswer.getAnswerCorrect()) {
                            thisQuestionEarnedPoints++;
                        } else {
                            thisQuestionEarnedPoints -= 2;
                        }
                    }
                    earnedPoints += thisQuestionEarnedPoints;
                    if (thisQuestionEarnedPoints == thisQuestionPossiblePoints) {
                        System.out.println("Otazku jsi uspesne vyresil. Gratuluji");
                    } else {
                        System.out.println("Chybna nebo neuplna odpoved!");
                    }

                }
                System.out.println("Prubezne skore:");
                System.out.println("Aktualne mas " + earnedPoints + " z " + possiblePoints + " moznych");
                if (possiblePoints != 0) {
                    System.out.println("To je " + (100 * earnedPoints / possiblePoints) + " %.");
                }
            }
            System.out.println("Konec testu:");
            System.out.println("Celkove jsi ziskal " + earnedPoints + " z " + possiblePoints + " moznych");
            if (possiblePoints != 0) {
                System.out.println("To je " + (100 * earnedPoints / possiblePoints) + " %.");
            }
        } while (anotherGame());


    }

    public static void printIntroduction() {
        String filePath = "src\\FileManagement\\read_me.txt";

        System.out.println("Present Project Directory : " + System.getProperty("user.dir"));

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            // end of file data processing
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
    }

    public static void readQAfromFile() {
        String filePath = "src\\FileManagement\\questions.txt";
        final String QUESTION_START = "QUESTION_START";
        final String QUESTION_END = "QUESTION_END";
        final String ANSWER_START = "ANSWER_START";
        final String ANSWER_END = "ANSWER_END";

        System.out.println("Present Project Directory : " + System.getProperty("user.dir"));
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String questionText = "";
            String answerText = "";
            boolean questionRead = false;
            boolean answerRead = false;
            int difficulty = 0;
            int lastQuestionHash = 0;

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
    }

    public static Boolean anotherGame() {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";

        Scanner again = new Scanner(System.in);
        String yesNo;
        while (true) {
            try {
                System.out.println("Nova hra?");
                yesNo = again.nextLine().toLowerCase();
                if ((yesNo.equals("ano")) || (yesNo.equals("ne"))) {
                    break;
                } else {
                    System.out.println(ANSI_RED + "Odpovez ano, nebo ne!" + ANSI_RESET);
                    again.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
                System.out.println("Odpovez ano, nebo ne!");
            }

        }
        if (yesNo.equals("ano")) {
            return true;
        } else {
            return false;
        }
    }

}