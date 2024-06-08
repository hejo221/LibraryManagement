package library.util;

import library.exceptions.FileReadException;
import library.model.Copy;
import library.model.Customer;
import library.model.Employee;
import library.model.Media;

import java.io.*;
import java.util.HashMap;

public class ReadUtil {

    public static HashMap<Integer, Employee> readEmployeesFromFile() throws FileReadException {
        HashMap<Integer, Employee> employeeDB = new HashMap<>();
        String filePath = "src/library/db/employeeDB.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Employee employee = Employee.readFromFile(line);
                employeeDB.put(employee.getEmployeeID(), employee);
            }
        } catch (IOException exception) {
            throw new FileReadException("Error reading from file: " + exception.getMessage());
        }

        return employeeDB;
    }

    public static HashMap<Integer, Customer> readCustomersFromFile() throws FileReadException {
        HashMap<Integer, Customer> customerDB = new HashMap<>();
        String filePath = "src/library/db/customerDB.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Customer customer = Customer.readFromFile(line);
                customerDB.put(customer.getCustomerID(), customer);
            }
        } catch (IOException exception) {
            throw new FileReadException("Error reading from file: " + exception.getMessage());
        }

        return customerDB;
    }

    public static HashMap<Integer, Media> readMediaFromFile() throws FileReadException {
        HashMap<Integer, Media> mediaDB = new HashMap<>();
        String filePath = "src/library/db/mediaDB.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Media media = Media.readFromFile(line);
                mediaDB.put(media.getMediaID(), media);
            }
        } catch (IOException exception) {
            throw new FileReadException("Error reading from file: " + exception.getMessage());
        }

        return mediaDB;
    }

    public static HashMap<Integer, Copy> readCopiesFromFile() throws FileReadException {
        HashMap<Integer, Copy> copyDB = new HashMap<>();
        String filePath = "src/library/db/copyDB.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Copy copy = Copy.readFromFile(line);
                copyDB.put(copy.getCopyID(), copy);
            }
        } catch (IOException exception) {
            throw new FileReadException("Error reading from file: " + exception.getMessage());
        }

        return copyDB;
    }
}
