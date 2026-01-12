import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// ===== Sealed Classes =====
sealed interface Animal permits Dog, Cat {}

record Dog(String name) implements Animal {}
record Cat(String name) implements Animal {}

public class Java17SwingDemo extends JFrame {

    private JTextField inputField;
    private JTextArea outputArea;

    public Java17SwingDemo() {
        setTitle("Java 17 - Swing Demo");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        inputField = new JTextField();
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        JScrollPane scroll = new JScrollPane(outputArea);

        JButton recordButton = new JButton("Record");
        JButton instanceofButton = new JButton("Pattern instanceof");
        JButton switchButton = new JButton("Switch Expression");
        JButton textBlockButton = new JButton("Text Block");

        recordButton.addActionListener(this::useRecord);
        instanceofButton.addActionListener(this::useInstanceof);
        switchButton.addActionListener(this::useSwitch);
        textBlockButton.addActionListener(this::useTextBlock);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("Entrada:"), BorderLayout.WEST);
        topPanel.add(inputField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        buttonPanel.add(recordButton);
        buttonPanel.add(instanceofButton);
        buttonPanel.add(switchButton);
        buttonPanel.add(textBlockButton);

        add(topPanel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // ===== Record =====
    private void useRecord(ActionEvent e) {
        String name = inputField.getText();
        Dog dog = new Dog(name);
        outputArea.setText("Record criado:\n" + dog);
    }

    // ===== Pattern Matching instanceof =====
    private void useInstanceof(ActionEvent e) {
        String name = inputField.getText();
        Animal animal = name.length() % 2 == 0 ? new Dog(name) : new Cat(name);

        if (animal instanceof Dog d) {
            outputArea.setText("É um Dog chamado: " + d.name());
        } else if (animal instanceof Cat c) {
            outputArea.setText("É um Cat chamado: " + c.name());
        }
    }

    // ===== Switch Expression =====
    private void useSwitch(ActionEvent e) {
        String value = inputField.getText();

        String result = switch (value.length()) {
            case 0 -> "Texto vazio";
            case 1, 2, 3 -> "Texto curto";
            case 4, 5 -> "Texto médio";
            default -> "Texto longo";
        };

        outputArea.setText("Resultado do switch:\n" + result);
    }

    // ===== Text Block =====
    private void useTextBlock(ActionEvent e) {
        String text = """
                ======================
                Java 17 - Text Block
                Entrada digitada:
                "%s"
                ======================
                """.formatted(inputField.getText());

        outputArea.setText(text);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Java17SwingDemo().setVisible(true));
    }
}
