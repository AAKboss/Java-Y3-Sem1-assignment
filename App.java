import java.io.*;
import java.util.*;

public class App {
    // Create File
    public static class EmployeeFileCreator {
        public static void createEmployeeFile() {
            String content = "Name Surname Years_Worked Salary\n" +
                    "John Smith 4 15000\n" +
                    "Ayanda Dube 10 200000\n" +
                    "Damien Naidoo 5 65000";

            try (FileWriter writer = new FileWriter("employee.txt")) {
                writer.write(content);
                System.out.println("File created successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file.");
                e.printStackTrace();
            }
        }
    }
    // Read file and display content
    public static class EmployeeFileViewer {
        public static void displayEmployeeFile() {
            String fileName = "employee.txt";
    
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                System.out.println("----------------------------------------");
                System.out.println("Contents of " + fileName + ":");
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                System.out.println("----------------------------------------");
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // Update contents
    public static class EmployeeSalaryUpdater {
        public static void updateEmployeeSalaries(String filename) {
            try {
                List<String> lines = new ArrayList<>();

                BufferedReader reader = new BufferedReader(new FileReader(filename));
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                reader.close();

                List<String> updatedLines = new ArrayList<>();
                updatedLines.add(lines.get(0));

                for (int i = 1; i < lines.size(); i++) {
                    String employeeData = lines.get(i);
                    String[] employeeInfo = employeeData.split("\\s+");

                    if (employeeInfo.length >= 4) {
                        String name = employeeInfo[0] + " " + employeeInfo[1];
                        int yearsWorked = Integer.parseInt(employeeInfo[2]);
                        double currentSalary = Double.parseDouble(employeeInfo[3]);

                        double salaryIncreasePercentage;
                        if (yearsWorked < 5) {
                            salaryIncreasePercentage = 0.05; // 5% increase
                        } else if (yearsWorked >= 5 && yearsWorked <= 10) {
                            salaryIncreasePercentage = 0.15; // 15% increase
                        } else {
                            salaryIncreasePercentage = 0.30; // 30% increase
                        }

                        double newSalary = currentSalary * (1 + salaryIncreasePercentage);
                        String updatedEmployeeData = name + " " + yearsWorked + " " + newSalary;
                        updatedLines.add(updatedEmployeeData);
                    } else {
                        updatedLines.add(employeeData);
                    }
                }

                // Write updated content 
                BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
                writer.close();

                System.out.println("Employee salaries updated successfully.");

            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + filename);
            } catch (IOException e) {
                System.out.println("Error reading or writing file: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Error parsing data: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        EmployeeFileCreator.createEmployeeFile();
        EmployeeFileViewer.displayEmployeeFile();

        String filename = "employee.txt";
        EmployeeSalaryUpdater.updateEmployeeSalaries(filename);

        EmployeeFileViewer.displayEmployeeFile();
    }
}