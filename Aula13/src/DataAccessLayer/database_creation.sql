CREATE SCHEMA `aula13-ifdm` ;

CREATE TABLE `aula13-ifdm`.`unidade_federativa` (
  `uf` CHAR(2) NOT NULL,
  `descricao` VARCHAR(45) NOT NULL,
  `media_saude` DOUBLE NOT NULL,
  `media_educacao` DOUBLE NOT NULL,
  `media_renda` DOUBLE NOT NULL,
  `media_ifdm` DOUBLE NOT NULL,
  PRIMARY KEY (`uf`));

  
CREATE TABLE `aula13-ifdm`.`cidade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uf` CHAR(2) NOT NULL,
  `descricao` VARCHAR(45) NOT NULL,
  `saude` DOUBLE NOT NULL,
  `edcacao` DOUBLE NOT NULL,
  `renda` DOUBLE NOT NULL,
  `ifdm` DOUBLE NOT NULL,
  `ranking_nacional` INT NULL,
  `ranking_estadual` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cidade_unidade_federativa_uf_idx` (`uf` ASC),
  CONSTRAINT `fk_cidade_unidade_federativa_uf`
    FOREIGN KEY (`uf`)
    REFERENCES `aula13-ifdm`.`unidade_federativa` (`uf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
