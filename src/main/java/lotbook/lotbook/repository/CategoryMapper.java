package lotbook.lotbook.repository;

import lotbook.lotbook.dto.entity.Category;
import lotbook.lotbook.dto.entity.Product;
import lotbook.lotbook.dto.mapper.CategoryProductWithReviewDTO;

import java.util.List;

public interface CategoryMapper {


    Category selectCategoryBySequence(int sequence);

    List<Integer> selectSubcategorySequences(int sequence);


    List<CategoryProductWithReviewDTO> getBigCategory(String view, String orderby);

    List<CategoryProductWithReviewDTO> getSmallCategory(String view, String orderby);
}
