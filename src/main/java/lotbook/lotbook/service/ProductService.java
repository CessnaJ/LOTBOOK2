package lotbook.lotbook.service;

import lotbook.lotbook.dto.entity.OrderDetail;
import lotbook.lotbook.dto.entity.Product;
import lotbook.lotbook.dto.response.ProductDetailWithReviews;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    Product get(int sequence);

<<<<<<< HEAD
    ProductDetailWithReviews getProductDetail(long sequence);
=======
    int updateByProductKeyWithSalesCount(OrderDetail v) throws Exception;
>>>>>>> 6f30a70428d275e892548c98704b5c48a9ba811d

}
