package lotbook.lotbook.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
    private long memberSequence;
    private long productSequence;
    private long orderdetailSequence;
    private int rating;
    private String comment;
}
