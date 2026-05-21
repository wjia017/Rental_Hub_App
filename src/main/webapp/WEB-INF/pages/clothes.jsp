<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">

<title>Manage Clothes | Rental Hub</title>

<meta name="viewport"
      content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_clothes.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">


</head>

<body>

<div class="layout">

    <!-- SIDEBAR -->
    <aside class="sidebar">

        <div class="logo">

            <i class="fa-solid fa-shirt"></i>

            <div>
                <h2>Rental Hub</h2>
                <p>Admin Portal</p>
            </div>

        </div>

        <nav>

            <a href="${pageContext.request.contextPath}/admin-dashboard">
                <i class="fa-solid fa-house"></i>
                Dashboard
            </a>

            <a href="${pageContext.request.contextPath}/manage-users">
                <i class="fa-solid fa-users"></i>
                Users
            </a>

            <a href="${pageContext.request.contextPath}/clothes"
               class="active">
                <i class="fa-solid fa-shirt"></i>
                Clothes
            </a>

            <a href="${pageContext.request.contextPath}/admin-rentals">
                <i class="fa-solid fa-calendar-check"></i>
                Rentals
            </a>

            <a href="${pageContext.request.contextPath}/logout">
                <i class="fa-solid fa-right-from-bracket"></i>
                Logout
            </a>

        </nav>

    </aside>

    <!-- MAIN -->
    <main class="content">

        <!-- TOPBAR -->
        <header class="topbar">

            <form action="${pageContext.request.contextPath}/clothes"
      method="get"
      class="search-box">

    <i class="fa-solid fa-magnifying-glass"></i>

    <input type="text"
           name="search"
           value="${search}"
           placeholder="Search clothes...">

</form>

            <div class="user-info">

                <i class="fa-regular fa-user"></i>

                <div>
                    <strong>
                        ${loggedUser.firstName}
                        ${loggedUser.lastName}
                    </strong>

                    <p>Administrator</p>
                </div>

            </div>

        </header>

        <!-- PAGE TITLE -->
        <section class="page-title">

            <h1>Manage Clothes</h1>

            <p>
                Add, edit or remove clothing items
            </p>

        </section>

        <!-- TABLE -->
        <section class="table-section">

            <div class="table-header">

                <form action="${pageContext.request.contextPath}/clothes"
      method="get"
      class="table-search">

    <i class="fa-solid fa-magnifying-glass"></i>

    <input type="text"
           name="search"
           value="${search}"
           placeholder="Search clothes...">

</form>

                <a href="${pageContext.request.contextPath}/add-cloth"
                   class="add-btn">

                    <i class="fa-solid fa-plus"></i>
                    Add New Item

                </a>

            </div>

            <div class="table-card">

                <table>

                    <thead>

                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Category</th>
                            <th>Size</th>
                            <th>Price/Day</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>

                    </thead>

                    <tbody>

                        <c:forEach var="cloth"
                                   items="${clothes}">

                            <tr>

                                <td>${cloth.clothId}</td>

                                <td>
                                    ${cloth.clothName}
                                </td>

                                <td>
                                    ${cloth.category}
                                </td>

                                <td>
                                    ${cloth.size}
                                </td>

                                <td>
                                    Rs. ${cloth.rentalPrice}
                                </td>

                                <td>

                                    <c:choose>

                                        <c:when test="${cloth.status == 'available'}">

                                            <span class="status available">
                                                Available
                                            </span>

                                        </c:when>

                                        <c:otherwise>

                                            <span class="status rented">
                                                ${cloth.status}
                                            </span>

                                        </c:otherwise>

                                    </c:choose>

                                </td>

                                <td class="actions">

                                    <a href="${pageContext.request.contextPath}/edit-cloth?id=${cloth.clothId}"
                                       class="edit-btn">

                                        <i class="fa-solid fa-pen"></i>

                                    </a>

                                    <a href="${pageContext.request.contextPath}/delete-cloth?id=${cloth.clothId}"
                                       class="delete-btn">

                                        <i class="fa-solid fa-trash"></i>

                                    </a>

                                </td>

                            </tr>

                        </c:forEach>

                    </tbody>

                </table>

            </div>

        </section>

    </main>

</div>

</body>
</html>