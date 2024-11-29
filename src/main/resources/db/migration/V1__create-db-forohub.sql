
CREATE TABLE IF NOT EXISTS `curso` (
  `id` int NOT NULL AUTO_INCREMENT,
  `categoria` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `curso` (`id`, `categoria`, `nombre`) VALUES
(1, 'programacion', 'java'),
(2, 'programacion', 'javascript');

CREATE TABLE IF NOT EXISTS `perfil` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `perfil` (`id`, `nombre`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

CREATE TABLE IF NOT EXISTS `respuesta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `mensaje` varchar(255) DEFAULT NULL,
  `solucion` int NOT NULL,
  `id_topico` int DEFAULT NULL,
  `id_usuario` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdy2m9spbhjl0ply8v5eblba5x` (`id_topico`),
  KEY `FK5hutcoe7y846pjv9m37l04fcr` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `topico` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `mensaje` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `id_curso` int DEFAULT NULL,
  `id_usuario` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8lw06jpau2opslm6b1njjnk4c` (`id_curso`),
  KEY `FK4plbgshkrxqy2wqnpsg0gwahq` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `contrasena` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `usuario` (`id`, `contrasena`, `email`, `nombre`) VALUES
(1, '$2y$10$5eLIGFhgyIxOo.hm7MGHU.NZrTQruUTHEJ8CdV..6/nDWEqVwpL5S', 'admin@admin.cl', 'admin');

CREATE TABLE IF NOT EXISTS `usuario_perfil` (
  `usuario_id` int NOT NULL,
  `perfil_id` int NOT NULL,
  KEY `FK22cgfn0obntlvqyfn33pyk24d` (`perfil_id`),
  KEY `FKnrjqnbylalt4ykxbcef24f57w` (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `usuario_perfil` (`usuario_id`, `perfil_id`) VALUES
(1, 1);
