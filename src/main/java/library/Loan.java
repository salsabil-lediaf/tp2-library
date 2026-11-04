package library;

import java.time.LocalDate;
import java.util.Objects;

public class Loan {
    private final Copy copy;
    private final Member member;
    private final LocalDate startDate;
    private final LocalDate dueDate;
    private LocalDate returnDate;

    public Loan(Copy copy, Member member, LocalDate startDate) {
        this.copy = Objects.requireNonNull(copy, "copy");
        this.member = Objects.requireNonNull(member, "member");
        this.startDate = Objects.requireNonNull(startDate, "startDate");
        this.dueDate = startDate.plusDays(14);
    }

    public Copy getCopy() { return copy; }
    public Member getMember() { return member; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public boolean isOpen() { return returnDate == null; }

    public boolean isLate(LocalDate date) {
        return isOpen() && date.isAfter(dueDate);
    }

    public void close(LocalDate date) {
        if (!isOpen()) throw new IllegalStateException("Loan is already closed");
        returnDate = Objects.requireNonNull(date, "date");
    }
}
