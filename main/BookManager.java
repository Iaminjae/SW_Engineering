package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookManager {

    private List<Book> books;

    public BookManager() {
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    // book을 매개변수로 받아 책을 추가합니다.. 이미 추가된 책이라면 예외를 던집니다.
    public void addBook(Book book) {
        // 모든 책을 검색하여 이미 추가된 책이 있는지 확인합니다.
        for(Book b : books) {
            if(b.getId() == book.getId()) {
                throw new IllegalArgumentException("해당 ID("+book.getId()+") 는 이미 존재합니다.");
            }
        }
        books.add(book);

        // serach_bs()를 위해 정렬합니다.
        Collections.sort(books);

        // 책 추가 성공 시 출력한다.
        System.out.println("Book{" +
                "id: '" + book.getId() + "', " +
                "제목: '" + book.getTitle() + "', " +
                "저자: '" + book.getAuthor() + "', " +
                "출판년도: " + book.getPublishYear() +
                "}" +
                "도서가 추가되었습니다.");
    }

    // book의 id를 매개변수로 받아 특정 책을 검색합니다. 존재하지 않는다면 예외를 던집니다.
    public void searchBook(int id) {
        // 모든 책을 검색하여 조회하려는 책이 존재하는지 확인한다.
        for(Book b : books) {
            if(b.getId() == id) {
                // 특정 책 조회 성공 시 출력합니다.
                System.out.println("검색 결과: ");
                System.out.println("Book{" +
                        "id: '" + b.getId() + "', " +
                        "제목: '" + b.getTitle() + "', " +
                        "저자: '" + b.getAuthor() + "', " +
                        "출판년도: " + b.getPublishYear() +
                        "}");
                return;
            }
        }
        throw new IllegalArgumentException("검색된 도서가 없습니다.");
    }

    // book의 id를 매개변수로 받아 특정 책을 이진 탐색합니다. 존재하지 않는다면 예외를 던집니다.
    public void search_bs(int id) {
        int left = 0;
        int right = books.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            Book midBook = books.get(mid);

            if (midBook.getId() == id) {
                System.out.println("검색결과: ");
                System.out.println("Book{" +
                        "id: '" + midBook.getId() + "', " +
                        "제목: '" + midBook.getTitle() + "', " +
                        "저자: '" + midBook.getAuthor() + "', " +
                        "출판년도: " + midBook.getPublishYear() +
                        "}");
                return;
            } else if (midBook.getId() < id) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        throw new IllegalArgumentException("검색된 도서가 없습니다.");
    }

    // book을 매개변수로 받아 해당 book을 books 배열에서 삭제합니다. 존재하지 않는다면 예외를 던집니다.
    public void removeBook(Book book){
        // 모든 책을 검색하여 삭제하려는 책이 존재하는지 확인합니다.
        for(Book b : books) {
            if(b.getId() == book.getId()) {
                books.remove(b);
                // 특정 책 삭제 시 출력합니다.
                System.out.println("Book{" +
                        "id: '" + book.getId() + "', " +
                        "제목: '" + book.getTitle() + "', " +
                        "저자: '" + book.getAuthor() + "', " +
                        "출판년도: " + book.getPublishYear() +
                        "}" +
                        "도서를 삭제하였습니다.");
                return;
            }
        }
        throw new IllegalArgumentException("해당 ID("+ book.getId() + ")의 도서를 찾을 수 없습니다.");
    }

}
