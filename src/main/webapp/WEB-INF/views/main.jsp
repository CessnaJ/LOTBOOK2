<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:include page="popup.jsp" />

<script>
AOS.init({
	easing: 'ease-out-back',
	duration: 1000
	});

console.log("hi");

</script>

<!-- Header Section Begin -->
<header class="header">
	<div class="header__top">
		<div class="container">
			<nav class="header__menu header__top__right mobile-menu"
				style="padding: 5px 0">
				<ul>
					<c:choose>
						<c:when test="${logincust != null }">
							<li class="active"><a
								href="/mypage?memberSeq=${logincust.sequence }"><i
									class="fa fa-user"></i> 마이페이지</a></li>
							<li class=""><a href="/member/logout"><i
									class="fa fa-user"></i> 로그아웃</a></li>
						</c:when>
						<c:otherwise>
							<li class="active"><a href="/page/signin"><i
									class="fa fa-user"></i> 로그인</a></li>
							<li class=""><a href="/page/signup"><i
									class="fa fa-user"></i> 회원가입</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</nav>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<div class="header__logo">
					<a href="/main"><img src="/img/logo.png" alt=""></a>
				</div>
			</div>
			<div class="col-lg-6">
				<nav class="header__menu">
					<ul id="header__menus" >
						<li class="active"><a href="/main"  style="font-size: 20px; font-weight: 700;">홈</a></li>
						<li><a href="/category?view=1"  style="font-size: 20px; font-weight: 700;">도서 전체</a></li>
						<li><a href="/page/contact" style="font-size: 20px; font-weight: 700;">고객센터</a></li>
					</ul>
				</nav>
			</div>
			<c:choose>
				<c:when test="${logincust != null }">
					<div class="col-lg-3">
						<div class="header__cart">
							<ul>
								<li><a
									href="/cart/shopingCart?memberSeq=${logincust.sequence }"><i
									class="fa fa-shopping-bag"></i> <span>${cartCount }</span></a></li>
							</ul>
						</div>
					</div>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</header>

<!-- Header Section End -->
<!-- Hero Section Begin -->
<section class="hero">
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<div class="hero__categories">
					<div class="hero__categories__all">
						<i class="fa fa-bars"></i> <span>카테고리</span>
					</div>
					<jsp:include page="common_categories.jsp" />
				</div>
			</div>
			<div id="searchList" style="width: 505px; overflow: auto; position: absolute; max-height: 400px; background-color: white; right: 470px; top: 190px; z-index: 100; display: none;">
			</div>
			<div class="col-lg-9">
				<div class="hero__search">
					<div class="hero__search__form" style="position: relative;">
						<form action="#" style="position: relative"
							onsubmit="event.preventDefault(); search(document.getElementById('keyword').value);">
							<div class="hero__search__categories">통합 검색</div>
							<input type="text" id="keyword" placeholder="검색어를 입력해주세요" autocomplete="off">
							<<button id="closeSearch" type="button" class="close" aria-label="Close" style="position: absolute; right: 100px; display: none;" onclick="initSearchBar()">
								<span aria-hidden="true">&times;</span>
							</button>
							<button type="submit" class="site-btn">검색</button>
						</form>
					</div>
				</div>
				<div>
					<a
						href="/product-detail/264">
						<img class="hero__item" src="/img/banner.png">
					</a>

				</div>
			</div>

		</div>
	</div>
</section>
<!-- Hero Section End -->

<!-- Featured Section Begin -->
<section class="featured spad" data-aos="fade-up" data-aos-duration="2000">
	<div class="container" >
		<div class="row">
			<div class="col-lg-12">
				<div class="section-title">
					<h2>🥰꾸준히 사랑받는 작품🥰</h2>
				</div>
			</div>
		</div>
		<!-- Categories Section Begin -->
		<section class="categories">
			<div class="container">
				<div class="row">
					<div class="categories__slider owl-carousel">
						<c:forEach items="${popularProducts }" var="product">
							<div class="col-lg-3">
								<a
									href="/product-detail/${product.sequence}">
									<div class="categories__item set-bg"
										data-setbg="${product.productImgurl }" style="width: 200px;">
									</div>
								</a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</section>
		<!-- Categories Section End -->
	</div>
</section>
<!-- Featured Section End -->
<br>
<br>
<br>
<!-- Banner Begin -->
<div class="banner">
	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-md-6 col-sm-6">
				<div class="banner__pic" data-aos="fade-right" data-aos-duration="1000">
					<a
						href="/product-detail/250">
					<img src="/img/banner/banner01.jpg" style="max-height: 100%" alt=""></a>
				</div>
			</div>
			<div class="col-lg-6 col-md-6 col-sm-6">
				<div class="banner__pic" data-aos="fade-left" data-aos-duration="1000">
					<a
						href="/product-detail/1">
					<img src="/img/banner/banner02.jpg" alt=""></a>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Banner End -->
