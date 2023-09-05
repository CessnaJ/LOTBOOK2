package lotbook.lotbook.service;

import lotbook.lotbook.dto.entity.OrderDetail;
import lotbook.lotbook.dto.entity.Product;
import lotbook.lotbook.dto.mapper.CategoryProductWithReviewDTO;
import lotbook.lotbook.dto.response.ProductDetailWithReviews;
import lotbook.lotbook.exception.CustomException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    Product get(int sequence);

    ProductDetailWithReviews getProductDetail(long sequence) throws CustomException;

    int updateByProductKeyWithSalesCount(OrderDetail v) throws Exception;

    int updateByProductKeyWithOrderDetail(OrderDetail v) throws Exception;


    List<CategoryProductWithReviewDTO> getPopular();

    List<CategoryProductWithReviewDTO> getLatest();
    List<CategoryProductWithReviewDTO> getHighPoint();
    List<CategoryProductWithReviewDTO> getHighDiscount();

}
