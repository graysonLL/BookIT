-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 21, 2024 at 03:17 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bookit`
--

-- --------------------------------------------------------

--
-- Table structure for table `hotel`
--

CREATE TABLE `hotel` (
  `hotel_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `stars` int(11) NOT NULL,
  PRIMARY KEY (`hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `room_number` int(11) NOT NULL,
  `room_type` ENUM('single_bed', 'double_bed', 'suite', 'deluxe_double_bed') NOT NULL,
  `status` ENUM('free', 'taken') NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`room_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `room` (`room_number`, `room_type`, `status`, `price`)
VALUES
(100, 'single_bed', 'free', 1800),
(101, 'single_bed', 'free', 1800),
(102, 'single_bed', 'free', 1800),
(103, 'single_bed', 'free', 1800),
(104, 'single_bed', 'free', 1800),
(105, 'single_bed', 'free', 1800),
(106, 'double_bed', 'free', 3200),
(107, 'double_bed', 'free', 3200),
(108, 'double_bed', 'free', 3200),
(109, 'double_bed', 'free', 3200),
(110, 'double_bed', 'free', 3200),
(111, 'suite', 'free', 7523),
(112, 'suite', 'free', 7523),
(113, 'suite', 'free', 7523),
(114, 'suite', 'free', 7523),
(115, 'suite', 'free', 7523),
(116, 'deluxe_double_bed', 'free', 11586),
(117, 'deluxe_double_bed', 'free', 11586),
(118, 'deluxe_double_bed', 'free', 11586),
(119, 'deluxe_double_bed', 'free', 11586),
(120, 'deluxe_double_bed', 'free', 11586),
(200, 'single_bed', 'free', 1800),
(201, 'single_bed', 'free', 1800),
(202, 'single_bed', 'free', 1800),
(203, 'single_bed', 'free', 1800),
(204, 'single_bed', 'free', 1800),
(205, 'single_bed', 'free', 1800),
(206, 'double_bed', 'free', 3200),
(207, 'double_bed', 'free', 3200),
(208, 'double_bed', 'free', 3200),
(209, 'double_bed', 'free', 3200),
(210, 'double_bed', 'free', 3200),
(211, 'suite', 'free', 7523),
(212, 'suite', 'free', 7523),
(213, 'suite', 'free', 7523),
(214, 'suite', 'free', 7523),
(215, 'suite', 'free', 7523),
(216, 'deluxe_double_bed', 'free', 11586),
(217, 'deluxe_double_bed', 'free', 11586),
(218, 'deluxe_double_bed', 'free', 11586),
(219, 'deluxe_double_bed', 'free', 11586),
(220, 'deluxe_double_bed', 'free', 11586),
(300, 'single_bed', 'free', 1800),
(301, 'single_bed', 'free', 1800),
(302, 'single_bed', 'free', 1800),
(303, 'single_bed', 'free', 1800),
(304, 'single_bed', 'free', 1800),
(305, 'single_bed', 'free', 1800),
(306, 'double_bed', 'free', 3200),
(307, 'double_bed', 'free', 3200),
(308, 'double_bed', 'free', 3200),
(309, 'double_bed', 'free', 3200),
(310, 'double_bed', 'free', 3200),
(311, 'suite', 'free', 7523),
(312, 'suite', 'free', 7523),
(313, 'suite', 'free', 7523),
(314, 'suite', 'free', 7523),
(315, 'suite', 'free', 7523),
(316, 'deluxe_double_bed', 'free', 11586),
(317, 'deluxe_double_bed', 'free', 11586),
(318, 'deluxe_double_bed', 'free', 11586),
(319, 'deluxe_double_bed', 'free', 11586),
(320, 'deluxe_double_bed', 'free', 11586),
(400, 'single_bed', 'free', 1800),
(401, 'single_bed', 'free', 1800),
(402, 'single_bed', 'free', 1800),
(403, 'single_bed', 'free', 1800),
(404, 'single_bed', 'free', 1800),
(405, 'single_bed', 'free', 1800),
(406, 'double_bed', 'free', 3200),
(407, 'double_bed', 'free', 3200),
(408, 'double_bed', 'free', 3200),
(409, 'double_bed', 'free', 3200),
(410, 'double_bed', 'free', 3200),
(411, 'suite', 'free', 7523),
(412, 'suite', 'free', 7523),
(413, 'suite', 'free', 7523),
(414, 'suite', 'free', 7523),
(415, 'suite', 'free', 7523),
(416, 'deluxe_double_bed', 'free', 11586),
(417, 'deluxe_double_bed', 'free', 11586),
(419, 'deluxe_double_bed', 'free', 11586),
(420, 'deluxe_double_bed', 'free', 11586),
(500, 'single_bed', 'free', 1800),
(501, 'single_bed', 'free', 1800),
(502, 'single_bed', 'free', 1800),
(503, 'single_bed', 'free', 1800),
(504, 'single_bed', 'free', 1800),
(505, 'single_bed', 'free', 1800),
(506, 'double_bed', 'free', 3200),
(507, 'double_bed', 'free', 3200),
(508, 'double_bed', 'free', 3200),
(509, 'double_bed', 'free', 3200),
(510, 'double_bed', 'free', 3200),
(511, 'suite', 'free', 7523),
(512, 'suite', 'free', 7523),
(513, 'suite', 'free', 7523),
(514, 'suite', 'free', 7523),
(515, 'suite', 'free', 7523),
(516, 'deluxe_double_bed', 'free', 11586),
(517, 'deluxe_double_bed', 'free', 11586),
(518, 'deluxe_double_bed', 'free', 11586),
(519, 'deluxe_double_bed', 'free', 11586),
(520, 'deluxe_double_bed', 'free', 11586);



-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `booking_id` int(11) NOT NULL AUTO_INCREMENT,
  `room_number` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `room_type` varchar(255) NOT NULL,
  `start_date` date NOT NULL,
  `total_days` int(11) NOT NULL,
  `total_cost` double NOT NULL,
  PRIMARY KEY (`booking_id`),
  FOREIGN KEY (`room_number`) REFERENCES `room` (`room_number`) ON DELETE CASCADE,
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
