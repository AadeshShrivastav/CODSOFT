import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Quiz {
    private String[] questions = {
        "What is the capital of France?",
        "Which planet is known as the Red Planet?",
        "What is 2 + 2?"
    };

    private String[][] options = {
        {"Paris", "Berlin", "Madrid"},
        {"Mars", "Venus", "Jupiter"},
        {"3", "4", "5"}
    };

    private int[] answers = {0, 0, 1}; // Index of correct answers

    public String getQuestion(int index) {
        return questions[index];
    }

    public String[] getOptions(int index) {
        return options[index];
    }

    public int getAnswer(int index) {
        return answers[index];
    }

    public int getNumQuestions() {
        return questions.length;
    }
}

public class QuizApp {
    private Quiz quiz;
    private int currentQuestion = 0;
    private int score = 0;
    private int timeRemaining = 30; // Initial time in seconds

    private JFrame frame;
    private JPanel panel;
    private JLabel questionLabel;
    private JButton[] optionButtons;
    private JLabel scoreLabel;
    private JLabel timerLabel;

    public QuizApp() {
        quiz = new Quiz();
        initializeUI();
        startTimer();
    }

    private void initializeUI() {
        frame = new JFrame("Java Quiz App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        questionLabel = new JLabel();
        panel.add(questionLabel);

        optionButtons = new JButton[3];
        for (int i = 0; i < 3; i++) {
            optionButtons[i] = new JButton();
            final int index = i;
            optionButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    checkAnswer(index);
                }
            });
            panel.add(optionButtons[i]);
        }

        scoreLabel = new JLabel("Score: 0");
        panel.add(scoreLabel);

        timerLabel = new JLabel("Time: " + timeRemaining);
        panel.add(timerLabel);

        frame.add(panel);
        frame.setVisible(true);

        showQuestion();
    }

    private void showQuestion() {
        if (currentQuestion < quiz.getNumQuestions()) {
            questionLabel.setText(quiz.getQuestion(currentQuestion));
            String[] options = quiz.getOptions(currentQuestion);
            for (int i = 0; i < 3; i++) {
                optionButtons[i].setText(options[i]);
            }
        } else {
            finishQuiz();
        }
    }

    private void checkAnswer(int selectedOption) {
        if (selectedOption == quiz.getAnswer(currentQuestion)) {
            score++;
        }
        currentQuestion++;
        scoreLabel.setText("Score: " + score);
        showQuestion();
    }

    private void startTimer() {
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (timeRemaining > 0) {
                    timeRemaining--;
                    timerLabel.setText("Time: " + timeRemaining);
                } else {
                    finishQuiz();
                }
            }
        });
        timer.start();
    }

    private void finishQuiz() {
        JOptionPane.showMessageDialog(null, "Quiz finished. Your score: " + score);
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new QuizApp();
            }
        });
    }
}
