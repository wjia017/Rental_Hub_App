<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Access Denied</title>

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
</head>
<body>

    <div style="height:100vh;
                display:flex;
                justify-content:center;
                align-items:center;
                flex-direction:column;
                font-family:'Plus Jakarta Sans',sans-serif;">

        <i class="fa-solid fa-lock"
           style="font-size:60px;color:#b3261e;margin-bottom:20px;"></i>

        <h1>Access Denied</h1>

        <p>You are not authorized to access this page.</p>

        <a href="${pageContext.request.contextPath}/dashboard"
           style="margin-top:20px;
                  text-decoration:none;
                  background:#7a4b32;
                  color:#fff;
                  padding:12px 20px;
                  border-radius:10px;">
            Back to Dashboard
        </a>

    </div>

</body>
</html>