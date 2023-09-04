<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Header Section Begin -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<header class="header">
    <div class="header__top">
        <div class="container">
            <nav class="header__menu header__top__right mobile-menu"
                 style="padding: 5px 0">
                <ul>
                    <c:choose>
                        <c:when test="${logincust != null }">
                            <li class="active"><a href="main.bit?view=mypage&memberSeq=${logincust.sequence }"><i
                                    class="fa fa-user"></i> 마이페이지</a></li>
                            <li class=""><a href="member.bit?view=logout"><i
                                    class="fa fa-user"></i> 로그아웃</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="active"><a href="/page/signin"><i
                                    class="fa fa-user"></i> 로그인</a></li>
                            <li class=""><a href="${pageContext.request.contextPath}/page/signup"><i
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
                    <a href="${pageContext.request.contextPath}/page/main"><img src="${pageContext.request.contextPath}/img/logo.png" alt=""></a>
                </div>
            </div>
            <div class="col-lg-6">
                <nav class="header__menu">
                    <ul id="header__menus" >
                        <li><a href="${pageContext.request.contextPath}/page/main"  style="font-size: 20px; font-weight: 700;">홈</a></li>
                        <li><a href="category.bit?view=1"  style="font-size: 20px; font-weight: 700;">도서 전체</a></li>
                        <li class="active"><a href="${pageContext.request.contextPath}/page/contact" style="font-size: 20px; font-weight: 700;">고객센터</a></li>
                    </ul>
                </nav>
            </div>
            <c:choose>
                <c:when test="${logincust != null }">
                    <div class="col-lg-3">
                        <div class="header__cart">
                            <ul>
                                <li><a href="main.bit?view=shopping-cart&memberSeq=${logincust.sequence }"><i class="fa fa-shopping-bag"></i> <span>${cartCount }</span></a></li>
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

<script>
    AOS.init({
        easing: 'ease-out-back',
        duration: 1000
    });
</script>

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
<section class="breadcrumb-section set-bg"
         data-setbg="/img/books.jpg">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="breadcrumb__text">
                    <h2>에러페이지</h2>
                    <div class="breadcrumb__option">
                        <a href="index.jsp">Home</a> <span>에러발생</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Breadcrumb Section End -->

<section class="d-flex flex-column justify-content-center align-items-center" style="height: 30vh;">
    <h2 class="mb-3">${errorCode}</h2>
    <h2>${errorMessage}</h2>

</section>

