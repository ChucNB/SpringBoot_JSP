<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: doanh
  Date: 19/02/2024
  Time: 11:32 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-5">
        <p>Khách hàng: ${hd.khachHang.ten}</p>
        <p>Nhân viên: ${hd.nhanVien.ten}</p>
        <p>Tổng thanh toán: <fmt:formatNumber value="${hd.tongHoaDon}" pattern="###,###,###"/> VND</p>
        <p>Ngày tạo: <fmt:formatDate value="${hd.ngayMuaHang}"/></p>


    </div>
    <div class="col-7">
        <div class="m-2">
            <div class="mt-5 p-5 float-end">
                <a onclick="thanhToan(${hd.tongHoaDon})" type="button" class="btn btn-primary btn-lg">Thanh
                    toán</a>
            </div>
        </div>
    </div>

</div>
<%--Modal--%>
<script>
    function thanhToan(tongHoaDon) {
        if (tongHoaDon <= 0) {
            alert("Vui lòng chọn thêm sản phẩm !")
        } else {
            location.href = "/ban-hang/index/${hd.id}/thanh-toan"

        }
    }
</script>

