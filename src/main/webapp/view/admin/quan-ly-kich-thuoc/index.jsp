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
            Quản lý kích thước
            <a href="create" class="btn btn-primary float-end">Thêm mới</a>
        </div>
        <div class="card-body">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">ID</th>
                    <th scope="col">Mã kích thước</th>
                    <th scope="col">Tên kích thước</th>
                    <th scope="col">Trạng thái</th>
                    <th scope="col">Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${lst}" var ="item" varStatus="index">
                    <tr>
                        <th scope="row">${index.count}</th>
                        <td>${item.id}</td>
                        <td>${item.ma}</td>
                        <td>${item.ten}</td>
                        <td>${item.trangThai==true?"Hết hàng":"Còn hàng"}</td>
                        <td><a href="edit/${item.id}" class="btn btn-success">Chỉnh sửa</a>
                            <a href="delete/${item.id}" class="btn btn-danger">Xóa</a>
                        </td>

                    </tr>

                </c:forEach>

                </tbody>
            </table>

        </div>
    </div>

</div>


