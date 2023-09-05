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
    public int register(Member mem){
        return memberMapper.insertMember(mem);
    }
    @Override
    public int modify(Member v) {
        return memberMapper.update(v);
    }
    @Override
    public Member get(Member mem){
        return memberMapper.select(mem);
    }

    @Override
    public Member getById(long k) {
        return memberMapper.selectById(k);
    }

    @Override
    public int modifyInfo(Member v) {
        return memberMapper.updateInfo(v);
    }

    @Override
    public Member checkDuplicateEmail(String emailToCheck) {
        return memberMapper.emailCheck(emailToCheck);
    }

    @Override
    public int updatePoint(long k) {
        return memberMapper.updatePoint(k);
    }

    @Override
    public int updatePointConfirm(Member k) {
        return memberMapper.updatePointConfirm(k);
    }





}
