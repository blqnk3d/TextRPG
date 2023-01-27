public class Barplot {
    public static String repeat(char c, int n) {
        String ret = "";
        for (int i = 0; i < n; i++) {
            ret += c;
        }
        return ret;
    }

    public static String drawLabel(String label, int length) {
        if (label.length() > length) {
            String lab = "";
            for (int i = 0; i < length; i++) {
                lab += label.charAt(i);
            }
            return lab;
        } else {
            int rest = length - label.length();
            for (int i = 0; i < rest; i++) {
                label += " ";
            }
            return label;
        }
    }

    static String drawBar(String label, int value) {
        label +=
                " |";
        if (value > 100) {
            return null;
        } else {
            for (int i = 0; i < value; i++) {
                label += "-";
            }
        }

        return label;
    }

    public static void main(String[] args) {
        System.out.println(drawBar("test", 5));
    }

}