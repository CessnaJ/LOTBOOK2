package lotbook.lotbook.repository;

import lotbook.lotbook.dto.entity.Product;
import lotbook.lotbook.dto.mapper.ProductRelatedNameMapper;

public interface ProductMapper {

    Product selectProductBySequence(long productSequence);

    ProductRelatedNameMapper selectRelatedNameAndCategoryByProductSequence(long productSequence);
}
