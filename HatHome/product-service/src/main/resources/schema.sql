DROP TABLE IF EXISTS PRODUCT;

CREATE TABLE `hathome`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `detail` VARCHAR(700) NOT NULL,
  `price` DOUBLE NOT NULL,
  `color` VARCHAR(20) NOT NULL,
  `brand` VARCHAR(100) NOT NULL,
  `amount` INT NOT NULL,
  `type` VARCHAR(20) NOT NULL,
  `image` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`));