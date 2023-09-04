package lotbook.lotbook.service;

import lotbook.lotbook.dto.entity.Member;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    int register(Member mem);
    int modify(Member v);
    Member get(Member mem);
    Member getById(int k);
    int modifyInfo(Member v);
    int updatePoint(long v);
    int updatePointConfirm(Member v);
    Member checkDuplicateEmail(String emailToCheck);
}
