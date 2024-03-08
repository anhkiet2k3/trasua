 <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
    <div class="main">
        <article>
            <div class="box_video"><span><div id="video_pattern"></div></span><video id="video_background" controls="" autoplay="" name="media" muted><source src="https://wujiateavn.com/images/layout/video.mp4"  type="video/mp4"></video></div>
            <div class="box_about">
                <section>
                    <div class="about_left">
                        <figure class="about_img_1 ">
                            <div class="about_cr">
                                <figure><img src="https://wujiateavn.com/images/icon/icon_1.jpg" alt="Hồng Trà YO"></figure>
                                <h3>Trà Xanh Latter</h3>
                            </div>
                        </figure>
                        <figure class="about_img_2 ">
                            <div class="about_cr">
                                <figure><img src="https://wujiateavn.com/images/icon/icon_2.jpg" alt="Hồng Trà YO"></figure>
                                <h3>Latte sương sáo</h3>
                            </div>
                        </figure>
                    </div>
                    <div class="about_center">
                        <div class="about_text">
                            <div class="about_title">
                                <figure class="yo">YO</figure><label>Chúng tôi là ai</label><span>Kế thừa hương vị trà truyền thống</span></div>
                            <div class="about_content">
                                <aside>
                                    <p style="margin-left:0px;">Vào năm 2019, nhằm truyền bá hương vị thơm ngon của trà Đài Loan, Hồng Trà YO Đài Loan đã có mặt tại khu vực miền Nam Việt Nam. Vì là loại trà thơm ngon, tự nhiên và tốt cho sức khỏe nên Hồng Trà YO rất được ưa chuộng
                                        và nhanh chóng nổi tiếng. Đến nay đã có gần 20 cửa hàng trên cả nước , trở thành đơn vị đi đầu trong việc kế thừa hương vị trà truyền thống.</p>
                                    <p style="margin-left:0px;">Hồng Trà YO không chỉ mở cửa hàng trực tiếp mà còn mở nhượng quyền kinh doanh, thay thế hình thức cửa hàng bằng hình thức xe đẩy trà bán hàng , giảm đáng kể chi phí hoạt động và sử dụng kết cấu trà đặc trưng chuyên
                                        nghiệp để tạo sự khác biệt cho thị trường...</p>
                                </aside>
                            </div>
                        </div>
                        <a href="/gioiThieu"><div class="more" style="margin-top: 40px"><span>Xem thêm</span></div></a>
                    </div>
                    <div class="about_right">
                        <figure class="about_img_3 ">
                            <div class="about_cr">
                                <figure> <img src="https://wujiateavn.com/images/icon/icon_3.jpg" alt="Hồng Trà YO"></figure>
                                <h3>Trà sữa đài loan</h3>
                            </div>
                        </figure>
                        <figure class="about_img_4 ">
                            <div class="about_cr">
                                <figure> <img src="https://wujiateavn.com/images/icon/icon_4.jpg" alt="Hồng Trà YO"></figure>
                                <h3>Trà Sữa Trân Châu Đường Đen </h3>
                            </div>
                        </figure>
                    </div>
                </section>
            </div>
            <div class=list_pro>
                <div class="list_icon_1">
                    <figure></figure>
                </div>
                <div class="box_section">
                    <div class="box_title">
                        <figure class="yo">YO</figure><label>MENU TOP 8 NEW</label></div>
                    <div class="slider_list">
                    	<c:forEach var="item" items="${items}" begin="0" end="7" step="1">
                        <div class="item_slider">
                             <a class="addCart" data-id="42" href="javascript:void(0);" title="${item.productName}">
                                  <figure><img src="/images/products/${item.productID}.jpg" alt="${item.productName}"></figure>
                                  <h3>${item.productName}</h3>
                                  <div class="pro_add"><span><fmt:formatNumber type="number" maxFractionDigits="0" value="${item.price}"/> VND</span><a href="detail/${item.productID}" class ="btn btn-primary ml-3">Chi tiết</a></div>
                              </a>
                        </div>
                      	</c:forEach>
                    </div>
                    <a href="/category/all" title="Xem thêm menu" class="more"><span>Xem thêm</span></a>
                <div class="list_icon_2">
                    <figure></figure>
                </div>
            </div>
            <div class="box_tuvan">
                <section>
                    <div class="box_title" style="margin-top: 30px">
                        <figure class="yo">YO</figure><code>Tư vấn</code><label>Tư vấn nhượng quyền</label></div>
                    <div class="div_tuvan">
                        <aside>Các chiến lược tiếp thị và kinh doanh của công ty nhấn mạnh nhất đến khái niệm “khách hàng” và định vị tâm lý của người tiêu dùng đối với chuỗi trà bong bóng Đài Loan dựa trên mức độ “sự hài lòng và giá trị mới lạ”, nhằm hướng
                            tới tương lai của công ty ở Trung Quốc Đại Trung Quốc. hoạt động, nó sẽ phát triển theo hướng tinh tế và nguyên bản hơn. </aside>
                    </div><a href="" title="Liên hệ nhượng quyền" class="more" style="margin-top: 40px"><label>Liên hệ nhượng quyền</label></a></section>
            </div>
     	</article>
     </div>
       