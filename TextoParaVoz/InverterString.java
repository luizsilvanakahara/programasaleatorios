public class InverterString {
    public static void main(String[] args) {
        String texto = "java";
        char[] chars = texto.toCharArray();

        for (int i = chars.length - 1; i >= 0; i--) {
            System.out.print(chars[i]);
        }
    }
}