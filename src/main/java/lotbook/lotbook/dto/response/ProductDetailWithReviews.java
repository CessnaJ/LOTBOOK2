package lotbook.lotbook.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lotbook.lotbook.dto.mapper.ReviewWithNameMapperDTO;
import lotbook.lotbook.enums.ProductStateEnum;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailWithReviews {
	
	private long sequence;
	private String productImgurl;
	private String productDetailImgurl;
	private String name;
	private int originalPrice;
	private int price;
	private double discountRate;
	private String content;
	private int stock;
	private String createdAt;

	private int pointAccumulation;
	private double pointAccumulationRate;
	private long salesCount;
	private ProductStateEnum state;
	
	private int mainCategorySequence;
	private String mainCategoryName;
	private int subCategorySequence;
	private String subCategoryName;
	
	// FK
	private int authorSequence;
	private int publisherSequence;
	private String authorName;
	private String publisherName;
	
	
	
	private List<ReviewWithNameMapperDTO> reviews;
	private double averageRating;

}
