<%--
  Created by IntelliJ IDEA.
  User: bangyu
  Date: 2019/4/17
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/static/LigerUI/Source/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
</head>
<body>


<script>
    $.ajax({
        url: 'http://localhost:8080/test',
        type: 'POST',
        data:JSON.stringify({username:"123",password:"123"}),
        contentType: 'application/json',
        dataType: "json",
        success: function (result) {
            alert(result.message);
            //$("#myModal").modal("hide");
            //oTable.fnDraw();
        },
        error: function (err) {
            alert("error");
        }
    });
    $.ajax({
        url: 'http://localhost:8080/test1',
        type: 'POST',
        data: JSON.stringify({
            "purId": "12",
            "isbn": "121",
            "num": 2,
            "unitprice": 15.2,
            "time": new Date(),
            "buyer": "asd",
            "status":1
        }),
        contentType: 'application/json',
        dataType: "json",
        success: function (result) {
            alert(result.message);
            //$("#myModal").modal("hide");
            oTable.fnDraw();
        },
        error: function (err) {
            alert("error");
        }
    });
</script>



</body>
</html>
