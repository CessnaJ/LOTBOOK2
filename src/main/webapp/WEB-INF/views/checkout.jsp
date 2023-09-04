<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
request.setCharacterEncoding("UTF-8");
String[] orderProductList = request.getParameterValues("orderProductList");
String[] sequences = request.getParameterValues("sequences");
%>


<script>
$(document).ready(function(){
   document.getElementById('register_form').addEventListener("keydown", evt => {
        if (evt.code === "Enter") 
           evt.preventDefault();
         });   
})

var payPrice = 0;
var usePoint = 0;
function orderProducts (sequences){
   location.href = 'main.bit?view=checkout-result&sequences=' + sequences;
   request.setAttribute('zipcode', document.getElementById("sample6_postcode").value);
} 

function get_my_info(name, email, memberPhone, zipcode, streetAddress, addressDetail) {
   const checkbox = document.getElementById("check_box");
   
   if (checkbox.checked) {
      document.getElementById("custName").value = name;
      document.getElementById("custEmail").value = email;
      document.getElementById("custPhone").value = memberPhone;
      document.getElementById("sample6_postcode").value = zipcode;
      document.getElementById("sample6_address").value = streetAddress;
      document.getElementById("sample6_detailAddress").value = addressDetail;
   } else {
      document.getElementById("custName").value = "";
      document.getElementById("custEmail").value = "";
      document.getElementById("custPhone").value = "";
      document.getElementById("sample6_postcode").value = "";
      document.getElementById("sample6_address").value = "";
      document.getElementById("sample6_detailAddress").value = "";
   }
}
function use_all_point(totalPoint, totalPrice) {
   var checkbox = document.getElementById("checkbox");
   
   if (checkbox.checked) {
      document.getElementById("usePoint").value = totalPoint;
      document.getElementById("totalPrice").innerHTML = (totalPrice - totalPoint) + " 원";
      document.getElementById("usePoint").disabled = true;
      payPrice = totalPrice - totalPoint;
      usePoint = totalPoint
   } else {
      document.getElementById("usePoint").disabled = false;
   }
}
function checkout_result(sequences) {
   location.href = "main.bit?view=checkout-result&sequences=" + sequences + "&cmd=" + 1 + "&usePoint=" + usePoint;
   
}
function use_point(value, totalPrice, myPoint) {
   if (value > myPoint) {
      alert("사용할 수 있는 포인트를 초과했습니다.");
      document.getElementById("usePoint").value = myPoint;
      document.getElementById("totalPrice").innerHTML = (totalPrice - myPoint) + " 원";
   } else {
      document.getElementById("totalPrice").innerHTML = (totalPrice - value) + " 원";
      payPrice = totalPrice - value;
      usePoint = value;
   }
   
}
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
                             href="/main/mypage?memberSeq=${logincust.sequence }"><i
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
                  <li><a href="/main"  style="font-size: 20px; font-weight: 700;">홈</a></li>
                  <li><a href="category.bit?view=1"  style="font-size: 20px; font-weight: 700;">도서 전체</a></li>
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
                           href="main.bit?view=shopping-cart&memberSeq=${logincust.sequence }"><i
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
         <div class="col-lg-9">
            <div class="hero__search">
               <div class="hero__search__form">
                  <form action="#"
                     onsubmit="event.preventDefault(); search(document.getElementById('keyword').value);">
                     <div class="hero__search__categories">통합 검색</div>
                     <input type="text" id="keyword" placeholder="검색어를 입력해주세요">
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
               <h2>Checkout</h2>
               <div class="breadcrumb__option">
                  <a href="index.jsp">Home</a> <span>Checkout</span>
               </div>
            </div>
         </div>
      </div>
   </div>
</section>
<!-- Breadcrumb Section End -->

