<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<style type="text/css">
.info-tag {
	width: 5vw;
	line-height: 1.2;
}

.normal-info {
	vertical-align: bottom;
	line-height: 1.2;
	display: block;
	min-width: 100px;
}

.bold-info {
	font-weight: bold;
	font-size: 1.3em;
	line-height: 1.2;
}

.content-style {
	line-height: 1.7;
	font-size: 1.1em;
}

.q-button {
	display: flex;
	width: 140px;
	height: 50px;
	display: inline;
	text-align: center;
	justify-content: space-between
}

.q-quantity {
	background-color: rgb(128, 128, 128);
}

.review-star {
	color: #EDBB0E;
	letter-spacing: -1px;
}

.d-inline {
	display: inline;
}

.created-at {
	font-size: 0.6em;
}
</style>


<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
  <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
    <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
  </symbol>
</svg>
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
					<ul id="header__menus">
						<li><a href="/main"
							style="font-size: 20px; font-weight: 700;">홈</a></li>
						<li class="active"><a href="/category?view=1"
							style="font-size: 20px; font-weight: 700;">도서 전체</a></li>
						<li><a href="/page/contact"
							   style="font-size: 20px; font-weight: 700;">고객센터</a></li>
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
<section class="hero hero-normal">
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
			</div>
		</div>
	</div>
</section>
<!-- Hero Section End -->

<!-- Breadcrumb Section Begin -->
<section class="breadcrumb-section set-bg" data-setbg="/img/books.jpg">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<div class="breadcrumb__text">
					<h2>${ productDetail.name }</h2>
					<div class="breadcrumb__option">
						<a href="/main">Home</a> <a href="./category?view=${productDetail.mainCategorySequence }">${productDetail.mainCategoryName }</a>
						<a href="./category?view=${productDetail.subCategorySequence }">${productDetail.subCategoryName }</a>
						<!-- TODO: 클릭시, 카테고리 검색으로 넘기기  -->
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Breadcrumb Section End -->

