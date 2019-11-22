DROP SCHEMA IF EXISTS `flightpub_group1`;
CREATE SCHEMA `flightpub_group1`;

USE flightpub_group1;

DROP TABLE IF EXISTS `bookings`;
DROP TABLE IF EXISTS `userbookings`;
DROP TABLE IF EXISTS `FlightInformation`;
DROP TABLE IF EXISTS `Price`;
DROP TABLE IF EXISTS `Flights`;
DROP TABLE IF EXISTS `Distances`;
DROP TABLE IF EXISTS `Destinations`;
DROP TABLE IF EXISTS `Availability`;
DROP TABLE IF EXISTS `PlaneType`;
DROP TABLE IF EXISTS `TicketType`;
DROP TABLE IF EXISTS `TicketClass`;
DROP TABLE IF EXISTS `AirlineReviews`;
DROP TABLE IF EXISTS `AirlineReviewsOverallScore`;
DROP TABLE IF EXISTS `Airlines`;
DROP TABLE IF EXISTS `Country`;
DROP TABLE IF EXISTS `users`;


CREATE TABLE `users` 
  ( 
     `id`          INT(12) auto_increment, 
     `username`    VARCHAR(50) NOT NULL UNIQUE, 
     `password`    VARCHAR(64) NOT NULL, 
     `firstname`   VARCHAR(50) NOT NULL DEFAULT '', 
     `middlename`  VARCHAR(50) NULL, 
     `lastname`    VARCHAR(50) NULL, 
     `dob`         VARCHAR(25) NULL, 
     `email`       VARCHAR(50) NULL, 
     `company`     VARCHAR(50) NULL, 
     `address`     VARCHAR(100) NULL, 
     `phonenumber` VARCHAR(20) NULL, 
     PRIMARY KEY (`id`) 
  ) 
engine=innodb 
auto_increment=1 
DEFAULT charset=latin1; 

