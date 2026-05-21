<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Cloth | Rental Hub</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/add_cloth.css">
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

            <a href="${pageContext.request.contextPath}/manage-users">
                <i class="fa-solid fa-users"></i> Users
            </a>

            <a href="${pageContext.request.contextPath}/clothes" class="active">
                <i class="fa-solid fa-shirt"></i> Clothes
            </a>

            <a href="${pageContext.request.contextPath}/admin-rentals">
                <i class="fa-solid fa-calendar-check"></i> Rentals
            </a>

            <a href="${pageContext.request.contextPath}/logout">
                <i class="fa-solid fa-right-from-bracket"></i> Logout
            </a>
        </nav>
    </aside>

    <main class="content">

        <header class="topbar">
            <div class="search-box">
                <i class="fa-solid fa-magnifying-glass"></i>
                <input type="text" placeholder="Search clothes...">
            </div>

            <div class="user-info">
                <i class="fa-regular fa-user"></i>
                <div>
                    <strong>${loggedUser.firstName} ${loggedUser.lastName}</strong>
                    <p>Administrator</p>
                </div>
            </div>
        </header>

        <section class="form-section">

            <div class="form-card">

                <div class="form-header">
                    <h1>Edit Cloth</h1>
                    <p>Update rental cloth information and image.</p>
                </div>

                <c:if test="${not empty error}">
                    <div class="message error">${error}</div>
                </c:if>

                <c:if test="${not empty cloth.imageName}">
                    <div class="current-image-box">
                        <label>Current Image</label>
                        <img src="${pageContext.request.contextPath}/get-cloth-image?file=${cloth.imageName}"
                             alt="${cloth.clothName}">
                    </div>
                </c:if>

                <form action="${pageContext.request.contextPath}/edit-cloth"
                      method="post"
                      enctype="multipart/form-data">

                    <input type="hidden" name="cloth_id" value="${cloth.clothId}">
                    <input type="hidden" name="old_image_name" value="${cloth.imageName}">

                    <div class="grid-2">
                        <div class="input-group">
                            <label>Cloth Name</label>
                            <input type="text" name="cloth_name" value="${cloth.clothName}" required>
                        </div>

                        <div class="input-group">
                            <label>Category</label>
                            <input type="text" name="category" value="${cloth.category}" required>
                        </div>
                    </div>

                    <div class="grid-2">
                        <div class="input-group">
                            <label>Size</label>
                            <input type="text" name="size" value="${cloth.size}" required>
                        </div>

                        <div class="input-group">
                            <label>Color</label>
                            <input type="text" name="color" value="${cloth.color}">
                        </div>
                    </div>

                    <div class="grid-2">
                        <div class="input-group">
                            <label>Price Per Day</label>
                            <input type="number" step="0.01" name="rental_price"
                                   value="${cloth.rentalPrice}" required>
                        </div>

                        <div class="input-group">
                            <label>Status</label>
                            <select name="status" required>
                                <option value="available"
                                    <c:if test="${cloth.status == 'available'}">selected</c:if>>
                                    Available
                                </option>

                                <option value="rented"
                                    <c:if test="${cloth.status == 'rented'}">selected</c:if>>
                                    Rented
                                </option>

                                <option value="maintenance"
                                    <c:if test="${cloth.status == 'maintenance'}">selected</c:if>>
                                    Maintenance
                                </option>
                            </select>
                        </div>
                    </div>

                    <div class="input-group">
                        <label>Change Image</label>
                        <input type="file" name="image_name" accept="image/*">
                    </div>

                    <div class="input-group">
                        <label>Description</label>
                        <textarea name="description" rows="5">${cloth.description}</textarea>
                    </div>

                    <button type="submit" class="submit-btn">
                        <i class="fa-solid fa-pen"></i> Update Cloth
                    </button>

                </form>

            </div>

        </section>

    </main>

</div>

</body>
</html>