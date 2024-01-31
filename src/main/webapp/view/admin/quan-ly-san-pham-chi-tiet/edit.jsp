<%--
  Created by IntelliJ IDEA.
  User: doanh
  Date: 12/01/2024
  Time: 2:08 CH
  To change this template use File | Settings | File Templates.
--%>

<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-fluid">

    <div class="card col-4 m-auto mt-5">
        <div class="card-header">
            Chỉnh sửa chi tiết sản phẩm "${obj.sanPham.ten}"
            <a href="/admin/quan-ly-san-pham-chi-tiet/index" class="btn btn-primary float-end"><< Quay lại</a>
        </div>
        <div class="card-body row">
            <div class="col-8 m-auto">

                <sf:form modelAttribute="obj" action="/admin/quan-ly-san-pham-chi-tiet/${obj.sanPham.id}/update/${obj.id}" method="post">
                    <div class="mt-3">
                        <label class="form-label">ID</label>
                        <sf:input class="form-control disabled" path="id"/>
                        <sf:errors class="form-text text-danger" path="id"/>
                    </div>


                    <div class="mt-3">
                        <label class="form-label">Mã sản phẩm chi tiết</label>
                        <sf:input class="form-control" path="maSPCT"/>
                        <sf:errors class="form-text text-danger" path="maSPCT"/>
                    </div>
                    <div class="mt-3">
                        <label class="form-label">Kích thước</label>

                        <sf:select path="kichThuoc.id" cssClass="form-select">

                            <sf:options items="${lstKT}" itemLabel="ten" itemValue="id"></sf:options>
                        </sf:select>
                        <sf:errors class="form-text text-danger" path=""/>
                    </div>

                    <div class="mt-3">
                        <label class="form-label">Kích thước</label>

                        <sf:select path="mauSac.id" cssClass="form-select">

                            <sf:options items="${lstMS}" itemLabel="ten" itemValue="id"></sf:options>
                        </sf:select>
                        <sf:errors class="form-text text-danger" path=""/>
                    </div>
                    <div class="mt-3">
                        <label class="form-label">Số lượng</label>
                        <sf:input class="form-control" path="soLuong"/>
                        <sf:errors class="form-text text-danger" path="soLuong"/>
                    </div>
                    <div class="mt-3">
                        <label class="form-label">Đơn giá</label>
                        <sf:input class="form-control" path="donGia"/>
                        <sf:errors class="form-text text-danger" path="donGia"/>
                    </div>
                    <div class="mt-3">
                        <sf:checkbox class="form-check-input" path="trangThai"/>
                        <label class="form-check-label">Còn hàng</label>
                    </div>

                    <div class="mt-3 text-end">
                        <sf:button class="btn btn-success">Chỉnh sửa</sf:button>
                    </div>
                </sf:form>
            </div>
        </div>
    </div>

</div>