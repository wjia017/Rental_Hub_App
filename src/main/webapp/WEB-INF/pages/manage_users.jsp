<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Users | Rental Hub</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
</head>
<body>

<div class="layout">

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
                <i class="fa-solid fa-house"></i> Dashboard
            </a>

            <a href="${pageContext.request.contextPath}/manage-users" class="active">
                <i class="fa-solid fa-users"></i> Users
            </a>

            <a href="${pageContext.request.contextPath}/clothes">
                <i class="fa-solid fa-shirt"></i> Clothes
            </a>

            <a href="${pageContext.request.contextPath}/admin-rentals">
                <i class="fa-solid fa-square-check"></i> Rentals
            </a>

            <a href="${pageContext.request.contextPath}/logout">
                <i class="fa-solid fa-right-from-bracket"></i> Logout
            </a>
        </nav>
    </aside>

    <main class="content">

        <header class="topbar">
            <form action="${pageContext.request.contextPath}/manage-users" method="get" class="search-box">
    <i class="fa-solid fa-magnifying-glass"></i>
    <input type="text" name="search" value="${search}" placeholder="Search users...">
</form>

            <div class="user-info">
                <i class="fa-regular fa-user"></i>
                <div>
                    <strong>${loggedUser.firstName} ${loggedUser.lastName}</strong>
                    <p>Administrator</p>
                </div>
            </div>
        </header>

        <section class="page-title">
            <h1>Manage Users</h1>
            <p>View customers, change roles and update account status.</p>
        </section>

        <section class="table-card">
            <table>
                <thead>
                    <tr>
                        <th>User</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Role</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>${user.firstName} ${user.lastName}</td>
                            <td>${user.username}</td>
                            <td>${user.email}</td>
                            <td>${user.phone}</td>
                            <td><span class="badge">${user.role}</span></td>
                            <td><span class="status">${user.status}</span></td>
                            <td class="action-area">

                                <a class="action edit"
                                   href="${pageContext.request.contextPath}/update-user-role?id=${user.userId}&role=admin">
                                    Admin
                                </a>

                                <a class="action view"
                                   href="${pageContext.request.contextPath}/update-user-role?id=${user.userId}&role=customer">
                                    User
                                </a>

                                <a class="action edit"
                                   href="${pageContext.request.contextPath}/update-user-status?id=${user.userId}&status=active">
                                    Active
                                </a>

                                <a class="action delete"
                                   href="${pageContext.request.contextPath}/update-user-status?id=${user.userId}&status=inactive">
                                    Block
                                </a>

                                <a class="action delete"
                                   href="${pageContext.request.contextPath}/delete-user?id=${user.userId}">
                                    Delete
                                </a>

                            </td>
                        </tr>
                    </c:forEach>

                    <c:if test="${empty users}">
                        <tr>
                            <td colspan="7" class="empty">No users found.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </section>

    </main>

</div>

</body>
</html>