package lotbook.lotbook.repository;

import lotbook.lotbook.dto.entity.Point;

import java.util.List;

public interface PointMapper {
    int insert(Point v);
    int update(Point v);
    int select();
    List<Point> getAll(Point v);
}
