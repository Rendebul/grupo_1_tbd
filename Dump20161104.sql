CREATE DATABASE  IF NOT EXISTS `tbd` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tbd`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tbd
-- ------------------------------------------------------
-- Server version	5.7.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `artist`
--

DROP TABLE IF EXISTS `artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artist` (
  `id_artist` int(11) NOT NULL AUTO_INCREMENT,
  `artist_name` varchar(45) DEFAULT NULL,
  `id_nationality` int(11) NOT NULL,
  PRIMARY KEY (`id_artist`),
  UNIQUE KEY `idartist_UNIQUE` (`id_artist`),
  UNIQUE KEY `artist_name_UNIQUE` (`artist_name`),
  KEY `fk_artist_nacionalidad1_idx` (`id_nationality`),
  CONSTRAINT `fk_artist_nacionalidad1` FOREIGN KEY (`id_nationality`) REFERENCES `nationality` (`id_nationality`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=180 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist`
--

LOCK TABLES `artist` WRITE;
/*!40000 ALTER TABLE `artist` DISABLE KEYS */;
INSERT INTO `artist` VALUES (1,'Metallica',2),(2,'The Strokes',2),(3,'The Weeknd',3),(4,'The XX',4),(5,'The Chainsmokers',2),(6,'Flume',5),(7,'Duran Duran',4),(8,'Two Door Cinema Club',12),(9,'Rancid',2),(10,'The 1975',4),(11,'G-Eazy',2),(12,'Melanie Martinez',2),(13,'Cage the Elephant',2),(14,'MØ',8),(15,'Oliver Heldens',14),(16,'Nervo',5),(17,'Catfish and the Bottlemen',4),(18,'Glass Animals',4),(19,'Griz',2),(20,'Vance Joy',5),(21,'Tegan and Sara',3),(22,'Don Diablo',11),(23,'Álex Anwandter',1),(24,'Bomba Estéreo',7),(25,'Lucybell',1),(26,'Tchami',9),(27,'Gondwana',1),(28,'Weichafe',1),(29,'Silversun Pickups',2),(30,'Borgore',13),(31,'La Pozze Latina',1),(32,'Alok',6),(33,'DJ Who',1),(34,'We are the Grand',1),(35,'Villa Cariño',1),(36,'Zaturno Feat. MC Piri',1),(37,'Mad Professor',10),(38,'(Me Llamo) Sebastian',1),(39,'López',1),(40,'Liricistas',1),(41,'Newen Afrobeat',1),(42,'Rootz Hifi & Macky Banton',1),(43,'Prehistoricos',1),(44,'Crizálida',1),(45,'Román & Castro',1),(46,'Mariel Mariel',1),(47,'DR Vena',1),(48,'Chicago Toys',1),(49,'Paz Court',1),(50,'Boraj',1),(51,'8 Monkys',1),(52,'Rey Puesto',1),(53,'Enrique Icka',1),(54,'Tus Amigos Nuevos',1),(55,'Vives&Forero',1),(56,'Rod Valdés',1),(57,'Amahiro',1),(58,'Tiësto',14),(59,'Dubfire: Live Hybrid',15),(60,'Apollonia',16),(61,'BlasterJaxx',11),(62,'Borgeous',2),(63,'Felix Jaehn',17),(64,'KSHMR',2),(65,'Lost Frequences',18),(66,'Marshmello',19),(67,'Mike Cervello',14),(68,'Moksi',14),(70,'Sam Feldt',14),(71,'Sven Väth',17),(72,'The Martinez Brothers',2),(73,'Yellow Claw',14),(74,'Youngr',4),(75,'Amalia Baltboltin',1),(76,'Inguerzon',1),(77,'Gisela Lindhorst',1),(78,'Cris Celiz',1),(79,'Tomás Villarroel',1),(80,'Frans Van Der Hoek',1),(82,'Hedo',1),(83,'Hard Condr',1),(84,'Tomas G',1),(85,'Air',9),(86,'Primal Scream',4),(87,'Courtney Barnett',5),(88,'Larry Gus',16),(89,'Ellen Allien',17),(90,'Tiga',3),(91,'Luisa Puterman',6),(92,'Andrea Paz',1),(93,'Eggglub',1),(94,'Los Barbara Blade',1),(95,'Mitú',7),(96,'Roisin Murphy',4),(97,'Matanza',1),(98,'IIOII',1),(99,'Fantasna',1),(100,'Guerra',1),(101,'La Femme',9),(102,'Lia Nadja',1),(103,'Com Truise',2),(105,'Mas569 & Aurelius98',1),(106,'Underground Resistance',2),(107,'Aye Aye',1),(108,'Camila Moreno',1),(109,'Edward Sharpe & The Magnetic Zeros',2),(110,'The Brian Jonestown Massacre',2),(111,'Kurt Vile ans The Violators',2),(112,'Trax Records Showcase',2),(113,'Los Tres',1),(114,'Chancho en Piedra',1),(115,'Joe Vasconcellos',1),(116,'Los Tetas',1),(117,'Nicole',1),(118,'Shaggy',20),(119,'Emir Kusturica',21),(120,'Mon Laferte',1),(121,'Perotá Chingó',22),(122,'Sum 41',3),(123,'Ataque 77',22),(124,'Javiera Mena',1),(125,'Los Cafres',22),(126,'Los Amigos Invisibles',23),(127,'Chistina Rosenvinge',24),(128,'Nach',24),(129,'La Vela Puerca',25),(130,'Morodo',24),(131,'Os Paralamas Do Sucesso',6),(132,'Caligaris',22),(133,'Tronic',1),(134,'Vectores',1),(135,'Dënver',1),(136,'Nonpalidece',22),(137,'Tote King',24),(138,'Ile',26),(139,'Como Asesinar a Felipes',1),(140,'Churupaca',22),(141,'Planeta No',1),(142,'Habitación del Pánico',1),(143,'Playing for Change',2),(144,'Carla Morrison',27),(145,'Travis',4),(146,'Adrenalize & Demi Kanon',8),(147,'Atmozfears',14),(148,'Audiotricz',14),(149,'Bass Modulators',14),(150,'Da Tweekaz',28),(151,'Frontliner',14),(152,'Psyko Punkz',14),(153,'Sickddellz',1),(154,'TNT',28),(155,'Zany',14),(156,'B-Front',14),(157,'D-Stroyer',6),(158,'Danidemente',1),(159,'Deetox',14),(160,'DJ K-Oss',18),(161,'Donkey Rollers',14),(162,'Frequencerz',14),(163,'Technoboy',29),(164,'Tuneboy',29),(165,'DJ Jubert',19),(166,'Hans Noise',1),(167,'Kallki',9),(168,'Korsakoff',14),(169,'Mad Dog',29),(170,'Miss Offender',1),(171,'Under-X',1),(172,'9Milliz',1),(173,'B-Freqz',14),(174,'Hans Reverze Noise',1),(175,'Happy Tweekay',28),(176,'Rick Mitchells',14),(177,'Stormerz',1),(178,'Tricz',4),(179,'Yurner',1);
/*!40000 ALTER TABLE `artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `festival`
--

DROP TABLE IF EXISTS `festival`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `festival` (
  `id_festival` int(11) NOT NULL AUTO_INCREMENT,
  `festival_name` varchar(45) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `filters` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_festival`),
  UNIQUE KEY `idfestival_UNIQUE` (`id_festival`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `festival`
--

LOCK TABLES `festival` WRITE;
/*!40000 ALTER TABLE `festival` DISABLE KEYS */;
INSERT INTO `festival` VALUES (1,'Lollapalooza','2017-04-01 00:00:00','2017-04-02 00:00:00',NULL),(2,'Creamfields','2016-11-06 12:00:00','2016-11-06 21:00:00',NULL),(3,'Fauna Primavera','2016-11-12 11:00:00','2016-11-13 12:00:00',NULL),(4,'La Cumbre dek Rock Chileno','2017-01-07 14:00:00','2017-01-07 23:00:00',NULL),(5,'Frontera','2016-12-03 00:00:00','2016-12-04 00:00:00',NULL),(6,'Fiis 2016','2016-11-05 10:00:00','2016-11-05 23:59:59',NULL),(7,'DEFQON.1','2016-12-10 10:00:00','2016-12-10 23:59:59',NULL);
/*!40000 ALTER TABLE `festival` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `festival_artist`
--

DROP TABLE IF EXISTS `festival_artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `festival_artist` (
  `id_festival` int(11) NOT NULL,
  `id_artist` int(11) NOT NULL,
  PRIMARY KEY (`id_festival`,`id_artist`),
  KEY `fk_festival_has_artist_artist1_idx` (`id_artist`),
  KEY `fk_festival_has_artist_festival1_idx` (`id_festival`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `festival_artist`
--

LOCK TABLES `festival_artist` WRITE;
/*!40000 ALTER TABLE `festival_artist` DISABLE KEYS */;
INSERT INTO `festival_artist` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(2,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(2,22),(1,23),(1,24),(1,25),(1,26),(1,27),(1,28),(1,29),(1,30),(1,31),(1,32),(1,33),(2,33),(1,34),(1,35),(1,36),(1,37),(1,38),(1,39),(1,40),(1,41),(1,42),(1,43),(1,44),(1,45),(3,45),(1,46),(1,47),(1,48),(1,49),(1,50),(1,51),(1,52),(1,53),(1,54),(1,55),(1,56),(2,56),(1,57),(2,58),(2,59),(2,60),(2,61),(2,62),(2,63),(2,64),(2,65),(2,66),(2,67),(2,68),(2,70),(2,71),(2,72),(2,73),(2,74),(2,75),(2,76),(2,77),(2,78),(2,79),(2,80),(2,82),(2,83),(2,84),(3,85),(3,86),(3,87),(3,88),(3,89),(3,90),(3,91),(3,92),(3,93),(3,94),(3,95),(3,96),(3,97),(3,98),(3,99),(3,100),(3,101),(3,102),(3,103),(3,105),(3,106),(3,107),(3,108),(3,109),(3,110),(3,111),(3,112),(4,113),(4,114),(4,115),(4,116),(4,117),(5,118),(5,119),(5,120),(5,121),(5,122),(5,123),(5,124),(5,125),(5,126),(5,127),(5,128),(5,129),(5,130),(5,131),(5,132),(5,133),(5,134),(5,135),(5,136),(5,137),(5,138),(5,139),(5,140),(6,141),(6,142),(6,143),(6,144),(6,145),(7,146),(7,147),(7,148),(7,149),(7,150),(7,151),(7,152),(7,153),(7,154),(7,155),(7,156),(7,157),(7,158),(7,159),(7,160),(7,161),(7,162),(7,163),(7,164),(7,165),(7,166),(7,167),(7,168),(7,169),(7,170),(7,171),(7,172),(7,173),(7,174),(7,175),(7,176),(7,177),(7,178),(7,179);
/*!40000 ALTER TABLE `festival_artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `festival_user`
--

DROP TABLE IF EXISTS `festival_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `festival_user` (
  `id_festival` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_festival`,`id_user`),
  KEY `fk_festival_has_user_user1_idx` (`id_user`),
  KEY `fk_festival_has_user_festival1_idx` (`id_festival`),
  CONSTRAINT `fk_festival_has_user_festival1` FOREIGN KEY (`id_festival`) REFERENCES `festival` (`id_festival`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_festival_has_user_user1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `festival_user`
--

LOCK TABLES `festival_user` WRITE;
/*!40000 ALTER TABLE `festival_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `festival_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nationality`
--

DROP TABLE IF EXISTS `nationality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nationality` (
  `id_nationality` int(11) NOT NULL,
  `country` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_nationality`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nationality`
--

LOCK TABLES `nationality` WRITE;
/*!40000 ALTER TABLE `nationality` DISABLE KEYS */;
INSERT INTO `nationality` VALUES (1,'Chile'),(2,'USA'),(3,'Canada'),(4,'Inglaterra'),(5,'Australia'),(6,'Brazil'),(7,'Colombia'),(8,'Dinamarca'),(9,'Francia'),(10,'Guyana'),(11,'Holanda'),(12,'Irlanda'),(13,'Israel'),(14,'Países Bajos'),(15,'Irán'),(16,'Grecia'),(17,'Alemania'),(18,'Bélgica'),(19,'Desconocido'),(20,'Jamaica'),(21,'Servia'),(22,'Argentina'),(23,'Venezuela'),(24,'España'),(25,'Uruguay'),(26,'Puerto Rico'),(27,'Mexico'),(28,'Noruega'),(29,'Italia');
/*!40000 ALTER TABLE `nationality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `id_rol` int(11) NOT NULL AUTO_INCREMENT,
  `rol_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_rol`),
  UNIQUE KEY `rol_id_UNIQUE` (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'Usuario'),(2,'Administrador');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `user_mail` varchar(45) DEFAULT NULL,
  `id_rol` int(11) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `user_id_UNIQUE` (`id_user`),
  KEY `fk_user_rol_idx` (`id_rol`),
  CONSTRAINT `fk_user_rol` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_artist`
--

DROP TABLE IF EXISTS `user_artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_artist` (
  `id_user` int(11) NOT NULL,
  `id_artist` int(11) NOT NULL,
  PRIMARY KEY (`id_user`,`id_artist`),
  KEY `fk_user_has_artist_artist1_idx` (`id_artist`),
  KEY `fk_user_has_artist_user1_idx` (`id_user`),
  CONSTRAINT `fk_user_has_artist_artist1` FOREIGN KEY (`id_artist`) REFERENCES `artist` (`id_artist`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_artist_user1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_artist`
--

LOCK TABLES `user_artist` WRITE;
/*!40000 ALTER TABLE `user_artist` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'tbd'
--

--
-- Dumping routines for database 'tbd'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-04 21:56:06
