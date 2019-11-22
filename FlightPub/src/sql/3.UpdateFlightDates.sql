USE flightpub_group1;

SET FOREIGN_KEY_CHECKS=0;
ALTER TABLE `Flights` DISABLE KEYS;

DROP TABLE IF EXISTS `_f_tmp`;
CREATE TABLE `_f_tmp` LIKE `Flights`;

ALTER TABLE `_f_tmp`
	DROP PRIMARY KEY;

INSERT INTO `_f_tmp`
SELECT * FROM `Flights`;

UPDATE 	`_f_tmp`
SET `DepartureTime` = DATE_ADD(`DepartureTime`, INTERVAL 1 YEAR)
WHERE `DepartureTime` IS NOT NULL;

UPDATE 	`_f_tmp`
SET `ArrivalTimeStopOver` = DATE_ADD(`ArrivalTimeStopOver`, INTERVAL 1 YEAR)
WHERE `ArrivalTimeStopOver` IS NOT NULL;

UPDATE 	`_f_tmp`
SET `DepartureTimeStopOver` = DATE_ADD(`DepartureTimeStopOver`, INTERVAL 1 YEAR)
WHERE `DepartureTimeStopOver` IS NOT NULL;

UPDATE 	`_f_tmp`
SET `ArrivalTime` = DATE_ADD(`ArrivalTime`, INTERVAL 1 YEAR)
WHERE `ArrivalTime` IS NOT NULL;

DROP TABLE IF EXISTS `_f_new`;
CREATE TABLE `_f_new` LIKE `Flights`;

ALTER TABLE `_f_new` DISABLE KEYS;
INSERT INTO `_f_new`
SELECT * FROM `_f_tmp`;
ALTER TABLE `_f_new` ENABLE KEYS;

DROP TABLE `Flights`;
DROP TABLE `_f_tmp`;

RENAME TABLE `_f_new` TO `Flights`;

ALTER TABLE `Flights` ENABLE KEYS;
SET FOREIGN_KEY_CHECKS=1;

