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

DROP TABLE IF EXISTS `Token`;
CREATE TABLE `Token` (
  `tokenId` bigint NOT NULL AUTO_INCREMENT,
  `token` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `expired` tinyint(1) NOT NULL DEFAULT '0',
  `revoked` tinyint(1) NOT NULL DEFAULT '0',
  `updatedDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `usersIndex` bigint NOT NULL,
  PRIMARY KEY (`tokenId`),
  KEY `usersIndex` (`usersIndex`),
  CONSTRAINT `Token_ibfk_1` FOREIGN KEY (`usersIndex`) REFERENCES `Users` (`usersIndex`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
