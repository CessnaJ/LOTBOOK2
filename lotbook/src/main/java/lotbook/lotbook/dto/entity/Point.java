package lotbook.lotbook.dto.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lotbook.lotbook.enums.PointStateEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Point {
	private long sequence;
	private int point;
	private String registeredAt;
	private PointStateEnum state;
	private long memberSequence;
}
