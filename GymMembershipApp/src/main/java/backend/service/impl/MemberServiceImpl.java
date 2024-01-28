package backend.service.impl;

import backend.domain.Member;
import backend.repository.MemberRepository;
import backend.service.MemberService;

import java.util.List;

/**
 * @author Miroslav Kološnjaji
 */
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void add(Member member) throws Exception {
        memberRepository.add(member);
    }

    @Override
    public void update(Member member) throws Exception {
        memberRepository.update(member);
    }

    @Override
    public void delete(Member member) throws Exception {
        memberRepository.delete(member);
    }

    @Override
    public List<Member> getAll() throws Exception {
        return memberRepository.getAll();
    }
}
