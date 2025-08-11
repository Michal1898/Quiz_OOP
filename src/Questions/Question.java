package Questions;

public class Question {
    private String question;
    private int hashCode=this.hashCode();
    public Question(String question) {
        this.question=question;
    }

    @Override
    public int hashCode() {
        final int prime = 13;
        if (this.question == null) {
            return super.hashCode();
        }  else {
            int result = prime * this.question.hashCode();
            return result;
        }

    }
    public String getQuestion() {
        return this.question;
    }
    public int getHashCode() {
        return this.hashCode;
    }

@Override
public String toString() {
    return "Question [question=" + question + ", hashCode=" + hashCode + "]";}
}