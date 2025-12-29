import java.io.*;

public class Serializador {

    public static void serializar(Pessoa pessoa, String arquivo) throws IOException {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(pessoa);
        }
    }

    public static Pessoa desserializar(String arquivo)
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(arquivo))) {
            return (Pessoa) ois.readObject();
        }
    }
}