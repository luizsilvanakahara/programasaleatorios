package br.com.meuprojeto.json;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainGUI {

    private ClienteCRUDJSON crud = new ClienteCRUDJSON();
    private DefaultListModel<Cliente> listModel = new DefaultListModel<>();

    public MainGUI() {
        JFrame frame = new JFrame("CRUD de Clientes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JTextField idField = new JTextField(5);
        JTextField nomeField = new JTextField(15);
        JButton addButton = new JButton("Adicionar");
        JButton removeButton = new JButton("Remover");
        JList<Cliente> clienteList = new JList<>(listModel);
        clienteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // painel superior com campos
        JPanel panel = new JPanel();
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(addButton);
        panel.add(removeButton);

        frame.getContentPane().add(panel, BorderLayout.NORTH);
        frame.getContentPane().add(new JScrollPane(clienteList), BorderLayout.CENTER);

        // preencher lista inicial
        atualizarLista();

        addButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String nome = nomeField.getText();
                Cliente c = new Cliente(id, nome);
                crud.salvar(c);
                atualizarLista();
                idField.setText("");
                nomeField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "ID deve ser um número!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        removeButton.addActionListener(e -> {
            Cliente selecionado = clienteList.getSelectedValue();
            if (selecionado != null) {
                crud.remover(selecionado);
                atualizarLista();
            }
        });

        frame.setVisible(true);
    }

    private void atualizarLista() {
        listModel.clear();
        for (Cliente c : crud.listar()) {
            listModel.addElement(c);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::new);
    }
}
