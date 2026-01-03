import java.util.*;

/**
 * Biblioteca utilitária para conversões entre
 * Array, List, Set e Queue.
 */
public final class ConversaoColecoes {

    // Evita instanciação
    private ConversaoColecoes() {}

    /* ===================== ARRAY <-> LIST ===================== */

    // Array -> List (não editável)
    public static <T> List<T> arrayParaList(T[] array) {
        return Arrays.asList(array);
    }

    // Array -> List (editável)
    public static <T> List<T> arrayParaListEditavel(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }

    // List -> Array
    public static <T> T[] listParaArray(List<T> list, T[] array) {
        return list.toArray(array);
    }

    /* ===================== LIST <-> SET ===================== */

    // List -> Set (remove duplicados)
    public static <T> Set<T> listParaSet(List<T> list) {
        return new HashSet<>(list);
    }

    // Set -> List
    public static <T> List<T> setParaList(Set<T> set) {
        return new ArrayList<>(set);
    }

    /* ===================== LIST <-> QUEUE ===================== */

    // List -> Queue (FIFO)
    public static <T> Queue<T> listParaQueue(List<T> list) {
        return new LinkedList<>(list);
    }

    // Queue -> List
    public static <T> List<T> queueParaList(Queue<T> queue) {
        return new ArrayList<>(queue);
    }

    /* ===================== SET <-> QUEUE ===================== */

    // Set -> Queue
    public static <T> Queue<T> setParaQueue(Set<T> set) {
        return new LinkedList<>(set);
    }

    // Queue -> Set
    public static <T> Set<T> queueParaSet(Queue<T> queue) {
        return new HashSet<>(queue);
    }

    /* ===================== ARRAY <-> SET ===================== */

    // Array -> Set
    public static <T> Set<T> arrayParaSet(T[] array) {
        return new HashSet<>(Arrays.asList(array));
    }

    // Set -> Array
    public static <T> T[] setParaArray(Set<T> set, T[] array) {
        return set.toArray(array);
    }
}
