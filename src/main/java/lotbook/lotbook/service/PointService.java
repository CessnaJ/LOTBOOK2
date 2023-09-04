package lotbook.lotbook.service;
import lotbook.lotbook.dto.entity.Point;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PointService {
    int insert(Point v) throws Exception;
    int modify(Point v) throws Exception;
    int getMemberSeq() throws Exception;
    List<Point> getAll(Point v) throws Exception;

}
