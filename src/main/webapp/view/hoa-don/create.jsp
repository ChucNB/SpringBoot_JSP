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
            Thêm hóa đơn
        </div>
        <div class="card-body row">
            <div class="col-8 m-auto">
                <sf:form modelAttribute="obj" action="/hoa-don/store"
                         method="post">
                    <div class="mt-3">
                        <label class="form-label">ID</label>
                        <sf:input class="form-control" path="id"/>
                        <sf:errors class="form-text text-danger" path="id"/>
                    </div>

                    <div class="mt-3">
                        <label class="form-label">Nhân viên</label>

                        <sf:select path="nhanVien.id" cssClass="form-select">
                            <sf:options items="${lstNV}" itemLabel="maNV" itemValue="id"></sf:options>
                        </sf:select>
                        <sf:errors class="form-text text-danger" path=""/>
                    </div>
                    <div class="mt-3">
                        <label class="form-label">Khách hàng</label>

                        <sf:select path="khachHang.id" cssClass="form-select">

                            <option value="-1">Chọn khách hàng ---</option>
                            <sf:options items="${lstKH}" itemLabel="maKH" itemValue="id"></sf:options>
                        </sf:select>
                        <sf:errors class="form-text text-danger" path="khachHang"/>
                    </div>


                    <div class="mt-3">
                        <sf:checkbox class="form-check-input" path="trangThai"/>
                        <label class="form-check-label">Thanh toán</label>
                    </div>

                    <div class="mt-3 text-end">
                        <sf:button class="btn btn-success">Thêm mới</sf:button>
                    </div>
                </sf:form>
            </div>
        </div>
    </div>

</div>