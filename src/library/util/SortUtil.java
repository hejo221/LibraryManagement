package library.util;

import library.model.Copy;
import library.model.Customer;
import library.model.Employee;
import library.model.Media;

import java.util.*;

public class SortUtil {

    private SortUtil() {
    }

    public static <T> LinkedHashMap<Integer, T> sortByComparator(HashMap<Integer, T> db, Comparator<? super T> comparator) {
        List<Map.Entry<Integer, T>> list = new LinkedList<>(db.entrySet());
        list.sort((entry1, entry2) -> comparator.compare(entry1.getValue(), entry2.getValue()));
        LinkedHashMap<Integer, T> sortedDB = new LinkedHashMap<>();
        for (Map.Entry<Integer, T> entry : list) {
            sortedDB.put(entry.getKey(), entry.getValue());
        }
        return sortedDB;
    }

    public static class CopyIDComparator implements Comparator<Copy> {
        @Override
        public int compare(Copy c1, Copy c2) {
            return Integer.compare(c1.getCopyID(), c2.getCopyID());
        }
    }

    public static class CustomerIDComparator implements Comparator<Customer> {
        @Override
        public int compare(Customer c1, Customer c2) {
            return Integer.compare(c1.getCustomerID(), c2.getCustomerID());
        }
    }

    public static class CustomerNameComparator implements Comparator<Customer> {
        @Override
        public int compare(Customer c1, Customer c2) {
            return c1.getFamilyName().compareTo(c2.getFamilyName());
        }
    }

    public static class EmployeeIDComparator implements Comparator<Employee> {
        @Override
        public int compare(Employee e1, Employee e2) {
            return Integer.compare(e1.getEmployeeID(), e2.getEmployeeID());
        }
    }

    public static class EmployeeNameComparator implements Comparator<Employee> {
        @Override
        public int compare(Employee e1, Employee e2) {
            return e1.getFamilyName().compareTo(e2.getFamilyName());
        }
    }

    public static class MediaReleaseYearComparator implements Comparator<Media> {
        @Override
        public int compare(Media m1, Media m2) {
            return Integer.compare(m1.getReleaseYear(), m2.getReleaseYear());
        }
    }

    public static class MediaTitleComparator implements Comparator<Media> {
        @Override
        public int compare(Media m1, Media m2) {
            return m1.getTitle().compareTo(m2.getTitle());
        }
    }
}