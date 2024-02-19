<%@page import="com.poly.assignment1.helper.JSPHelper" %>
<%--
  Created by IntelliJ IDEA.
  User: doanh
  Date: 12/01/2024
  Time: 2:38 CH
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="container-fluid">

    <div class="card m-2">
        <div class="card-header">
            Chi tiết hóa đơn "${hd.id}"<br>


        </div>
        <div class="card-body">


            <div class="col-8">
                <div data-bs-spy="scroll" data-bs-target="#navbar-example3" data-bs-smooth-scroll="true"
                     class="scrollspy-example-2" tabindex="0">

                </div>
            </div>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">ID</th>
                    <th scope="col">Sản phẩm</th>
                    <th scope="col">Số lượng</th>
                    <th scope="col">Đơn giá</th>
                    <th scope="col">Thành tiền</th>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${lstHDCT}" var="item" varStatus="index">
                    <tr>
                        <th scope="row">${index.count}</th>
                        <td>${item.id}</td>
                        <td>${item.sanPhamChiTiet.maSPCT}</td>
                        <td>${item.soLuong}</td>
                        <td><fmt:formatNumber value="${item.donGia}" pattern="###,###,###"/> VND</td>

                        <td><fmt:formatNumber value="${item.donGia*item.soLuong}" pattern="###,###,###"/> VND</td>
                    </tr>

                </c:forEach>
                <tr class="table-dark">
                    <td colspan="4">Ngày tạo: ${hd.ngayMuaHang}</td>
                    <td colspan="2">Tổng thanh toán: <fmt:formatNumber value="${hd.tongHoaDon}"
                                                                       pattern="###,###,###"/>
                        VND
                    </td>
                </tr>

                </tbody>
            </table>

        </div>
        <div class="card-footer">

            <div class="row g-3 float-end ">
                <a href="/ban-hang/${hd.id}/create"
                   class="my-3 btn btn-primary btn-sm col-auto ${!(hd.trangThai==0)?"d-none":""}">Chỉnh sửa</a>

            </div>
        </div>
    </div>

</div>

