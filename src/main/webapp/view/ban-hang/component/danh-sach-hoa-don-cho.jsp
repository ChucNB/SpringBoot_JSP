<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: doanh
  Date: 19/02/2024
  Time: 10:36 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="card my-3 me-2">

    <div class="card-body">
        <ul class="nav nav-tabs" id="myTab" role="tablist">
            <c:forEach items="${lstHDC}" var="item">
                <li class="nav-item" role="presentation">
                    <div class="nav-link ${hd.id==item.id?"active":""}">
                        <a href="/ban-hang/index/${item.id}" style="text-decoration: none"
                           data-bs-target="#home" type="button" role="tab" aria-controls="home"
                           aria-selected="true">Hóa Đơn ${item.id}
                        </a>
                        <a href="/ban-hang/delete-hoa-don/${item.id}" class="ms-3" style="text-decoration: none;">x</a>
                    </div>


                </li>

            </c:forEach>
            <li class="nav-item " role="presentation">
                <a type="button" class="btn text-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                    Thêm +
                </a>

                <!-- Modal -->
                <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false"
                     tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="staticBackdropLabel">Thêm hóa đơn mới</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <form action="/ban-hang/them-hoa-don/store"
                                  method="post">
                                <div class="modal-body">
                                    <div class="card-body row">
                                        <div class="col-8 m-auto">


                                            <div class="mt-3">
                                                <label class="form-label">Khách hàng</label>

                                                <select name="idKH" class="form-select">
                                                    <option value="-1">Chọn</option>
                                                    <c:forEach items="${lstKH}" var="item">
                                                        <option value="${item.id}">${item.ten}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="mt-3">
                                                <label class="form-label">Nhân viên</label>

                                                <select name="idNV" class="form-select">
                                                    <option value="-1">Chọn</option>
                                                    <c:forEach items="${lstNV}" var="item">
                                                        <option value="${item.id}">${item.ten}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>


                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                                    </button>
                                    <button type="submit" class="btn btn-primary">Thêm mới</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </li>

        </ul>
        <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                <div style="max-height:40vh; overflow:auto;">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Mã Sản phẩm</th>
                            <th scope="col">Tên Sản phẩm</th>
                            <th scope="col">Số lượng</th>
                            <th scope="col">Đơn giá</th>
                            <th scope="col">Thao tác</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${hd.hoaDonChiTietsById}" var="item">
                            <tr>
                                <td scope="row">${item.sanPhamChiTiet.maSPCT}</td>
                                <td>${item.sanPhamChiTiet.sanPham.ten}</td>
                                <td>${item.soLuong}</td>
                                <td><fmt:formatNumber pattern="###,###,###" value="${item.sanPhamChiTiet.donGia}"/>
                                    VND
                                </td>
                                <td><a href="/ban-hang/delete-hoa-don-chi-tiet/${item.id}">Xóa</a>
                                    <a class="ms-2"
                                       onclick="themSL(${item.sanPhamChiTiet.id},${item.sanPhamChiTiet.soLuong})"
                                       href="#">Thêm</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>

        </div>
    </div>
</div>
<script>
    function themSL(idSPCT, soLuong) {
        var soLuongThem = prompt("Nhập vào số lượng\r\nSố lượng còn lại " + soLuong, 1);
        while (soLuongThem > soLuong) {
            soLuongThem = prompt("Vượt quá số lượng tối đa\r\nSố lượng còn lại " + soLuong, 1);
        }
        if (!(soLuongThem == null) && soLuongThem <= soLuong) {
            window.location.href = "http://localhost:8080/ban-hang/index/${hd.id}/them-san-pham?idSPCT=" + idSPCT + "&soLuong=" + soLuongThem
        }

    }
</script>