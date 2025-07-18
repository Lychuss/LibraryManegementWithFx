package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LibraryData {
	
	    private static ArrayList<Book> books = new ArrayList<>();
	    private static ArrayList<Users> createdUser = new ArrayList<>();
	    private static HashMap<String, Integer> users = new HashMap<>();
	    private static HashMap<Integer, ArrayList<Book>> borrowBooks = new HashMap<>();
	    private static HashMap<String, String> employeeAccounts = new HashMap<>();
	    
	    public static HashMap<String, String> getEmployeeAccounts(){
	    	return employeeAccounts;
	    }
	    
	    public static void setEmployeeAccounts(HashMap<String, String> accounts) {
	    	employeeAccounts = accounts;
	    }
	    
	    public static HashMap<Integer, ArrayList<Book>> getBorrows(){
	    	return borrowBooks;
	    }
	    
	    public static void setBorrowBooks(HashMap<Integer, ArrayList<Book>> borrow) {
	    	borrowBooks = borrow;
	    }
	    
	    public static HashMap<String, Integer> getUser(){
	    	return users;
	    }
	    
	    public static void setUsers(HashMap<String, Integer> user) {
	    	users = user;
	    }

	    public static ArrayList<Book> getBooks() {
	        return books;
	    }

	    public static void setBooks(ArrayList<Book> b) {
	        books = b;
	    }

	    public static ArrayList<Users> getCreatedUser() {
	        return createdUser;
	    }

	    public static void setCreatedUser(ArrayList<Users> user) {
	        createdUser = user;
	    }
}
