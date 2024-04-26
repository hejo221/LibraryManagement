package library.util;

import library.controller.LibraryController;
import library.model.Employee;
import library.model.Employee.EmployeeStatus;
import library.view.EmployeeView;

import java.util.LinkedHashMap;
import java.util.Map;

public class Initializer {

    public void initializeLibrary() {
        System.out.println("Initializing the library...");

        LibraryController libraryController = new LibraryController();

        Employee manager = libraryController.getEmployeeController().addNewEmployee("Max", "Mustermann", 5000.0,
                EmployeeStatus.MANAGER, "passwort123");
        Employee intern = libraryController.getEmployeeController().addNewEmployee("Maria", "Musterfrau", 2000.0,
                EmployeeStatus.INTERN, "geheim123");
        Employee normal = libraryController.getEmployeeController().addNewEmployee("Tom", "Beispiel", 3500.0,
                EmployeeStatus.NORMAL, "123456");

        System.out.println("Initialization complete.");

        System.out.println("Manager ID: " + manager.getEmployeeID());

        LinkedHashMap<Integer, Employee> sortedEmployees = SortUtil.sortByComparator(libraryController.getEmployeeController().getEmployeeDB(), new SortUtil.EmployeeIDComparator());

        System.out.println("Sorted Employees:");

        for (Map.Entry<Integer, Employee> entry : sortedEmployees.entrySet()) {
            Integer employeeID = entry.getKey();
            Employee employee = entry.getValue();
            System.out.println("ID: " + employeeID + ", Employee Name: " + employee.getFirstName() + " " + employee.getFamilyName());
        }

        EmployeeView employeeView = new EmployeeView(libraryController);
        employeeView.displayLogin();
    }
}
