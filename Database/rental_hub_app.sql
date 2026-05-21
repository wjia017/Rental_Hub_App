-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 21, 2026 at 12:51 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rental_hub_app`
--

-- --------------------------------------------------------

--
-- Table structure for table `clothes`
--

CREATE TABLE `clothes` (
  `cloth_id` int(11) NOT NULL,
  `cloth_name` varchar(150) NOT NULL,
  `category` varchar(100) NOT NULL,
  `size` varchar(50) NOT NULL,
  `color` varchar(50) DEFAULT NULL,
  `rental_price` decimal(10,2) NOT NULL,
  `description` text DEFAULT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `status` varchar(30) DEFAULT 'available'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `clothes`
--

INSERT INTO `clothes` (`cloth_id`, `cloth_name`, `category`, `size`, `color`, `rental_price`, `description`, `image_name`, `status`) VALUES
(1, 'Classic Black Blazer', 'Formal', 'M', '', 2500.00, 'Premium black blazer perfect for formal meetings, office events and dinner occasions.', '1779259169980.jpg', 'available'),
(3, 'Elegant Evening Dress', 'Party', 'M', 'Black', 4500.00, 'Stylish evening dress designed for parties, receptions and special celebrations.', '1779266762781.jpg', 'available');

-- --------------------------------------------------------

--
-- Table structure for table `rentals`
--

CREATE TABLE `rentals` (
  `rental_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `cloth_id` int(11) NOT NULL,
  `rental_date` date NOT NULL,
  `return_date` date NOT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  `status` varchar(30) DEFAULT 'pending',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rentals`
--

INSERT INTO `rentals` (`rental_id`, `user_id`, `cloth_id`, `rental_date`, `return_date`, `total_price`, `status`, `created_at`) VALUES
(1, 4, 1, '2026-05-14', '2026-05-21', 2500.00, 'approved', '2026-05-20 04:49:06'),
(2, 4, 1, '2026-05-20', '2026-05-22', 2500.00, 'returned', '2026-05-20 04:51:47'),
(3, 4, 1, '2026-05-20', '2026-05-22', 5000.00, 'rejected', '2026-05-20 06:32:28'),
(4, 4, 1, '2026-05-14', '2026-05-28', 35000.00, 'approved', '2026-05-20 08:39:59'),
(5, 4, 3, '2026-05-20', '2026-05-28', 36000.00, 'pending', '2026-05-20 08:47:31'),
(6, 4, 3, '2026-05-21', '2026-05-27', 27000.00, 'pending', '2026-05-21 08:53:50');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `email` varchar(150) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(30) DEFAULT 'customer',
  `status` varchar(30) DEFAULT 'active',
  `address` varchar(255) DEFAULT NULL,
  `profile_image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `first_name`, `last_name`, `username`, `email`, `phone`, `password`, `role`, `status`, `address`, `profile_image`) VALUES
(1, 'Lahana', 'Lawaju', 'lahanalawaju017', 'laha@gmail.com', '9876543210', '$2a$10$A7fAPeKd3oXJRzUIUu/bwuYGAuV/Z45twosNNJNvAXFIBfRgPmcpO', 'customer', 'active', NULL, NULL),
(3, 'AdminLahana', 'ALawaju', 'Admin_Lahana', 'Admin@gmail.com', '9999999999', '$2a$10$oHyMT597T8SRaSKLbCxqo./nCaVpzoeLvQlnLN0Qy6/2/DU3/ZJoS', 'admin', 'active', NULL, NULL),
(4, 'Wang', 'jia', 'wjia_017', 'wjia3349@gmail.com', '9876543211', '$2a$10$nzThGINouUdrrgOfbl6W0uTRbhhNnnaXWjk30Fzo5N5B0ROfq..Hy', 'customer', 'active', 'kathmandu', 'user_4.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clothes`
--
ALTER TABLE `clothes`
  ADD PRIMARY KEY (`cloth_id`);

--
-- Indexes for table `rentals`
--
ALTER TABLE `rentals`
  ADD PRIMARY KEY (`rental_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `cloth_id` (`cloth_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `phone` (`phone`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clothes`
--
ALTER TABLE `clothes`
  MODIFY `cloth_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `rentals`
--
ALTER TABLE `rentals`
  MODIFY `rental_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `rentals`
--
ALTER TABLE `rentals`
  ADD CONSTRAINT `rentals_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `rentals_ibfk_2` FOREIGN KEY (`cloth_id`) REFERENCES `clothes` (`cloth_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
