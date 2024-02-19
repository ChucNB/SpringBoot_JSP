<%--
  Created by IntelliJ IDEA.
  User: doanh
  Date: 19/01/2024
  Time: 6:33 SA
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-5">
        <jsp:include page="component/danh-sach-san-pham.jsp"/>
    </div>
    <div class="col-7">
        <jsp:include page="component/danh-sach-hoa-don-cho.jsp"/>
        <jsp:include page="component/info.jsp"/>
        <div>

        </div>
    </div>


</div>


