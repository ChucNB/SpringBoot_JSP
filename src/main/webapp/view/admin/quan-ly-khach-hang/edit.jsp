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
            Chỉnh sửa
            <a href="/admin/quan-ly-khach-hang/index" class="btn btn-primary float-end"><< Quay lại</a>
        </div>
        <div class="card-body row">
            <div class="col-8 m-auto">

                <sf:form modelAttribute="obj"  action="/admin/quan-ly-khach-hang/update/${obj.id}" method="post">
                    <div class="mt-3">
                        <label class="form-label">ID</label>
                        <sf:input class="form-control disabled" path="id"/>
                        <sf:errors class="form-text text-danger" path="id"/>
                    </div>

                    <div class="mt-3">
                        <label class="form-label">Họ và tên</label>
                        <sf:input class="form-control" path="ten"/>
                        <sf:errors class="form-text text-danger" path="ten"/>
                    </div>

                    <div class="mt-3">
                        <label class="form-label">Số điện thoại</label>
                        <sf:input class="form-control" path="sdt"/>
                        <sf:errors class="form-text text-danger" path="sdt"/>
                    </div>

                    <div class="mt-3">
                        <label class="form-label">Mã khách hàng</label>
                        <sf:input class="form-control" path="maKH"/>
                        <sf:errors class="form-text text-danger" path="maKH"/>
                    </div>

                    <div class="mt-3">
                        <sf:checkbox  class="form-check-input" path="trangThai"/>
                        <label class="form-check-label">Hoạt động</label>
                    </div>

                    <div class="mt-3 text-end">
                        <sf:button  class="btn btn-success" >Cập nhật</sf:button>
                    </div>
                </sf:form>
            </div>
        </div>
    </div>

</div>