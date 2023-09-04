package lotbook.lotbook.dto.request;

import lombok.Data;

@Data
public class AddToCartRequestDTO {
    private long memberSequence;
    private long productSequence;
    private int count;


}
