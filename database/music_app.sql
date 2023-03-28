CREATE DATABASE  IF NOT EXISTS `music_app` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `music_app`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: music_app
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ma_ads`
--

DROP TABLE IF EXISTS `ma_ads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ma_ads` (
  `id` int NOT NULL AUTO_INCREMENT,
  `song_id` int DEFAULT NULL,
  `content` varchar(300) DEFAULT NULL,
  `img` varchar(50) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '0' COMMENT '0 is show, 1 is hidden',
  PRIMARY KEY (`id`),
  KEY `ma_ads_ma_song_id_fk` (`song_id`),
  CONSTRAINT `ma_ads_ma_song_id_fk` FOREIGN KEY (`song_id`) REFERENCES `ma_song` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_ads`
--

LOCK TABLES `ma_ads` WRITE;
/*!40000 ALTER TABLE `ma_ads` DISABLE KEYS */;
INSERT INTO `ma_ads` VALUES (1,7,'Đừng Bỏ Lỡ của Hà Nhi sắp ra mắt. Các bạn đã chuẩn bị chưa!!','80f14dbb-3273-4c6e-bba6-95f20eb5e1f3.jpg',0),(2,1,'Bộ tứ BLACK PINK đã phát hành bài hát mới. Bạn đã nghe thử chưa?','d752c283-dac7-4d5d-8a99-44a601d89c5c.jpg',0),(3,8,'Don\'t be constrained. The rules were always made to be broken anyway','cd920fd4-72c3-471d-8106-2fc970549f59.jpg',0),(4,4,'If you ever feel bored, watch this video and you will live again.','fabc14a2-9c24-4e85-a372-0c0a7d809fe3.jpg',0);
/*!40000 ALTER TABLE `ma_ads` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ma_album`
--

DROP TABLE IF EXISTS `ma_album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ma_album` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `singer_name` varchar(100) DEFAULT NULL,
  `img` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_album`
--

LOCK TABLES `ma_album` WRITE;
/*!40000 ALTER TABLE `ma_album` DISABLE KEYS */;
INSERT INTO `ma_album` VALUES (1,'BlackPink Is My Life','BLACKPINK','ac1c5a0d-3a25-4be8-91a7-e11f39e28115.jpg'),(2,'Chỉ Có Thể Là Avicii','Avicii','620cb996-1297-4a03-9a14-8d7fe00d2113.jpg'),(3,'Different World','Alan Walker','1a9934ef-ead6-4be1-8d9a-71c54dcb4a87.jpg'),(4,'Noo Phước Thịnh\'s Top Hits','Noo Phước Thịnh','5681c589-be42-40e1-8523-7c7648d05797.jpg'),(5,'Những Bài Hát Hay Nhất Của Marshmello','Marshmello','3cce726b-060e-41f2-bb20-ebe0f8df4e61.jpg'),(6,'Những Bài Hát Hay Nhất Của The Chainsmokers','Chainsmokers','f521c38e-b08b-495b-bc0b-0bea40ab32fd.jpg'),(7,'Những Bài Hát Hay Nhất Của DEAMN','DEAMN','01f635c9-5065-4acf-8032-153cba9e8706.jpg'),(8,'Chỉ Có Thể Là Alan Walker','Alan Walker','53d96724-1c45-4540-bddf-73f191702ae9.jpg'),(9,'Những Bài Hát Hay Nhất Của K-391','K-391','8a03c11a-b2d9-4932-976c-179516d43530.jpg'),(10,'Những Bài Hát Hay Nhất Của DIMZ','DIMZ','ea61b5bd-74f0-428b-9c4b-7f747c243606.jpg'),(11,'Hit-Maker: Masew','Masew','66b04ee2-fd3f-4327-bf45-f84f1cc8455a.jpg'),(12,'Những Bài Hát Hay Nhất Của B Ray','B Ray','3d889637-24fd-4d28-8435-c0a569e885ea.jpg'),(13,'Chỉ Có Thể Là Taylor Swift','Taylor Swift','eeebdf6d-9530-48f0-9f00-31cd7cfb963f.jpg');
/*!40000 ALTER TABLE `ma_album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ma_category`
--

DROP TABLE IF EXISTS `ma_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ma_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `topic_ids` json DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `img` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_category`
--

LOCK TABLES `ma_category` WRITE;
/*!40000 ALTER TABLE `ma_category` DISABLE KEYS */;
INSERT INTO `ma_category` VALUES (1,'[3, 1]','Acoustic EDM','30f5ca1d-869e-4c68-9bf3-accbacfa07b6.jpg'),(2,'[5, 3]','Níu Duyên','3e13a44a-34a2-454b-b387-c35490ba8940.jpg'),(3,'[5]','Chạm Đáy Nỗi Đau','e387a7ab-ee83-4405-b366-e077779c35a8.jpg'),(4,'[5]','Đừng như thói quen','a3646d97-5ed7-4082-b993-b2a87d039cae.jpg'),(5,'[1, 2]','Acoustic V-Pop','45d26f52-9dd0-41e6-a8e3-ca1510e1f2a4.jpg'),(6,'[1, 3]','Guitar Acoustic','ff4f85e6-c6f0-497d-929c-4927d34c246a.jpg'),(7,'[1, 3]','Acoustic Chill','ca4d99d4-9f04-4cdd-84a0-6ee410596ed1.jpg'),(8,'[6, 3, 8]','EDM Đỉnh Cao','1f45c4b0-399d-421d-b24f-8b802256e98a.jpg'),(9,'[6, 8]','Best EDM For Gaming','14a622c0-d00c-4b28-907a-7870b2e3afc7.jpg'),(10,'[3, 6, 7, 1]','EDM Remix','caa1e799-bd3f-455c-9bc5-fb1945c06bf7.jpg'),(11,'[1, 3, 7]','Remix Việt Hay Nhất','4978c5b7-1d56-4b33-b9ce-bf62d7ec22a6.jpg'),(12,'[7, 8]','Remix Party','66272563-bf19-4d31-a31e-23e7a29b257f.jpg'),(13,'[3, 7]','V-Pop Remix','2bb574f2-b7e7-4e45-ad48-068b51dd8a5b.jpg'),(14,'[1, 3, 7]','Top Hit Remix','b97d3f4f-26ad-4ea5-b13d-fba321d2c523.jpg'),(15,'[3, 4, 2]','Sleep Away','0c94175f-c5f2-4aed-ae3e-891b5a63561a.jpg'),(16,'[1, 8]','V-Pop Một Thời Đã Xa','c63dd455-9ae7-4441-8795-8b9873b1aa5a.jpg'),(17,'[1, 4, 3]','Acoustic Before Sleep','27519782-271d-4464-8251-3fe99662aaad.jpg'),(18,'[6, 8]','Today\'s EDM Hits','43249c68-9d62-424b-bac7-d449fa1e37ab.jpg');
/*!40000 ALTER TABLE `ma_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ma_permission`
--

DROP TABLE IF EXISTS `ma_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ma_permission` (
  `id` int NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `authority` varchar(36) DEFAULT NULL,
  `slug` varchar(100) DEFAULT NULL,
  `active` tinyint(1) DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ma_permission_authority_uindex` (`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_permission`
--

LOCK TABLES `ma_permission` WRITE;
/*!40000 ALTER TABLE `ma_permission` DISABLE KEYS */;
INSERT INTO `ma_permission` VALUES (1,'Admin','Can access to admin','cdf8ae23-f6ec-4339-b1a7-c4749e89263a','/admin/**',0,'2023-03-24 14:10:51','2023-03-28 16:45:58'),(2,'Dashboard','Can access to dashboard','22e1151e-bc20-4754-a120-e97cb13c5012','/admin/dashboard/**',0,'2023-03-24 14:10:51','2023-03-28 20:17:36'),(3,'Musics','Can access to music','25f348bc-6ab4-423a-a09e-1981263014a0','/admin/musics/**',0,'2023-03-24 14:10:51','2023-03-25 11:20:59');
/*!40000 ALTER TABLE `ma_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ma_playlist`
--

DROP TABLE IF EXISTS `ma_playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ma_playlist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `img_icon` varchar(50) DEFAULT NULL,
  `img_background` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_playlist`
--

LOCK TABLES `ma_playlist` WRITE;
/*!40000 ALTER TABLE `ma_playlist` DISABLE KEYS */;
INSERT INTO `ma_playlist` VALUES (1,'Top 100 Nhạc Electronic/Dance Âu Mỹ Hay Nhất','2253f64c-35bd-456e-b61a-c44dc1aac45b.jpg','c4dc334c-8c7b-4810-b5ad-1390a0c9338d.jpg'),(2,'Top 100 Nhạc Hàn Quốc Hay Nhất','9c7cc15c-f02a-4898-bc0b-25d90f8d6e16.jpg','87ad5b2b-721e-48c4-b8fa-f8255d5194d8.jpg'),(3,'Top 100 Nhạc Phim Hàn Quốc Hay Nhất','fa59a63d-4e76-42b8-86d0-9391680ad56f.jpg','d0e6bc9a-8769-4840-9d5d-eb24d985554b.jpg'),(4,'Top 100 Nhạc Rap Việt Nam Hay Nhất','a5c6b12d-4332-4ff5-aea4-cb829f64848d.jpg','1fbba457-d0a0-4fa4-a786-a702424e85ec.jpg'),(5,'Top 100 Nhạc Trance/House/Techno Âu Mỹ Hay Nhất','ccaa2d06-2e9f-4962-bcca-d97b43e41a19.jpg','737aaa76-009d-4088-b785-c03442e6db1c.jpg'),(6,'Top 100 Pop Âu Mỹ Hay Nhất','43cbe0a0-a314-40bd-a112-cba22e0a38dc.jpg','dc4856b4-2141-4360-8c89-83c40a3983ff.jpg'),(7,'Top 100 Nhạc Blue/Jazz Âu Mỹ Hay Nhất','ede656a7-7a77-448f-a98d-8c2f8931e257.jpg','18c73f1e-b3de-4424-a9ac-ca57e17d5bc1.jpg'),(8,'Top 100 Bài Hát Nhạc Trẻ Hay Nhất','b86686bb-89f2-4860-b112-007e9f56ce94.jpg','0c2df499-fb7a-44dd-a64e-8cea4c701468.jpg'),(9,'Top 100 Nhạc EDM Việt Hay Nhất','b6cc82d2-1c8a-428b-81ae-a3dd99e13a87.jpg','a0545dad-56ac-4b72-b6c7-c36a4e61973d.jpg'),(10,'Top 100 Nhạc Hòa Tấu Nhạc Cụ Piano Hay Nhất','427fa1a9-c806-4025-8d21-84cee9f03ba3.jpg','2a73cc5c-643e-445f-9313-2c0b0ab1ca46.jpg');
/*!40000 ALTER TABLE `ma_playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ma_role`
--

DROP TABLE IF EXISTS `ma_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ma_role` (
  `id` int NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `active` tinyint(1) DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_role`
--

LOCK TABLES `ma_role` WRITE;
/*!40000 ALTER TABLE `ma_role` DISABLE KEYS */;
INSERT INTO `ma_role` VALUES (1,'Admin','Admin',0,'2023-03-24 14:11:53',NULL),(2,'Staff','Staff',0,'2023-03-28 16:00:07',NULL);
/*!40000 ALTER TABLE `ma_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ma_role_permission`
--

DROP TABLE IF EXISTS `ma_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ma_role_permission` (
  `role_id` int NOT NULL,
  `permission_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_role_permission`
--

LOCK TABLES `ma_role_permission` WRITE;
/*!40000 ALTER TABLE `ma_role_permission` DISABLE KEYS */;
INSERT INTO `ma_role_permission` VALUES (1,1,'2023-03-24 14:12:05',NULL),(1,2,'2023-03-28 16:00:31','2023-03-28 20:13:54'),(1,3,'2023-03-28 16:04:19','2023-03-28 20:13:54'),(2,2,'2023-03-28 16:00:31',NULL),(2,3,'2023-03-28 16:04:19',NULL);
/*!40000 ALTER TABLE `ma_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ma_song`
--

DROP TABLE IF EXISTS `ma_song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ma_song` (
  `id` int NOT NULL AUTO_INCREMENT,
  `album_ids` json DEFAULT NULL,
  `category_ids` json DEFAULT NULL,
  `playlist_ids` json DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `img` varchar(50) DEFAULT NULL,
  `singer_name` varchar(100) DEFAULT NULL,
  `audio` varchar(50) DEFAULT NULL,
  `likes` int DEFAULT '0',
  `status` tinyint(1) DEFAULT '0' COMMENT '0 là show, 1 là ẩn',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_song`
--

LOCK TABLES `ma_song` WRITE;
/*!40000 ALTER TABLE `ma_song` DISABLE KEYS */;
INSERT INTO `ma_song` VALUES (1,'[1]','[14]','[2]','How You Like That','d3158251-d4c8-415a-bda9-bf75d3ac0e3a.jpg','BLACKPINK','41e1c5b9-c930-4a29-b36d-85298bf26d5c.mp3',0,0),(2,'[1]','[14]','[2]','Kill This Love','25e48099-9a24-4537-8b61-97238f92b82f.jpg','BLACKPINK','612e3df4-4047-4cd9-96b6-6d2612ad3b3a.mp3',0,0),(3,'[2]','[1, 6, 8, 9, 10, 14, 18]','[1, 6]','Waiting For Love','21cf2f63-ea67-4b6c-ba5f-f4b3e7b62555.jpg','Avicii','36ca195e-a77c-4af2-872e-9f60101bb4cf.mp3',0,0),(4,'[2]','[1, 7, 6, 8, 9, 10, 14]','[1, 6]','The Night','53d6895d-0c6b-4e41-a060-6d785cdb49d4.jpg','Avicii','ad16918a-6ff6-4cbc-99bb-ee5cea372238.mp3',0,0),(5,'[3, 8]','[1, 7, 8, 9, 10, 12, 14]','[1]','Lost Control','8fc30a43-911d-470c-b3eb-ceb9c8dbdc5d.jpg','Alan Walker, Sorana','20f34dd6-56b7-42f2-b5fd-0660e4a4e98d.mp3',0,0),(6,'[3, 8]','[1, 8, 9, 14]','[1, 6]','All Falls Down','9b253a9a-e850-4728-a8db-1649a45c9c8c.jpg','Alan Walker, Noah Cyrus, Digital Farm Animals','f4f582de-d5bb-4ae4-96bb-10fabf4fe8f4.mp3',0,0),(7,'[]','[1, 2, 3]','[8]','Đừng Bỏ Lỡ','d6a70ac7-3171-4803-a33f-c92b1b7feb2f.jpg','Hà Nhi','2bfb2a5c-bafa-49b4-a171-883ed9d4090b.mp3',0,0),(8,'[]','[1, 7, 8, 14]','[1]','Ride On','f5a8ad4d-a79e-4879-937c-5b68eee0e6b1.jpg','Wave Star','331e1250-3175-4fb6-95f1-20e3a1a43e7a.mp3',0,0),(9,'[]','[1, 9, 14]','[1]','Animal','fbb9fbf0-bfb7-4263-b0e2-73af8937b4f1.jpg','Vicetone - (feat.Jordan Powers &amp; Bekah Novi)','7068c9f0-c13e-4798-8fa5-1dbe5740a205.mp3',0,0),(10,'[]','[1, 3, 8, 9, 10, 14]','[1]','Stay','1b16ebec-ea54-4feb-b43d-3af7c5af87af.jpg','Zedd, Alessia Cara','29ac603c-6191-4e0b-b6bc-d9e6766b12b0.mp3',0,0),(11,'[]','[1, 3, 9, 10]','[1]','By Your Side','11f95de4-0505-47ed-9cb4-8ca5053b0a70.jpg','Calvin Harris, Tom Grennan','36686e0f-edd0-4cd0-99de-59e49ffe8b06.mp3',0,0),(12,'[]','[2, 3, 5, 11, 13]','[8, 9]','Sai Lầm Của Anh','790e169a-fd29-4bb0-9dee-3ee99e2410ec.jpg','Đình Dũng','088dce69-7acb-4c09-a43e-c4c7524f98e0.mp3',0,0),(13,'[]','[2, 3, 4, 11, 13]','[8]','Tình Đẹp Đến Mấy Cũng Tàn','4d51f370-0a9c-4278-b9ce-cd65ade8c209.jpg','Như Việt','25d4828b-dd87-4a29-ba53-7a138b0278da.mp3',0,0),(14,'[]','[2, 3, 4, 5, 7]','[8]','Yêu Một Người Tổn Thương','9b801ee2-100b-471f-951c-b97438496c5a.jpg','Nhật Phong','f3f451cd-b5cc-4780-a0a5-c06d24ec4ef4.mp3',0,0),(15,'[]','[2, 3, 4, 5, 7, 11, 13]','[8, 9]','Từng Yêu','5ec4a879-6395-4e07-82ea-d92173027cc6.jpg','Phan Duy Anh','05336160-9588-47da-8b3b-a1e0088ecf06.mp3',0,0),(16,'[]','[2, 3, 4, 5, 14]','[8]','Có Tất Cả Nhưng Thiếu Anh','375bef47-8256-4c1b-9b6c-0c9de0c7ad5c.jpg','Erik','ae92d35e-7c1e-4f0e-b80c-3c0a5520c28d.mp3',0,0),(17,'[]','[3, 4, 5, 7]','[8]','Đừng Như Thói Quen','102fc5db-3b11-4b86-9fe6-4937b30222cf.jpg','Jaykii, Sara Luu','a3829a97-8b0c-43cd-9e43-abd852e479dd.mp3',0,0),(18,'[]','[5, 2, 7, 11, 13, 14]','[8, 9]','Sài Gòn Hôm Nay Mưa','af1851c1-19dd-441e-aa57-3e3ceef005c6.jpg','JSOL ft. Hoàng Duyên','94ee588b-d6e0-4a79-878a-3c0c3dfba4ef.mp3',0,0),(19,'[]','[5, 7, 11, 13]','[8, 9]','Có Anh Ở Đây Rồi','3da357f8-bd24-4071-b161-1c64a2e02600.jpg','Dương Edward, Anh Quân Idol','1877db59-ba68-4965-9039-933204c5d3c3.mp3',0,0),(20,'[]','[5, 3, 2, 7, 10, 11, 13, 14]','[8, 9]','Sài Gòn Đau Lòng Quá','2ea5ba69-9b33-4292-a026-c0da3a656fd3.jpg','HỨA KIM TUYỀN x HOÀNG DUYÊN','8db02738-1a54-488d-bc27-1cd3db040156.mp3',0,0),(21,'[13]','[1, 7, 6, 17, 14]','[6]','Love Story','7c9b6c16-7f56-4b44-b6d8-0d5e90086fa3.jpg','Taylor Swift','e4e3581f-943f-47bf-b017-7822927be62d.mp3',0,0),(22,'[]','[1, 5, 6, 15, 17, 7, 13]','[8, 9]','Một Đêm Say','fdeb3218-0cdb-4b0b-a9f8-26d3ecfde75e.jpg','Thính Suy','2a110b67-832f-4c2e-8b6e-4cad9b8fcbf9.mp3',0,0),(23,'[3, 8]','[8, 9, 10, 14]','[1, 6]','Don\'t You Hold Me Down','2fcc5c7c-0805-488c-9ca8-4d30e2691db2.jpg','Alan Walker, Georgia Ku','cc01352f-e458-4dd6-a9e6-a79098a59a5f.mp3',0,0),(24,'[]','[8, 9, 10, 12, 14]','[1, 6]','Reality','5c953b54-4492-48ce-8f96-d98ef799b08a.jpg','Lost Frequencies','39e64754-0722-4e30-a93d-1bef338cb925.mp3',0,0),(25,'[]','[8, 9, 10, 12, 14, 18]','[1, 6]','Stay','48086380-2203-4041-8a2d-c4dd163b0671.jpg','The Kid LAROI, Justin Bieber','f9c6beab-cc93-40b3-934a-11f6ec7d0d26.mp3',0,0),(26,'[]','[9, 10, 12, 18]','[1, 6]','The Ocean','1f048971-1dc2-466a-b1ca-45229c8a2ffb.jpg','Mike Perry, Shy Martin','23e8ca9b-7521-49d6-b28c-c8b66a1169af.mp3',0,0),(27,'[11]','[11, 14]','[8, 9]','05 (Không Phai)','7a6faecc-c13f-4c2d-ba03-e30f455fd631.jpg','Tăng Duy Tân, Phong Max, Masew','9de48f22-5a19-4b4e-ad7c-69434fcd2ed6.mp3',0,0),(28,'[]','[11, 13]','[8, 9]','Tuyệt Sắc','c7d0ba0f-1e63-4764-abfe-c777a171e64a.jpg','LongDrae, Namduc','2c4aebb3-84c0-492c-a312-82cd504fbc65.mp3',0,0),(29,'[11]','[18, 11, 5, 7, 13, 14]','[8, 9]','Túy Âm','5b741471-5e90-42b9-aa68-f6d52bd017d7.jpg','Xesi, Masew','6080dd95-ccea-4f3f-9e58-da4edd4664dc.mp3',0,0),(30,'[]','[5, 11, 13, 3, 7, 14]','[8, 9]','Ghen','08a67f6b-9c66-46b0-84aa-4471f958787d.jpg','Khắc Hưng, ERIK, Min','14498356-8ed2-4369-84d2-ec13f38172a8.mp3',0,0),(31,'[]','[5, 11, 13, 15, 16, 17]','[8, 9]','Mặt Trời Của Em','30a4755f-4e3a-45b7-8218-883f8f0957a2.jpg','Phương Ly','78c2bd1f-4dd9-4e33-ab50-7b7c25db313b.mp3',0,0),(32,'[]','[1, 12, 15, 17, 18, 8, 9, 10, 14]','[1, 6]','Move Your Body','f102ea3a-4f9d-409c-a825-fca922adf818.jpg','Sia','6ca96b26-eeb4-4ea8-94d4-7681219f8f70.mp3',0,0),(33,'[]','[1, 7, 8, 9, 12, 18, 14]','[1]','Unstoppable','9340984e-f0fb-4798-86a1-940cc06fe2d7.jpg','Sia','fe277c4e-bd5c-42ab-80dd-18ca4f270f20.mp3',0,0),(34,'[]','[1, 6, 7, 9, 10, 12, 14]','[1]','Mood','05d54079-48ad-4d21-bf45-2b22312054e1.jpg','24KGoldn, Justin Bieber, J Balvin','ba365158-164d-422f-b1d5-6518935b6d47.mp3',0,0),(35,'[]','[5, 15, 17, 13, 6, 7, 16]','[9, 10]','Say You Do','5b4bf188-2342-45c7-bb3b-9fb70a030e04.jpg','Tiên Tiên','1d71e9c4-0403-4707-a204-fc90a71bb219.mp3',0,0),(36,'[]','[5, 6, 7, 15, 17, 11, 13, 16]','[9, 10]','Ba Kể Con Nghe','f09e83cf-03bb-413a-802b-107fe278ece9.jpg','Dương Trần Nghĩa','0931c105-4da2-44fa-b03d-cb96ccb2b99c.mp3',0,0),(37,'[]','[1, 12, 15, 17, 18, 14]','[1, 10]','Love Me Like You Do','4ccf4028-16c2-41ec-8392-287bc8a50ba2.jpg','Tiffany Alvord, Chester See','ca6dafa2-8082-479d-a66a-dbf912db7185.mp3',0,0),(38,'[]','[1, 6, 7, 15, 17, 13]','[1, 6, 10]','A Thousand Years','df3c5e9f-1fa8-4131-b44a-2ba273cf7f06.jpg','Jasmine Thompson','c6979021-ce2b-4a87-9ab2-92dad84ab62e.mp3',0,0),(39,'[]','[15, 17, 5, 16]','[8, 9, 10]','Chiếc Khăn Gió Ấm','aef71826-f83c-4e1b-b689-d7bf3b59b586.jpg','Khánh Phương','8bef49b8-a83f-4e06-8a86-99918296af29.mp3',0,0),(40,'[]','[1, 8, 9, 10, 18, 12, 14]','[1, 6]','In My Mind','3dccd2ec-6ed8-4b46-a7af-159927ee88f8.jpg','Dynoro & Gigi D’Agostino','d1884848-07fc-4fa2-a4a9-922cf45f004b.mp3',0,0),(41,'[1]','[1, 7]','[2]','Lovesick Girls','f976b248-e369-4753-8858-612eaeae049e.jpg','BLACKPINK','912c44b4-2588-4b08-967b-aadb9a31df1e.mp3',0,0);
/*!40000 ALTER TABLE `ma_song` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ma_topic`
--

DROP TABLE IF EXISTS `ma_topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ma_topic` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `img` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_topic`
--

LOCK TABLES `ma_topic` WRITE;
/*!40000 ALTER TABLE `ma_topic` DISABLE KEYS */;
INSERT INTO `ma_topic` VALUES (1,'Acoustic','bed8a42b-cd93-4b4d-b348-fa6d315b3880.jpg'),(2,'R&B','ff53dd58-0513-47db-abbf-6480c36bc244.jpg'),(3,'Chill','f766f688-bfff-4142-a112-fa32137ff36d.jpg'),(4,'Ngủ ngon','2188e19b-69d0-4194-9274-8019cffee24d.jpg'),(5,'Giai điệu buồn','c601c6f5-0df6-4fb6-9251-1ae8fb545e21.jpg'),(6,'EDM','dc874257-3522-4a48-9c99-c0359922f1db.jpg'),(7,'Remix','b190d602-a5b2-483d-aca1-a28b9524e39f.jpg'),(8,'Party','d322d355-f5fa-4a69-b784-0239c02bc602.jpg');
/*!40000 ALTER TABLE `ma_topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ma_user`
--

DROP TABLE IF EXISTS `ma_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ma_user` (
  `id` int NOT NULL,
  `role_id` int NOT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `gender` tinyint(1) DEFAULT '0',
  `avatar` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `ma_user_ma_role_id_fk` (`role_id`),
  CONSTRAINT `ma_user_ma_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `ma_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_user`
--

LOCK TABLES `ma_user` WRITE;
/*!40000 ALTER TABLE `ma_user` DISABLE KEYS */;
INSERT INTO `ma_user` VALUES (1,1,'Admin','MCT',1,NULL,NULL,'admin@gmail.com','admin','$2a$10$zkMwzvYjYqllgpkG4Ac4wuYkDkSV.5jxhAGDvSX4PrOSF5GxxT0B6',0,'2023-03-24 14:14:08','2023-03-25 09:41:48'),(2,2,'Son','Nguyen',1,NULL,NULL,'sona9k15tl@gmail.com','sonnc11','$2a$10$zkMwzvYjYqllgpkG4Ac4wuYkDkSV.5jxhAGDvSX4PrOSF5GxxT0B6',0,'2023-03-25 13:00:24','2023-03-28 16:02:00');
/*!40000 ALTER TABLE `ma_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-29  3:19:24
