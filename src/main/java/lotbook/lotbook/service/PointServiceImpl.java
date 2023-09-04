package lotbook.lotbook.service;

import lombok.RequiredArgsConstructor;
import lotbook.lotbook.dto.entity.Point;
import lotbook.lotbook.repository.PointMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {

    private final PointMapper pointMapper;

    @Override
    public int register(Point v) throws Exception {
        int result = 0;
        try {
            result = pointMapper.insert(v);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("포인트 적립 에러");
        }
        return result;
    }

    @Override
    public int modify(Point v) throws Exception {
        int result = 0;
        try {
            result = pointMapper.update(v);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("사용자 포인트 수정 에러");
        }
        return result;
    }

    @Override
    public int getMemberSeq() throws Exception {
        int result = 0;
        try {
            result = pointMapper.select();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("ER6003 - 사용자 정보 조회 에러");
        }
        return result;
    }

    @Override
    public List<Point> getAll(Point v) throws Exception {
        List<Point> points = new ArrayList<>();
        try {
            points = pointMapper.getAll(v);
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception("ER6004 - 포인트 조회 에러");
        }
        return points;
    }
}
