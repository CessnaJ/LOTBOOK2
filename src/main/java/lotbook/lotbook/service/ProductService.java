package lotbook.lotbook.service;

import lotbook.lotbook.dto.entity.OrderDetail;
import lotbook.lotbook.dto.entity.Product;
import lotbook.lotbook.dto.response.ProductDetailWithReviews;
import lotbook.lotbook.exception.CustomException;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    Product get(int sequence);

    ProductDetailWithReviews getProductDetail(long sequence) throws CustomException;
    
    int updateByProductKeyWithSalesCount(OrderDetail v) throws Exception;

    int updateByProductKeyWithOrderDetail(OrderDetail v) throws Exception;

}
