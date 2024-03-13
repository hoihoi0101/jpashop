package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;


    @Transactional
    //회원가입
    public Long join(Member member) {
        validateDuplicateMember(member);//중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //Exception
        List<Member> findmembers = memberRepository.findByName(member.getName());

        if (!findmembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }


    }

    //@Transactional(readOnly = true)
    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //@Transactional(readOnly = true)
    //회원 한명 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
