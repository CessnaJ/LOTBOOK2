package lotbook.lotbook.dto.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRelatedNameMapperDTO {
	private String authorName;
	private String publisherName;
	private int mainCategorySequence;
	private String mainCategoryName;
	private int subCategorySequence;
	private String subCategoryName;
}
