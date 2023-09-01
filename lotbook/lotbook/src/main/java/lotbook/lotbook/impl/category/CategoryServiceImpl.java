//package lotbook.lotbook.impl.category;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.apache.ibatis.session.SqlSession;
//
//import app.dto.entity.Product;
//import app.dto.mapper.CategoryProductWithReview;
//import app.dto.mapper.SearchProductMapper;
//import app.frame.DaoFrame;
//import app.frame.GetSessionFacroty;
//
//public class CategoryServiceImpl implements DaoFrame<Product, Product> {
//
//	SqlSession session;
//	CategoryDaoImpl categoryDao;
//
//	public CategoryServiceImpl() {
//		super();
//		categoryDao = new CategoryDaoImpl();
//	}
//
//	@Override
//	public int insert(Product v, SqlSession session) throws Exception {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int update(Product v, SqlSession session) throws Exception {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int delete(Product k, SqlSession session) throws Exception {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public Product select(Product k, SqlSession session) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Product> select(SqlSession session) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public Object selectComputerCategory() throws Exception {
//		session = GetSessionFacroty.getInstance().openSession();
//		List<Product> list = null;
//		try {
//			list = categoryDao.selectComputerCategory(list, session);
//		} catch (Exception e) {
//			e.getStackTrace();
//			e.printStackTrace();
//			throw new Exception("컴퓨터 카테고리 책 검색 에러");
//		}
//		return list;
//	}
//
//	public List<CategoryProductWithReview> selectCategoryByView(String view, String orderby) throws Exception {
//		session = GetSessionFacroty.getInstance().openSession();
//		List<CategoryProductWithReview> productList = null;
//
//		productList = categoryDao.selectCategory(Integer.parseInt(view), session);
//
//		for (CategoryProductWithReview product : productList) {
//		    int currentPrice = product.getPrice();
//		    double discountRate = product.getDiscountRate();
//		    int newPrice = (int) (currentPrice * (1 - discountRate / 100));
//		    newPrice = (newPrice / 10) * 10;
//		    product.setPrice(newPrice);
//		}
//
//		for (CategoryProductWithReview product : productList) {
//		    long sales = product.getSalesCount();
//		    int score = (int) (sales + (product.getRating_avg() == 0 ? 300 : (long)  product.getRating_avg() * 100));
//		    product.setPopularity(score);
//		}
//
//
//
//
//		if(orderby == null)
//			orderby = "sales";
//
//		switch (orderby.toLowerCase()) {
//		case "latest":
//			productList.sort(Comparator.comparing(CategoryProductWithReview::getCreatedAt).reversed());
//			break;
//		case "sales":
//			productList.sort(Comparator.comparing(CategoryProductWithReview::getSalesCount).reversed());
//			break;
//		case "high_to_low":
//			productList.sort(Comparator.comparing(CategoryProductWithReview::getPrice).reversed());
//			break;
//		case "low_to_high":
//			productList.sort(Comparator.comparing(CategoryProductWithReview::getPrice));
//			break;
//		 case "popular":
//	        default:
//	        	productList.sort(Comparator.comparingLong(CategoryProductWithReview::getPopularity).reversed());
//	            break;
//		}
//
//		return productList;
//	}
//}
