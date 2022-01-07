-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3325
-- Generation Time: May 15, 2021 at 08:58 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `movies_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `user` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`user`, `password`) VALUES
('1111', '1111'),
('12tra', '?2Trfa$25'),
('a', '123'),
('a1', 'a1'),
('an', '123456'),
('asd', 'asd'),
('asdasd', '987'),
('dang', '123'),
('dung', '123'),
('dung1', '123123a'),
('dung12', '33'),
('dungdeptrai', '12345'),
('dunv', 'vvv'),
('phat', '123'),
('quy', '123456'),
('test01', 'a123'),
('test02', 'a123'),
('test03', '1111á'),
('test04', '123123'),
('test05', 'asdfa'),
('test06', '123'),
('test123', '123123'),
('thedung', 'aaa'),
('ttttt', 'asasas');

-- --------------------------------------------------------

--
-- Table structure for table `favorite_film`
--

CREATE TABLE `favorite_film` (
  `id` int(11) NOT NULL,
  `id_user` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_film` int(11) NOT NULL,
  `ten_film` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `imgUrl` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `imgCover` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(700) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `favorite_film`
--

INSERT INTO `favorite_film` (`id`, `id_user`, `id_film`, `ten_film`, `imgUrl`, `imgCover`, `description`) VALUES
(19, 'phat', 278, 'The Shawshank Redemption', '/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg', 'qwerqwe', 'qweqwe'),
(23, 'dang', 460465, 'Mortal Kombat', '/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg', '', ''),
(29, 'dang', 793723, 'Sentinelle', '/3YvTBmi15BBdLrOekchIAyzfYt3.jpg', '', ''),
(30, 'dang', 615678, 'Thunder Force', '/duK11VQd4UPDa7UJrgrGx90xJOx.jpg', '', ''),
(32, 'dang', 726684, 'Miraculous World: Shanghai – The Legend of Ladydra', '/msI5a9TPnepx47JUb2vl88hb80R.jpg', '', ''),
(34, 'phat', 726684, 'Miraculous World: Shanghai – The Legend of Ladydra', '/msI5a9TPnepx47JUb2vl88hb80R.jpg', '', ''),
(35, 'dang', 238, 'The Godfather', '/3bhkrj58Vtu7enYsRolD1fZdja1.jpg', '', ''),
(49, 'phat', 615457, 'Nobody', '/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg', '', ''),
(50, 'phat', 238, 'The Godfather', '/3bhkrj58Vtu7enYsRolD1fZdja1.jpg', '', ''),
(64, 'dung', 813258, 'Monster Pets: A Hotel Transylvania Short', '/dkokENeY5Ka30BFgWAqk14mbnGs.jpg', '', ''),
(75, 'dung', 19404, 'Dilwale Dulhania Le Jayenge', '/2CAL2433ZeIihfX1Hb2139CX0pW.jpg', '', ''),
(81, 'dung', 372058, 'Your Name.', '/q719jXXEzOoYaps6babgKnONONX.jpg', '', ''),
(82, 'dung', 240, 'The Godfather: Part II', '/hek3koDUyRQk7FIhPXsa6mT2Zc3.jpg', '', ''),
(87, 'a', 804435, 'Vanquish', '/AoWY1gkcNzabh229Icboa1Ff0BM.jpg', '', ''),
(89, 'a', 399566, 'Godzilla vs. Kong', '/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg', '', ''),
(96, 'a', 811367, '22 vs. Earth', '/32vLDKSzcCMh55zqqaSqqUA8naz.jpg', '', ''),
(102, 'a', 424, 'Schindlers List', '/sF1U4EUQS8YHUYjNl3pMGNIQyr0.jpg', '', ''),
(103, 'a', 791373, 'Zack Snyders Justice League', '/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg', '', ''),
(107, 'dung', 804435, 'Vanquish', '/AoWY1gkcNzabh229Icboa1Ff0BM.jpg', '', ''),
(109, 'dung', 567189, 'Tom Clancys Without Remorse', '/rEm96ib0sPiZBADNKBHKBv5bve9.jpg', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

CREATE TABLE `movie` (
  `id` int(11) NOT NULL,
  `title` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `imgUrl` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `imgCover` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(700) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` (`id`, `title`, `imgUrl`, `imgCover`, `description`) VALUES
(1, 'Naruto cho wibu', '', '', ''),
(2, 'Tiệc trăng máu', '', '', ''),
(3, 'Hài Hoài Linh', '', '', ''),
(4, 'Thuỷ hử', '', '', ''),
(5, 'Mộng Tam Quốc', '', '', ''),
(6, 'Tây du kí', '', '', ''),
(7, 'Đảo hải tặc', '', '', ''),
(8, 'Lật mặt 24h', '', '', ''),
(9, 'Thám tử conan', '', '', ''),
(10, '2 con mèo con', '', '', ''),
(238, 'The Godfather', '/3bhkrj58Vtu7enYsRolD1fZdja1.jpg', '', ''),
(278, 'The Shawshank Redemption', '/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg', '', ''),
(399566, 'Godzilla vs. Kong', '/6zbKgwgaaCyyBXE4Sun4oWQfQmi.jpg', '', ''),
(460465, 'Mortal Kombat', '/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg', '', ''),
(544401, 'Cherry', '/pwDvkDyaHEU9V7cApQhbcSJMG1w.jpg', '', ''),
(567189, 'Tom Clancy\'s Without Remorse', '/rEm96ib0sPiZBADNKBHKBv5bve9.jpg', '', ''),
(615457, 'Nobody', '/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg', '', ''),
(615678, 'Thunder Force', '/duK11VQd4UPDa7UJrgrGx90xJOx.jpg', '', ''),
(635302, 'Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen T', '/uQtqiAu2bBlokqjlURVLEha6zoi.jpg', '', ''),
(724089, 'Gabriel\'s Inferno Part II', '/x5o8cLZfEXMoZczTYWLrUo1P7UJ.jpg', '', ''),
(726684, 'Miraculous World: Shanghai – The Legend of Ladydra', '/msI5a9TPnepx47JUb2vl88hb80R.jpg', '', ''),
(761053, 'Gabriel\'s Inferno Part III', '/fYtHxTxlhzD4QWfEbrC1rypysSD.jpg', '', ''),
(791373, 'Zack Snyder\'s Justice League', '/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg', '', ''),
(793723, 'Sentinelle', '/3YvTBmi15BBdLrOekchIAyzfYt3.jpg', '', ''),
(804435, 'Vanquish', '/AoWY1gkcNzabh229Icboa1Ff0BM.jpg', '', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`user`);

--
-- Indexes for table `favorite_film`
--
ALTER TABLE `favorite_film`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `favorite_film`
--
ALTER TABLE `favorite_film`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=110;

--
-- AUTO_INCREMENT for table `movie`
--
ALTER TABLE `movie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=804436;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `favorite_film`
--
ALTER TABLE `favorite_film`
  ADD CONSTRAINT `favorite_film_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `account` (`user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
