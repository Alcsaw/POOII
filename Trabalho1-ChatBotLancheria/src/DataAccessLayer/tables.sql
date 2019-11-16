-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema lancheria
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema lancheria
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `lancheria` DEFAULT CHARACTER SET utf8 ;
USE `lancheria` ;

-- -----------------------------------------------------
-- Table `lancheria`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lancheria`.`categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lancheria`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lancheria`.`cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lancheria`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lancheria`.`produto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  `imagem` VARCHAR(200) NULL,
  `preco` DECIMAL(6,2) NOT NULL,
  `categoria_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_produto_categoria_idx` (`categoria_id` ASC) VISIBLE,
  CONSTRAINT `fk_produto_categoria`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `lancheria`.`categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lancheria`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lancheria`.`pedido` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data` DATETIME NOT NULL,
  `finalizado` TINYINT(1) NULL,
  `entregue` TINYINT(1) NULL,
  `cliente_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pedido_cliente1_idx` (`cliente_id` ASC) VISIBLE,
  CONSTRAINT `fk_pedido_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `lancheria`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lancheria`.`pedido_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lancheria`.`pedido_item` (
  `produto_id` INT NOT NULL,
  `pedido_id` INT NOT NULL,
  `quantidade` INT NOT NULL,
  `preco` DECIMAL(6,2) NOT NULL,
  `observacao` VARCHAR(200) NULL,
  PRIMARY KEY (`produto_id`, `pedido_id`),
  INDEX `fk_produto_has_pedido_pedido1_idx` (`pedido_id` ASC) VISIBLE,
  INDEX `fk_produto_has_pedido_produto1_idx` (`produto_id` ASC) VISIBLE,
  CONSTRAINT `fk_produto_has_pedido_produto1`
    FOREIGN KEY (`produto_id`)
    REFERENCES `lancheria`.`produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_has_pedido_pedido1`
    FOREIGN KEY (`pedido_id`)
    REFERENCES `lancheria`.`pedido` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
