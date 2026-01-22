import javax.swing.*;
import java.awt.*;

public class SwotMatrixApp extends JFrame {

    private JTextArea strengthsArea;
    private JTextArea weaknessesArea;
    private JTextArea opportunitiesArea;
    private JTextArea threatsArea;
    private JTextArea resultArea;

    public SwotMatrixApp() {
        setTitle("Cadastro de Matriz SWOT");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        strengthsArea = new JTextArea(4, 20);
        weaknessesArea = new JTextArea(4, 20);
        opportunitiesArea = new JTextArea(4, 20);
        threatsArea = new JTextArea(4, 20);

        panel.add(new JLabel("Strengths (Forças):"));
        panel.add(new JScrollPane(strengthsArea));

        panel.add(new JLabel("Weaknesses (Fraquezas):"));
        panel.add(new JScrollPane(weaknessesArea));

        panel.add(new JLabel("Opportunities (Oportunidades):"));
        panel.add(new JScrollPane(opportunitiesArea));

        panel.add(new JLabel("Threats (Ameaças):"));
        panel.add(new JScrollPane(threatsArea));

        JButton saveButton = new JButton("Salvar Matriz SWOT");
        panel.add(saveButton);

        resultArea = new JTextArea(8, 40);
        resultArea.setEditable(false);
        panel.add(new JScrollPane(resultArea));

        add(panel);

        saveButton.addActionListener(e -> salvarSwot());
    }

    private void salvarSwot() {
        String result =
                "=== MATRIZ SWOT CADASTRADA ===\n\n" +
                "Strengths:\n" + strengthsArea.getText() + "\n\n" +
                "Weaknesses:\n" + weaknessesArea.getText() + "\n\n" +
                "Opportunities:\n" + opportunitiesArea.getText() + "\n\n" +
                "Threats:\n" + threatsArea.getText();

        resultArea.setText(result);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SwotMatrixApp().setVisible(true);
        });
    }
}
