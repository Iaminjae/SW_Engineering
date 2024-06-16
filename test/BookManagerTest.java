package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import main.Book;
import main.BookManager;

import java.util.Random;


class BookManagerTest {
    private BookManager bookManager;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    void setUp() {
        bookManager = new BookManager();
        book1 = new Book(1, "분산 컴퓨팅", "윤영", 2024);
        book2 = new Book(2, "Internet of Things: Principles and Paradigms", "Morgan Kaufmann", 2016);
        book3 = new Book(3, "소프트웨어 공학의 모든 것", "최은만", 2020);
    }

    @Test
    void testAddBook() {
        bookManager.addBook(book1);
        bookManager.addBook(book2);
        bookManager.addBook(book3);

        // 3개의 책을 추가하였기 때문에 book의 개수는 3이여야 합니다.
        Assertions.assertEquals(3, bookManager.getBooks().size());
        // 이미 추가한 book1을 다시 추가하려면 예외가 발생해야 합니다.
        assertThrows(IllegalArgumentException.class, () -> bookManager.addBook(book1));
    }

    @Test
    void testSearchBook() {
        bookManager.addBook(book1);
        bookManager.addBook(book2);

        // 이미 추가한 book1을 검색하면 예외가 발생하지 않아야 합니다. .
        assertDoesNotThrow(() -> bookManager.searchBook(1));
        // book3는 추가하지 않은 책이므로 예외가 발생해야 합니다.
        assertThrows(IllegalArgumentException.class, () -> bookManager.searchBook(3));
    }

    @Test
    void testSearch_bs() {
        bookManager.addBook(book2);
        bookManager.addBook(book3);

        // 이미 추가한 book1을 검색하면 예외가 발생하지 않아야 합니다.
        assertDoesNotThrow(() -> bookManager.search_bs(2));
        // book3는 추가하지 않은 책이므로 예외가 발생해야 합니다.
        assertThrows(IllegalArgumentException.class, () -> bookManager.search_bs(1));
    }

    @Test
    void testRemoveBook() {
        bookManager.addBook(book1);
        bookManager.addBook(book2);

        // 추가된 book1에 대한 삭제는 예외가 발생하지 않아야 합니다.
        assertDoesNotThrow(() -> bookManager.removeBook(book1));
        // book1을 삭제 했으므로 book의 개수는 1이여야 합니다.
        assertEquals(1, bookManager.getBooks().size());
        // 추가하지 않은 book3를 삭제하려하면 예외가 발생해야 합니다.
        assertThrows(IllegalArgumentException.class, () -> bookManager.removeBook(book3));
    }

    @Test
    public void testPerformance() {
        Random random = new Random();
        long startTime, endTime;
        // 성능 테스트는 100번 진행합니다.
        int iterations = 100;

        for(int i=0; i<100; i++) {
            Book book = new Book(i,"test" + i, "최재훈", 2024);
            bookManager.addBook(book);
        }

        // 순차 검색 성능 측정합니다.
        startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            // 0~9 사이의 임의의 id를 선택합니다.
            int id = random.nextInt(10);
            bookManager.searchBook(id);
        }
        endTime = System.currentTimeMillis();
        System.out.println("순차 검색 소요 시간: " + (endTime - startTime) + " ms");

        // 이진 검색 성능 측정
        startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            int id = random.nextInt(10);
            bookManager.search_bs(id);
        }

        endTime = System.currentTimeMillis();
        System.out.println("이진 검색 소요 시간: " + (endTime - startTime) + " ms");
    }
}