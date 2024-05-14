DROP TABLE IF EXISTS `Users`;
CREATE TABLE `Users` (
  `usersIndex` bigint NOT NULL AUTO_INCREMENT,
  `usersName` varchar(255) NOT NULL,
  `usersPass` varchar(255) NOT NULL,
  `usersToken` varchar(255) DEFAULT NULL,
  `usersToken2` varchar(255) DEFAULT NULL,
  `usersRole` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'MEMBER',
  `usersCreatedTime` timestamp NOT NULL,
  `usersUpdatedTime` timestamp NOT NULL,
  PRIMARY KEY (`usersIndex`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