<br>
<br>
<br>
<br>
<div data-aos="fade-up" data-aos-duration="1500">
<!-- Latest Product Section Begin -->
<section class="latest-product spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-6">
				<div class="latest-product__text">
					<h4>따끈따끈 신작✨</h4>
					<div class="latest-product__slider owl-carousel">
						<div class="latest-prdouct__slider__item">
							<c:forEach items="${LatestProducts}" var="product" begin="0" end="2">
								<div class="col-lg-12">
									<a
										href="/product-detail/${product.sequence}"
										class="latest-product__item">
										<div class="latest-product__item__pic">
											<img src="${product.productImgurl}" alt="">
										</div>
										<div class="latest-product__item__text">
											<h6>${product.name}</h6>
												<c:set var="price" value="${product.price}"/>
	                                 			<span class="price">
	                                 			<fmt:formatNumber type="number" maxFractionDigits="0" value="${price}"/>
	                                      		원
	                                      		</span>
										</div>
									</a>
								</div>
							</c:forEach>
						</div>
						<div class="latest-prdouct__slider__item">
							<c:forEach items="${LatestProducts}" var="product" begin="3" end="5">
								<div class="col-lg-12">
									<a
										href="/product-detail/${product.sequence}"
										class="latest-product__item">
										<div class="latest-product__item__pic">
											<img src="${product.productImgurl}" alt="">
										</div>
										<div class="latest-product__item__text">
											<h6>${product.name}</h6>
											<c:set var="price" value="${product.price}"/>
	                                 			<span class="price">
	                                 			<fmt:formatNumber type="number" maxFractionDigits="0" value="${price}"/>
	                                      		원
	                                      		</span>
										</div>
									</a>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-4 col-md-6">
				<div class="latest-product__text">
					<h4>포인트 팡팡🎉</h4>
					<div class="latest-product__slider owl-carousel">
						<div class="latest-prdouct__slider__item">
							<c:forEach items="${PointProducts}" var="product" begin="0" end="2">
								<div class="col-lg-12">
									<a
										href="/product-detail/${product.sequence}"
										class="latest-product__item">
										<div class="latest-product__item__pic">
											<img src="${product.productImgurl}" alt="">
										</div>
										<div class="latest-product__item__text">
											<h6>${product.name}</h6>
											<c:set var="price" value="${product.price}"/>
	                                 			<span class="price">
	                                 			<fmt:formatNumber type="number" maxFractionDigits="0" value="${price}"/>
	                                      		원
	                                      		</span>
										</div>
									</a>
								</div>
							</c:forEach>
						</div>
						<div class="latest-prdouct__slider__item">
							<c:forEach items="${PointProducts}" var="product" begin="3" end="5">
								<div class="col-lg-12">
									<a
										href="/product-detail/${product.sequence}"
										class="latest-product__item">
										<div class="latest-product__item__pic">
											<img src="${product.productImgurl}" alt="">
										</div>
										<div class="latest-product__item__text">
											<h6>${product.name}</h6>
											<c:set var="price" value="${product.price}"/>
	                                 			<span class="price">
	                                 			<fmt:formatNumber type="number" maxFractionDigits="0" value="${price}"/>
	                                      		원
	                                      		</span>
										</div>
									</a>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-4 col-md-6">
				<div class="latest-product__text">
					<h4>사장님이 미쳤어요😆</h4>
					<div class="latest-product__slider owl-carousel">
						<div class="latest-prdouct__slider__item">
							<c:forEach items="${DiscountrProducts}" var="product" begin="0" end="2">
								<div class="col-lg-12">
									<a
										href="/product-detail/${product.sequence}"
										class="latest-product__item">
										<div class="latest-product__item__pic">
											<img src="${product.productImgurl}" alt="">
										</div>
										<div class="latest-product__item__text">
											<h6>${product.name}</h6>
											<c:set var="price" value="${product.price}"/>
	                                 			<span class="price">
	                                 			<fmt:formatNumber type="number" maxFractionDigits="0" value="${price}"/>
	                                      		원
	                                      		</span>
										</div>
									</a>
								</div>
							</c:forEach>
						</div>
						<div class="latest-prdouct__slider__item">
							<c:forEach items="${DiscountrProducts}" var="product" begin="3" end="5">
								<div class="col-lg-12">
									<a
										href="/product-detail/${product.sequence}"
										class="latest-product__item">
										<div class="latest-product__item__pic">
											<img src="${product.productImgurl}" alt="">
										</div>
										<div class="latest-product__item__text">
											<h6>${product.name}</h6>
											<c:set var="price" value="${product.price}"/>
	                                 			<span class="price">
	                                 			<fmt:formatNumber type="number" maxFractionDigits="0" value="${price}"/>
	                                      		원
	                                      		</span>
										</div>
									</a>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Latest Product Section End -->

</div>

<!-- Blog Section Begin -->
<section class="from-blog spad"></section>
<!-- Blog Section End -->
