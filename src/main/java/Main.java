import java.util.Scanner;

class Main {
    private static double concatFractionalPart(double d) {
        String[] string = Double.toString(d).split("\\.");
        String first = "0.";
        return Double.parseDouble(first+string[1]);
    }

    private static String convertFractionalPart(double d, int radix) {
        StringBuilder toSent = new StringBuilder();
        double toParse = d * radix;
        int convert;
        for (int i = 0; i < 5; i++) {
            String[] string = Double.toString(toParse).split("\\.");
            convert = Integer.parseInt(string[0]);
            if (convert > 9) {
                toSent.append(convertInteger(convert, radix));
            } else toSent.append(Integer.toString(convert));
            toParse = Double.parseDouble("0." + string[1]);
            toParse = toParse * radix;
        }
        return toSent.toString();
    }

    private static String convertInteger(long num, int radix) {
        StringBuilder output = new StringBuilder();
        if (radix == 1) {
            for (int i = 0; i < num; i++) {
                output.append("1");
            }
            return output.toString();
        } else {
            return Long.toString(num, radix);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int radixOfNum, radixTarget;
        String numInString;
        try {
            while (true) {
                radixOfNum = sc.nextInt();
                numInString = sc.next();
                radixTarget = sc.nextInt();
                if (radixOfNum > 0 && radixOfNum < 37 && radixTarget > 0 && radixTarget < 37) {
                    break;
                } else {
                    System.out.println("The radix value is not in the range");
                }
            }

            String[] number = numInString.split("\\.");
            String convertedFractionalPart = "";
            if (number.length > 1) {
                double fractionalPart = 0d;
                char[] ch = number[1].toCharArray();
                long[] fractionalNumbers = new long[ch.length];
                for (int i = 0; i < ch.length; i++) {
                    fractionalNumbers[i] = Long.parseLong(String.valueOf(ch[i]), radixOfNum);
                }
                for (int i = 1; i < fractionalNumbers.length + 1; i++) {
                    fractionalPart += fractionalNumbers[i - 1] / Math.pow(radixOfNum, i);
                }
                convertedFractionalPart = convertFractionalPart(fractionalPart, radixTarget);
            }

            long num;
            if (radixOfNum == 1) {
                num = number[0].length();
            } else {
                num = Long.parseLong(number[0], radixOfNum);
            }
            String convertedInteger = convertInteger(num, radixTarget);

            System.out.println(number.length > 1 ? convertedInteger + "." + convertedFractionalPart : convertedInteger);
        } catch (Exception ex) {
            System.out.println("Error");
        }
    }
}