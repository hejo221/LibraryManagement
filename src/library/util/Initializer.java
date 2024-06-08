package library.util;

import library.controller.LibraryController;
import library.model.Customer;
import library.model.Employee;
import library.model.Employee.EmployeeStatus;
import library.model.Game;
import library.model.Media;
import library.view.MainView;

import java.util.LinkedHashMap;
import java.util.Map;

public class Initializer {

    public void initializeLibrary() {
        System.out.println("Initializing the library...");

        LibraryController libraryController = new LibraryController();

        MainView mainView = new MainView(libraryController);
        mainView.displayLogin();
    }
}
