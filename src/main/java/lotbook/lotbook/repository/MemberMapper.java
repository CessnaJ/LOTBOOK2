package lotbook.lotbook.repository;

import lotbook.lotbook.dto.entity.Member;

import java.util.List;

public interface MemberMapper {
    Member select(Member member);

    List<Member> selectAllMembers();

    Member selectMemberBySequence(int sequence);

    int insertMember(Member member);

    int updateMember(Member member);

    int deleteMember(int sequence);
    boolean emailCheck(String emailToCheck);
    int updateInfo(Member v);

}
