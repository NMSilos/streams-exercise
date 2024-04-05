package application;

import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Employee> list = new ArrayList<>();

        System.out.print("Enter full file path: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }

            System.out.print("Enter salary: ");
            double salary = sc.nextDouble();

            List<String> highSalary = list.stream().filter(e -> e.getSalary() > salary)
                    .map(p -> p.getEmail()).sorted().toList();

            highSalary.forEach(System.out::println);

            double sum = list.stream().filter(e -> e.getName().charAt(0) == 'M')
                    .map(e -> e.getSalary()).reduce(0.0, (x, y) -> x + y);

            System.out.println("Sum of salary of people whose name starts whit 'M': " + String.format("%.2f", sum));

        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        finally {
            sc.close();
        }
    }
}
