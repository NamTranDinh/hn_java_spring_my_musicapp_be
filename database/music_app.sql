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
  `content` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '0' COMMENT '0 là show, 1 là ẩn	',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_ads`
--

LOCK TABLES `ma_ads` WRITE;
/*!40000 ALTER TABLE `ma_ads` DISABLE KEYS */;
INSERT INTO `ma_ads` VALUES (1,7,'Đừng Bỏ Lỡ của Hà Nhi sắp ra mắt. Các bạn đã chuẩn bị chưa!!','dung-bo-lo-ha-nhi-16-9.jpg',0),(2,1,'Bộ tứ BLACK PINK đã phát hành bài hát mới. Bạn đã nghe thử chưa?','how-do-u-like-that-ads-16-9.jpg',0),(3,8,'Don\'t be constrained. The rules were always made to be broken anyway','ride-on-ads-16-9.jpg',0),(4,4,'If you ever feel bored, watch this video and you will live again.','the-nights-avicii-ads-16-9.jpg',0);
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
  `name` varchar(255) DEFAULT NULL,
  `singer_name` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_album`
--

LOCK TABLES `ma_album` WRITE;
/*!40000 ALTER TABLE `ma_album` DISABLE KEYS */;
INSERT INTO `ma_album` VALUES (1,'BlackPink Is My Life','BLACKPINK','black-pink-is-my-life.jpg'),(2,'Chỉ Có Thể Là Avicii','Avicii','Chi-Co-The-La-Avicii.jpg'),(3,'Different World','Alan Walker','Different-World.jpg'),(4,'Noo Phước Thịnh\'s Top Hits','Noo Phước Thịnh','1517dc7b1c8b44e7132327796535a5c8.jpg'),(5,'Những Bài Hát Hay Nhất Của Marshmello','Marshmello','d8f81b09eec6f03ed1d38853561b8690.jpg'),(6,'Những Bài Hát Hay Nhất Của The Chainsmokers','Chainsmokers','a0c874cfdd8d54e02e5c7a384d1423ba.jpg'),(7,'Những Bài Hát Hay Nhất Của DEAMN','DEAMN','f0d77726c045180a9c9dbe0cb8456fe3.jpg'),(8,'Chỉ Có Thể Là Alan Walker','Alan Walker','b48b4d53b43fe7c49d332c1d3c7f3465.jpg'),(9,'Những Bài Hát Hay Nhất Của K-391','K-391','cf400b0e7a77e2898bc0a1d25ce6328c.jpg'),(10,'Những Bài Hát Hay Nhất Của DIMZ','DIMZ','40f7e7d7e0459345d80291a3617d2c48.jpg'),(11,'Hit-Maker: Masew','Masew','8b3987063fbd567752134fb0bfcf7ea1.jpg'),(12,'Những Bài Hát Hay Nhất Của B Ray','B Ray','9459b0c8f264b9b30f37a7862cb493b1.jpg'),(13,'Chỉ Có Thể Là Taylor Swift','Taylor Swift','f641416da25298007841c382df7d6975.jpg');
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
  `name` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_category`
--

LOCK TABLES `ma_category` WRITE;
/*!40000 ALTER TABLE `ma_category` DISABLE KEYS */;
INSERT INTO `ma_category` VALUES (1,'[3, 1]','Acoustic EDM','acoustic-edm.jpg'),(2,'[5, 3]','Níu Duyên','niu-duyen.jpg'),(3,'[5]','Chạm Đáy Nỗi Đau','1ed89e84a0b230bedda5c27642b936b6.jpg'),(4,'[5]','Đừng như thói quen','c0a090148a5381ed73829a0e53a62cde.jpg'),(5,'[1, 2]','Acoustic V-Pop','4204b98ba82b6952ee5311adbb455693.jpg'),(6,'[1, 3]','Guitar Acoustic','0daf3bf36c5cb4ddde3626171ccbcf68.jpg'),(7,'[1, 3]','Acoustic Chill','75191c17d95e999da6ffc8d5d69987c1.jpg'),(8,'[6, 3, 8]','EDM Đỉnh Cao','4587672b177d49726611d1a5d870f368.jpg'),(9,'[6, 8]','Best EDM For Gaming','7f7b11cd8488d0d3924df5bce5814b48.jpg'),(10,'[3, 6, 7, 1]','EDM Remix','b084d2bf0f32fdc296b0d611a55982e0.jpg'),(11,'[1, 3, 7]','Remix Việt Hay Nhất','023ec1a260786a871b488a508b81ad8f.jpg'),(12,'[7, 8]','Remix Party','15f5e4f6ee324d198ff30de46e815c12.jpg'),(13,'[3, 7]','V-Pop Remix','787e5872a5c5c49d71f8823cde102e98.jpg'),(14,'[1, 3, 7]','Top Hit Remix','d5762221d5031c280bd1b127dcd2463d.jpg'),(15,'[3, 4, 2]','Sleep Away','19ccdfed9387bbf331a743775e04b7c9.jpg'),(16,'[1, 8]','V-Pop Một Thời Đã Xa','14b5b1c2501ff133c30c2bd01926f275.jpg'),(17,'[1, 4, 3]','Acoustic Before Sleep','a9b7f11b90dd9383270ffe0f26b6c8cd.jpg'),(18,'[6, 8]','Today\'s EDM Hits','00045e2fb64477d881d28dfd4b251a5f.jpg');
/*!40000 ALTER TABLE `ma_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ma_playlist`
--

DROP TABLE IF EXISTS `ma_playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ma_playlist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `img_icon` varchar(255) DEFAULT NULL,
  `img_background` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_playlist`
--

LOCK TABLES `ma_playlist` WRITE;
/*!40000 ALTER TABLE `ma_playlist` DISABLE KEYS */;
INSERT INTO `ma_playlist` VALUES (1,'Top 100 Nhạc Electronic/Dance Âu Mỹ Hay Nhất','icon-top100-edm.jpg','background-top100-edm.jpg'),(2,'Top 100 Nhạc Hàn Quốc Hay Nhất','529110950-top-100-nhac-han-quoc.jpg','446518950-background-top-100-nhac-han-quoc.jpg'),(3,'Top 100 Nhạc Phim Hàn Quốc Hay Nhất','top-100-nhac-phim-han-quoc.jpg','top-100-nhac-phim-han-quoc-hay-bg.jpg'),(4,'Top 100 Nhạc Rap Việt Nam Hay Nhất','rap-viet.jpg','rap-viet-bg.jpg'),(5,'Top 100 Nhạc Trance/House/Techno Âu Mỹ Hay Nhất','bf3c2da839bb18ac9b195a08bb882a5.jpg','17974f707c5db89a2e499f3ab0da4be1.jpg'),(6,'Top 100 Pop Âu Mỹ Hay Nhất','d3b956e6b62b4c2006736c11f4dac0bd.jpg','c947a97ce30b8c739f59380f199f62fe.jpg'),(7,'Top 100 Nhạc Blue/Jazz Âu Mỹ Hay Nhất','f714adb9de227923806c7076b99dd6c0.jpg','b2d5ad10390956158ac47e22ee6a8c13.jpg'),(8,'Top 100 Bài Hát Nhạc Trẻ Hay Nhất','ace686da0b78c0f3af4582bfe9c1a192.jpg','f1f257a3e43a512b612c39035507558f.jpg'),(9,'Top 100 Nhạc EDM Việt Hay Nhất','e7fd4a8069cd341063b838aeaa249626.jpg','c59195e183f0466f6b7653030884f643.jpg'),(10,'Top 100 Nhạc Hòa Tấu Nhạc Cụ Piano Hay Nhất','85fd9f4dbd3a1df69966f5260da67966.jpg','801c3895e2ff86cb9d58cc2bfb7de636.jpg');
/*!40000 ALTER TABLE `ma_playlist` ENABLE KEYS */;
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
  `name` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `singer_name` varchar(255) DEFAULT NULL,
  `audio` varchar(255) DEFAULT NULL,
  `likes` int DEFAULT '0',
  `status` tinyint(1) DEFAULT '0' COMMENT '0 là show, 1 là ẩn',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_song`
