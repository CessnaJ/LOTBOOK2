package lotbook.lotbook.service;

import lombok.RequiredArgsConstructor;
import lotbook.lotbook.dto.entity.Member;
import lotbook.lotbook.dto.entity.Review;
import lotbook.lotbook.dto.response.ReviewDetails;
import lotbook.lotbook.repository.ReviewMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewMapper reviewMapper;
    @Override
    public int register(Review v) throws Exception {
        int result = 0;
        try {
            result = reviewMapper.insert(v);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("ER7001 - 리뷰 생성 에러");
        }
        return result;
    }

    @Override
    public int modify(Review v) throws Exception {
        int result = 0;
        try {
            result = reviewMapper.update(v);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("ER7002 - 리뷰 수정 에러");
        }
        return result;
    }

    @Override
    public int remove(Review k) throws Exception {
        int result = 0;
        try {
            result = reviewMapper.delete(k);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("ER7003 - 리뷰 삭제 에러");
        }
        return result;
    }

    @Override
    public Review get(Review k) throws Exception {
        Review review = null;

        try {
            review = reviewMapper.selectReviewByOrderdetail(k);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("ER7004");
        }

        return review;
    }

    @Override
    public List<Review> get() throws Exception {
        return null;
    }

    @Override
    public List<ReviewDetails> get(long member) throws Exception {
        List<ReviewDetails> reviewDetail = new ArrayList<>();
        try {
            reviewDetail = reviewMapper.selectReviewsByMember(member);
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception("ER7005");
        }
        return reviewDetail;
    }
}
