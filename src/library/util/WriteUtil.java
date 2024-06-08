package library.util;

import library.exceptions.FileWriteException;
import library.model.Copy;
import library.model.Customer;
import library.model.Employee;
import library.model.Media;

import java.io.*;
import java.util.HashMap;

public class WriteUtil {

    public static void writeCopiesToFile(HashMap<Integer, Copy> copyDB) throws FileWriteException {
        String filePath = "src/library/db/copyDB.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Copy copy : copyDB.values()) {
                writer.write(copy.writeToFile());
                writer.newLine();
            }
        } catch (IOException exception) {
            throw new FileWriteException("Error writing to file: " + exception.getMessage());
        }
    }

    public static void writeEmployeesToFile(HashMap<Integer, Employee> employeeDB) throws FileWriteException {
        String filePath = "src/library/db/employeeDB.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Employee employee : employeeDB.values()) {
                writer.write(employee.writeToFile());
                writer.newLine();
            }
        } catch (IOException exception) {
            throw new FileWriteException("Error writing to file: " + exception.getMessage());
        }
    }

    public static void writeCustomersToFile(HashMap<Integer, Customer> customerDB) throws FileWriteException {
        String filePath = "src/library/db/customerDB.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Customer customer : customerDB.values()) {
                writer.write(customer.writeToFile());
                writer.newLine();
            }
        } catch (IOException exception) {
            throw new FileWriteException("Error writing to file: " + exception.getMessage());
        }
    }

    public static void writeMediaToFile(HashMap<Integer, Media> mediaDB) throws FileWriteException {
        String filePath = "src/library/db/mediaDB.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Media media : mediaDB.values()) {
                writer.write(media.writeToFile());
                writer.newLine();
            }
        } catch (IOException exception) {
            throw new FileWriteException("Error writing to file: " + exception.getMessage());
        }
    }
}
