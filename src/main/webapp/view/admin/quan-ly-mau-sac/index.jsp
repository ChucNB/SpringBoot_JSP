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
            Quản lý màu sắc
            <a href="create" class="btn btn-primary float-end">Thêm mới</a>
        </div>
        <div class="card-body">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">ID</th>
                    <th scope="col">Mã màu sắc</th>
                    <th scope="col">Tên màu sắc</th>
                    <th scope="col">Trạng thái</th>
                    <th scope="col">Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageData.content}" var="item" varStatus="index">
                    <tr>
                        <th scope="row">${index.count}</th>
                        <td>${item.id}</td>
                        <td>${item.ma}</td>
                        <td>${item.ten}</td>
                        <td>${item.trangThai==1?"Hết hàng":"Còn hàng"}</td>
                        <td><a href="edit/${item.id}" class="btn btn-success">Chỉnh sửa</a>
                            <a href="delete/${item.id}" class="btn btn-danger">Xóa</a>
                        </td>

                    </tr>

                </c:forEach>

                </tbody>
            </table>
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <li class="page-item disabled">
                        <a class="page-link">Previous</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#">${pageData.number+1}</a></li>
                    <li class="page-item"><a class="page-link" href="#">${pageData.number}</a></li>
                    <li class="page-item"><a class="page-link" href="#">${pageData.number}</a></li>
                    <li class="page-item">
                        <a class="page-link" href="#">Next</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>

</div>


