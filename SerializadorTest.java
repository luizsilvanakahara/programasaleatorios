import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.File;

public class SerializadorTest {

    @Test
    public void deveSerializarEDesserializarPessoa() throws Exception {
        Pessoa pessoaOriginal = new Pessoa("Luis", 30);
        String arquivo = "pessoa.ser";

        // Serialização
        Serializador.serializar(pessoaOriginal, arquivo);

        // Desserialização
        Pessoa pessoaRecuperada = Serializador.desserializar(arquivo);

        // Testes
        assertNotNull(pessoaRecuperada);
        assertEquals("Luis", pessoaRecuperada.getNome());
        assertEquals(30, pessoaRecuperada.getIdade());

        // Limpeza
        new File(arquivo).delete();
    }
}
