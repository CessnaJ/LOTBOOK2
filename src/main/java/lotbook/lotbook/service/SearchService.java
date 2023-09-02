package lotbook.lotbook.service;


import lotbook.lotbook.dto.mapper.SearchProductMapperDTO;
import lotbook.lotbook.dto.response.SearchResult;

import java.util.List;

public interface SearchService {

    public SearchResult getAllByKeyword(String keyword, String orderby, String category) throws Exception;

    // TODO: 아래 기본검색은 따로 구현
    public SearchResult getAllByProductName(String keyword, String orderby, String category) throws Exception;
    public SearchResult getAllByAuthorName(String keyword, String orderby, String category) throws Exception;
    public SearchResult getAllByCategoryName(String keyword, String orderby, String category) throws Exception;
    public SearchResult getAllByPublisherName(String keyword, String orderby, String category) throws Exception;

}
