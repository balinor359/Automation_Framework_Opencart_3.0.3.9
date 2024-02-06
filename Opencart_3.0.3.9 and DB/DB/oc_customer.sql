-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 05, 2024 at 11:14 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test_oc_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `oc_customer`
--

CREATE TABLE `oc_customer` (
  `customer_id` int NOT NULL,
  `customer_group_id` int NOT NULL,
  `store_id` int NOT NULL DEFAULT '0',
  `language_id` int NOT NULL,
  `firstname` varchar(32) NOT NULL,
  `lastname` varchar(32) NOT NULL,
  `email` varchar(96) NOT NULL,
  `telephone` varchar(32) NOT NULL,
  `fax` varchar(32) NOT NULL,
  `password` varchar(40) NOT NULL,
  `salt` varchar(9) NOT NULL,
  `cart` text,
  `wishlist` text,
  `newsletter` tinyint(1) NOT NULL DEFAULT '0',
  `address_id` int NOT NULL DEFAULT '0',
  `custom_field` text NOT NULL,
  `ip` varchar(40) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `safe` tinyint(1) NOT NULL,
  `token` text NOT NULL,
  `code` varchar(40) NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `oc_customer`
--

INSERT INTO `oc_customer` (`customer_id`, `customer_group_id`, `store_id`, `language_id`, `firstname`, `lastname`, `email`, `telephone`, `fax`, `password`, `salt`, `cart`, `wishlist`, `newsletter`, `address_id`, `custom_field`, `ip`, `status`, `safe`, `token`, `code`, `date_added`) VALUES
(2, 1, 0, 1, 'Georgi', 'Petrov', 'petrov@test.test', '0883225512', '', 'e45272d46e0be3261dd9eaef01d6aa399217d8a0', 'juiyxaSli', NULL, NULL, 0, 2, '[]', '127.0.0.1', 1, 0, '', '', '2023-11-24 15:13:50'),
(3, 1, 0, 0, 'Gergana', 'Dimitrova', 'dimitrova@test.test', '0888888888', '', 'a21608ad8005d38d1db7bbd17b945a3119a6db1e', 'lz7URF19Q', NULL, NULL, 1, 3, '[]', '', 1, 0, '', '', '2023-11-24 15:16:16'),
(40, 1, 0, 0, 'Kiril', 'Todorov', 'todorov@test.test', '0883198565', '', '67aebcce578fd77edabec220a76237054c3b1f47', 'kvZprUa4R', NULL, NULL, 1, 4, '[]', '', 1, 0, '', '', '2023-11-28 18:26:48'),
(122, 1, 0, 1, 'Vasil', 'Vasilev', 'vasilev@test.test', '0884395614', '', '152470bde5bddc738cfcd5c9c74ce98195bee254', 'h1YNTH7Gd', NULL, NULL, 1, 0, '', '127.0.0.1', 1, 0, '', '', '2024-02-05 19:12:32');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `oc_customer`
--
ALTER TABLE `oc_customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `oc_customer`
--
ALTER TABLE `oc_customer`
  MODIFY `customer_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=159;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
