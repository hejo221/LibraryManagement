package library.view;

import library.controller.LibraryController;

public class LibraryView {
    private MainView mainView;
    private CustomerView customerView;
    private EmployeeView employeeView;


    public LibraryView(LibraryController libraryController) {
        this.mainView = new MainView(libraryController);
        this.customerView = new CustomerView(libraryController);
        this.employeeView = new EmployeeView(libraryController);
    }

    public MainView getMainView() {
        return mainView;
    }

    public CustomerView getCustomerView() {
        return customerView;
    }

    public EmployeeView getEmployeeView() {
        return employeeView;
    }
}
