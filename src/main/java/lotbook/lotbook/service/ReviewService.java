package lotbook.lotbook.service;

import lotbook.lotbook.dto.entity.Member;
import lotbook.lotbook.dto.entity.Review;
import lotbook.lotbook.dto.response.ReviewDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    int register(Review v) throws Exception;
    int modify(Review v) throws Exception;
    int remove(Review k) throws Exception;
    Review get(Review k) throws Exception;
    List<Review> get() throws Exception;
    List<ReviewDetails> get(long member) throws Exception;
}