<!-- Product Details Section Begin -->
<section class="product-details spad">
	<div style="display: none;" id="productSeq">${productDetail.sequence }</div>
	<div style="display: none;" id="productName">'${productDetail.name }'</div>
	<div style="display: none;" id="productImgUrl">'${productDetail.productImgurl }'</div>
	<div style="display: none;" id="custSeq">${logincust.sequence }</div>
	<div class="container">
		<div class="row d-flex justify-content-center">
			<div class="col-lg-5 col-md-5 mr-3">
				<div class="product__details__pic">
					<div class="product__details__pic__item">
						<img class="product__details__pic__item--large"
							src=${productDetail.productImgurl } alt="">
					</div>

				</div>
			</div>
			<div class="col-lg-6 col-md-6">
				<div class="product__details__text">
					<h3>${ productDetail.name }</h3>
					<div class="product__details__rating">
						<c:set var="fullStars"
							value="${Math.floor(productDetail.averageRating)}" />
						<c:set var="halfStar"
							value="${productDetail.averageRating % 1 >= 0.5 ? 1 : 0}" />
						<c:set var="emptyStars" value="${5 - fullStars - halfStar}" />
						<c:forEach var="i" begin="1" end="${fullStars}">
							<i class="fa fa-star"></i>
						</c:forEach>

						<c:if test="${halfStar == 1}">
							<i class="fa fa-star-half-o"></i>
						</c:if>

						<c:forEach var="i" begin="1" end="${emptyStars}">
							<i class="fa fa-star-o"></i>
						</c:forEach>
						
						

						<span><fmt:formatNumber value="${productDetail.averageRating}" pattern="#0.0"/></span> <span>(${ productDetail.reviews.size() }개의
							리뷰)</span>
					</div>


					<div class="d-flex flex-column mb-3">
						<div class="d-flex flex-row">
							<h5 class="my-2 info-tag normal-info">정가</h5>
							<div class="my-2">
								<fmt:formatNumber
									value="${ productDetail.originalPrice }"
									type="number" pattern="#,##0" />
								원
							</div>
						</div>
						<div class="d-flex flex-row">
							<h5 class="my-2 info-tag normal-info">판매가</h5>
							<div class="my-2">
								<span class="bold-info"> <fmt:formatNumber
										value="${ productDetail.price }" type="number"
										pattern="#,##0" />
								</span> 원( <span class="text-warning">${ productDetail.discountRate.intValue()}%,
								</span> <span> <fmt:formatNumber
										value="${ productDetail.originalPrice - productDetail.price }"
										type="number" pattern="#,##0" /> 원 할인)
								</span>
							</div>
						</div>
						<div class="d-flex flex-row">
							<h5 class="my-2 info-tag normal-info">배송료</h5>
							<div class="my-2">무료</div>
						</div>
						<div class="d-flex flex-col" style="margin-top: 5px; margin-bottom: 5px; posistion: relative;">
							<a id="kakaotalk-sharing-btn" class="btn" style="padding: 0px;">
								<img style="width: 40px;" src="https://contents.kyobobook.co.kr/resources/fo/images/common/ink/ico_sns_kakao_48x48@2x.png">
							</a>
							<a id="copy-link-btn" class="btn" style="padding: 0px; margin-left: 20px;">
								<img style="width: 40px;" src="https://contents.kyobobook.co.kr/resources/fo/images/common/ink/ico_sns_url_48x48@2x.png">
							</a>
							<div class="bg-warning text-white" id="success-alert" style="margin-left: 10px; posistion: absolute; height: 40px; border-radius: 20px; padding-top: 7px; padding-right: 10px; padding-left: 10px;">
							  <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
							  복사되었습니다
							</div>
						</div>
					</div>
					<div class="mb-4">
						<p style="display: inline;">${productDetail.content.substring(0, 100)}...<a
								href="#details-description" class="text-muted"> 더보기</a>
						</p>

					</div>
					<div class="d-flex flex-row">


						<div class="d-flex flex-row align-items-center bg-light mr-5"
							style="width: 115px; display: inline-block; margin-right: 16px; margin-bottom: 4px;">
							<span class="p-3 text-dark btn " style="width: 50px;"
								onclick="decreaseQuantity()">-</span> <input type="text"
								value="1" id="product-count"
								class="fw-normal mb-0 ml-2 bg-light"
								style="width: 50px; border: none; text-align: center;" readonly>
							<span class="p-3 text-dark btn bg-light" style="width: 50px;"
								onclick="increaseQuantity()">+</span>
						</div>




						<!-- stock 0개일때 style, button disabled -->
						<c:choose>
							<c:when test="${productDetail.stock > 0}">
								<button type="button" class="primary-btn border_none"
									onclick='addToCart(${productDetail.sequence}, ${logincust.sequence})'
									 id="addToCartButton">장바구니에 담기</button>
								<button type="button" class="primary-btn border_none"
									onclick='checkOutBuyNow(${productDetail.sequence}, ${logincust.sequence})'
									id="checkoutbuynow">바로 구매</button>
							</c:when>
							<c:otherwise>
								<button type="button" class="primary-btn border_none"
									id="addToCartButton" style="background: #C8C8C8;" disabled>장바구니에 담기</button>
								<button type="button" class="primary-btn border_none"
									id="checkoutbuynow" style="background: #C8C8C8;" disabled>바로 구매</button>
							</c:otherwise>
						</c:choose>
					</div>


					<div class="d-flex py-4" style="height: 5vh;">
						<h5 class="normal-info" id="totalPriceTag"></h5>
						<div id="totalPrice" class="bold-info ml-3"
							style="line-height: 1.1;"></div>
					</div>

					<ul>
						<li><b>구매가능 여부</b> <span><c:choose>
									<c:when test="${productDetail.stock > 0}">구매가능</c:when>
									<c:otherwise><span style="color: black; font-weight:bolder;">품절</span></c:otherwise>
								</c:choose></span></li>
						<li><b>작가</b> <span>${productDetail.authorName }</span></li>
						<li><b>출판사</b> <span>${productDetail.publisherName }</span></li>
						<li><b>포인트 적립</b> <span><span class="text-warning">${productDetail.pointAccumulation }</span>포인트
								/ <span class="text-warning">${productDetail.pointAccumulationRate }%</span></span></li>
					</ul>
				</div>
			</div>
			<div class="col-lg-12">
				<div class="product__details__tab">
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item"><a class="nav-link active"
							data-toggle="tab" href="#tabs-1" role="tab" aria-selected="true">상세
								설명</a></li>
						<li class="nav-item"><a class="nav-link" data-toggle="tab"
							href="#tabs-2" role="tab" aria-selected="false">리뷰 <span>(${ productDetail.reviews.size() }개)</span></a>
						</li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="tabs-1" role="tabpanel">
							<div class="product__details__tab__desc" id="details-description">
								<h6>상세 설명</h6>
								<div class="d-flex flex-column align-items-center">
									<img
										class="product__details__pic__item--large col-lg-6 col-md-6 mb-5"
										src=${productDetail.productImgurl } alt="">
									<p class="col-lg-8 col-md-8 content-style">${productDetail.content}</p>
								</div>

								<div class="d-flex flex-column align-items-center mb-5">
									<c:if
										test="${productDetail.productDetailImgurl != null}">
										<img
											class="product__details__pic__item--large col-lg-6 col-md-6 mb-5"
											src=${productDetail.productDetailImgurl }
											alt="상품디테일사진">
									</c:if>
								</div>


							</div>
						</div>

						<div class="tab-pane" id="tabs-2" role="tabpanel">
							<div class="product__details__tab__desc">
								<h6>리뷰</h6>
								<div class="reviews">
									<c:choose>
										<c:when test="${empty productDetail.reviews}">
											<p>등록된 리뷰가 없습니다.</p>
										</c:when>


										<c:otherwise>
											<c:forEach items="${productDetail.reviews}"
												var="review">
												<fmt:parseDate value="${review.createdAt}"
													pattern="yyyy-MM-dd HH:mm:ss" var="parsedDate" />
												<fmt:formatDate value="${parsedDate}"
													pattern="yyyy년 MM월 dd일" var="formattedDate" />

												<div class="review mb-4">
													<div class="review__header">
														<h5 class="review__author d-inline">${review.name.substring(0, 2)}****</h5>
														<div
															class="product__details__rating review-star d-inline ml-3">
															<c:set var="fullStars"
																value="${Math.floor(review.rating)}" />
															<c:set var="halfStar"
																value="${review.rating % 1 >= 0.5 ? 1 : 0}" />
															<c:set var="emptyStars"
																value="${5 - fullStars - halfStar}" />
															<c:forEach var="i" begin="1" end="${fullStars}">
																<i class="fa fa-star"></i>
															</c:forEach>

															<c:if test="${halfStar == 1}">
																<i class="fa fa-star-half-o"></i>
															</c:if>

															<c:forEach var="i" begin="1" end="${emptyStars}">
																<i class="fa fa-star-o"></i>
															</c:forEach>

															<span>${review.rating }</span> <span>점</span>
														</div>
														<p class="review__date d-inline created-at ml-2">${formattedDate}</p>

													</div>
													<div class="review__content mt-2">
														<p class="review__comment">${review.comment}</p>
													</div>
												</div>

											</c:forEach>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Product Details Section End -->

