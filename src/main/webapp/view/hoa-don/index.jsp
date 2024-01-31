<%--
  Created by IntelliJ IDEA.
  User: doanh
  Date: 12/01/2024
  Time: 2:38 CH
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-fluid">

    <div class="card m-5">
        <div class="card-header">
            Danh sách hóa đơn
            <a href="create" class="btn btn-primary float-end">Thêm mới</a>
        </div>
        <div class="card-body">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">ID</th>
                    <th scope="col">Nhân viên</th>
                    <th scope="col">Khách hàng</th>
                    <th scope="col">Ngày mua hàng</th>
                    <th scope="col">Trạng thái</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${lst}" var="item" varStatus="index">
                    <tr>
                        <th scope="row">${index.count}</th>
                        <td>${item.id}</td>
                        <td>${item.nhanVien.maNV}</td>
                        <td>${item.khachHang.maKH}</td>
                        <td>${item.ngayMuaHang}</td>
                        <td>${item.trangThai==true?"Đã thanh toán":"Chưa thanh toán"}</td>
                        

                    </tr>

                </c:forEach>

                </tbody>
            </table>

        </div>
    </div>

</div>


