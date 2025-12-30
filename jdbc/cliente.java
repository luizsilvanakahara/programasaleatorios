import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class ClienteCRUD extends JFrame {
    private JTextField txtNome, txtEmail;
    private JButton btnSalvar, btnAtualizar, btnDeletar;
    private JTable tabela;
    private DefaultTableModel modelo;

    // Dados do banco
    private String url = "jdbc:mysql://localhost:3306/seu_banco";
    private String usuario = "root";
    private String senha = "senha";

    private int clienteSelecionado = -1;

    public ClienteCRUD() {
        super("Cadastro de Clientes");
        setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 20, 80, 25);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(100, 20, 200, 25);
        add(txtNome);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 60, 80, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(100, 60, 200, 25);
        add(txtEmail);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(20, 100, 90, 30);
        add(btnSalvar);

        btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(120, 100, 100, 30);
        add(btnAtualizar);

        btnDeletar = new JButton("Deletar");
        btnDeletar.setBounds(230, 100, 90, 30);
        add(btnDeletar);

        modelo = new DefaultTableModel(new String[]{"ID", "Nome", "Email"}, 0);
        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 150, 300, 200);
        add(scroll);

        // Eventos
        btnSalvar.addActionListener(e -> incluirCliente());
        btnAtualizar.addActionListener(e -> atualizarCliente());
        btnDeletar.addActionListener(e -> deletarCliente());

        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                clienteSelecionado = tabela.getSelectedRow();
                if (clienteSelecionado != -1) {
                    txtNome.setText(modelo.getValueAt(clienteSelecionado, 1).toString());
                    txtEmail.setText(modelo.getValueAt(clienteSelecionado, 2).toString());
                }
            }
        });

        setSize(360, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        listarClientes();
    }

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }

    private void listarClientes() {
        modelo.setRowCount(0);
        String sql = "SELECT * FROM clientes";
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getInt("id"), rs.getString("nome"), rs.getString("email")});
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar clientes: " + ex.getMessage());
        }
    }

    private void incluirCliente() {
        String sql = "INSERT INTO clientes (nome, email) VALUES (?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, txtNome.getText());
            stmt.setString(2, txtEmail.getText());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Cliente incluído com sucesso!");
            txtNome.setText("");
            txtEmail.setText("");
            listarClientes();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao incluir cliente: " + ex.getMessage());
        }
    }

    private void atualizarCliente() {
        if (clienteSelecionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para atualizar!");
            return;
        }

        String sql = "UPDATE clientes SET nome=?, email=? WHERE id=?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int id = (int) modelo.getValueAt(clienteSelecionado, 0);
            stmt.setString(1, txtNome.getText());
            stmt.setString(2, txtEmail.getText());
            stmt.setInt(3, id);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Cliente atualizado!");
            txtNome.setText("");
            txtEmail.setText("");
            clienteSelecionado = -1;
            listarClientes();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar cliente: " + ex.getMessage());
        }
    }

    private void deletarCliente() {
        if (clienteSelecionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para deletar!");
            return;
        }

        String sql = "DELETE FROM clientes WHERE id=?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int id = (int) modelo.getValueAt(clienteSelecionado, 0);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Cliente deletado!");
            txtNome.setText("");
            txtEmail.setText("");
            clienteSelecionado = -1;
            listarClientes();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao deletar cliente: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new ClienteCRUD();
    }
}
