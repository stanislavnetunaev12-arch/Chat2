public class DialogLogic {
    private QuestionStorage questionStorage;
    private int currentQuestionIndex;

    public DialogLogic(QuestionStorage questionStorage) {
        this.questionStorage = questionStorage;
        this.currentQuestionIndex = 0;
    }

    public String getWelcomeMessage() {
        return "Привет! Я бот-загадчик. Я буду задавать тебе загадки, а ты попробуй отгадать.\n" +
                "Напиши '\\help' чтобы увидеть это сообщение снова.\n" +
                "Напиши '\\exit' чтобы выйти.\n" +
                "Давай начнем!";
    }

    public String getHelpMessage() {
        return getWelcomeMessage();
    }

    public boolean hasNextQuestion() {
        return currentQuestionIndex < questionStorage.getQuestionsCount();
    }

    public String getNextQuestion() {
        if (!hasNextQuestion()) {
            currentQuestionIndex = 0;
        }

        QuestionStorage.Question question = questionStorage.getQuestion(currentQuestionIndex);
        return question.getQuestion();
    }

    public String processAnswer(String userAnswer) {
        if (userAnswer.equals("\\help")) {
            return getHelpMessage();
        }

        if (userAnswer.equals("\\exit")) {
            return "exit";
        }

        QuestionStorage.Question currentQuestion = questionStorage.getQuestion(currentQuestionIndex);
        boolean isCorrect = currentQuestion.checkAnswer(userAnswer);

        String result;
        if (isCorrect) {
            result = "Правильно! Молодец!";
        } else {
            result = "Неправильно! Правильный ответ: " + currentQuestion.getAnswer();
        }

        currentQuestionIndex++;

        if (!hasNextQuestion()) {
            result += "\n\nТы ответил на все загадки! Хочешь начать заново?";
            currentQuestionIndex = 0;
        }

        return result;
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }
}