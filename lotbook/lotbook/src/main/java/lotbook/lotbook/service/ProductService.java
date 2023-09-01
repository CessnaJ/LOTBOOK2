package lotbook.lotbook.service;

import lotbook.lotbook.dto.entity.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    Product get(int sequence);

}