<!-- Modal -->
<div class="modal fade" id="addToCartModal" tabindex="-1" role="dialog"
	aria-labelledby="addToCartModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered mt-sm-3 mt-md-5 mt-lg-6"
		role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="addToCartModalLabel">장바구니 추가</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">장바구니에 상품을 담았습니다. 장바구니로 이동하시겠습니까?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
				<a
					href="/cart/shopingCart?memberSeq=${logincust.sequence}"
					class="btn btn-danger">장바구니로 가기</a>
			</div>
		</div>
	</div>
</div>
<textarea id="urlArea" style="display: none;"></textarea>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
   $(document).ready(function() {
	   $("#success-alert").hide();

	   Kakao.init('893713b29c0f17f54e1560263bc47e13');

	   $('#kakaotalk-sharing-btn').click(function(){
 	   Kakao.Share.sendDefault({
 		   objectType: 'commerce',
 		   content: {
 		     title: `${productDetail.content.substring(0, 100)}`,
 		     imageUrl:
 		     `${productDetail.productImgurl }`,
 		     //TODO : 추후 배포 url로 변경
 		     link: {
 		       mobileWebUrl: `http://127.0.0.1/product-detail/${productDetail.sequence}`,
 		       webUrl: `http://127.0.0.1/product-detail/${productDetail.sequence}`,
 		     },
 		   },
 		   commerce: {
 		     productName: `${productDetail.name }`,
 		     regularPrice: ${productDetail.originalPrice},
 		     discountRate: ${productDetail.discountRate.intValue()},
 		     discountPrice: ${productDetail.price},
 		   },
 		   buttons: [
 		     {
 		       title: '구매하기',
 		       link: {
 		         mobileWebUrl: `http://127.0.0.1/product-detail/${productDetail.sequence}`,
 		         webUrl:`http://127.0.0.1/product-detail/${productDetail.sequence}`,
 		       },
 		     },
 		   ],
 		 });})

 		 
	  $("#copy-link-btn").click(function() {
		  
		  var url = '';
			var textarea = document.createElement("textarea");
			document.body.appendChild(textarea);
			url = window.document.location.href;
			textarea.value = url;
			textarea.select();
			document.execCommand("copy");
			document.body.removeChild(textarea);

			$("#success-alert").fadeTo(2000, 500).slideUp(500, function() {
			      $("#success-alert").slideUp(500);
			    });
	  });
	   
      $("#addToCartButton").click(function() {
         $("#addToCartModal").modal("show");
      });
      
      prodSeqList = getCookie("prodSeqList");
      prodNameList = getCookie("prodNameList");
      prodImgList = getCookie("prodImgList");

      if (prodSeqList == null) {
         prodSeqList = [];
      } else {
         prodSeqList = getCookie("prodSeqList").split(',');
      }
      if (prodNameList == null) {
         prodNameList = [];
      } else {
         prodNameList = getCookie("prodNameList").split(',');
      }
      if (prodImgList == null) {
         prodImgList = [];
      } else {
         prodImgList = getCookie("prodImgList").split(',');
      }
      
      var custSeq = document.getElementById("custSeq").innerText;
      var prodSeq = document.getElementById("productSeq").innerText;
      var prodName = document.getElementById("productName").innerText;
      var prodImg = document.getElementById("productImgUrl").innerText;

      if (custSeq == '') {
         
      }
      else {
         var seqList = getCookie('prodSeqList');
         var seqDup = false;
         
         if (seqList == null) {
            
         } else {
            for(var i=0; i<prodSeqList.length; i++) {
               if (prodSeqList[i] == prodSeq) {
                  seqDup = true;
               }
            }
         }

         if (seqDup) {
            
         } else {
            prodSeqList.push(prodSeq);
            prodNameList.push(prodName);
            prodImgList.push(prodImg);
            setCookie('prodSeqList', prodSeqList, 1);
            setCookie('prodNameList', prodNameList, 1);
            setCookie('prodImgList', prodImgList, 1);
         }
      }
   });

   let count = 1;
   
   function addToCart(productSeq, memberSeq) {
      if (memberSeq === undefined) {
           alert("로그인이 필요합니다. 로그인 페이지로 이동합니다.");
           window.location.href = '/page/signin';
           
           return;
       }
      
      var count = Number($('#product-count').val());
      console.log(productSeq, memberSeq, count)


	   axios.post('/api/addToCart', {
		   memberSequence: memberSeq,
		   productSequence: productSeq,
		   count: count
	   })
			   .then(function (response) {
				   console.log(response);
				   if (!response.data) { // If the data is 0 or null or undefined...
					   alert("카트에 추가하지 못했습니다. 다시 시도해주세요.");
					   return;
				   }

				   $('#addToCartModal').modal('show');
			   })
			   .catch(function (error) {
				   console.log(error);
				   alert("카트에 넣는 도중 오류가 발생했습니다. 다시 시도해주세요.")
			   });
   }
   
   function checkOutBuyNow(productId, memberSeq) {
       var count = Number($('#productQuantity').val());
       if (memberSeq === undefined) {
           alert("로그인이 필요합니다. 로그인 페이지로 이동합니다.");
           window.location.href = '/page/signin';
           
           return;
       }
       var count = Number($('#product-count').val());
       // Redirect to the checkout page with the specified count and product ID
       window.location.href = '/checkout/api/checkoutbuynow?count=' + count + '&productId=' + productId + '&memberSeq=' + memberSeq;


			   }
   
   
    

    function increaseQuantity() {
      count++;
      document.getElementById("product-count").value = count;
      renderExpectedPrice(count);
    }

    function decreaseQuantity() {
      if (count > 1) {
        count--;
        document.getElementById("product-count").value = count;
        renderExpectedPrice(count);
      }
    }
   
   
   // 개수에 따라 총상품금액 선택적 렌더링
    function renderExpectedPrice(cnt) {
      console.log("발동");
      var count = Number(cnt);
      if (count >= 2) {
         document.getElementById('totalPriceTag').textContent = '총 상품금액: ';
         var totalPrice = count * ${productDetail.price};
        // document.getElementById('totalPrice').textContent = count * ${productDetail.price};
        document.getElementById('totalPrice').textContent = totalPrice.toLocaleString() + "원";
      } else {
         document.getElementById('totalPriceTag').textContent = '';
        document.getElementById('totalPrice').textContent = '';
      }
    }
   
    function sendKakao(){
    	console.log('here');
 	   Kakao.Share.sendDefault({
 		   objectType: 'commerce',
 		   content: {
 		     title: '언제 어디든, 더 쉽고 편하게 당신의 일상을 더 즐겁게, 헤이 라이언의 이야기를 들려드릴게요.',
 		     imageUrl:
 		       'http://k.kakaocdn.net/dn/dScJiJ/btqB3cwK1Hi/pv5qHVwetz5RZfPZR3C5K1/kakaolink40_original.png',
 		     link: {
 		       mobileWebUrl: 'https://developers.kakao.com',
 		       webUrl: 'https://developers.kakao.com',
 		     },
 		   },
 		   commerce: {
 		     productName: `${productDetail.name }`,
 		     regularPrice: 100000,
 		     discountRate: 10,
 		     discountPrice: 90000,
 		   },
 		   buttons: [
 		     {
 		       title: '구매하기',
 		       link: {
 		         mobileWebUrl: 'https://developers.kakao.com',
 		         webUrl: 'https://developers.kakao.com',
 		       },
 		     },
 		     {
 		       title: '공유하기',
 		       link: {
 		         mobileWebUrl: 'https://developers.kakao.com',
 		         webUrl: 'https://developers.kakao.com',
 		       },
 		     },
 		   ],
 		 });
 	   };

</script>
