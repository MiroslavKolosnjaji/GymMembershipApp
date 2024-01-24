package com.example.gymmembershipapp.domain;

import java.util.Objects;

/**
 * @author Miroslav Kološnjaji
 */
public class Member {

    private Long memberId;
    private Person member;

    private Membership membership;

    public Member() {
    }

    public Member(Long memberId) {
        this.memberId = memberId;
    }

    public Member(Long memberId, Person member, Membership membership) {
        this.memberId = memberId;
        this.member = member;
        this.membership = membership;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Person getMember() {
        return member;
    }

    public void setMember(Person member) {
        this.member = member;
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
        Member member1 = (Member) o;
        return Objects.equals(memberId, member1.memberId) && Objects.equals(member, member1.member) && Objects.equals(membership, member1.membership);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, member, membership);
    }
}
