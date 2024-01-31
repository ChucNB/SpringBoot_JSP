<%--
  Created by IntelliJ IDEA.
  User: doanh
  Date: 15/01/2024
  Time: 11:11 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Dự án bán hàng</title>
</head>
<!-- CDN Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- Link FontAwesome -->
<script src="https://kit.fontawesome.com/e3b1e915d4.js" crossorigin="anonymous"></script>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Dự án bán hàng</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/ban-hang">Bán hàng</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/quan-ly-khach-hang/index">Khách hàng</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/hoa-don/index">Hóa đơn</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/quan-ly-nhan-vien/index">Nhân viên</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Sản phẩm
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/admin/quan-ly-mau-sac/index">Màu sắc</a></li>
                        <li><a class="dropdown-item" href="/admin/quan-ly-kich-thuoc/index">Kích thước</a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="/admin/quan-ly-san-pham/index">Quản lý sản phẩm</a></li>
                    </ul>
                </li>

            </ul>
            <form class="d-flex m-0" role="search">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>
<jsp:include page="${string}.jsp"></jsp:include>
<div class="contain"></div>

</body>
</html>
