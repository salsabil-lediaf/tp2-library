package library;

import java.util.Objects;

public class Member {
    private final String memberNumber;
    private final String name;

    public Member(String memberNumber, String name) {
        this.memberNumber = Objects.requireNonNull(memberNumber, "memberNumber");
        this.name = Objects.requireNonNull(name, "name");
    }

    public String getMemberNumber() { return memberNumber; }
    public String getName() { return name; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Member)) return false;
        Member other = (Member) obj;
        return memberNumber.equals(other.memberNumber);
    }

    @Override
    public int hashCode() { return memberNumber.hashCode(); }
}
