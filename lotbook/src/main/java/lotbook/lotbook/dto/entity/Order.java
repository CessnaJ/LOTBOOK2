package lotbook.lotbook.dto.entity;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lotbook.lotbook.dto.response.OrderDetailResponse;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	private long sequence;
	private String createdAt;
	
	private String zipcode;
	private String streetAddress;
	private String addressDetail;
	private String receiverEmail;
	private String orderPhone;
	private String receiverName;
	
	private String vendorMessage;
	private long memberSequence;
	
	private List<OrderDetailResponse> orderDetailList;
}
