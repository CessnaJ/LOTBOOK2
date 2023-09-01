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
}
