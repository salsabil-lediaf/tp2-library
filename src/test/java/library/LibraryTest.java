package library;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LibraryTest {
    private Library library;
    private Book book;
    private Member member;

    @BeforeEach
    void setUp() {
        library = new Library();
        book = new Book("978-0001", "Java Basics", 2024);
        library.addCopy(new Copy("C-001", book));
        member = new Member("M-001", "Sara");
        library.registerMember(member);
    }

    @Test
    void lendsAvailableCopy() {
        assertTrue(library.lend("C-001", "M-001", LocalDate.of(2026, 1, 1)).isPresent());
        assertFalse(library.findCopy("C-001").isAvailable());
    }

    @Test
    void returnsCopy() {
        library.lend("C-001", "M-001", LocalDate.of(2026, 1, 1));
        assertTrue(library.returnCopy("C-001", LocalDate.of(2026, 1, 5)));
        assertTrue(library.findCopy("C-001").isAvailable());
    }

    @Test
    void cannotLendUnavailableCopy() {
        library.lend("C-001", "M-001", LocalDate.of(2026, 1, 1));
        assertTrue(library.lend("C-001", "M-001", LocalDate.of(2026, 1, 2)).isEmpty());
    }

    @Test
    void unknownMemberCannotBorrow() {
        assertTrue(library.lend("C-001", "UNKNOWN", LocalDate.now()).isEmpty());
    }

    @Test
    void loanDueDateIsFourteenDaysLater() {
        Loan loan = library.lend("C-001", "M-001", LocalDate.of(2026, 1, 1)).orElseThrow();
        assertEquals(LocalDate.of(2026, 1, 15), loan.getDueDate());
    }

    @Test
    void searchesTitleIgnoringCase() {
        assertEquals(1, library.searchByTitle("JAVA").size());
    }

    @Test
    void memberCannotHaveMoreThanFiveOpenLoans() {
        for (int i = 1; i <= 6; i++) {
            Book nextBook = new Book("ISBN-" + i, "Book " + i, 2025);
            library.addCopy(new Copy("COPY-" + i, nextBook));
        }
        for (int i = 1; i <= 5; i++) {
            assertTrue(library.lend("COPY-" + i, "M-001", LocalDate.now()).isPresent());
        }
        assertTrue(library.lend("COPY-6", "M-001", LocalDate.now()).isEmpty());
    }
}
