package lotbook.lotbook.repository;

import lotbook.lotbook.dto.entity.Member;

public interface MemberMapper {
    Member select(Member member);
    Member selectById(int sequence);
    int update(Member v);
    int updateInfo(Member v);
    int updatePoint(long k);
    int insertMember(Member member);
    Member emailCheck(String emailToCheck);
    int updatePointConfirm(Member member);

}
