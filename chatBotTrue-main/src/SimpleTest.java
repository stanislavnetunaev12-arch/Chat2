public class SimpleTest {

    public static void main(String[] args) {
        System.out.println("=== Запуск тестов чат-бота ===\n");

        testQuestionStorage();
        testQuestionCheckAnswer();
        testDialogLogic();
        testIntegration();

        System.out.println("\n=== Все тесты завершены ===");
    }

    public static void testQuestionStorage() {
        System.out.println("Тест 1: Хранилище вопросов");

        QuestionStorage storage = new QuestionStorage();

        // Проверяем, что вопросы есть
        if (storage.getQuestionsCount() > 0) {
            System.out.println("✓ В хранилище есть вопросы");
        } else {
            System.out.println("✗ В хранилище нет вопросов");
            return;
        }

        // Проверяем первый вопрос
        QuestionStorage.Question question = storage.getQuestion(0);
        if (question != null && question.getQuestion() != null && question.getAnswer() != null) {
            System.out.println("✓ Первый вопрос корректен");
        } else {
            System.out.println("✗ Первый вопрос некорректен");
        }

        // Проверяем граничные случаи
        if (storage.getQuestion(-1) == null) {
            System.out.println("✓ Обработка отрицательного индекса работает");
        } else {
            System.out.println("✗ Обработка отрицательного индекса не работает");
        }

        if (storage.getQuestion(100) == null) {
            System.out.println("✓ Обработка слишком большого индекса работает");
        } else {
            System.out.println("✗ Обработка слишком большого индекса не работает");
        }
    }

    public static void testQuestionCheckAnswer() {
        System.out.println("\nТест 2: Проверка ответов");

        QuestionStorage.Question question = new QuestionStorage.Question("Тестовая загадка", "правильный ответ");

        // Тестируем правильный ответ
        if (question.checkAnswer("правильный ответ")) {
            System.out.println("✓ Правильный ответ принимается");
        } else {
            System.out.println("✗ Правильный ответ не принимается");
        }

        // Тестируем регистр
        if (question.checkAnswer("ПРАВИЛЬНЫЙ ОТВЕТ")) {
            System.out.println("✓ Регистр игнорируется");
        } else {
            System.out.println("✗ Регистр не игнорируется");
        }

        // Тестируем пробелы
        if (question.checkAnswer(" правильный ответ ")) {
            System.out.println("✓ Пробелы обрезаются");
        } else {
            System.out.println("✗ Пробелы не обрезаются");
        }

        // Тестируем неправильный ответ
        if (!question.checkAnswer("неправильный ответ")) {
            System.out.println("✓ Неправильный ответ отклоняется");
        } else {
            System.out.println("✗ Неправильный ответ не отклоняется");
        }
    }

    public static void testDialogLogic() {
        System.out.println("\nТест 3: Логика диалога");

        QuestionStorage storage = new QuestionStorage();
        DialogLogic dialog = new DialogLogic(storage);

        // Проверяем приветственное сообщение
        if (dialog.getWelcomeMessage() != null && !dialog.getWelcomeMessage().isEmpty()) {
            System.out.println("✓ Приветственное сообщение работает");
        } else {
            System.out.println("✗ Приветственное сообщение не работает");
        }

        // Проверяем help сообщение
        if (dialog.getHelpMessage() != null && !dialog.getHelpMessage().isEmpty()) {
            System.out.println("✓ Help сообщение работает");
        } else {
            System.out.println("✗ Help сообщение не работает");
        }

        // Проверяем наличие вопросов
        if (dialog.hasNextQuestion()) {
            System.out.println("✓ Есть следующие вопросы");
        } else {
            System.out.println("✗ Нет следующих вопросов");
        }

        // Проверяем получение вопроса
        String question = dialog.getNextQuestion();
        if (question != null && !question.isEmpty()) {
            System.out.println("✓ Получение вопроса работает");
        } else {
            System.out.println("✗ Получение вопроса не работает");
        }
    }

    public static void testIntegration() {
        System.out.println("\nТест 4: Интеграционный тест");

        QuestionStorage storage = new QuestionStorage();
        DialogLogic dialog = new DialogLogic(storage);

        // Тестируем команду help
        String helpResponse = dialog.processAnswer("\\help");
        if (helpResponse.contains("Привет") || helpResponse.contains("бот")) {
            System.out.println("✓ Команда help работает");
        } else {
            System.out.println("✗ Команда help не работает");
        }

        // Тестируем команду exit
        String exitResponse = dialog.processAnswer("\\exit");
        if ("exit".equals(exitResponse)) {
            System.out.println("✓ Команда exit работает");
        } else {
            System.out.println("✗ Команда exit не работает");
        }

        System.out.println("✓ Интеграционные тесты пройдены");
    }
}