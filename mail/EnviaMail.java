import jakarta.mail.*;
import jakarta.mail.internet.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Properties;

public class EmailComAnexoGUI extends JFrame {

    private JTextField txtPara, txtAssunto;
    private JTextArea txtMensagem;
    private JButton btnEnviar, btnAnexo;
    private File arquivoAnexo;

    // CONFIGURE SEU EMAIL
    private final String usuario = "seuemail@gmail.com";
    private final String senha = "SENHA_DE_APP";

    public EmailComAnexoGUI() {
        setTitle("Envio de E-mail com Anexo");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(5, 2, 5, 5));

        painel.add(new JLabel("Para:"));
        txtPara = new JTextField();
        painel.add(txtPara);

        painel.add(new JLabel("Assunto:"));
        txtAssunto = new JTextField();
        painel.add(txtAssunto);

        painel.add(new JLabel("Mensagem:"));
        txtMensagem = new JTextArea(5, 20);

        btnAnexo = new JButton("Selecionar Anexo");
        btnEnviar = new JButton("Enviar");

        painel.add(btnAnexo);
        painel.add(btnEnviar);

        add(painel, BorderLayout.NORTH);
        add(new JScrollPane(txtMensagem), BorderLayout.CENTER);

        btnAnexo.addActionListener(e -> escolherAnexo());
        btnEnviar.addActionListener(e -> enviarEmail());
    }

    private void escolherAnexo() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            arquivoAnexo = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this,
                    "Anexo selecionado:\n" + arquivoAnexo.getName());
        }
    }

    private void enviarEmail() {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(usuario, senha);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(usuario));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(txtPara.getText())
            );
            message.setSubject(txtAssunto.getText());

            // Corpo do e-mail
            MimeBodyPart corpoTexto = new MimeBodyPart();
            corpoTexto.setText(txtMensagem.getText());

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(corpoTexto);

            // Anexo
            if (arquivoAnexo != null) {
                MimeBodyPart anexo = new MimeBodyPart();
                anexo.attachFile(arquivoAnexo);
                multipart.addBodyPart(anexo);
            }

            message.setContent(multipart);

            Transport.send(message);

            JOptionPane.showMessageDialog(this, "E-mail enviado com sucesso!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao enviar:\n" + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new EmailComAnexoGUI().setVisible(true)
        );
    }
}
