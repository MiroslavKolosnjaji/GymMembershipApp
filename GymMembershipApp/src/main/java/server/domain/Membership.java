package server.domain;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Miroslav Kološnjaji
 */
public class Membership {
    private Member member;
    private LocalDate dateFrom;
    private LocalDate dateTo;


    public Membership() {
    }

    public Membership(Member member) {
        this.member = member;
    }

    public Membership(Member member, LocalDate dateFrom, LocalDate dateTo) {
        this.member = member;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Membership that = (Membership) o;
        return Objects.equals(member, that.member) && Objects.equals(dateFrom, that.dateFrom) && Objects.equals(dateTo, that.dateTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member, dateFrom, dateTo);
    }
}
