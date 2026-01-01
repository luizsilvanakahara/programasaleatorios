package br.com.meuprojeto.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ClienteCRUDJSON {

    private List<Cliente> clientes = new ArrayList<>();
    private final String arquivo = "clientes.json";

    public ClienteCRUDJSON() {
        carregar();
    }

    public void salvar(Cliente c) {
        clientes.add(c);
        salvarArquivo();
    }

    public void remover(Cliente c) {
        clientes.remove(c);
        salvarArquivo();
    }

    public List<Cliente> listar() {
        return clientes;
    }

    private void salvarArquivo() {
        try (FileWriter fw = new FileWriter(arquivo)) {
            new Gson().toJson(clientes, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregar() {
        File f = new File(arquivo);
        if (f.exists()) {
            try (Reader r = new FileReader(f)) {
                Type tipoLista = new TypeToken<List<Cliente>>() {}.getType();
                clientes = new Gson().fromJson(r, tipoLista);
                if (clientes == null) clientes = new ArrayList<>();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
