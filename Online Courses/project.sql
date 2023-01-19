-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 22, 2022 at 11:19 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `addCourse` (IN `nameIN` VARCHAR(50), IN `priceIN` FLOAT(10), IN `descIN` TEXT)   INSERT INTO `courses` (`courses`.`name`, `courses`.`price`, `courses`.`description`)
VALUES(nameIN, priceIN, descIN)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addUser` (IN `usernameIN` VARCHAR(50), IN `nameIN` VARCHAR(200), IN `emailIN` VARCHAR(50), IN `ageIN` INT(10))   INSERT INTO `users` (`users`.`username`,`users`.`name`, `users`.`email`, `users`.`age`)
VALUES(usernameIN, nameIN, emailIN, ageIN)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteCourse` (IN `idIN` INT(10))   DELETE FROM `courses` WHERE `courses`.`id` = idIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteUser` (IN `idIN` INT(50))   DELETE FROM `users` WHERE `users`.`userid` = idIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `editCourse` (IN `idIN` INT(10), IN `nameIN` VARCHAR(50), IN `priceIN` FLOAT(10), IN `descIN` TEXT)   UPDATE `courses` SET `courses`.`name` = nameIN,
`courses`.`price` = priceIN,
`courses`.`description` = descIN
WHERE `courses`.`id` = idIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `editUser` (IN `idIN` INT(10), IN `usernameIN` VARCHAR(50), IN `nameIN` VARCHAR(200), IN `emailIN` VARCHAR(50), IN `ageIN` INT(10))   UPDATE `users` SET `users`.`username` = usernameIN,
`users`.`name` = nameIN,
`users`.`email` = emailIN,
`users`.`age` = ageIN
WHERE `users`.`userid` = idIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `findUserByEmail` (IN `emailIN` VARCHAR(50))   SELECT * FROM `users`
WHERE `users`.`email` = emailIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getCourses` ()   SELECT * FROM `courses`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getCoursesforUser` (IN `useridIN` INT)   SELECT `users`.`username` AS 'Username',
`courses`.`name` AS 'Course',
`courses`.`description`AS 'Course Description',
`purchased_courses`.`purchased_date` AS 'Purchase Date' 
FROM `users` 
JOIN `purchased_courses` ON `purchased_courses`.`user_id` = `users`.`userid`
JOIN `courses` ON `purchased_courses`.`course_id` = `courses`.`id`
WHERE `purchased_courses`.`user_id` = useridIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getPurchasedCourses` ()   SELECT `users`.`username` AS 'Username',
`courses`.`name` AS 'Course',
`courses`.`description`AS 'Course Description',
`purchased_courses`.`purchased_date` AS 'Purchase Date' 
FROM `users` 
JOIN `purchased_courses` ON `purchased_courses`.`user_id` = `users`.`userid`
JOIN `courses` ON `purchased_courses`.`course_id` = `courses`.`id`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getUsers` ()   SELECT * FROM `users`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `nrOfCourses` ()   SELECT COUNT(*) as NumberOfCourses FROM courses$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `nrOfUsers` ()   SELECT COUNT(*) AS NumberOfUsers FROM users$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `orderByCheapest` ()   SELECT * FROM courses
ORDER BY courses.price$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `purchase_course` (IN `user_idIN` INT(10), IN `course_idIN` INT(10))   INSERT INTO `purchased_courses` (`purchased_courses`.`user_id`,
                                 `purchased_courses`.`course_id`)
VALUES (user_idIN, course_idIN)$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` float DEFAULT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`id`, `name`, `price`, `description`) VALUES
(1, 'Java', 119.99, 'Java for beginners, a training that teaches students the basic knowledge about java'),
(2, 'HTML/CSS', 170, 'Web Programming for beginners'),
(4, 'C and C++', 85, 'Understanding C family languages'),
(6, 'Java II', 135, 'Makes a revision of Java for beginners and goes to more advanced stuff'),
(7, 'Python', 60, 'Introduction to Python'),
(8, 'SQL', 90, 'Get to know Databases'),
(9, 'Javascript', 100, 'Get to know JS'),
(11, 'Python II', 84.5, 'Dive deeper into Python'),
(16, 'PHP', 120, 'Web Programming');

-- --------------------------------------------------------

--
-- Table structure for table `purchased_courses`
--

CREATE TABLE `purchased_courses` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `purchased_date` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `purchased_courses`
--

INSERT INTO `purchased_courses` (`id`, `user_id`, `course_id`, `purchased_date`) VALUES
(2, 1, 2, '2022-11-15 16:28:47'),
(3, 1, 1, '2022-11-15 16:31:11'),
(4, 1, 6, '2022-11-15 16:31:47'),
(5, 6, 8, '2022-11-17 00:11:47'),
(6, 6, 4, '2022-11-17 00:12:03'),
(7, 2, 9, '2022-11-21 00:52:03');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userid` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userid`, `username`, `name`, `email`, `age`) VALUES
(1, 'ditaa99', 'Dita Pelaj', 'dita@mail.com', 23),
(2, 'Egor19', 'Egor Kolonyuk', 'Egor@mail.com', 19),
(4, 'anass', 'Anas Aqli', 'anas@mail.com', 20),
(6, 'shiny', 'Chaimae Binjach', 'shiny@mail.com', 21),
(7, 'mezo', 'Mazen Basunaid', 'mazen@mail.com', 20),
(8, 'ibrahimos', 'ibrahim', 'ibrahim@mail.com', 19);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `purchased_courses`
--
ALTER TABLE `purchased_courses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `course_id` (`course_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `purchased_courses`
--
ALTER TABLE `purchased_courses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `purchased_courses`
--
ALTER TABLE `purchased_courses`
  ADD CONSTRAINT `purchased_courses_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`userid`),
  ADD CONSTRAINT `purchased_courses_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
