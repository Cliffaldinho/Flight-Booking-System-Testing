USE flightpub_group1;

LOCK TABLES `Destinations` WRITE;
/*!40000 ALTER TABLE `Destinations` DISABLE KEYS */;

INSERT INTO `Destinations` (`DestinationCode`, `Airport`, `CountryCode3`)
VALUES
	('ADL','Adelaide','AUS'),
	('AMS','Amsterdam','NLD'),
	('ATL','Atlanta','USA'),
	('BKK','Bangkok','THA'),
	('BNE','Brisbane','AUS'),
	('CBR','Canberra','AUS'),
	('CDG','Paris - Charles De Gaulle','FRA'),
	('CNS','Cairns','AUS'),
	('DOH','Doha','QAT'),
	('DRW','Darwin','AUS'),
	('DXB','Dubai','ARE'),
	('FCO','Rome-Fiumicino','ITA'),
	('GIG','Rio De Janeiro','BRA'),
	('HBA','Hobart','AUS'),
	('HEL','Helsinki','FIN'),
	('HKG','Hong Kong','CHN'),
	('HNL','Honolulu','USA'),
	('JFK','New York - JFK','USA'),
	('JNB','Johannesburg','ZAF'),
	('KUL','Kuala Lumpur','MYS'),
	('LAX','Los Angeles','USA'),
	('LGA','New York - Laguardia','USA'),
	('LGW','London-Gatwick','GBR'),
	('LHR','London-Heathrow','GBR'),
	('MAD','Madrid','ESP'),
	('MEL','Melbourne','AUS'),
	('MIA','Miami','USA'),
	('MUC','Munich','DEU'),
	('NRT','Tokyo - Narita','JPN'),
	('OOL','Gold Coast','AUS'),
	('ORD','Chicago - OHare Intl.','USA'),
	('ORY','Paris - Orly','FRA'),
	('PER','Perth','AUS'),
	('SFO','San Francisco','USA'),
	('SIN','Singapore','SGP'),
	('SYD','Sydney','AUS'),
	('VIE','Vienna','AUT'),
	('YYZ','Toronto','CAN');

/*!40000 ALTER TABLE `Destinations` ENABLE KEYS */;
UNLOCK TABLES;
