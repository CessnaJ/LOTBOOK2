package lotbook.lotbook.repository;

import lotbook.lotbook.dto.entity.Category;

import java.util.List;

public interface CategoryMapper {

    Category selectCategoryBySequence(int sequence);

    List<Integer> selectSubcategorySequences(int sequence);
}
