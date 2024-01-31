<%--
  Created by IntelliJ IDEA.
  User: doanh
  Date: 19/01/2024
  Time: 6:33 SA
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    .hd {
        margin: 0;
        font-size: 8px;
    }

    .scrollable-container-1 {
        height: 200px;
        overflow: auto;
        padding: 10px;

    }

    .scrollable-container-3 {
        height: 350px;
        overflow: auto;
        padding: 10px;

    }

</style>
<div class="row">
    <div class="col-6">
        <div>
            <div class="d-flex justify-content-between">
                <div>Hóa đơn</div>
                <!-- Button trigger modal -->
                <div>
                    <a href="/ban-hang/them-hoa-don">Thêm</a>
                </div>


            </div>
            <div class="scrollable-container-1">

                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Nhân viên</th>
                        <th>Khách hàng</th>
                        <th>Thời gian</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${lstHD}" var="item" varStatus="index">
                        <tr class="${hoaDon.id==item.id?"table-primary":""}"
                            onclick="location.href='http://localhost:8080/ban-hang/${item.id}'"
                        >
                            <td class="pe-2">${index.count}</td>
                            <td class="pe-3">${item.nhanVien.ten}</td>
                            <td class="pe-3">${item.khachHang.ten==null?"":item.khachHang.ten}</td>
                            <td class="pe-3">${item.ngayMuaHang}</td>
                        </tr>

                    </c:forEach>


                    </tbody>
                </table>
            </div>
        </div>
        <div>
            <div class="d-flex justify-content-between">
                <div>Sản phẩm</div>
            </div>
            <div class="scrollable-container-1">

                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>Tên sản phẩm</th>
                        <th>Mã SPCT</th>
                        <th>Số lượng</th>
                        <th>Đơn giá</th>
                        <th>Thành tiền</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${lstHDCT}" var="item">
                        <tr>
                            <td class="pe-2">${item.sanPhamChiTiet.sanPham.ten}</td>
                            <td class="pe-2">${item.sanPhamChiTiet.maSPCT}</td>
                            <td class="pe-3">${item.soLuong}</td>
                            <td class="pe-3">${item.donGia}</td>
                            <td class="pe-3">${item.soLuong * item.donGia}</td>
                        </tr>

                    </c:forEach>


                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="col-6">
        <div>
            <div class=" d-flex justify-content-between">
                <div>Sản phẩm</div>

            </div>
            <div class="scrollable-container-3">

                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Mã sản phẩm</th>
                        <th>Tên sản phẩm</th>
                        <th>Thao tác</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${lstSP}" var="item" varStatus="index">
                        <tr>
                            <td class="pe-2">${index.count}</td>
                            <td class="pe-3">${item.ma}</td>
                            <td class="pe-3">${item.ten}</td>
                            <td class="pe-3"><a href="add/${item.id}">Thêm</a></td>
                        </tr>

                    </c:forEach>


                    </tbody>
                </table>
            </div>
        </div>
        <div>

        </div>
    </div>
</div>


