package lotbook.lotbook.repository;

import lotbook.lotbook.dto.entity.Product;

public interface ProductMapper {

    Product selectProductBySequence(long sequence);
}
