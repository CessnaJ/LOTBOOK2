package lotbook.lotbook.repository;

import lotbook.lotbook.dto.entity.Review;
import lotbook.lotbook.dto.mapper.ReviewWithNameMapperDTO;
import lotbook.lotbook.dto.response.ReviewDetails;

import java.util.List;

public interface ReviewMapper {
    List<ReviewWithNameMapperDTO> selectReviewsByProductSequence(long productSequence);
    int insert(Review v);
    int update(Review v);
    int updateReviewState(Review k);
    Review select(Review k);
    List<ReviewDetails> selectReviewsByMember(long k);
    Review selectReviewByOrderdetail(Review k);
}
