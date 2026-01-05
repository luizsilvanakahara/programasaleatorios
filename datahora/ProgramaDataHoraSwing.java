import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ProgramaDataHoraSwing {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sistema Completo de Data e Hora");
        frame.setSize(600, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(10, 2, 5, 5));

        JTextField txtEntrada = new JTextField("04/01/2026 15:30:45");

        JTextField txtDia = new JTextField();
        JTextField txtMes = new JTextField();
        JTextField txtAno = new JTextField();
        JTextField txtHora = new JTextField();
        JTextField txtMinuto = new JTextField();
        JTextField txtSegundo = new JTextField();

        JLabel lblResultado = new JLabel(" ");

        JButton btnValidar = new JButton("Validar Data");
        JButton btnConverter = new JButton("Converter / Atualizar");

        frame.add(new JLabel("Data e Hora (dd/MM/yyyy HH:mm:ss):"));
        frame.add(txtEntrada);

        frame.add(new JLabel("Novo Dia:"));
        frame.add(txtDia);

        frame.add(new JLabel("Novo Mês:"));
        frame.add(txtMes);

        frame.add(new JLabel("Novo Ano:"));
        frame.add(txtAno);

        frame.add(new JLabel("Nova Hora:"));
        frame.add(txtHora);

        frame.add(new JLabel("Novo Minuto:"));
        frame.add(txtMinuto);

        frame.add(new JLabel("Novo Segundo:"));
        frame.add(txtSegundo);

        frame.add(btnValidar);
        frame.add(btnConverter);

        frame.add(lblResultado);

        // 🔹 VALIDAR DATA
        btnValidar.addActionListener(e -> {
            try {
                LocalDateTime.parse(txtEntrada.getText(), FORMATTER);
                lblResultado.setText("✅ Data válida");
            } catch (DateTimeParseException ex) {
                lblResultado.setText("❌ Data inválida");
            }
        });

        // 🔹 CONVERTER E ATUALIZAR CAMPOS
        btnConverter.addActionListener(e -> {
            try {
                LocalDateTime data =
                        LocalDateTime.parse(txtEntrada.getText(), FORMATTER);

                if (!txtDia.getText().isEmpty())
                    data = data.withDayOfMonth(Integer.parseInt(txtDia.getText()));

                if (!txtMes.getText().isEmpty())
                    data = data.withMonth(Integer.parseInt(txtMes.getText()));

                if (!txtAno.getText().isEmpty())
                    data = data.withYear(Integer.parseInt(txtAno.getText()));

                if (!txtHora.getText().isEmpty())
                    data = data.withHour(Integer.parseInt(txtHora.getText()));

                if (!txtMinuto.getText().isEmpty())
                    data = data.withMinute(Integer.parseInt(txtMinuto.getText()));

                if (!txtSegundo.getText().isEmpty())
                    data = data.withSecond(Integer.parseInt(txtSegundo.getText()));

                txtEntrada.setText(data.format(FORMATTER));
                lblResultado.setText("⏱️ Data atualizada com sucesso");

            } catch (DateTimeParseException ex) {
                lblResultado.setText("❌ Formato inválido");
            } catch (NumberFormatException ex) {
                lblResultado.setText("❌ Valor numérico inválido");
            } catch (Exception ex) {
                lblResultado.setText("❌ Erro: " + ex.getMessage());
            }
        });

        frame.setVisible(true);
    }
}
