package lotbook.lotbook.service;

import lotbook.lotbook.dto.entity.Member;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    Member get(Member mem);
}
