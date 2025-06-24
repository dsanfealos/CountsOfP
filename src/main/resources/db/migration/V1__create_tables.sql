
--
-- Table structure for table `amulet`
--

DROP TABLE IF EXISTS `amulet`;
CREATE TABLE `amulet` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `description` text,
                          `name` varchar(255) DEFAULT NULL,
                          `weight` decimal(3,1) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `armor`
--

DROP TABLE IF EXISTS `armor`;
CREATE TABLE `armor` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `name` varchar(255) DEFAULT NULL,
                         `type` int DEFAULT NULL,
                         `weight` decimal(3,1) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `attribute`
--

DROP TABLE IF EXISTS `attribute`;
CREATE TABLE `attribute` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `name` varchar(255) DEFAULT NULL,
                             `sprite` varchar(300) DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `attribute_increase_amulet`
--

DROP TABLE IF EXISTS `attribute_increase_amulet`;
CREATE TABLE `attribute_increase_amulet` (
                                             `id` bigint NOT NULL AUTO_INCREMENT,
                                             `flat_increase` int DEFAULT NULL,
                                             `amulet_id` bigint NOT NULL,
                                             `attribute_id` bigint NOT NULL,
                                             PRIMARY KEY (`id`),
                                             KEY `FK42ovo81ylq8qogpj1fqmflw1e` (`amulet_id`),
                                             KEY `FKpc0fxr77reosu9w9vju7odi7j` (`attribute_id`),
                                             CONSTRAINT `FK42ovo81ylq8qogpj1fqmflw1e` FOREIGN KEY (`amulet_id`) REFERENCES `amulet` (`id`),
                                             CONSTRAINT `FKpc0fxr77reosu9w9vju7odi7j` FOREIGN KEY (`attribute_id`) REFERENCES `attribute` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `weapon_upgrade_n`
--

DROP TABLE IF EXISTS `weapon_upgrade_n`;
CREATE TABLE `weapon_upgrade_n` (
                                    `current_lvl` bigint NOT NULL AUTO_INCREMENT,
                                    `ergo` bigint DEFAULT NULL,
                                    `material` varchar(60) DEFAULT NULL,
                                    `quantity` int DEFAULT NULL,
                                    PRIMARY KEY (`current_lvl`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `weapon_upgrade_s`
--

DROP TABLE IF EXISTS `weapon_upgrade_s`;
CREATE TABLE `weapon_upgrade_s` (
                                    `current_lvl` bigint NOT NULL AUTO_INCREMENT,
                                    `ergo` bigint DEFAULT NULL,
                                    `material` varchar(60) DEFAULT NULL,
                                    `quantity` int DEFAULT NULL,
                                    PRIMARY KEY (`current_lvl`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `blade`
--

DROP TABLE IF EXISTS `blade`;
CREATE TABLE `blade` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `elemental_attack` int DEFAULT NULL,
                         `name` varchar(60) DEFAULT NULL,
                         `physical_attack` int DEFAULT NULL,
                         `sprite` varchar(300) DEFAULT NULL,
                         `total_attack` int DEFAULT NULL,
                         `weight` decimal(3,1) DEFAULT NULL,
                         `current_level` bigint NOT NULL,
                         PRIMARY KEY (`id`),
                         KEY `FK8jq2xhgh82g8wkkbjqgqh2w8i` (`current_level`),
                         CONSTRAINT `FK8jq2xhgh82g8wkkbjqgqh2w8i` FOREIGN KEY (`current_level`) REFERENCES `weapon_upgrade_n` (`current_lvl`)
) ENGINE=InnoDB AUTO_INCREMENT=320 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `handle`
--

DROP TABLE IF EXISTS `handle`;
CREATE TABLE `handle` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `advance` char(1) DEFAULT NULL,
                          `motivity` char(1) DEFAULT NULL,
                          `name` varchar(60) DEFAULT NULL,
                          `sprite` varchar(300) DEFAULT NULL,
                          `technique` char(1) DEFAULT NULL,
                          `weight` decimal(3,1) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `level_p`
--

DROP TABLE IF EXISTS `level_p`;
CREATE TABLE `level_p` (
                           `level` bigint NOT NULL AUTO_INCREMENT,
                           `ergo` bigint DEFAULT NULL,
                           PRIMARY KEY (`level`)
) ENGINE=InnoDB AUTO_INCREMENT=301 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `p_organ`
--

DROP TABLE IF EXISTS `p_organ`;
CREATE TABLE `p_organ` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `level` int DEFAULT NULL,
                           `quartzs` int DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `scaling`
--

DROP TABLE IF EXISTS `scaling`;
CREATE TABLE `scaling` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `bonus_attack` double DEFAULT NULL,
                           `letter` char(1) DEFAULT NULL,
                           `level` int DEFAULT NULL,
                           `attribute_id` bigint NOT NULL,
                           PRIMARY KEY (`id`),
                           KEY `FKkrl65byesdnqwjtdibx43on3h` (`attribute_id`),
                           CONSTRAINT `FKkrl65byesdnqwjtdibx43on3h` FOREIGN KEY (`attribute_id`) REFERENCES `attribute` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1501 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `stat`
--

DROP TABLE IF EXISTS `stat`;
CREATE TABLE `stat` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) DEFAULT NULL,
                        `sprite` varchar(300) DEFAULT NULL,
                        `base_value` decimal(5,2) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `stat_increase`
--

DROP TABLE IF EXISTS `stat_increase`;
CREATE TABLE `stat_increase` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `attribute_value` int DEFAULT NULL,
                                 `increase` decimal(5,2) DEFAULT NULL,
                                 `attribute_id` bigint NOT NULL,
                                 `stat_id` bigint NOT NULL,
                                 PRIMARY KEY (`id`),
                                 KEY `FKeb00wgio21cpbvlrk41bwc7hv` (`attribute_id`),
                                 KEY `FKct8ak4hspum3mxl5r4lgki91h` (`stat_id`),
                                 CONSTRAINT `FKct8ak4hspum3mxl5r4lgki91h` FOREIGN KEY (`stat_id`) REFERENCES `stat` (`id`),
                                 CONSTRAINT `FKeb00wgio21cpbvlrk41bwc7hv` FOREIGN KEY (`attribute_id`) REFERENCES `attribute` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3321 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `stat_increase_amulet`
--

DROP TABLE IF EXISTS `stat_increase_amulet`;
CREATE TABLE `stat_increase_amulet` (
                                        `id` bigint NOT NULL AUTO_INCREMENT,
                                        `flat_increase` int DEFAULT NULL,
                                        `percentage_increase` decimal(3,2) DEFAULT NULL,
                                        `amulet_id` bigint NOT NULL,
                                        `stat_id` bigint NOT NULL,
                                        PRIMARY KEY (`id`),
                                        KEY `FK5dojhf7jv04iols6gk6qfsjmv` (`amulet_id`),
                                        KEY `FKhb0i1ewtvdqhjisu9spipeqpa` (`stat_id`),
                                        CONSTRAINT `FK5dojhf7jv04iols6gk6qfsjmv` FOREIGN KEY (`amulet_id`) REFERENCES `amulet` (`id`),
                                        CONSTRAINT `FKhb0i1ewtvdqhjisu9spipeqpa` FOREIGN KEY (`stat_id`) REFERENCES `stat` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `stat_increase_armor`
--

DROP TABLE IF EXISTS `stat_increase_armor`;
CREATE TABLE `stat_increase_armor` (
                                       `id` bigint NOT NULL AUTO_INCREMENT,
                                       `flat_increase` decimal(5,2) DEFAULT NULL,
                                       `armor_id` bigint NOT NULL,
                                       `stat_id` bigint NOT NULL,
                                       PRIMARY KEY (`id`),
                                       KEY `FK4g2jsno50lox38e6ka0kwjuc0` (`armor_id`),
                                       KEY `FK5qwtx6rirk6mhvf3awb5nspqk` (`stat_id`),
                                       CONSTRAINT `FK4g2jsno50lox38e6ka0kwjuc0` FOREIGN KEY (`armor_id`) REFERENCES `armor` (`id`),
                                       CONSTRAINT `FK5qwtx6rirk6mhvf3awb5nspqk` FOREIGN KEY (`stat_id`) REFERENCES `stat` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=442 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `stats_weapon_s`
--

DROP TABLE IF EXISTS `stats_weapon_s`;
CREATE TABLE `stats_weapon_s` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `advance` char(1) DEFAULT NULL,
                                  `elemental_attack` int DEFAULT NULL,
                                  `motivity` char(1) DEFAULT NULL,
                                  `name` varchar(60) DEFAULT NULL,
                                  `physical_attack` int DEFAULT NULL,
                                  `sprite` varchar(300) DEFAULT NULL,
                                  `technique` char(1) DEFAULT NULL,
                                  `total_attack` int DEFAULT NULL,
                                  `weight` decimal(3,1) DEFAULT NULL,
                                  `current_level` bigint NOT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `FKrq7igldle12ejjc0n0xnn1ada` (`current_level`),
                                  CONSTRAINT `FKrq7igldle12ejjc0n0xnn1ada` FOREIGN KEY (`current_level`) REFERENCES `weapon_upgrade_s` (`current_lvl`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

