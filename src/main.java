
import java.util.HashMap;
import java.util.Map;

public class main {
    public static void main(String ... args) {
        String[][] input = new String[][]{{"B2","E5","F6"}, {"A1","B2","C3","D4"}, {"D4","G7","I9"}, {"G7","H8"}};

        Map<String, String[]> output = new HashMap<>();
        String director = findCEO(input, output);
        long startTime = System.nanoTime();
        String result = sumOfMultiples(3, 5, 1000) + "";
        long endTime = System.nanoTime();
        System.out.println(result + " with " + (endTime - startTime)/1000000 + "milliseconds");

    }

    private static int sumOfMultiples(int mulp1, int mulp2, int size) {
//        int sum = 0;
//        for (int i = 1; i < 1000; i++) {
//            if (i % mulp1 == 0 || i % mulp2 == 0) {
//                sum += i;
//            }
//        }
//        return sum;
        return getSumAP(mulp1, size) + getSumAP(mulp2, size) - getSumAP((mulp1 * mulp2), size);

    }

    private static int getSumAP(int number, int size) {
        int lastNumber = 0;
        for (int i = size - 1; i > 0; i --) {
            if (i % number == 0) {
                lastNumber = i;
                break;
            }
        }
        int n = lastNumber/number; // terms to add
        return n * (lastNumber + number) / 2;
    }

    private static String printOrgChart(String director, Map<String, String[]> map, int level) {
        StringBuilder output = new StringBuilder();
        output.append(director);
        output.append("\n");
        if (map.containsKey(director)) {
            String[] employees = map.get(director);
            for (int i = 1; i < employees.length; i++) {
                output.append(generateSpace(level));
                output.append(printOrgChart(employees[i], map, level + 1));
            }
        }
        return output.toString();
    }

    private static String generateSpace(int level) {
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < level; i++) {
            spaces.append("    ");
        }
        return spaces.toString();
    }

    private static String findCEO(String[][] input, Map<String, String[]> map) {
        String director = input[0][0];
        map.put(director, input[0]);
        for (int i = 1; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++){
                if (j == 0) {
                    map.put(input[i][0], input[i]);
                }
                if (input[i][j].equals(director)) {
                    director = input[i][0];
                    break;
                }
            }
        }
        return director;
    }
}
