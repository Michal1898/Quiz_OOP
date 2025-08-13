package Answers;

public class Answer {
    private int hashCode;
    private String answer;
    private boolean answerCorrect;

    public Answer(int hashCode, String answer, boolean isCorrect) {
        this.hashCode = hashCode;
        this.answer = answer;
        this.answerCorrect = isCorrect;
    }

    @Override
    public String toString() {
        String result = this.answer;
        return result;
    }

    public int getHashCode() {
        return this.hashCode;
    }

    public boolean isAnswerCorrect() {
        return this.answerCorrect;
    }

}
