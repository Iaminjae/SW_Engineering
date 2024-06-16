package main;

public class Book implements Comparable<Book> {
    public int id;
    public String title;
    public String author;
    public int publishYear;

    public Book(int id, String title, String author, int publishYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublishYear() {
        return publishYear;
    }

    @Override
    public int compareTo(Book book) {
        return Integer.compare(this.id, book.id);
    }
}
