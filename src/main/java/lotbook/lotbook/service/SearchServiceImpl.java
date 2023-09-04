package lotbook.lotbook.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotbook.lotbook.dto.entity.Category;
import lotbook.lotbook.dto.mapper.SearchProductMapperDTO;
import lotbook.lotbook.dto.response.SearchResult;
import lotbook.lotbook.repository.CategoryMapper;
import lotbook.lotbook.repository.SearchMapper;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final SearchMapper searchMapper;
    private final CategoryMapper categoryMapper;

    @Override
    public SearchResult getAllByKeyword(String keyword, String orderby, String category) throws Exception {
        log.info(keyword + orderby + category);
        keyword = keyword.replaceAll("\\s","");
        List<SearchProductMapperDTO> searchedList = searchMapper.selectProductsByKeyword(keyword);
        List<SearchProductMapperDTO> filteredList;
        SearchResult searchResult = null;

        // popularity, pointAccumulation, discountedPrice 계산 및 설정
        searchedList.forEach(item -> {
            long popularity = calculatePopularity(item);
            int pointAccumulation = calculatePointAccumulation(item);
            int discountedPrice = calculateDiscountedPrice(item);

            item.setPopularity(popularity);
            item.setPointAccumulation(pointAccumulation);
            item.setDiscountedPrice(discountedPrice);
        });

        // 정렬 기준을 적용
        orderBy(searchedList, orderby);

        // 카테고리별 검색결과 수 계산하기. -> 카테고리 별 검색결과 screening 하기 전에 개수만 파악.
        Map<String, Integer> countByCategory = searchedList.stream().collect(
                Collectors.groupingBy(SearchProductMapperDTO::getCategoryName, Collectors.summingInt(item -> 1)));
        countByCategory.put("전체", searchedList.size());

        // 카테고리 기본값 0 -> 전체를 의미. 나머지는 매칭되는거를 걸러줌.
        int categorySequence = 0;
        String categoryName = "전체";
        if (category != null) {
            categorySequence = Integer.parseInt(category);
            Category categoryInstance = categoryMapper.selectCategoryBySequence(categorySequence);
            categoryName = categoryInstance.getName();
        }

        switch (categorySequence) {
            case 0:
                // 모든 검색결과 할당
                filteredList = searchedList; // 모든 검색결과 할당
                break;
            default:
                List<Integer> subcategoryIds = categoryMapper.selectSubcategorySequences(categorySequence);
                subcategoryIds.add(categorySequence); // 하위카테고리 선택을 위한 자기 자신 넣기.

                filteredList = searchedList.stream().filter(item -> subcategoryIds.contains(item.getCategorySequence()))
                        .collect(Collectors.toList());
        }

        searchResult = SearchResult.builder().searchKeyword(keyword).totalCount(searchedList.size())
                .categoryCount(filteredList.size()).countByCategory(countByCategory)
                .currentCategorySequence(categorySequence).currentCategoryName(categoryName).orderBy(orderby)
                .searchList(filteredList).build();

        log.warn(searchResult.toString());
        return searchResult;
    }

    @Override
    public SearchResult getAllByProductName(String keyword, String orderby, String category) throws Exception {
        return null;
    }

    @Override
    public SearchResult getAllByAuthorName(String keyword, String orderby, String category) throws Exception {
        return null;
    }

    @Override
    public SearchResult getAllByCategoryName(String keyword, String orderby, String category) throws Exception {
        return null;
    }

    @Override
    public SearchResult getAllByPublisherName(String keyword, String orderby, String category) throws Exception {
        return null;
    }


    private void orderBy(List<SearchProductMapperDTO> list, String orderBy) {
        switch (orderBy.toLowerCase()) {
            case "latest":
                list.sort(Comparator.comparing(SearchProductMapperDTO::getCreatedAt).reversed());
                break;
            case "sales":
                list.sort(Comparator.comparing(SearchProductMapperDTO::getSalesCount).reversed());
                break;
            case "high_to_low":
                list.sort(Comparator.comparing(SearchProductMapperDTO::getDiscountedPrice).reversed());
                break;
            case "low_to_high":
                list.sort(Comparator.comparing(SearchProductMapperDTO::getDiscountedPrice));
                break;
            case "popular":
            default:
                list.sort(Comparator.comparingLong(SearchProductMapperDTO::getPopularity).reversed());
                break;
        }
    }

    private long calculatePopularity(SearchProductMapperDTO item) {
        return item.getSalesCount() + (item.getRatingAvg() == 0 ? 300 : (long) item.getRatingAvg() * 100);
    }

    private int calculatePointAccumulation(SearchProductMapperDTO item) {
        return (int) (item.getPrice() * item.getPointAccumulationRate() / 100);
    }

    private int calculateDiscountedPrice(SearchProductMapperDTO item) {
        double discountRate = item.getDiscountRate();
        int discountedPrice = (int) (item.getPrice() * (1 - discountRate / 100));
        return (discountedPrice / 10) * 10;
    }
}
