package lotbook.lotbook.repository;

import lotbook.lotbook.dto.entity.Product;
import lotbook.lotbook.dto.mapper.ProductRelatedNameMapperDTO;

public interface ProductMapper {

    Product selectProductBySequence(long productSequence);

    ProductRelatedNameMapperDTO selectRelatedNameAndCategoryByProductSequence(long productSequence);


}
