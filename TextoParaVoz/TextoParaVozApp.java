import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TextoParaVozApp {

    public static void main(String[] args) {
        // Criar janela
        JFrame frame = new JFrame("Texto para Voz");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Campo de texto
        JTextField campoTexto = new JTextField(25);

        // Botão
        JButton botaoFalar = new JButton("Falar");

        // Ação do botão
        botaoFalar.addActionListener((ActionEvent e) -> {
            String texto = campoTexto.getText();

            try {
                String comando = "powershell -Command \"Add-Type -AssemblyName System.Speech; "
                        + "$fala = New-Object System.Speech.Synthesis.SpeechSynthesizer; "
                        + "$fala.Speak('" + texto + "');\"";

                Runtime.getRuntime().exec(comando);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Adicionar na tela
        frame.add(new JLabel("Digite o texto:"));
        frame.add(campoTexto);
        frame.add(botaoFalar);

        // Mostrar
        frame.setVisible(true);
    }
}