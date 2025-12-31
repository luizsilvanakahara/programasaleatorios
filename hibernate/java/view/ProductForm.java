package view;

import dao.ProdutoDAO;
import model.Produto;

import javax.swing.*;
import java.awt.*;

public class ProdutoForm extends JFrame {

    private JTextField txtNome;
    private JTextField txtPreco;
    private JTextArea txtLista;

    private ProdutoDAO dao = new ProdutoDAO();

    public ProdutoForm() {
        setTitle("Cadastro de Produtos");
        setSize(400, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painel = new JPanel(new GridLayout(3, 2));
        painel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painel.add(txtNome);

        painel.add(new JLabel("Preço:"));
        txtPreco = new JTextField();
        painel.add(txtPreco);

        JButton btnSalvar = new JButton("Salvar");
        painel.add(btnSalvar);

        JButton btnListar = new JButton("Listar");
        painel.add(btnListar);

        add(painel, BorderLayout.NORTH);

        txtLista = new JTextArea();
        add(new JScrollPane(txtLista), BorderLayout.CENTER);

        btnSalvar.addActionListener(e -> salvar());
        btnListar.addActionListener(e -> listar());
    }

    private void salvar() {
        String nome = txtNome.getText();
        Double preco = Double.parseDouble(txtPreco.getText());

        Produto p = new Produto(nome, preco);
        dao.salvar(p);

        JOptionPane.showMessageDialog(this, "Produto salvo!");
        txtNome.setText("");
        txtPreco.setText("");
    }

    private void listar() {
        txtLista.setText("");
        for (Produto p : dao.listar()) {
            txtLista.append(
                p.getId() + " - " + p.getNome() + " - R$ " + p.getPreco() + "\n"
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProdutoForm().setVisible(true);
        });
    }
}
