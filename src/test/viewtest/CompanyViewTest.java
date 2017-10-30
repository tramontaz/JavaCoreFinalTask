package test.viewtest;

import view.CompanyView;
import view.CompanyViewImpl;

public class CompanyViewTest {
    public static void main(String[] args) {
        CompanyView companyView = new CompanyViewImpl();
        companyView.saveCompany();
    }
}
