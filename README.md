# LibraryManagementSystem
The Library Management System is a Java application designed to facilitate library management tasks for librarians. It allows librarians to perform various operations such as adding new books, updating book details, checking out books to users, and managing user accounts.

## Classes

### Book Class
- Attributes:
  - Book ID
  - Title
  - Author
  - Genre
  - Availability status

### User Class
- Attributes:
  - User ID
  - Name
  - Contact information
  - Borrowed books

### Library Class
- Contains collections to store books and users.
- Methods for:
  - Adding new books
  - Adding new users
  - Checking out books
  - Returning books
  - Searching for books by title or author

### Main Class (LibraryManagementSystem)
- Entry point for the program.
- Implements a menu-driven interface for librarians to:
  - Add books
  - Add users
  - Display books
  - Borrow/Check out books
  - Return books
  - Search for books by user ID

## Additional Considerations

### File Handling
- Implements simple file-based data persistence to store book and user information between application runs.

### Error Handling
- Implements error handling to manage invalid user inputs and edge cases gracefully.

### Validation
- Implements validation checks to ensure correct entry of book and user information.

## Setup and Running Instructions

1. Ensure you have Java Development Kit (JDK) installed on your system.
2. Clone the repository to your local machine.
3. Open the project in your preferred Java IDE.
4. Compile the Java files.
5. Run the `LibraryManagementSystem` class to start the application.

## Prerequisites
- Java Development Kit (JDK)
- Java IDE (e.g., vscode, IntelliJ IDEA, Eclipse)
- Basic knowledge of Java programming

## Contributors
- [Misbah Shaheen]
- [misbahshaheentiwana007@gmail.com]

## License
This project is licensed under the [MIT License](LICENSE).

