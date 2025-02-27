import java.io.*;
import java.util.Scanner;

// Employee class to store details
class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String designation;
    private double salary;

    // Constructor
    public Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    // Format employee details for file storage
    public String toFileFormat() {
        return id + "," + name + "," + designation + "," + salary;
    }

    // Display Employee details
    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Designation: " + designation);
        System.out.println("Salary: $" + salary);
        System.out.println("-----------------------------");
    }

    // Convert a line from file to an Employee object
    public static Employee fromFileFormat(String line) {
        String[] parts = line.split(",");
        return new Employee(
            Integer.parseInt(parts[0]), 
            parts[1], 
            parts[2], 
            Double.parseDouble(parts[3])
        );
    }
}

public class EmployeeManagement {
    private static final String FILE_NAME = "employees.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==== Employee Management Menu ====");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    System.out.println("Exiting the application...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please enter 1, 2, or 3.");
            }
        }
    }

    // Method to add an employee
    private static void addEmployee() {
        System.out.print("\nEnter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Employee Designation: ");
        String designation = scanner.nextLine();

        System.out.print("Enter Employee Salary: ");
        double salary = scanner.nextDouble();

        Employee employee = new Employee(id, name, designation, salary);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(employee.toFileFormat());
            writer.newLine();
            System.out.println("Employee added successfully!");
        } catch (IOException e) {
            System.out.println("Error saving employee details: " + e.getMessage());
        }
    }

    // Method to display all employees
    private static void displayEmployees() {
        File file = new File(FILE_NAME);
        if (!file.exists() || file.length() == 0) {
            System.out.println("\nNo employee records found.");
            return;
        }

        System.out.println("\n==== Employee Records ====");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Employee emp = Employee.fromFileFormat(line);
                emp.display();
            }
        } catch (IOException e) {
            System.out.println("Error reading employee details: " + e.getMessage());
        }
    }
}
