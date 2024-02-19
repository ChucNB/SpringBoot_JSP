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
<%@taglib prefix="fmt" uri="jakarta.tags.fmt" %>


<div class="container-fluid">

    <div class="card m-2">
        <div class="card-header">
            <p>Quản lý sản phẩm</p>
            <div class="row g-3 float-end">
                <div class="col-auto">
                    <label for="status" class="col-form-label-sm">Trạng thái</label>
                </div>
                <div class="col-auto">
                    <form modelAttribute="filter">
                        <select class="form-select-sm" id="status" aria-label="Default select example"
                                onchange="this.form.submit()" name="active">
                            <option ${param.get("active")=="-1"?"selected":""} value="-1">Tất cả</option>
                            <option ${param.get("active")=="1"?"selected":""} value="1">Đã thanh toán</option>
                            <option ${param.get("active")=="0"?"selected":""} value="0">Chưa thanh toán</option>
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
                        <a href="${JSPHelper.initParam(param,"sortBy=khachHangId")}"
                           class="link-${param.containsValue("khachHangId")?"primary":"dark"} link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
                            Mã Khách hàng <c:if test='${param.containsValue("khachHangId")}'><i
                                class="fa-solid fa-sort-${param.containsValue("DESC")?"up":"down"}"></i></c:if>
                        </a>
                    </th>
                    <th scope="col">
                        <a href="${JSPHelper.initParam(param,"sortBy=ngayMuaHang")}"
                           class="link-${param.containsValue("ngayMuaHang")?"primary":"dark"} link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
                            Ngày mua hàng <c:if test='${param.containsValue("ngayMuaHang")}'><i
                                class="fa-solid fa-sort-${param.containsValue("DESC")?"up":"down"}"></i></c:if>
                        </a>
                    </th>
                    <th scope="col">
                        <a href="${JSPHelper.initParam(param,"sortBy=tongHoaDon")}"
                           class="link-${param.containsValue("tongHoaDon")?"primary":"dark"} link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
                            Tổng thanh toán <c:if test='${param.containsValue("tongHoaDon")}'><i
                                class="fa-solid fa-sort-${param.containsValue("DESC")?"up":"down"}"></i></c:if>
                        </a>
                    </th>

                    <th scope="col">Trạng thái</th>
                    <th scope="col">Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${page.content}" var="item" varStatus="index">
                    <tr>
                        <th scope="row">${index.count}</th>
                        <td>${item.id}</td>
                        <td><a target="_blank" href="/admin/quan-ly-khach-hang/edit/${item.khachHang.id}"
                        >${item.khachHang.ma}</a></td>
                        <td><fmt:formatDate value="${item.ngayMuaHang}"
                                            pattern="dd-MM-yyyy HH:mm:ss"></fmt:formatDate></td>
                        <td><fmt:formatNumber pattern="###,###,###" value="${item.tongHoaDon}"/> VND</td>
                        <td>${item.trangThai==0?"Chưa thanh toán":"Đã thanh toán"}</td>
                        <td>
                            <a href="/admin/quan-ly-hoa-don-chi-tiet/${item.id}/index" class="btn btn-success">Xem
                                chi tiết</a>
                            <a href="delete/${item.id}"
                               class="btn btn-danger ${item.trangThai==0?"":"disabled"}">Xóa</a>
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