CREATE TABLE `Country` (
  `countryCode2` char(2) NOT NULL,
  `countryCode3` char(3) NOT NULL,
  `countryName` varchar(80) NOT NULL DEFAULT '',
  `alternateName1` varchar(80) NOT NULL DEFAULT '',
  `alternateName2` varchar(80) NOT NULL DEFAULT '',
  `motherCountryCode3` char(3) NOT NULL DEFAULT '',
  `motherCountryComment` varchar(80) NOT NULL DEFAULT '',
  PRIMARY KEY (`countryCode3`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `Airlines` (
  `AirlineCode` char(2) NOT NULL,
  `AirlineName` varchar(30) NOT NULL,
  `CountryCode3` char(3) NOT NULL,
  `AirlineRating` decimal(3,1) DEFAULT 0,
  PRIMARY KEY (`AirlineCode`),
  KEY `AirlinesCountryCode3_FK` (`CountryCode3`),
  CONSTRAINT `AirlinesCountryCode3_FK` FOREIGN KEY (`CountryCode3`) REFERENCES `Country` (`countryCode3`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `TicketClass` (
  `ClassCode` char(3) NOT NULL,
  `Details` varchar(20) NOT NULL,
  PRIMARY KEY (`ClassCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `TicketType` (
  `TicketCode` char(1) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Transferrable` bit(1) NOT NULL,
  `Refundable` bit(1) NOT NULL,
  `Exchangeable` bit(1) NOT NULL,
  `FrequentFlyerPoints` bit(1) NOT NULL,
  PRIMARY KEY (`TicketCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `PlaneType` (
  `PlaneCode` varchar(20) NOT NULL,
  `Details` varchar(50) NOT NULL,
  `NumFirstClass` int(11) NOT NULL,
  `NumBusiness` int(11) NOT NULL,
  `NumPremiumEconomy` int(11) NOT NULL,
  `Economy` int(11) NOT NULL,
  PRIMARY KEY (`PlaneCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `Availability` (
  `AirlineCode` char(2) NOT NULL,
  `FlightNumber` varchar(6) NOT NULL,
  `DepartureTime` datetime NOT NULL,
  `ClassCode` char(3) NOT NULL,
  `TicketCode` char(1) NOT NULL,
  `NumberAvailableSeatsLeg1` int(11) NOT NULL,
  `NumberAvailableSeatsLeg2` int(11) DEFAULT 0,
  `AvailabilityId` int AUTO_INCREMENT NOT NULL,
  PRIMARY KEY (`AirlineCode`,`FlightNumber`,`DepartureTime`,`ClassCode`,`TicketCode`),
  KEY `AvailabilityClassCode_FK` (`ClassCode`),
  KEY `AvailabilityTicketCode_FK` (`TicketCode`),
  KEY `AvailabilityId` (`AvailabilityId`),
  CONSTRAINT `AvailabilityTicketCode_FK` FOREIGN KEY (`TicketCode`) REFERENCES `TicketType` (`TicketCode`),
  CONSTRAINT `AvailabilityAirlineCode_FK` FOREIGN KEY (`AirlineCode`) REFERENCES `Airlines` (`AirlineCode`),
  CONSTRAINT `AvailabilityClassCode_FK` FOREIGN KEY (`ClassCode`) REFERENCES `TicketClass` (`ClassCode`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `Destinations` (
  `DestinationCode` char(3) NOT NULL,
  `Airport` varchar(30) NOT NULL,
  `CountryCode3` char(3) NOT NULL,
  PRIMARY KEY (`DestinationCode`),
  KEY `DestinationCountryCode_FK` (`CountryCode3`),
  CONSTRAINT `DestinationCountryCode_FK` FOREIGN KEY (`CountryCode3`) REFERENCES `Country` (`countryCode3`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `Distances` (
  `DestinationCode1` char(3) NOT NULL,
  `DestinationCode2` char(3) NOT NULL,
  `DistancesInKms` int(11) NOT NULL,
  PRIMARY KEY (`DestinationCode1`,`DestinationCode2`),
  KEY `DestinationCode2_FK` (`DestinationCode2`),
  CONSTRAINT `DestinationCode2_FK` FOREIGN KEY (`DestinationCode2`) REFERENCES `Destinations` (`DestinationCode`),
  CONSTRAINT `DestinationCode1_FK` FOREIGN KEY (`DestinationCode1`) REFERENCES `Destinations` (`DestinationCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `Flights` (
  `id` INT NOT NULL AUTO_INCREMENT,  -- Surrogate key to maintain ActiveJDBC compatibility
  `AirlineCode` char(2) NOT NULL,
  `FlightNumber` varchar(6) NOT NULL,
  `DepartureCode` char(3) NOT NULL,
  `StopOverCode` char(3) DEFAULT NULL,
  `DestinationCode` char(3) NOT NULL,
  `DepartureTime` datetime NOT NULL,
  `ArrivalTimeStopOver` datetime DEFAULT NULL,
  `DepartureTimeStopOver` datetime DEFAULT NULL,
  `ArrivalTime` datetime NOT NULL,
  `PlaneCode` varchar(20) NOT NULL,
  `Duration` int(11) NOT NULL,
  `DurationSecondLeg` int(11) DEFAULT 0,
  PRIMARY KEY (`AirlineCode`,`FlightNumber`,`DepartureTime`),
  KEY `FlightsDepartureCode_FK` (`DepartureCode`),
  KEY `FlightsStopOverCode_FK` (`StopOverCode`),
  KEY `FlightsDestinationCode_FK` (`DestinationCode`),
  KEY `FlightsPlaneCode_FK` (`PlaneCode`),
  CONSTRAINT `FlightsPlaneCode_FK` FOREIGN KEY (`PlaneCode`) REFERENCES `PlaneType` (`PlaneCode`),
  CONSTRAINT `FlightsAirlineCode_FK` FOREIGN KEY (`AirlineCode`) REFERENCES `Airlines` (`AirlineCode`),
  CONSTRAINT `FlightsDepartureCode_FK` FOREIGN KEY (`DepartureCode`) REFERENCES `Destinations` (`DestinationCode`),
  CONSTRAINT `FlightsDestinationCode_FK` FOREIGN KEY (`DestinationCode`) REFERENCES `Destinations` (`DestinationCode`),
  CONSTRAINT `FlightsStopOverCode_FK` FOREIGN KEY (`StopOverCode`) REFERENCES `Destinations` (`DestinationCode`),
  UNIQUE INDEX (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `Price` (
  `AirlineCode` char(2) NOT NULL,
  `FlightNumber` varchar(6) NOT NULL,
  `ClassCode` char(3) NOT NULL,
  `TicketCode` char(1) NOT NULL,
  `StartDate` datetime NOT NULL,
  `EndDate` datetime NOT NULL,
  `Price` decimal(10,2) NOT NULL,
  `PriceLeg1` decimal(10,2) DEFAULT NULL,
  `PriceLeg2` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`AirlineCode`,`FlightNumber`,`ClassCode`,`TicketCode`,`StartDate`),
  KEY `PriceClassCode_FK` (`ClassCode`),
  KEY `PriceTicketCode_FK` (`TicketCode`),
  CONSTRAINT `PriceAirlineCode_FK` FOREIGN KEY (`AirlineCode`) REFERENCES `Airlines` (`AirlineCode`),
  CONSTRAINT `PriceClassCode_FK` FOREIGN KEY (`ClassCode`) REFERENCES `TicketClass` (`ClassCode`),
  CONSTRAINT `PriceTicketCode_FK` FOREIGN KEY (`TicketCode`) REFERENCES `TicketType` (`TicketCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `AirlineReviews` (
  `AirlineReviewId` int AUTO_INCREMENT,
  `AirlineCode` char(2) NOT NULL,
  `AirlineOverallRating` decimal(3,1) NULL,
  `AirlineServiceRating` decimal(3,1) NULL,
  `AirlineFoodAndBeverageRating` decimal(3,1) NULL,
  `AirlineSeatAndComfortRating` decimal(3,1) NULL,
  `AirlinePunctualityRating` decimal(3,1) NULL,
  `AirlineCleanlinessRating` decimal(3,1) NULL,
  `AirlineEntertainementRating` decimal(3,1) NULL,
  `AirlineRecommendedRating` decimal(3,1) NULL,
  PRIMARY KEY (`AirlineReviewId`),
  CONSTRAINT `AirlinesCodeReviewLink_FK` FOREIGN KEY (`AirlineCode`) REFERENCES `Airlines` (`AirlineCode`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP PROCEDURE IF EXISTS `UpdateAirlineReviewScore`;

DELIMITER //
CREATE PROCEDURE UpdateAirlineReviewScore(IN AirlineCodeInput char(2)) 
BEGIN

SET SQL_SAFE_UPDATES=0;
UPDATE airlines a SET a.AirlineRating = 
(SELECT AVG(`AirlineOverallRating`) FROM AirlineReviews ar WHERE ar.AirlineCode = AirlineCodeInput)
WHERE a.AirlineCode = AirlineCodeInput;
SET SQL_SAFE_UPDATES=1;

END //
DELIMITER ;



