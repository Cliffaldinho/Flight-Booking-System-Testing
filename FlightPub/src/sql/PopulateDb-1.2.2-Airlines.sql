USE flightpub_group1;

LOCK TABLES `Airlines` WRITE;
/*!40000 ALTER TABLE `Airlines` DISABLE KEYS */;

INSERT INTO `Airlines` (`AirlineCode`, `AirlineName`, `CountryCode3`)
VALUES
	('AA','American Airlines','USA'),
	('AC','Air Canada','CAN'),
	('AF','Air France','FRA'),
	('AI','Air India','IND'),
	('AM','Air Mexico','MEX'),
	('AR','Aerolineas Argentinas','ARG'),
	('AY','Finnair','FIN'),
	('BA','British Airways','GBR'),
	('CA','Air China','CHN'),
	('CI','China Airlines','CHN'),
	('CO','Continental Airlines','USA'),
	('CX','Cathay Pacific Airways','CHN'),
	('DJ','Virgin Blue','AUS'),
	('DL','Delta Air Lines','USA'),
	('EI','Aer Lingus','IRL'),
	('EK','Qatar Airways','QAT'),
	('IB','Iberia','ESP'),
	('JL','Japan Airlines','JPN'),
	('JQ','Jetstar Airlines','AUS'),
	('KE','Korean Airlines','KOR'),
	('KL','KLM-Royal Dutch Airlines','NLD'),
	('LH','Lufthansa','DEU'),
	('LY','El Al Israel Airlines','ISR'),
	('MH','Malaysia Airlines','MYS'),
	('MS','Egyptair','EGY'),
	('MX','Mexicana de Aviacion','MEX'),
	('NA','North American Airlines','USA'),
	('NW','Northwest Airlines','USA'),
	('NZ','Air New Zealand','NZL'),
	('OS','Austrian Airlines','AUT'),
	('PR','Philippine Airlines','PHL'),
	('QF','Qantas Airways','AUS'),
	('QR','Emirates Airlines','ARE'),
	('RJ','Royal Jordanian','JOR'),
	('SA','South African','ZAF'),
	('SK','SAS-Scandinavian Airlines','SWE'),
	('SQ','Singapore Airlines','SGP'),
	('SU','Aeroflot','RUS'),
	('TG','Thai Airways','THA'),
	('TK','Turkish Airlines','TUR'),
	('TW','Trans World Airlines','USA'),
	('UA','United Airlines','USA'),
	('VH','Aeropostal Alas de Venezuela','VEN'),
	('VS','Virgin Atlantic Airways','GBR');

/*!40000 ALTER TABLE `Airlines` ENABLE KEYS */;
UNLOCK TABLES;
