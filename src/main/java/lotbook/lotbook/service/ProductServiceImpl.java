package lotbook.lotbook.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lotbook.lotbook.dto.entity.Product;
import lotbook.lotbook.repository.ProductMapper;
import lotbook.lotbook.repository.ReviewMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ReviewMapper reviewMapper;

    @Override
	public Product get(int sequence) {
        Product product = productMapper.selectProductBySequence(sequence);


		return product;
	}

}