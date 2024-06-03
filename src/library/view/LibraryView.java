package library.view;

import library.controller.LibraryController;

public class LibraryView {
    private MainView mainView;
    private CustomerView customerView;
    private EmployeeView employeeView;
    private MediaView mediaView;


    public LibraryView(LibraryController libraryController) {
        this.mainView = new MainView(libraryController);
        this.customerView = new CustomerView(libraryController);
        this.employeeView = new EmployeeView(libraryController);
        this.mediaView = new MediaView(libraryController);
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

    public MediaView getMediaView() {
        return mediaView;
    }
}
