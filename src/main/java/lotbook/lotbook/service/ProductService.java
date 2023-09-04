package lotbook.lotbook.service;

import lotbook.lotbook.dto.entity.OrderDetail;
import lotbook.lotbook.dto.entity.Product;
import lotbook.lotbook.dto.response.ProductDetailWithReviews;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    Product get(int sequence);

    ProductDetailWithReviews getProductDetail(long sequence);
    
    int updateByProductKeyWithSalesCount(OrderDetail v) throws Exception;

}
