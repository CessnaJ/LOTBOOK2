package lotbook.lotbook.repository;

import lotbook.lotbook.dto.entity.OrderDetail;
import lotbook.lotbook.dto.entity.Product;
import lotbook.lotbook.dto.mapper.ProductRelatedNameMapperDTO;

public interface ProductMapper {

    Product selectProductBySequence(long productSequence);

<<<<<<< HEAD
    ProductRelatedNameMapperDTO selectRelatedNameAndCategoryByProductSequence(long productSequence);


=======
    ProductRelatedNameMapper selectRelatedNameAndCategoryByProductSequence(long productSequence);

    int updateByProductKeyWithSalesCount(OrderDetail v);
>>>>>>> 6f30a70428d275e892548c98704b5c48a9ba811d
}
