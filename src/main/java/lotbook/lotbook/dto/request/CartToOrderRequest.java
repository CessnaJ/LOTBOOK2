package lotbook.lotbook.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartToOrderRequest {
    private long memberSequence;
    private String sequences;
    private String receiverName;
    private String orderPhone;
    private String zipcode;
    private String streetAddress;
    private String addressDetail;
    private String vendorMessage;
    private String email;
    private int usePoint;
}
