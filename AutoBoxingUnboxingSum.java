import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AutoBoxingUnboxingSum {
    
    // Method to convert a list of string numbers into Integer objects
    public static List<Integer> parseStringToIntegers(String[] strNumbers) {
        List<Integer> numbers = new ArrayList<>();
        for (String str : strNumbers) {
            try {
                // Autoboxing: int to Integer
                numbers.add(Integer.parseInt(str));
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format: " + str);
            }
        }
        return numbers;
    }

    // Method to calculate sum using unboxing (Integer -> int)
    public static int calculateSum(List<Integer> numbers) {
        int sum = 0;
        for (Integer num : numbers) {
            // Unboxing: Integer to int
            sum += num;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User input for numbers as a space-separated string
        System.out.println("Enter numbers separated by spaces:");
        String input = scanner.nextLine();
        scanner.close();

        // Split input string into an array of numbers
        String[] strNumbers = input.split("\\s+");

        // Convert string numbers to Integer list
        List<Integer> numbers = parseStringToIntegers(strNumbers);

        // Calculate and display the sum
        int sum = calculateSum(numbers);
        System.out.println("Sum of numbers: " + sum);
    }
}
