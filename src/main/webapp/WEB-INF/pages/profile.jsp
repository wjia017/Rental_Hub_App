<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile | Rental Hub</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
</head>
<body>

<div class="layout">

    <aside class="sidebar">
        <div class="logo">
            <i class="fa-solid fa-shirt"></i>
            <div>
                <h2>Rental Hub</h2>
                <p>User Portal</p>
            </div>
        </div>

        <nav>
            <a href="${pageContext.request.contextPath}/user-dashboard">
                <i class="fa-solid fa-house"></i> Home
            </a>

            <a href="${pageContext.request.contextPath}/browse-clothes">
                <i class="fa-solid fa-magnifying-glass"></i> Browse Clothes
            </a>

            <a href="${pageContext.request.contextPath}/my-rentals">
                <i class="fa-solid fa-cube"></i> My Rentals
            </a>

            <a href="${pageContext.request.contextPath}/profile" class="active">
                <i class="fa-solid fa-user"></i> Profile
            </a>

            <a href="${pageContext.request.contextPath}/logout">
                <i class="fa-solid fa-right-from-bracket"></i> Logout
            </a>
        </nav>
    </aside>

    <main class="content">

        <header class="topbar profile-topbar">

            <div class="user-info">
                <c:choose>
                    <c:when test="${not empty loggedUser.profileImage}">
                        <img class="top-profile-img"
                             src="${pageContext.request.contextPath}/get-image?file=${loggedUser.profileImage}"
                             alt="Profile Image">
                    </c:when>

                    <c:otherwise>
                        <div class="initial-circle">
                            ${fn:substring(loggedUser.firstName, 0, 1)}
                        </div>
                    </c:otherwise>
                </c:choose>

                <div>
                    <strong>${loggedUser.firstName} ${loggedUser.lastName}</strong>
                    <p>User</p>
                </div>
            </div>

        </header>

        <section class="profile-wrapper">

            <section class="profile-card">

                <div class="profile-left">
                    <div class="avatar-box">
                        <c:choose>
                            <c:when test="${not empty loggedUser.profileImage}">
                                <img src="${pageContext.request.contextPath}/get-image?file=${loggedUser.profileImage}"
                                     alt="Profile Image">
                            </c:when>

                            <c:otherwise>
                                <span>${fn:substring(loggedUser.firstName, 0, 1)}</span>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <h2>${loggedUser.firstName} ${loggedUser.lastName}</h2>
                    <p>${loggedUser.role}</p>

                    <div class="profile-contact">
                        <span>
                            <i class="fa-solid fa-envelope"></i>
                            ${loggedUser.email}
                        </span>

                        <span>
                            <i class="fa-solid fa-phone"></i>
                            ${loggedUser.phone}
                        </span>
                    </div>
                </div>

                <div class="profile-right">

                    <div class="title-row">
                        <div>
                            <h1>Personal Information</h1>
                            <p>Update your profile details and photo.</p>
                        </div>
                    </div>

                    <c:if test="${not empty success}">
                        <div class="message success">${success}</div>
                    </c:if>

                    <c:if test="${not empty error}">
                        <div class="message error">${error}</div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/profile"
                          method="post"
                          enctype="multipart/form-data">

                        <label>Profile Image</label>
                        <input type="file" name="profile_image" accept="image/*">

                        <div class="form-row">
                            <div>
                                <label>First Name</label>
                                <input type="text" name="first_name"
                                       value="${loggedUser.firstName}" required>
                            </div>

                            <div>
                                <label>Last Name</label>
                                <input type="text" name="last_name"
                                       value="${loggedUser.lastName}" required>
                            </div>
                        </div>

                        <label>Email</label>
                        <input type="email" name="email"
                               value="${loggedUser.email}" required>

                        <label>Phone</label>
                        <input type="text" name="phone"
                               value="${loggedUser.phone}" required>

                        <label>Address</label>
                        <textarea name="address" rows="4">${loggedUser.address}</textarea>

                        <button type="submit">
                            <i class="fa-solid fa-pen"></i> Update Profile
                        </button>
                    </form>

                </div>

            </section>

        </section>

    </main>

</div>

</body>
</html>