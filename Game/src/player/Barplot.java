package player;

public class Barplot {


    public static String drawLabel(String label, int length) {
        if (label.length() > length) {
            StringBuilder lab = new StringBuilder();
            for (int i = 0; i < length; i++) {
                lab.append(label.charAt(i));
            }
            return lab.toString();
        } else {
            int rest = length - label.length();
            label = label + " ".repeat(rest);
            return label;
        }
    }

    static String drawBar(String label, int value) {
        label +=
                " |";
        if (value > 100) {
            return null;
        } else {
            label = label + "-".repeat(Math.max(0, value));
        }

        return label;
    }

    public static void main(String[] args) {
        System.out.println(drawBar("test", 5));
    }

}