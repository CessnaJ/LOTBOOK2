package lotbook.lotbook.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lotbook.lotbook.dto.entity.Product;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDetails {
	private long sequence;
	private int rating;
	private String comment;
	private String createdAt;
	private String updatedAt;
	private boolean isDeleted;
	private Product reviewDetailProduct;
	
	// FK
	private long memberSequence;
	private long productSequence;
}
