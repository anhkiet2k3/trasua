<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<div class="main">
        <article>
            <!-- GOOGLE BREADCRUMB STRUCTURED DATA -->

            <div class="div_banners"><img src="https://wujiateavn.com/images/banners.jpg" alt="Hồng Trà YO"></div>
            <div class="box_pages" style="background: #ffffff;padding: 50px 0px">
                <section>
                    <div class="pro_left">
                        <div class="pro_cate">
                            <ul>
                            	<li><a href="/category/all">TẤT CẢ</a></li>
                            	<c:forEach var="cate" items="${cates}">
                                <li><a href="/category/${cate.categoryID}">${cate.categoryName}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="pro_right">
                        <div class="pro_list">
                        	<c:forEach var="item" items="${items}">
                        	
	                            <div class="item">
	                                <a class="addCart" data-id="42" href="javascript:void(0);" title="${item.productName}">
	                                    <figure><img src="/images/products/${item.productID}.jpg" alt="${item.productName}"></figure>
	                                    <h3>${item.productName}</h3>
	                                    <div class="pro_add"><span>
	                                    <fmt:formatNumber value="${item.price}"  type="number" maxFractionDigits="0"/> VND</span><a href="detail/${item.productID}" class ="btn btn-primary"> Chi tiết</a>
	                                    </div>
	                                </a>
	                            </div> 
                            
                            </c:forEach>
                        </div>
                    </div>
                </section>
            </div>
        </article>
    </div>