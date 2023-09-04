package lotbook.lotbook.repository;

import lotbook.lotbook.dto.entity.Member;
import lotbook.lotbook.dto.entity.Review;
import lotbook.lotbook.dto.mapper.ReviewWithNameMapperDTO;
import lotbook.lotbook.dto.response.ReviewDetails;

import java.util.List;

public interface ReviewMapper {
    List<ReviewWithNameMapperDTO> selectReviewsByProductSequence(long productSequence);
    int insert(Review v);
    int update(Review v);
    int delete(Review k);
    Review select(Review k);
    List<ReviewDetails> selectReviewsByMember(Member k);
    Review selectReviewByOrderdetail(Review k);
}
