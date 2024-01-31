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
            Chỉnh sửa màu sắc
            <a href="/admin/quan-ly-mau-sac/index" class="btn btn-primary float-end"><< Quay lại</a>
        </div>
        <div class="card-body row">
            <div class="col-8 m-auto">

                <sf:form modelAttribute="obj" action="/admin/quan-ly-mau-sac/update/${obj.id}" method="post">
                    <div class="mt-3">
                        <label class="form-label">ID</label>
                        <sf:input class="form-control" path="id"/>
                        <sf:errors class="form-text text-danger" path="id"/>
                    </div>

                    <div class="mt-3">
                        <label class="form-label">Mã màu sắc</label>
                        <sf:input class="form-control" path="ma"/>
                        <sf:errors class="form-text text-danger" path="ma"/>
                    </div>

                    <div class="mt-3">
                        <label class="form-label">Tên màu sắc</label>
                        <sf:input class="form-control" path="ten"/>
                        <sf:errors class="form-text text-danger" path="ten"/>
                    </div>

                    <div class="mt-3">
                        <label class="form-label">Trạng thái</label>
                        <sf:select path="trangThai">
                            <sf:option value="null">---Chọn---</sf:option>
                            <sf:option value="1">Còn hàng</sf:option>
                            <sf:option value="0">Hết hàng</sf:option>
                            <sf:option value="-1">Ngưng bán</sf:option>
                        </sf:select>>
                        <sf:errors class="form-text text-danger" path="trangThai"/>
                    </div>

                    <div class="mt-3 text-end">
                        <sf:button class="btn btn-success">Cập nhật</sf:button>
                    </div>
                </sf:form>
            </div>
        </div>
    </div>

</div>