<!-- Checkout Section Begin -->
<section class="checkout spad">
   <div class="container">
      <div class="row"></div>
      <div class="checkout__form">
         <h4>결제 확인서</h4>
         <form id="register_form" action="/cart/cartToOrderResult" method="post">
            <input style="display: none;" id="productSequences" name="sequences" value="${sequences }">
            <input style="display: none;" id="memberSequence" name="memberSequence" value="${logincust.sequence }">
            <input type="hidden" name="view" value="checkout-result" />
            <div class="row">
               <div class="col-lg-8 col-md-6">
                  <div class="d-flex flex-col align-items-center">
                     <input id="check_box" type="checkbox" class="mb-3 text-dark"
                        onclick="get_my_info('${logincust.name }', '${logincust.email }', '${logincust.memberPhone }', '${logincust.zipcode }', '${logincust.streetAddress }', '${logincust.addressDetail }')">
                     <p class="ml-2 text-muted">내 정보 불러오기</p>
                  </div>
                  <div class="row">
                     <div class="col-lg-8 col-md-6">
                        <div class="checkout__input">
                           <p>
                              받는 분 성함<span>*</span>
                           </p>
                           <input class="text-dark" id="custName" type="text"
                              name="receiverName" required>
                        </div>
                     </div>
                  </div>
                  <div class="checkout__input">
                     <p>
                        이메일(청구서 수신용)<span>*</span>
                     </p>
                     <input class="text-dark" id="custEmail" type="email"
                        name="email" required>
                  </div>

                  <div class="checkout__input">
                     <p>
                        연락처<span>*</span>
                     </p>
                     <input class="text-dark" id="custPhone" type="tel"
                        pattern="[0-9]{3}[0-9]{4}[0-9]{4}" name="orderPhone" required>
                  </div>
                  <div class="checkout__input">
                     <p>
                        우편 번호<span>*</span>
                     </p>
                     <input class="text-dark" type="text" id="sample6_postcode"
                        placeholder="우편번호" name="zipcode"> <input
                        type="button" onclick="getAddress()" value="우편번호 찾기"><br>
                  </div>
                  <div class="checkout__input">
                     <p>
                        주소<span>*</span>
                     </p>
                     <input class="text-dark" type="text" id="sample6_address"
                        placeholder="주소" name="streetAddress"><br>
                     <input class="text-dark" type="text" id="sample6_detailAddress"
                        placeholder="상세주소" name="addressDetail"> <input
                        class="text-dark" type="text" id="sample6_extraAddress"
                        placeholder="배송 메세지" name="vendorMessage">
                  </div>
               </div>
               <div class="col-lg-4 col-md-6">
                  <div class="checkout__order">
                     <h4>주문 내역</h4>
                     <div class="checkout__order__products">
                        상품 목록 <span>금액</span>
                     </div>
                     <span style="display: none" id="totalLength">${fn:length(orderProductList) }</span>
                     <ul>
                        <c:forEach items="${orderProductList }" var="product">
                           <span style="display: none;" id="productName">${product.name }</span>
                           <c:choose>
                              <c:when test="${fn:length(product.name) < 10 }">
                                 <li>${product.name }X${product.count }
                                    <span> <c:set var="price"
                                          value="${(product.price * ((100 - product.discountRate) * 0.01)) * product.count - ((product.price * ((100 - product.discountRate) * 0.01)) * product.count)%10 }" />
                                       <fmt:formatNumber type="number" maxFractionDigits="3"
                                          value="${price}" /> 원
                                 </span>
                                 </li>
                              </c:when>
                              <c:otherwise>
                                 <li>${product.name.substring(0, 10) }...X${product.count }
                                    <span> <c:set var="price"
                                          value="${(product.price * ((100 - product.discountRate) * 0.01)) * product.count - ((product.price * ((100 - product.discountRate) * 0.01)) * product.count)%10 }" />
                                       <fmt:formatNumber type="number" maxFractionDigits="3"
                                          value="${price}" /> 원
                                 </span>
                                 </li>
                              </c:otherwise>
                           </c:choose>
                        </c:forEach>
                     </ul>
                     <div class="checkout__order__total">

                        적립 예정 포인트 
                        <span>
                           <c:set var="price" value="${orderProductList[fn:length(orderProductList) -1].totalPoint }"/>
                           <fmt:formatNumber type="number" maxFractionDigits="3" value="${price}"/>
                             점</span>
                     </div>
                     <div style="border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: rgb(225, 225, 225); padding-bottom: 10px; margin-bottom: 15px;">
                        <div class="checkout__order__total" style="border: none; margin-bottom: -5px">
                        포인트 사용<span><input id="usePoint" name="usePoint" type="text" value=0 style="width: 100px; text-align: right; border: none;" onchange="use_point(this.value, ${orderProductList[fn:length(orderProductList) - 1].totalPrice }, ${logincust.accumulatedPoint })"></span>
                        </div>
                        <div class="text-muted" style="text-align: right; font-size: 8px;">
                           사용 가능 포인트: ${logincust.accumulatedPoint } 점
                        </div>
                        <div style="width: 100%; text-align: right;">
                           <input id="checkbox" type="checkbox" name="usePoint" onclick="use_all_point(${logincust.accumulatedPoint }, ${orderProductList[fn:length(orderProductList) - 1].totalPrice })"><label style="text-align: right; margin-left: 5px;">전액 사용</label>
                        </div>
                     </div>
                     <div class="checkout__order__total">
                  총 결제 금액(원)
                  <span id="totalPrice">
                     ${orderProductList[fn:length(orderProductList) - 1].totalPrice }
                       </span>
               </div>
               <button type="submit" class="site-btn" >주문하기</button>
              <img src="/img/payment-kakao.png" style="margin-top:10px;"  alt="kakao 결제" onClick={requestPay()}>
            </div>
         </div>
      </div></form>
      </div>
   </div>
