CREATE DATABASE `accesphp`;

CREATE TABLE  `accesphp`.`utilizator` (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`username` VARCHAR( 20 ) NOT NULL ,
`password` VARCHAR( 20 ) NOT NULL
) ENGINE = MYISAM;

INSERT INTO `accesphp`.`utilizator` 
	(`id`, `username`, `password`) 
    VALUES 
    (NULL, 'admin', '123456');