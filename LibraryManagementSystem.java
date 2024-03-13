import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

class Book {
    private int bookID;
    private String title;
    private String author;
    private String genre;
    private boolean availabilityStatus;

    // Constructor
    public Book(int bookID, String title, String author, String genre, boolean availabilityStatus) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availabilityStatus = availabilityStatus;
    }

    // Getter methods
    public int getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return availabilityStatus;
    }

    // Setter methods
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAvailabilityStatus(boolean availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
}

class User {
    private int userId;
    private String name;
    private String contactInformation;
    private ArrayList<Book> borrowedBooks;

    public User(int userId, String name, String contactInformation) {
        this.userId = userId;
        this.name = name;
        this.contactInformation = contactInformation;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(ArrayList<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public void addBorrowedBook(Book book) {
        borrowedBooks.add(book);
    }

    public void removeBorrowedBook(Book book) {
        borrowedBooks.remove(book);
    }
}

class Library {
    private ArrayList<Book> books;
    private ArrayList<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }
    
    // Methods for managing books
    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    // Methods for managing users
    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    // Method for checking out books
    public void checkOutBook(Book book, User user) {
        if (books.contains(book) && user != null) {
            book.setAvailabilityStatus(false);
            user.addBorrowedBook(book);
        }
    }

    // Method for returning books
    public void returnBook(Book book, User user) {
        if (books.contains(book) && user != null) {
            book.setAvailabilityStatus(true);
            user.removeBorrowedBook(book);
        }
    }

    // Method for searching books by title
    public ArrayList<Book> searchByTitle(String title) {
        ArrayList<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    // Method for searching books by author
    public ArrayList<Book> searchByAuthor(String author) {
        ArrayList<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }
  
    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

  

    // Method to save books to a file
    public void saveBooksToFile(String Books) {
        try (PrintWriter writer = new PrintWriter(Books)) {
            for (Book book : books) {
                writer.println(book.getBookID() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getGenre() + "," + book.isAvailable());
            }
            System.out.println("Books saved to " + Books + " successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }

    // Method to load books from a file
    public void loadBooksFromFile(String Books) {
        try (Scanner scanner = new Scanner(new File(Books))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                Book book = new Book(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], Boolean.parseBoolean(parts[4]));
                books.add(book);
            }
            System.out.println("Books loaded from " + Books + " successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }

    // Method to save users to a file
    public void saveUsersToFile(String Users) {
        try (PrintWriter writer = new PrintWriter(Users)) {
            for (User user : users) {
                writer.println(user.getUserId() + "," + user.getName() + "," + user.getContactInformation());
            }
            System.out.println("Users saved to " + Users + " successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }

    // Method to load users from a file
    public void loadUsersFromFile(String Users) {
        users.clear(); // Clear existing users
        try (BufferedReader reader = new BufferedReader(new FileReader(Users))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                User user = new User(Integer.parseInt(parts[0]), parts[1], parts[2]);
                users.add(user);
            }
            System.out.println("Users loaded from " + Users + " successfully.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}




public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        // Load users from file
        library.loadUsersFromFile("Users.txt");
        // Load books from file
        library.loadBooksFromFile("Books.txt");
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. Display Books");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Search Books by Title");
            System.out.println("7. Search Books by Author");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
        try{
            switch (choice) {
                case 1:
                    addBook(library, scanner);
                    break;
                case 2:
                    addUser(library, scanner);
                    break;
                case 3:
                    library.displayBooks();
                    break;
                case 4:
                    borrowBook(library, scanner);
                    break;
                case 5:
                    returnBook(library, scanner);
                    break;
                case 6:
                    searchBooksByTitle(library, scanner);
                    break;
                case 7:
                    searchBooksByAuthor(library, scanner);
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
            catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer choice.");
                scanner.nextLine(); // Clear the invalid input
                choice = 0; // Reset choice to continue the loop
            }
        } while (choice != 8);

        scanner.close();
        // Save users to file before exiting
        library.saveUsersToFile("Users.txt");
        // Save users to file before exiting
        library.saveBooksToFile("Books.txt");
    }

    private static void addBook(Library library, Scanner scanner) {
        System.out.print("Enter Book ID: ");
        int bookID = scanner.nextInt();
        scanner.nextLine(); // Consume newlinere
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter Availability Status (true/false): ");
        boolean availabilityStatus = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline
        library.addBook(new Book(bookID, title, author, genre, availabilityStatus));
        System.out.println("Book added successfully.");
    }

    private static void addUser(Library library, Scanner scanner) {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Contact Information: ");
        String contactInfo = scanner.nextLine();
        library.addUser(new User(userId, name, contactInfo));
        System.out.println("User added successfully.");
    }

    private static void borrowBook(Library library, Scanner scanner) {
        System.out.print("Enter Book ID to borrow: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        Book bookToBorrow = null;
        User user = null;
        
        for (Book book : library.getBooks()) {
            if (book.getBookID() == bookId) {
                bookToBorrow = book;
                break;
            }
        }
        
        for (User u : library.getUsers()) {
            if (u.getUserId() == userId) {
                user = u;
                break;
            }
        }
        
        if (bookToBorrow == null) {
            System.out.println("Book not found with ID: " + bookId);
        } else if (user == null) {
            System.out.println("User not found with ID: " + userId);
        } else if (!bookToBorrow.isAvailable()) {
            System.out.println("Book is not available for borrowing.");
        } else {
            library.checkOutBook(bookToBorrow, user);
            System.out.println("Book borrowed successfully.");
        }
    }

    private static void returnBook(Library library, Scanner scanner) {
        System.out.print("Enter Book ID to return: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        Book bookToReturn = null;
        User user = null;
        
        for (Book book : library.getBooks()) {
            if (book.getBookID() == bookId) {
                bookToReturn = book;
                break;
            }
        }
        
        for (User u : library.getUsers()) {
            if (u.getUserId() == userId) {
                user = u;
                break;
            }
        }
        
        if (bookToReturn == null) {
            System.out.println("Book not found with ID: " + bookId);
        } else if (user == null) {
            System.out.println("User not found with ID: " + userId);
        } else {
            library.returnBook(bookToReturn, user);
            System.out.println("Book returned successfully.");
        }
    }

    private static void searchBooksByTitle(Library library, Scanner scanner) {
        System.out.print("Enter the title to search: ");
        String title = scanner.nextLine();
        
        ArrayList<Book> foundBooks = library.searchByTitle(title);
        
        if (foundBooks.isEmpty()) {
            System.out.println("No books found with title: " + title);
        } else {
            System.out.println("Books found with title: " + title);
            for (Book book : foundBooks) {
                System.out.println(book);
            }
        }
    }

    private static void searchBooksByAuthor(Library library, Scanner scanner) {
        System.out.print("Enter the author's name to search: ");
        String author = scanner.nextLine();
        
        ArrayList<Book> foundBooks = library.searchByAuthor(author);
        
        if (foundBooks.isEmpty()) {
            System.out.println("No books found with author: " + author);
        } else {
            System.out.println("Books found with author: " + author);
            for (Book book : foundBooks) {
                System.out.println(book);
            }
        }
    }
}

