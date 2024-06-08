package library.controller;
public class LibraryController {
    private CustomerController customerController;
    private EmployeeController employeeController;
    private MediaController mediaController;
    private CopyController copyController;

    public LibraryController() {
        this.customerController = new CustomerController();
        this.employeeController = new EmployeeController();
        this.mediaController = new MediaController();
        this.copyController = new CopyController();
    }

    public CustomerController getCustomerController() {
        return customerController;
    }

    public EmployeeController getEmployeeController() {
        return employeeController;
    }

    public MediaController getMediaController() {
        return mediaController;
    }

    public CopyController getCopyController() {
        return copyController;
    }
}
