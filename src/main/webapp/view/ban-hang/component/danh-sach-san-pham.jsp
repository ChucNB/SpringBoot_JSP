<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: doanh
  Date: 19/02/2024
  Time: 10:37 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="card my-3 ms-2">
    <div class="card-header">
        Danh sách sản phẩm
        <div class="float-end">
            <form class="d-flex">
                <input name="findByTenLike" value="${param.get("findByTenLike")}" class="form-control me-2"
                       type="search" placeholder="Tìm theo tên hoặc mã"
                       aria-label="Search">
            </form>
        </div>
    </div>
    <div class="card-body">
        <div style="height:65vh; overflow:auto;" data-bs-spy="scroll" data-bs-target="#list-example"
             data-bs-offset="0" class="scrollspy-example" tabindex="0">
            <div class="table-responsive">
                <table class="table ">
                    <thead>
                    <tr class="table-dark">
                        <th scope="col">Mã sản phẩm</th>
                        <th scope="col">Tên sản phẩm</th>
                        <th scope="col">Số lượng</th>
                        <th scope="col">Thêm</th>
                    </tr>
                    </thead>
                    <tbody>


                    <c:forEach items="${lstSPCTF}" var="item">
                        <tr>
                            <td>${item.maSPCT}</td>
                            <td>${item.sanPham.ten}</td>
                            <td>${item.soLuong}</td>
                            <td>
                                <a class="text-primary" onclick="myFunction(${item.id},${item.soLuong})">Thêm</a>
                            </td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script>
    function myFunction(idSPCT, soLuong) {
        var soLuongThem = prompt("Nhập vào số lượng\r\nSố lượng còn lại " + soLuong, 1);
        while (soLuongThem > soLuong) {
            soLuongThem = prompt("Vượt quá số lượng tối đa\r\nSố lượng còn lại " + soLuong, 1);
        }
        if (!(soLuongThem == null) && soLuongThem <= soLuong) {
            window.location.href = "http://localhost:8080/ban-hang/index/${hd.id}/them-san-pham?idSPCT=" + idSPCT + "&soLuong=" + soLuongThem
        }

    }
</script>