</section>
<!-- Checkout Section End -->
<script>
$(document).ready(function(){
   document.getElementById('register_form').addEventListener("keydown", evt => {
        if (evt.code === "Enter") 
           evt.preventDefault();
         });   
   
   IMP.init("imp56873007");

     
})

function requestPay() {
   var productName = document.getElementById("productName").innerText;
   var totalPrice = document.getElementById("totalPrice").innerText;
   var sequences = document.getElementById("productSequences").value;
   var orderLength = document.getElementById("totalLength").innerText;
   var receiverName = document.getElementById("custName").value;
   var receiverEmail = document.getElementById("custEmail").value;
   var receiverPhone = document.getElementById("custPhone").value;
   var receiverPostCode = document.getElementById("sample6_postcode").value;
   var receiverAddress = document.getElementById("sample6_address").value;
   var receiverDetailAddress = document.getElementById("sample6_detailAddress").value;
   var receiverMessage = document.getElementById("sample6_extraAddress").value;
   var loginUserSeq = document.getElementById("memberSequence").value;


   var form = document.createElement('form');
   form.setAttribute('method', 'post');
   form.setAttribute('action', '/cart/cartToOrderResult');
   var obj1 = document.createElement('input');
   obj1.setAttribute('name', 'memberSequence');
   obj1.setAttribute('value', loginUserSeq);
   var obj2 = document.createElement('input');
   obj2.setAttribute('name', 'sequences');
   obj2.setAttribute('value', sequences);
   var obj3 = document.createElement('input');
   obj3.setAttribute('name', 'receiverName');
   obj3.setAttribute('value', receiverName);
   var obj4 = document.createElement('input');
   obj4.setAttribute('name', 'orderPhone');
   obj4.setAttribute('value', receiverPhone);
   var obj5 = document.createElement('input');
   obj5.setAttribute('name', 'zipcode');
   obj5.setAttribute('value', receiverPostCode);
   var obj6 = document.createElement('input');
   obj6.setAttribute('name', 'streetAddress');
   obj6.setAttribute('value', receiverAddress);
   var obj7 = document.createElement('input');
   obj7.setAttribute('name', 'addressDetail');
   obj7.setAttribute('value', receiverDetailAddress);
   var obj8 = document.createElement('input');
   obj8.setAttribute('name', 'vendorMessage');
   obj8.setAttribute('value', receiverMessage);
   var obj9 = document.createElement('input');
   obj9.setAttribute('name', 'email');
   obj9.setAttribute('value', receiverEmail);
   var obj10 = document.createElement('input');
   obj10.setAttribute('name', 'usePoint');
   obj10.setAttribute('value', document.getElementById("usePoint").value);

   form.append(obj1, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
   document.body.appendChild(form);

    IMP.request_pay({
        pg : 'kakaopay',
        pay_method : 'card',
        merchant_uid: 'merchant_' + new Date().getTime(), 
        name : orderLength*1 === 1 ? productName : productName + " 외 " + String(Number(orderLength)-1) + "건",
        amount : totalPrice,
        buyer_email : receiverEmail,
        buyer_name : receiverName,
        buyer_tel : receiverPhone,
        buyer_addr : receiverAddress,
        buyer_postcode :receiverPostCode
    }, function (rsp) { // callback
       if (rsp.success) {

          form.submit();
       }

    });
}
</script>
