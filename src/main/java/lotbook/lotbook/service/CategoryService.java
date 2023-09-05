package lotbook.lotbook.service;

import lotbook.lotbook.dto.entity.Product;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

    Product get(int sequence);

}
