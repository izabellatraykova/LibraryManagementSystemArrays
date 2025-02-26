import java.util.Scanner;

public class Library {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        Book[] libraryBooks = {
                new Book("The Bell Jar", "Sylvia Plath", 1963),
                new Book("Pride and Prejudice", "Jane Austen", 1813),
                new Book("The Stranger", "Albert Camus", 1942),
        };

        while (true){
            System.out.print("---Library Management System---\n");
            System.out.print("1. Display Library: \n");
            System.out.print("2. Borrow Book: \n");
            System.out.print("3. Return Book: \n");
            System.out.print("0. Exit: \n");
            int choice = reader.nextInt();
            reader.nextLine();

            switch(choice){
                case 1: displayBooks(libraryBooks);
                        break;
                case 2:
                    System.out.println("Choose book to borrow:");
                        borrowBook(libraryBooks, reader);
                        break;
                case 3:
                    returnBook(libraryBooks, reader);
                        break;
                case 0:
                    System.out.println("Thank you for using Library!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }

    }

    public static void displayBooks(Book[] books){
        for (int i = 0; i < books.length; i++){
            System.out.println(books[i].getDetails());
        }
    }

    public static void borrowBook(Book[] books, Scanner reader){
        System.out.print("Enter title: ");
        String title = reader.nextLine();
        int BI = findBookByTitle(books, title.trim());
        if (BI != -1){
            System.out.println("Enter your name:");
            String borrowerName = reader.nextLine();
            books[BI].borrowBook(borrowerName);
        }
        else{
            System.out.println("Book not found!");
        }
    }
    public static void returnBook(Book[] books, Scanner reader){
        System.out.println("Enter title of book to return: ");
        String returnTitle = reader.nextLine();
        int BI = findBookByTitle(books, returnTitle);
        if (BI != -1){
            books[BI].returnBook();
        }
        else{
            System.out.println("Book not found!");
        }

    }
    public static int findBookByTitle(Book[] books, String title){
        for (int i = 0; i < books.length; i++){
            if(books[i].getTitle().equalsIgnoreCase(title)){
                return i;
            }
        }
        return -1;
    }

}
class Book{
    private String title;
    private String author;
    private int yearPublished;
    private String borrowerName;

    public Book(String title, String author, int yearPublished){
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.borrowerName = null;
    }

    public String getDetails(){
        if (borrowerName == null){
            return "Title: " + title + " | " + "Author: " + author + " | " + "Year Published: " + yearPublished + " | " + "Borrowed By: Available";
        }
        return "Title: " + title + " | " + "Author: " + author + " | " + "Year Published: " + yearPublished + " | " + "Borrowed By: " + borrowerName;
    }

    public void updateBookInfo(String newTitle, String newAuthor, int newYearPublished, String borrowerName){
        this.title = newTitle;
        this.author = newAuthor;
        this.yearPublished = newYearPublished;
    }

    public void borrowBook(String borrowerName){
        if (this.borrowerName == null){
            this.borrowerName = borrowerName;
            System.out.println("Book Successfully Borrowed!");
        }
        else{
            System.out.println("Book Already Borrowed!");
        }
    }

    public void returnBook(){
        if(this.borrowerName != null){
            this.borrowerName = null;
            System.out.println("Book Successfully Returned!");
        }
        else{
            System.out.println("Book Was Not Borrowed!");
        }
    }

    public String getBorrowerName(){
        return borrowerName;
    }

    public String getTitle(){
        return title;
    }
}
