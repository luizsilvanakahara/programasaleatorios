import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.stream.Collectors;

public class ConversaoColecoesSwing extends JFrame {

    private JTextField txtEntrada;
    private JTextArea txtResultado;

    public ConversaoColecoesSwing() {
        setTitle("Conversão de Coleções");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        criarTela();
    }

    private void criarTela() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));

        // Entrada
        JPanel painelEntrada = new JPanel(new BorderLayout());
        painelEntrada.add(new JLabel("Valores (separados por vírgula):"), BorderLayout.NORTH);

        txtEntrada = new JTextField();
        painelEntrada.add(txtEntrada, BorderLayout.CENTER);

        // Botões
        JPanel painelBotoes = new JPanel(new GridLayout(1, 2, 10, 10));

        JButton btnListSet = new JButton("List → Set");
        JButton btnListMap = new JButton("List → Map");

        painelBotoes.add(btnListSet);
        painelBotoes.add(btnListMap);

        // Resultado
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtResultado);

        // Ações
        btnListSet.addActionListener(e -> converterListParaSet());
        btnListMap.addActionListener(e -> converterListParaMap());

        painel.add(painelEntrada, BorderLayout.NORTH);
        painel.add(painelBotoes, BorderLayout.CENTER);
        painel.add(scroll, BorderLayout.SOUTH);

        add(painel);
    }

    private List<String> obterLista() {
        String texto = txtEntrada.getText();

        if (texto == null || texto.isBlank()) {
            return Collections.emptyList();
        }

        return Arrays.stream(texto.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private void converterListParaSet() {
        List<String> lista = obterLista();
        Set<String> set = new HashSet<>(lista);

        txtResultado.setText("SET (sem duplicados):\n" + set);
    }

    private void converterListParaMap() {
        List<String> lista = obterLista();

        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < lista.size(); i++) {
            map.put(i, lista.get(i));
        }

        txtResultado.setText("MAP (índice → valor):\n" + map);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConversaoColecoesSwing().setVisible(true));
    }
}
