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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="container-fluid">

    <div class="card m-2">
        <div class="card-header">
            Quản lý chi tiết sản phẩm "${sp.ten}"<br>
            <a href="create" class="my-3 btn btn-primary btn-sm col-auto">Thêm mới</a>

            <div class="row g-3 float-end">
                <div class="col-auto">
                    <label for="status" class="col-form-label-sm">Trạng thái</label>
                </div>
                <div class="col-auto">
                    <form modelAttribute="filter">
                        <select class="form-select-sm" id="status" aria-label="Default select example"
                                onchange="this.form.submit()" name="active">
                            <option ${param.get("active")=="-1"?"selected":""} value="-1">Tất cả</option>
                            <option ${param.get("active")=="1"?"selected":""} value="1">Còn hàng</option>
                            <option ${param.get("active")=="0"?"selected":""} value="0">Hết hàng</option>
                        </select>
                    </form>
                </div>


            </div>
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
                        <a href="${JSPHelper.initParam(param,"sortBy=maSPCT")}"
                           class="link-${param.containsValue("maSPCT")?"primary":"dark"} link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
                            Mã sản phẩm chi tiết <c:if test='${param.containsValue("maSPCT")}'><i
                                class="fa-solid fa-sort-${param.containsValue("DESC")?"up":"down"}"></i></c:if>
                        </a>
                    </th>
                    <th scope="col">
                        <a href="${JSPHelper.initParam(param,"sortBy=kichThuocTen")}"
                           class="link-${param.containsValue("kichThuocTen")?"primary":"dark"} link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
                            Kích thước <c:if test='${param.containsValue("kichThuocTen")}'><i
                                class="fa-solid fa-sort-${param.containsValue("DESC")?"up":"down"}"></i></c:if>
                        </a>
                    </th>
                    <th scope="col">
                        <a href="${JSPHelper.initParam(param,"sortBy=mauSacTen")}"
                           class="link-${param.containsValue("mauSacTen")?"primary":"dark"} link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
                            Màu sắc <c:if test='${param.containsValue("mauSacTen")}'><i
                                class="fa-solid fa-sort-${param.containsValue("DESC")?"up":"down"}"></i></c:if>
                        </a>
                    </th>

                    <th scope="col">
                        <a href="${JSPHelper.initParam(param,"sortBy=soLuong")}"
                           class="link-${param.containsValue("soLuong")?"primary":"dark"} link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
                            Số lượng <c:if test='${param.containsValue("soLuong")}'><i
                                class="fa-solid fa-sort-${param.containsValue("DESC")?"up":"down"}"></i></c:if>
                        </a>
                    </th>
                    <th scope="col">
                        <a href="${JSPHelper.initParam(param,"sortBy=donGia")}"
                           class="link-${param.containsValue("donGia")?"primary":"dark"} link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
                            Đơn giá <c:if test='${param.containsValue("donGia")}'><i
                                class="fa-solid fa-sort-${param.containsValue("DESC")?"up":"down"}"></i></c:if>
                        </a>
                    </th>

                    <th scope="col">Trạng thái</th>
                    <th scope="col">Thao Tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${page.content}" var="item" varStatus="index">
                    <tr>
                        <th scope="row">${index.count}</th>
                        <td>${item.id}</td>
                        <td>${item.maSPCT}</td>
                        <td>${item.kichThuoc.ten}</td>
                        <td>${item.mauSac.ten}</td>
                        <td>${item.soLuong}</td>
                        <td>${item.donGia}</td>
                        <td>${item.trangThai==1?"Còn hàng":"Hết hàng"}</td>
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

