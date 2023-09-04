package lotbook.lotbook.repository;

import lotbook.lotbook.dto.mapper.ReviewWithNameMapperDTO;
import lotbook.lotbook.dto.mapper.SearchProductMapperDTO;

import java.util.List;

public interface SearchMapper {

    List<SearchProductMapperDTO> selectProductsByKeyword(String keyword);
}
