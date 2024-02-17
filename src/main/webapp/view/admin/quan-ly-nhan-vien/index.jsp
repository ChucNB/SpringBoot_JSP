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
<%@taglib prefix="fn" uri="jakarta.tags.functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="container-fluid">

    <div class="card m-2">
        <div class="card-header">
            <p>Quản lý màu sắc</p>
            <div class="row g-3 float-end">
                <div class="col-auto">
                    <label for="status" class="col-form-label-sm">Trạng thái</label>
                </div>
                <div class="col-auto">
                    <form modelAttribute="filter">
                        <select class="form-select-sm" id="status" aria-label="Default select example"
                                onchange="this.form.submit()" name="active">
                            <option ${param.get("active")=="-1"?"selected":""} value="-1">Tất cả</option>
                            <option ${param.get("active")=="1"?"selected":""} value="1">Hoạt động</option>
                            <option ${param.get("active")=="0"?"selected":""} value="0">Ngưng hoạt động</option>
                        </select>
                    </form>
                </div>


            </div>
            <a href="create" class="btn btn-primary btn-sm col-auto">Thêm mới</a>
        </div>
        <div class="card-body">


            <div class="col-8">
                <div data-bs-spy="scroll" data-bs-target="#navbar-example3" data-bs-smooth-scroll="true"
                     class="scrollspy-example-2" tabindex="0">

                </div>
            </div>
            ${requestScope.getR}
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>


                    <th scope="col">
                        <a href="${JSPHelper.initParam(param,"sortBy=id")}"
                           class="link-${param.containsValue("id")?"primary":"dark"} link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
                            ID <c:if test='${param.containsValue("id")}'><i
                                class="fa-solid fa-sort-${param.containsValue("DESC")?"up":"down"}"></i></c:if>
                        </a>
                    </th>
                    <th scope="col">
                        <a href="${JSPHelper.initParam(param,"sortBy=ma")}"
                           class="link-${param.containsValue("ma")?"primary":"dark"} link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
                            Mã màu sắc <c:if test='${param.containsValue("ma")}'><i
                                class="fa-solid fa-sort-${param.containsValue("DESC")?"up":"down"}"></i></c:if>
                        </a>
                    </th>
                    <th scope="col">
                        <a href="${JSPHelper.initParam(param,"sortBy=ten")}"
                           class="link-${param.containsValue("ten")?"primary":"dark"} link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
                            Tên màu sắc <c:if test='${param.containsValue("ten")}'><i
                                class="fa-solid fa-sort-${param.containsValue("DESC")?"up":"down"}"></i></c:if>
                        </a>
                    </th>
                    <th scope="col">
                        <a href="${JSPHelper.initParam(param,"sortBy=tenDangNhap")}"
                           class="link-${param.containsValue("tenDangNhap")?"primary":"dark"} link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
                            Tên đăng nhập <c:if test='${param.containsValue("tenDangNhap")}'><i
                                class="fa-solid fa-sort-${param.containsValue("DESC")?"up":"down"}"></i></c:if>
                        </a>
                    </th>

                    <th scope="col">Mật khẩu</th>
                    <th scope="col">Trạng thái</th>
                    <th scope="col">Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${page.content}" var="item" varStatus="index">
                    <tr>
                        <th scope="row">${index.count}</th>
                        <td>${item.id}</td>
                        <td>${item.ma}</td>
                        <td>${item.ten}</td>
                        <td>${item.tenDangNhap}</td>
                        <td>${fn:substring("***********************************", 0, fn:length(item.matKhau))}</td>
                        <td>${item.trangThai==1?"Hoạt động":"Ngưng hoạt động"}</td>
                        <td><a href="edit/${item.id}" class="btn btn-success">Chỉnh sửa</a>
                            <a href="delete/${item.id}" class="btn btn-danger">Xóa</a>
                        </td>

                    </tr>

                </c:forEach>

                </tbody>
            </table>

        </div>
        <div class="card-footer">

            <nav aria-label="Page navigation example">

                <ul class="pagination pt-2 justify-content-end">
                    <div class="m-2">
                        Trang ${page.number+1}/${page.totalPages}
                    </div>
                    <li class="page-item ${(page.number)>0?'':'disabled'}">
                        <a class="page-link "
                           href="${JSPHelper.initParam(param,"")}&page=${page.number-1}">Privious</a>
                    </li>
                    <li class="page-item ${page.number-1<0?'d-none':''}"><a class="page-link"
                                                                            href="${JSPHelper.initParam(param,"")}&page=${page.number-1}">${page.number}</a>
                    </li>
                    <li class="page-item active"><a class="page-link"
                                                    href="${JSPHelper.initParam(param,"")}&page=${page.number}">${page.number+1}</a>
                    </li>

                    <li class="page-item ${(page.number)+1<page.totalPages?'':'d-none'}"><a class="page-link"
                                                                                            href="${JSPHelper.initParam(param,"")}&page=${page.number+1}">${page.number+2}</a>
                    </li>

                    <li class="page-item ${(page.number)+1<page.totalPages?'':'disabled'}">
                        <a class="page-link "
                           href="${JSPHelper.initParam(param,"")}&page=${page.number+1}">Next</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>

</div>

