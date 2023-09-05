package lotbook.lotbook.service;

import lombok.RequiredArgsConstructor;
import lotbook.lotbook.dto.entity.Cart;
import lotbook.lotbook.dto.entity.Product;
import lotbook.lotbook.dto.mapper.CategoryProductWithReviewDTO;
import lotbook.lotbook.dto.response.CartProduct;
import lotbook.lotbook.enums.ErrorCode;
import lotbook.lotbook.exception.CustomException;
import lotbook.lotbook.repository.CartMapper;
import lotbook.lotbook.repository.CategoryMapper;
import lotbook.lotbook.repository.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;


    @Override
    public List<CategoryProductWithReviewDTO> getBigCategory(String view, String orderby) throws Exception {
        List<CategoryProductWithReviewDTO> Products = categoryMapper.getBigCategory(view, orderby);

        for (CategoryProductWithReviewDTO product : Products) {
            int currentPrice = product.getPrice();
            double discountRate = product.getDiscountRate();
            int newPrice = (int) (currentPrice * (1 - discountRate / 100));
            newPrice = (newPrice / 10) * 10;
            product.setPrice(newPrice);
        }

        for (CategoryProductWithReviewDTO product : Products) {
            long sales = product.getSalesCount();
            int score = (int) (sales + (product.getRating_avg() == 0 ? 300 : (long)  product.getRating_avg() * 100));
            product.setPopularity(score);
        }

        switch (orderby.toLowerCase()) {
            case "latest":
                Products.sort(Comparator.comparing(CategoryProductWithReviewDTO::getCreatedAt).reversed());
                break;
            case "sales":
                Products.sort(Comparator.comparing(CategoryProductWithReviewDTO::getSalesCount).reversed());
                break;
            case "high_to_low":
                Products.sort(Comparator.comparing(CategoryProductWithReviewDTO::getPrice).reversed());
                break;
            case "low_to_high":
                Products.sort(Comparator.comparing(CategoryProductWithReviewDTO::getPrice));
                break;
            case "popular":
            default:
                Products.sort(Comparator.comparingLong(CategoryProductWithReviewDTO::getPopularity).reversed());
                break;
        }

        return Products;
    }

    @Override
    public List<CategoryProductWithReviewDTO> getSmallCategory(String view, String orderby) throws Exception {
        List<CategoryProductWithReviewDTO> Products = categoryMapper.getSmallCategory(view, orderby);

        for (CategoryProductWithReviewDTO product : Products) {
            int currentPrice = product.getPrice();
            double discountRate = product.getDiscountRate();
            int newPrice = (int) (currentPrice * (1 - discountRate / 100));
            newPrice = (newPrice / 10) * 10;
            product.setPrice(newPrice);
        }

        for (CategoryProductWithReviewDTO product : Products) {
            long sales = product.getSalesCount();
            int score = (int) (sales + (product.getRating_avg() == 0 ? 300 : (long)  product.getRating_avg() * 100));
            product.setPopularity(score);
        }

        switch (orderby.toLowerCase()) {
            case "latest":
                Products.sort(Comparator.comparing(CategoryProductWithReviewDTO::getCreatedAt).reversed());
                break;
            case "sales":
                Products.sort(Comparator.comparing(CategoryProductWithReviewDTO::getSalesCount).reversed());
                break;
            case "high_to_low":
                Products.sort(Comparator.comparing(CategoryProductWithReviewDTO::getPrice).reversed());
                break;
            case "low_to_high":
                Products.sort(Comparator.comparing(CategoryProductWithReviewDTO::getPrice));
                break;
            case "popular":
            default:
                Products.sort(Comparator.comparingLong(CategoryProductWithReviewDTO::getPopularity).reversed());
                break;
        }

        return Products;
    }
}
