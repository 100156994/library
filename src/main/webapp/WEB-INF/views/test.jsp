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
    var o1 ={
        "purId": "12",
        "isbn": "121",
        "num": 2,
        "unitprice": 15.2,
        "time": "2015-1-1",
        "buyer": "asd",
        "status":1
    };
    var o2=o1;
    var row = new Array();
    row.push(o1);
    row.push(o2);
    $.ajax({
        url: 'http://localhost:8080/test2',
        type: 'POST',
        data:JSON.stringify(row),
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
    alert(JSON.stringify(row));
    // $.ajax({
    //     url: 'http://localhost:8080/test1',
    //     type: 'POST',
    //     data: JSON.stringify({
    //         "purId": "12",
    //         "isbn": "121",
    //         "num": 2,
    //         "unitprice": 15.2,
    //         "time": new Date(),
    //         "buyer": "asd",
    //         "status":1
    //     }),
    //     contentType: 'application/json',
    //     dataType: "json",
    //     success: function (result) {
    //         alert(result.message);
    //         //$("#myModal").modal("hide");
    //         oTable.fnDraw();
    //     },
    //     error: function (err) {
    //         alert("error");
    //     }
    // });
</script>



</body>
</html>

