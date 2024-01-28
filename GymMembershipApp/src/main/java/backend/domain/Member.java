package backend.domain;

import java.util.Objects;

/**
 * @author Miroslav Kološnjaji
 */
public class Member {

    private Long memberId;

    private String firstName;
    private String lastName;

    private String email;
    private String phone;
    private Membership membership;

    public Member() {
    }

    public Member(Long memberId) {
        this.memberId = memberId;
    }

    public Member(Long memberId, String firstName, String lastName, String email, String phone, Membership membership) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.membership = membership;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(memberId, member.memberId) && Objects.equals(firstName, member.firstName) && Objects.equals(lastName, member.lastName) && Objects.equals(email, member.email) && Objects.equals(phone, member.phone) && Objects.equals(membership, member.membership);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, firstName, lastName, email, phone, membership);
    }
}
