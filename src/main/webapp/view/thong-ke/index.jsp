<%--
  Created by IntelliJ IDEA.
  User: doanh
  Date: 19/02/2024
  Time: 3:34 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link
            rel="stylesheet"
            href="../contrast-bootstrap-pro/css/bootstrap.min.css"/>
    <link
            rel="stylesheet"
            href="../contrast-bootstrap-pro/css/cdb.css"/>
    <script
            src="../contrast-bootstrap-pro/js/cdb.js"></script>
    <script
            src="../contrast-bootstrap-pro/js/bootstrap.min.js"></script>
    <script
            src="https://kit.fontawesome.com/9d1d9a82d2.js"
            crossorigin="anonymous"></script>

    <title>How to create bootstrap charts using bootstrap 5</title>
</head>
<style>
    .chart-container {
        width: 50%;
        height: 50%;
        margin: auto;
    }
</style>

<body>
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

    </div>
    <div class="card-body">


        <div class="card chart-container">
            <canvas id="chart"></canvas>
        </div>

    </div>
    <div class="card-footer">


    </div>
</div>
</body>

<script
        src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.js">
</script>
<script>
    const ctx = document.getElementById("chart").getContext('2d');
    const myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: ["sunday", "monday", "tuesday",
                "wednesday", "thursday", "friday", "saturday"],
            datasets: [
                {
                    label: 'Tháng ${thangTruoc}',
                    backgroundColor: 'rgba(101, 198, 247, 1)',
                    borderColor: 'rgb(47, 128, 237)',
                    data: [3000, 4000, 2000, 5000, 8000, 9000],
                },
                {
                    label: 'Tháng ${thangNay}',
                    backgroundColor: 'rgba(161, 198, 247, 1)',
                    borderColor: 'rgb(47, 128, 237)',
                    data: [3000, 4000, 2000, 5000, 8000, 9000, 2000],
                }
            ]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true,
                    }
                }]
            }
        },
    });
</script>

</html>
