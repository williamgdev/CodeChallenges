
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class main {
    private static final Scanner scan = new Scanner(System.in);


    public static void main(String[] args) {

        int[] stockPricesYesterday = new int[] {10, 7, 5, 8, 11, 9};

        getMaxProfit(stockPricesYesterday);
    }

    private static void getMaxProfit(int[] stockPricesYesterday) {
        int maxProfit = 0;
        for (int i = 0; i < stockPricesYesterday.length; i++) {

        }
        List<? extends  main> L;
    }

    private static void makeRotattionNTimes() {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int r = in.nextInt();
        int[][] matrix = new int[m][n];
        for(int matrix_i = 0; matrix_i < m; matrix_i++){
            for(int matrix_j = 0; matrix_j < n; matrix_j++){
                matrix[matrix_i][matrix_j] = in.nextInt();
            }
        }
        for (int i = 0; i < r; i++) {
            matrixRotation(matrix);
        }
        printArray(matrix);
        in.close();
    }
// returns 6 (buying for $5 and selling for $11)

    static void matrixRotation(int[][] matrix) {
        // Complete this function
        int col = 0, row = 0, lastRow = matrix.length - 1, lastCol = matrix[0].length - 1;
        while (row < lastRow && col < lastCol) {
            int first =  matrix[row][col];
            // right
            for (int i = col + 1; i <= lastCol; i++) {
                matrix[row][i - 1] = matrix[row][i];
            }

            // down
            for (int i = row; i < lastRow; i++) {
                matrix[i][lastCol] = matrix[i + 1][lastCol];
            }

            //left
            for (int i = lastCol; i > col; i--) {
                matrix[lastRow][i] = matrix[lastRow][i - 1];
            }

            //up
            for (int i = lastRow; i > row; i--) {
                matrix[i][col] = matrix[i - 1][col];
            }

            matrix[row+1][col] = first;
            col++;
            row++;
            lastCol--;
            lastRow--;
        }
    }

    static void printArray(int[][] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    static String timeConversion(String s) {
        /*
         * Write your code here.
         */
        String dayPart = s.substring(8, 10);
        String result = s.substring(0, 8);
        int hour = Integer.parseInt(s.substring(0, 2));
        if (dayPart.equals("AM") && hour == 12) {
            result = "00" + s.substring(2, 8);
        } else if (dayPart.equals("PM") && hour != 12) {
            hour += 12;
            if (hour == 24) {
                result = "12" + s.substring(2, 8);
            } else {
                result = hour + s.substring(2, 8);
            }
        }
        return result;
    }

    static void plusMinus(int[] arr) {
        /*
         * Write your code here.
         * 6
         * 1 2 -3 4 6
         */
        double aux1 = 0, aux2 = 0;
        for (int i = 0; i < arr.length; i ++) {
            if (arr[i] == 0) {
                aux2 ++;
            } else if (arr[i] > 0){
                aux1 ++;
            }
        }
        DecimalFormat df = new DecimalFormat("#.######");
        System.out.println(df.format(aux1 / arr.length));
        System.out.println(df.format((arr.length - aux1 - aux2) / arr.length));
        System.out.println(df.format(aux2 / arr.length));
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
