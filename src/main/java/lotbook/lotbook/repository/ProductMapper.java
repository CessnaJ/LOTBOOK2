package lotbook.lotbook.repository;

import lotbook.lotbook.dto.entity.OrderDetail;
import lotbook.lotbook.dto.entity.Product;
import lotbook.lotbook.dto.mapper.CategoryProductWithReviewDTO;
import lotbook.lotbook.dto.mapper.ProductRelatedNameMapperDTO;

import java.util.List;

public interface ProductMapper {

    Product selectProductBySequence(long productSequence);

    ProductRelatedNameMapperDTO selectRelatedNameAndCategoryByProductSequence(long productSequence);
    
    
    int updateByProductKeyWithSalesCount(OrderDetail v);

    List<CategoryProductWithReviewDTO> getPopular();
    List<CategoryProductWithReviewDTO> getLatest();
    List<CategoryProductWithReviewDTO> getHighPoint();
    List<CategoryProductWithReviewDTO> getHighDiscount();
}
