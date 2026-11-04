package library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Library {
    private final Map<String, Copy> copies = new LinkedHashMap<>();
    private final Map<String, Member> members = new LinkedHashMap<>();
    private final List<Loan> loans = new ArrayList<>();
    private static final int MAX_OPEN_LOANS = 5;

    public void addCopy(Copy copy) {
        Objects.requireNonNull(copy, "copy");
        if (copies.containsKey(copy.getInventoryNumber())) {
            throw new IllegalArgumentException("Duplicate inventory number");
        }
        copies.put(copy.getInventoryNumber(), copy);
    }

    public void registerMember(Member member) {
        Objects.requireNonNull(member, "member");
        if (members.containsKey(member.getMemberNumber())) {
            throw new IllegalArgumentException("Duplicate member number");
        }
        members.put(member.getMemberNumber(), member);
    }

    public Copy findCopy(String inventoryNumber) {
        return copies.get(inventoryNumber);
    }

    public Optional<Loan> lend(String inventoryNumber, String memberNumber, LocalDate startDate) {
        Copy copy = copies.get(inventoryNumber);
        Member member = members.get(memberNumber);
        if (copy == null || member == null || !copy.isAvailable()) {
            return Optional.empty();
        }
        long openLoans = loans.stream()
                .filter(loan -> loan.getMember().equals(member) && loan.isOpen())
                .count();
        if (openLoans >= MAX_OPEN_LOANS) return Optional.empty();

        copy.borrow();
        Loan loan = new Loan(copy, member, startDate);
        loans.add(loan);
        return Optional.of(loan);
    }

    public boolean returnCopy(String inventoryNumber, LocalDate returnDate) {
        Copy copy = copies.get(inventoryNumber);
        if (copy == null || copy.isAvailable()) return false;

        Loan loan = loans.stream()
                .filter(item -> item.getCopy().equals(copy) && item.isOpen())
                .findFirst().orElse(null);
        if (loan == null) return false;

        loan.close(returnDate);
        copy.giveBack();
        return true;
    }

    public List<Book> searchByTitle(String titlePart) {
        String query = Objects.requireNonNull(titlePart, "titlePart").toLowerCase();
        return copies.values().stream()
                .map(Copy::getBook)
                .distinct()
                .filter(book -> book.getTitle().toLowerCase().contains(query))
                .toList();
    }

    public List<Loan> getLoans() {
        return List.copyOf(loans);
    }
}
