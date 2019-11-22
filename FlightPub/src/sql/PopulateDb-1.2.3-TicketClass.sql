USE flightpub_group1;

LOCK TABLES `TicketClass` WRITE;
/*!40000 ALTER TABLE `TicketClass` DISABLE KEYS */;

INSERT INTO `TicketClass` (`ClassCode`, `Details`)
VALUES
	('BUS','Business Class'),
	('ECO','Economy'),
	('FIR','First Class'),
	('PME','Premium Economy');

/*!40000 ALTER TABLE `TicketClass` ENABLE KEYS */;
UNLOCK TABLES;