--

LOCK TABLES `ma_song` WRITE;
/*!40000 ALTER TABLE `ma_song` DISABLE KEYS */;
INSERT INTO `ma_song` VALUES (1,'[1]','[14]','[2]','How You Like That','How-You-Like-That.jpg','BLACKPINK','how-do-you-like-that.mp3',0,0),(2,'[1]','[14]','[2]','Kill This Love','Kill-This-Love.jpg','BLACKPINK','kill-this-love.mp3',0,0),(3,'[2]','[1, 6, 8, 9, 10, 14, 18]','[1, 6]','Waiting For Love','Waiting-For-Love.jpg','Avicii','waiting-for-love.mp3',0,0),(4,'[2]','[1, 7, 6, 8, 9, 10, 14]','[1, 6]','The Night','The-Nights.jpg','Avicii','the-night.mp3',0,0),(5,'[3, 8]','[1, 7, 8, 9, 10, 12, 14]','[1]','Lost Control','Lost-Control.jpg','Alan Walker, Sorana','lost-control.mp3',0,0),(6,'[3, 8]','[1, 8, 9, 14]','[1, 6]','All Falls Down','All-Falls-Down.jpg','Alan Walker, Noah Cyrus, Digital Farm Animals','all-falls-down.mp3',0,0),(7,'[]','[1, 2, 3]','[8]','Đừng Bỏ Lỡ','ha-nhi.jpg','Hà Nhi','dung-bo-lo.mp3',0,0),(8,'[]','[1, 7, 8, 14]','[1]','Ride On','521636522825-ride-on-avo-wave.jpg','Wave Star','ride-on.mp3',0,0),(9,'[]','[1, 9, 14]','[1]','Animal','d0a3f72f100026b17df02cccfd89ec35.jpg','Vicetone - (feat.Jordan Powers &amp; Bekah Novi)','animal-vicetone.mp3',0,0),(10,'[]','[1, 3, 8, 9, 10, 14]','[1]','Stay','stay-000223803622.jpg','Zedd, Alessia Cara','stay-zedd.mp3',0,0),(11,'[]','[1, 3, 9, 10]','[1]','By Your Side','Calvin-Harris-By-Your-Side.jpg','Calvin Harris, Tom Grennan','by-your-side.mp3',0,0),(12,'[]','[2, 3, 5, 11, 13]','[8, 9]','Sai Lầm Của Anh','sai-lam-cua-anh.jpg','Đình Dũng','sai-lam-cua-anh.mp3',0,0),(13,'[]','[2, 3, 4, 11, 13]','[8]','Tình Đẹp Đến Mấy Cũng Tàn','545412489dsh441245465.jpg','Như Việt','tinh-dep-den-may-cung-tan.mp3',0,0),(14,'[]','[2, 3, 4, 5, 7]','[8]','Yêu Một Người Tổn Thương','yeu-mot-nguoi-ton-thuong.jpg','Nhật Phong','yeu-mot-nguoi-ton-thuong.mp3',0,0),(15,'[]','[2, 3, 4, 5, 7, 11, 13]','[8, 9]','Từng Yêu','tung-yeu.jpg','Phan Duy Anh','tung-yeu.mp3',0,0),(16,'[]','[2, 3, 4, 5, 14]','[8]','Có Tất Cả Nhưng Thiếu Anh','co-tat-ca-nhung-thieu-anh.jpg','Erik','co-tat-ca-nhung-thieu-anh.mp3',0,0),(17,'[]','[3, 4, 5, 7]','[8]','Đừng Như Thói Quen','e493fc8488a6989f0f08b711ea1dbbdd.jpg','Jaykii, Sara Luu','dung-nhu-thoi-quen.mp3',0,0),(18,'[]','[5, 2, 7, 11, 13, 14]','[8, 9]','Sài Gòn Hôm Nay Mưa','6cef4ad676e7405541bd7e87fa1db40b.jpg','JSOL ft. Hoàng Duyên','sai-gon-hom-nay-mua.mp3',0,0),(19,'[]','[5, 7, 11, 13]','[8, 9]','Có Anh Ở Đây Rồi','f8c7e5878b7cc919b8a55d984305c470.jpg','Dương Edward, Anh Quân Idol','co-anh-o-day-roi.mp3',0,0),(20,'[]','[5, 3, 2, 7, 10, 11, 13, 14]','[8, 9]','Sài Gòn Đau Lòng Quá','08b711ea1dbb5345313545.jpg','HỨA KIM TUYỀN x HOÀNG DUYÊN','sai-gon-dau-long-qua.mp3',0,0),(21,'[13]','[1, 7, 6, 17, 14]','[6]','Love Story','1613195457da25298007841c382.jpg','Taylor Swift','love-story.mp3',0,0),(22,'[]','[1, 5, 6, 15, 17, 7, 13]','[8, 9]','Một Đêm Say','9f23d551d205f6454a266c3abb670ff3.jpg','Thính Suy','mot-dem-say.mp3',0,0),(23,'[3, 8]','[8, 9, 10, 14]','[1, 6]','Don\'t You Hold Me Down','32j3k324523k45l645l423xsxf.jpg','Alan Walker, Georgia Ku','don-hold-me-down.mp3',0,0),(24,'[]','[8, 9, 10, 12, 14]','[1, 6]','Reality','33hjlk32h5ljdlfd9fyu324c.jpg','Lost Frequencies','reality-lostfrequencies.mp3',0,0),(25,'[]','[8, 9, 10, 12, 14, 18]','[1, 6]','Stay','b5e0045c6a7f399ec39bc899f8483927.jpg','The Kid LAROI, Justin Bieber','stay-justin-bieber.mp3',0,0),(26,'[]','[9, 10, 12, 18]','[1, 6]','The Ocean','4c28a1b25f9b014f8bfb6b64acba6215_1469436646.jpg','Mike Perry, Shy Martin','the-ocean.mp3',0,0),(27,'[11]','[11, 14]','[8, 9]','05 (Không Phai)','319a048010efc72fef6eb7c545c74bfe.jpg','Tăng Duy Tân, Phong Max, Masew','khong-nam-khong-phai.mp3',0,0),(28,'[]','[11, 13]','[8, 9]','Tuyệt Sắc','725e36e0b2729b49ee7c67d992857dda.jpg','LongDrae, Namduc','tuyet-sac.mp3',0,0),(29,'[11]','[18, 11, 5, 7, 13, 14]','[8, 9]','Túy Âm','1505445783173ac545d564e.jpg','Xesi, Masew','tuy-am.mp3',0,0),(30,'[]','[5, 11, 13, 3, 7, 14]','[8, 9]','Ghen','d05f9b3c87cf7ccda468174b28757489_1495770525.jpg','Khắc Hưng, ERIK, Min','ghen.mp3',0,0),(31,'[]','[5, 11, 13, 15, 16, 17]','[8, 9]','Mặt Trời Của Em','8321ed6e6a1f78e36c8fc7ca31ed9b90.jpg','Phương Ly','mat-troi-cua-em.mp3',0,0),(32,'[]','[1, 12, 15, 17, 18, 8, 9, 10, 14]','[1, 6]','Move Your Body','8ecb6cb927e29a0e557187119db2399d_147697659.jpg','Sia','move-your-body.mp3',0,0),(33,'[]','[1, 7, 8, 9, 12, 18, 14]','[1]','Unstoppable','cb6cb927e29a0e55718fdg456412fdsf.jpg','Sia','unstoppable.mp3',0,0),(34,'[]','[1, 6, 7, 9, 10, 12, 14]','[1]','Mood','ec359c594229814c28cb116dddc696d8.png','24KGoldn, Justin Bieber, J Balvin','mood.mp3',0,0),(35,'[]','[5, 15, 17, 13, 6, 7, 16]','[9, 10]','Say You Do','9814c28cb1wef3235d45432fs32254324.jpg','Tiên Tiên','say-you-do.mp3',0,0),(36,'[]','[5, 6, 7, 15, 17, 11, 13, 16]','[9, 10]','Ba Kể Con Nghe','45433efe328cb1wesd2fs453nh34dse54324.jpg','Dương Trần Nghĩa','ba-ke-con-nghe.mp3',0,0),(37,'[]','[1, 12, 15, 17, 18, 14]','[1, 10]','Love Me Like You Do','d6ceb3d08f00801894357a95e025976bf9f2071c.jpg','Tiffany Alvord, Chester See','love-me-like-you-do.mp3',0,0),(38,'[]','[1, 6, 7, 15, 17, 13]','[1, 6, 10]','A Thousand Years','ceb3d5456d4s1f534657852154e415354.jpg','Jasmine Thompson','a-thousand-years.mp3',0,0),(39,'[]','[15, 17, 5, 16]','[8, 9, 10]','Chiếc Khăn Gió Ấm','120574ddf3252312gf745454.jpg','Khánh Phương','chiec-khan-gio-am.mp3',0,0),(40,'[]','[1, 8, 9, 10, 18, 12, 14]','[1, 6]','In My Mind','000551dce2375tt3253425692.jpg','Dynoro & Gigi D’Agostino','in-my-mind.mp3',0,0),(41,'[1]','[1, 7]','[2]','Lovesick Girls','BLACKPINK_lovesick_girls.jpg','BLACKPINK','blackpink_lovesick_girls.mp3',0,0);
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
  `name` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_topic`
--

LOCK TABLES `ma_topic` WRITE;
/*!40000 ALTER TABLE `ma_topic` DISABLE KEYS */;
INSERT INTO `ma_topic` VALUES (1,'Acoustic','acoustic.jpg'),(2,'R&B','R&B.jpg'),(3,'Chill','461563346-chill.jpg'),(4,'Ngủ ngon','641229515-ngu-ngon.jpg'),(5,'Giai điệu buồn','giai-dieu-buon.jpg'),(6,'EDM','9adcf62c1562be1b0de00358329c2b7f.jpg'),(7,'Remix','7a083801926b8a65fa5531b948dcecbb.jpg'),(8,'Party','7f3211c85f057eaf1adfc4debad997d7.jpg');
/*!40000 ALTER TABLE `ma_topic` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-05 22:24:33
