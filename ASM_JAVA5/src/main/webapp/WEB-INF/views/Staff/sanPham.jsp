<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<head>
    <style type="text/css">
        .labF {
            color: rgb(255, 255, 255);
            font-weight: bolder;
        }
        
        .card-footer{
        	height: 70px;
        }
        
        .borRa {
            border-radius: 5px;
        }
        
        .f {
            background: rgb(2, 0, 36);
            background: linear-gradient(106deg, rgba(2, 0, 36, 1) 0%, rgba(23, 23, 57, 1) 41%, rgba(0, 212, 255, 1) 100%);
        }
        
    </style>
</head>

<div class="main pt-3 col-12">
<article class="col-12 p-0">
	<c:choose>
		<c:when test="${auth == true}">
	      <div class="card">
	          <div class="card-header bg-light" style="height: 58px;">
	              <div class="d-flex justify-content-between">
	                  <h4 class="m-0 pt-1">Quản Lý Sản Phẩm</h4>
	                  <form action="/product_find" class="m-0 mt-1" style="margin-bottom: 3px;">
		                  <div class="input-group">
							  <input type="text" name="search" class="form-control" placeholder="Tìm Kiếm">
							  <button class="btn btn-secondary mt-0" type="submit"><i class="bi bi-search"></i></button>
							</div>
					  </form>
	                  <h4 class="m-0 pt-1">Page ${list.number+1}</h4>
	              </div>
	
	          </div>
	          <div class="card-body p-0">
	              <div class="row m-0 border">
	                  <div class="col-4 pt-3 pr-3 pl-3 f">
	                      <form:form name="my-form" action="" method="POST" modelAttribute="product">
	                          <div class="form-group">
	                              <label class="labF" for="">ID</label>
	                              <form:input type="text" name="id" path="productID" placeholder="HTBD" class="form-control"/>
	                          </div>
	
	                          <div class="form-group">
	                              <label class="labF" for="">Tên SP</label>
	                              <form:input type="text" path="productName" placeholder="Hồng Trà Bí Đao" class="form-control"/>
	                          </div>
	
							<div class="form-group">
	                            <label class="labF" for="">Mô Tả</label>
	                              <form:input placeholder="Hồng trà YO mix Trà Bí Đao" type="text" path="description" class="form-control"/>
	                          </div>
	                          
	                         <div class="form-group">
	                        	<label class="labF mr-2" for="">Size</label>
	                        		
						        <form:radiobutton value="true" path="size" name="size" id="960CC"/>				        
						        <label class="labF" for="nam">960CC</label>
						      
								<form:radiobutton class="ml-3" value="false" path="size" name="size" id="720CC" />
								<label class="labF" for="nu">720CC</label>
					    	</div>
					    
					    	<div class="form-group">
	                              <label class="labF" for="">Thể Loại</label>
	                              <form:select path="categoryID" class="form-select">
	                              	<c:forEach var="item" items="${theLoai}">
	                              		<form:option value="${item.categoryID}">${item.categoryName}</form:option>
	                              	</c:forEach>
	                              </form:select>
	                          </div>
	                          
	                          <div class="form-group">
	                              <label class="labF" for="">Giá</label>
	                              <form:input path="price" placeholder="18000" type="text" class="form-control"/>
	                          </div>
	
	                          <div class="form-group">
		                          <button formaction="/product/add" class="btn btn-outline-light">Thêm</button>
		                          <button formaction="/product/update" class="btn btn-outline-light">Cập Nhật</button>
		                          <button formaction="/product/delete/${product.productID}" class="btn btn-outline-light">Xóa</button>
		                          <button formaction="/product/reset" class="btn btn-outline-light">Reset</button>
	                          </div>
	                      </form:form>
	                  </div>
	                  <div class="col-8 p-0">
	
	                      <table class="table text-center mb-0 table-border">
	                          <thead class="thead-dark">
	                              <tr>
	                                  <th scope="col">ID</th>
	                                  <th scope="col">Tên SP</th>
	                                  <th class="form-label" scope="col">Mô Tả</th>
	                                  <th scope="col">Size</th>
	                                  <th scope="col">Mã Loại</th>
	                                  <th scope="col">Giá</th>
	                                  <th scope="col">Active</th>
	                              </tr>
	                          </thead>
	                          <tbody>
							<c:forEach var="item" items="${list.content}">
	                               <tr>
	                                   <td>${item.productID}</td>
	                                   <td>${item.productName}</td>
	                                   <td>${item.description}</td>
	                                  	<td>${item.size? '960CC':'720CC'}</td>
	                                  	<td>${item.categoryID}</td>
	                                  	<td><fmt:formatNumber type="number" maxFractionDigits="0" value="${item.price}" /></td>
	                                   <td>
	                                       <a href="/product/edit/${item.productID}" class="btn-info btn"> <i class="bi bi-pencil text-white"></i> </a>
	                                       <a href="/product/delete/${item.productID}" class="btn-danger btn"> <i class="bi bi-trash text-white"></i> </a>
	                                   </td>
	                               </tr>
							</c:forEach>
	                          </tbody>
	                      </table>
	                  </div>
	              </div>
	          </div>
	          <div class="card-footer bg-light p-2">
	              <div class="btn-group col-3 mt-2 mb-3 d-flex m-auto">
					<a href="/sanPham?p=0" class="btn btn-outline-dark">First</a>
					<a href="/sanPham?p=${list.number-1}" class="btn btn-outline-dark">Prev</a>
					<a href="/sanPham?p=${list.number+1}" class="btn btn-outline-dark">Next</a>
					<a href="/sanPham?p=${list.totalPages-1}" class="btn btn-outline-dark">Last</a>
				</div>
	          </div>
	      </div>
		</c:when>	
		<c:otherwise>
			<div class="m-auto">
				<div class="box_title text-center mt-5 mb-5">
	                   <h3>VUI LÒNG ĐĂNG NHẬP ĐÚNG VAI TRÒ ĐỂ XEM </h3>
	                   <h3>DANH SÁCH CÁC SẢN PHẨM!</h3>
	               </div>
             </div>	
		</c:otherwise>
	</c:choose>  
	</article>   
</div>

