package lotbook.lotbook.repository;

import lotbook.lotbook.dto.entity.Product;

public interface ReviewMapper {
    Product selectReviewsByProductSequence(long productSequence);

}
