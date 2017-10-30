package test.viewtest;

import view.CustomerView;
import view.CustomerViewImpl;

public class CustomerViewTest {
    public static void main(String[] args) {
        CustomerView customerView = new CustomerViewImpl();
        customerView.saveCustomer();
    }

}
