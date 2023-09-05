package lotbook.lotbook.service;

import lotbook.lotbook.dto.entity.Product;
import lotbook.lotbook.dto.mapper.CategoryProductWithReviewDTO;
import lotbook.lotbook.dto.response.SearchResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public List<CategoryProductWithReviewDTO> getBigCategory(String view, String orderby) throws Exception;
    public List<CategoryProductWithReviewDTO> getSmallCategory(String view,String orderby) throws Exception;


}
