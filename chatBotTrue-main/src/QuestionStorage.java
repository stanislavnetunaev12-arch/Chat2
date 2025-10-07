import java.util.ArrayList;
import java.util.List;

public class QuestionStorage {
    private List<Question> questions;

    public QuestionStorage() {
        questions = new ArrayList<>();
        initializeQuestions();
    }

    private void initializeQuestions() {
        questions.add(new Question("Что идет без ног?", "время"));
        questions.add(new Question("Что можно сломать, даже если не трогать?", "молчание"));
        questions.add(new Question("Чем больше берешь, тем больше становиться. Что это?", "яма"));
        questions.add(new Question("Висит груша - нельзя скушать. Что это?", "лампочка"));
        questions.add(new Question("Зимой и летом одним цветом. Что это?", "ель"));
    }

    public Question getQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            return questions.get(index);
        }
        return null;
    }

    public int getQuestionsCount() {
        return questions.size();
    }

    public static class Question {
        private String question;
        private String answer;

        public Question(String question, String answer) {
            this.question = question;
            this.answer = answer;
        }

        public String getQuestion() {
            return question;
        }

        public String getAnswer() {
            return answer;
        }

        public boolean checkAnswer(String userAnswer) {
            return answer.equalsIgnoreCase(userAnswer.trim());
        }
    }
}