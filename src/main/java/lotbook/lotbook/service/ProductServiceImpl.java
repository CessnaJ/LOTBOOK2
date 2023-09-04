package lotbook.lotbook.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lotbook.lotbook.dto.entity.OrderDetail;
import lotbook.lotbook.dto.entity.Product;
import lotbook.lotbook.dto.mapper.CategoryProductWithReviewDTO;
import lotbook.lotbook.dto.mapper.ProductRelatedNameMapperDTO;
import lotbook.lotbook.dto.mapper.ReviewWithNameMapperDTO;
import lotbook.lotbook.dto.response.ProductDetailWithReviews;
import lotbook.lotbook.enums.ProductStateEnum;
import lotbook.lotbook.exception.CustomException;
import lotbook.lotbook.repository.ProductMapper;
import lotbook.lotbook.repository.ReviewMapper;
import org.springframework.stereotype.Service;

import static lotbook.lotbook.enums.ErrorCode.ERROR_CODE_1;
import static lotbook.lotbook.enums.ErrorCode.PRODUCT_DETAIL_ERROR_1;


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
    public ProductDetailWithReviews getProductDetail(long sequence) throws CustomException {
        Product product = productMapper.selectProductBySequence(sequence);
        if (product == null) {
            throw new CustomException(PRODUCT_DETAIL_ERROR_1);
        }

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


    }

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
    }

    @Override
    public int updateByProductKeyWithOrderDetail(OrderDetail v) throws Exception {
        int result = 0;
        try {
            result = productMapper.updateByProductKeyWithOrderDetail(v);

        } catch (Exception e) {
            e.getStackTrace();
            throw new Exception("주문 상태 변경 에러");
        }
        return result;
    }


    @Override
    public List<CategoryProductWithReviewDTO> getPopular() {

        List<CategoryProductWithReviewDTO> popularProducts = productMapper.getPopular();

        for (CategoryProductWithReviewDTO product : popularProducts) {
            int currentPrice = product.getPrice();
            double discountRate = product.getDiscountRate();
            int newPrice = (int) (currentPrice * (1 - discountRate / 100));
            newPrice = (newPrice / 10) * 10;
            product.setPrice(newPrice);
        }

        return popularProducts;
    }

    @Override
    public List<CategoryProductWithReviewDTO> getLatest() {
        List<CategoryProductWithReviewDTO> latestProducts = productMapper.getLatest();

        for (CategoryProductWithReviewDTO product : latestProducts) {
            int currentPrice = product.getPrice();
            double discountRate = product.getDiscountRate();
            int newPrice = (int) (currentPrice * (1 - discountRate / 100));
            newPrice = (newPrice / 10) * 10;
            product.setPrice(newPrice);
        }

        return latestProducts;
    }

    @Override
    public List<CategoryProductWithReviewDTO> getHighPoint() {
        List<CategoryProductWithReviewDTO> pointProducts = productMapper.getHighPoint();

        for (CategoryProductWithReviewDTO product : pointProducts) {
            int currentPrice = product.getPrice();
            double discountRate = product.getDiscountRate();
            int newPrice = (int) (currentPrice * (1 - discountRate / 100));
            newPrice = (newPrice / 10) * 10;
            product.setPrice(newPrice);
        }

        return pointProducts;
    }

    @Override
    public List<CategoryProductWithReviewDTO> getHighDiscount() {
        List<CategoryProductWithReviewDTO> highProducts = productMapper.getHighDiscount();

        for (CategoryProductWithReviewDTO product : highProducts) {
            int currentPrice = product.getPrice();
            double discountRate = product.getDiscountRate();
            int newPrice = (int) (currentPrice * (1 - discountRate / 100));
            newPrice = (newPrice / 10) * 10;
            product.setPrice(newPrice);
        }


        return highProducts;
    }

}