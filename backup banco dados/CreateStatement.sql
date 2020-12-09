CREATE DATABASE `crud_web_livraria` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `cwl_livros` (
  `liv_id` int NOT NULL AUTO_INCREMENT,
  `liv_isbn` varchar(13) NOT NULL,
  `liv_titulo` varchar(45) NOT NULL,
  `liv_autor` varchar(45) NOT NULL,
  `liv_editora` varchar(45) NOT NULL,
  `liv_edicao` int NOT NULL,
  `liv_dt_lancamento` datetime NOT NULL,
  `liv_preco` double NOT NULL,
  PRIMARY KEY (`liv_id`),
  UNIQUE KEY `liv_id_UNIQUE` (`liv_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `cwl_funcionarios` (
  `fnc_id` int NOT NULL AUTO_INCREMENT,
  `fnc_nome` varchar(45) NOT NULL,
  `fnc_cpf` varchar(11) NOT NULL,
  `fnc_matricula` int NOT NULL,
  `fnc_salario` double NOT NULL,
  PRIMARY KEY (`fnc_id`),
  UNIQUE KEY `fnc_id_UNIQUE` (`fnc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `cwl_livros` (
  `liv_id` int NOT NULL AUTO_INCREMENT,
  `liv_isbn` varchar(13) NOT NULL,
  `liv_titulo` varchar(45) NOT NULL,
  `liv_autor` varchar(45) NOT NULL,
  `liv_editora` varchar(45) NOT NULL,
  `liv_edicao` int NOT NULL,
  `liv_dt_lancamento` datetime NOT NULL,
  `liv_preco` double NOT NULL,
  PRIMARY KEY (`liv_id`),
  UNIQUE KEY `liv_id_UNIQUE` (`liv_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `cwl_vendas_livros` (
  `vpl_quantia_livros` int NOT NULL,
  `fk_vendas` int NOT NULL,
  `fk_livros` int NOT NULL,
  PRIMARY KEY (`fk_vendas`,`fk_livros`),
  KEY `fk_vendas_idx` (`fk_vendas`),
  KEY `fk_livros_idx` (`fk_livros`),
  CONSTRAINT `fk_livros` FOREIGN KEY (`fk_livros`) REFERENCES `cwl_livros` (`liv_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_vendas` FOREIGN KEY (`fk_vendas`) REFERENCES `cwl_vendas` (`ven_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
