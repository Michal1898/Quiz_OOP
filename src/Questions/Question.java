package Questions;

import java.util.Objects;

public class Question {
    private String question;
    private String type;
    private String difficulty;
    private int hashCode=this.hashCode();
    public Question(String question, String type, int difficulty) {
        this.question=question;
        if (type.toLowerCase().contains("single")) {
            this.type = "Single choice";
        }else {
            this.type = "Multiple choice";
        }
        switch (difficulty) {
            case 1: {
                this.difficulty = "Easy";
                break; }
            case 2: {
                this.difficulty = "Medium";
                break;
            }
            case 3: {
                this.difficulty = "Hard";
                break;
            }
            default:{
                this.difficulty = "None";
                break;
            }
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, type, difficulty);
        }

    public String getQuestion() {
        return this.question;
    }



@Override
public String toString() {
    return (question + "\n" + "type: " + type + "\n" + "obtiznost: " + difficulty ); }
}