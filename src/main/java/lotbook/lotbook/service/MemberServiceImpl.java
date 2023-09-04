package lotbook.lotbook.service;

import lombok.RequiredArgsConstructor;
import lotbook.lotbook.dto.entity.Member;
import lotbook.lotbook.repository.MemberMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Override
    public Member get(Member mem){
        return memberMapper.select(mem);
    }

    @Override
    public Member getById(int k) {
        return memberMapper.selectById(k);
    }

    @Override
    public int modifyInfo(Member v) {
        return 0;
    }

    @Override
    public int updatePoint(long memberSequence) {
        return 0;
    }

    @Override
    public int updatePointConfirm(Member v) {
        return 0;
    }

    public int register(Member mem){
        return memberMapper.insertMember(mem);
    }

    @Override
    public Member checkDuplicateEmail(String emailToCheck) {
        return memberMapper.emailCheck(emailToCheck);
    }

    @Override
    public int modify(Member v) {
        return memberMapper.updateInfo(v);
    }


}
