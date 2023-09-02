package lotbook.lotbook.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lotbook.lotbook.dto.entity.OrderDetail;
import lotbook.lotbook.dto.entity.Product;
import lotbook.lotbook.dto.mapper.ProductRelatedNameMapperDTO;
import lotbook.lotbook.dto.mapper.ReviewWithNameMapperDTO;
import lotbook.lotbook.dto.response.ProductDetailWithReviews;
import lotbook.lotbook.enums.ProductStateEnum;
import lotbook.lotbook.repository.ProductMapper;
import lotbook.lotbook.repository.ReviewMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ReviewMapper reviewMapper;

    @Override
	public Product get(int sequence) {
        return productMapper.selectProductBySequence(sequence);
	}

    @Override
<<<<<<< HEAD
    public ProductDetailWithReviews getProductDetail(long sequence) {
        Product product = productMapper.selectProductBySequence(sequence);
        ProductRelatedNameMapperDTO relatedName = productMapper.selectRelatedNameAndCategoryByProductSequence(sequence);
        List<ReviewWithNameMapperDTO> reviews = reviewMapper.selectReviewsByProductSequence(sequence);
        double avgRating = 0;

        if (!reviews.isEmpty()) {
            avgRating = reviews.stream().mapToInt(ReviewWithNameMapperDTO::getRating).average().orElse(0.0);
        }
        int discountedPrice = (product.getPrice() * (int) (100.0 - product.getDiscountRate()) / 100) / 10 * 10;
        int pointAccumulation = (int) (product.getPrice() * product.getPointAccumulationRate() / 100);

        return ProductDetailWithReviews.builder()
                .sequence(product.getSequence())
                .productImgurl(product.getProductImgurl())
                .productDetailImgurl(product.getProductDetailImgurl())
                .name(product.getName())
                .originalPrice(product.getPrice())
                .discountRate(product.getDiscountRate())
                .price(discountedPrice)
                .content(product.getContent())
                .stock(product.getStock())
                .createdAt(product.getCreatedAt())
                .pointAccumulationRate(product.getPointAccumulationRate())
                .pointAccumulation(pointAccumulation)
                .salesCount(product.getSalesCount())
                // TODO: ENUM type handler-from mybatis
                .state(ProductStateEnum.ACTIVE)
                .authorSequence(product.getAuthorSequence())
                .authorName(relatedName.getAuthorName())
                .publisherSequence(product.getPublisherSequence())
                .publisherName(relatedName.getPublisherName())
                .mainCategorySequence(relatedName.getMainCategorySequence())
                .mainCategoryName(relatedName.getMainCategoryName())
                .subCategorySequence(relatedName.getSubCategorySequence())
                .subCategoryName(relatedName.getSubCategoryName())
                // TODO: reviewer name handling using dto with optional
                .reviews(reviews)
                .averageRating(avgRating)
                .build();
=======
    public int updateByProductKeyWithSalesCount(OrderDetail v) throws Exception {
        int result = 0;
        try {
            result = productMapper.updateByProductKeyWithSalesCount(v);
        } catch (Exception e) {
            e.getStackTrace();
            e.printStackTrace();
            throw new Exception("베스트셀러 책 검색 에러");
        }
        return result;
>>>>>>> 6f30a70428d275e892548c98704b5c48a9ba811d
    }

}