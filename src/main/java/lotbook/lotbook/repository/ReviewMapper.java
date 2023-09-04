package lotbook.lotbook.repository;

import lotbook.lotbook.dto.mapper.ReviewWithNameMapperDTO;

import java.util.List;

public interface ReviewMapper {
    List<ReviewWithNameMapperDTO> selectReviewsByProductSequence(long productSequence);

}
