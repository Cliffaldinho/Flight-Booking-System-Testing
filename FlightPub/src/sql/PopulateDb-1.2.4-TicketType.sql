USE flightpub_group1;

LOCK TABLES `TicketType` WRITE;
/*!40000 ALTER TABLE `TicketType` DISABLE KEYS */;

INSERT INTO `TicketType` (`TicketCode`, `Name`, `Transferrable`, `Refundable`, `Exchangeable`, `FrequentFlyerPoints`)
VALUES
	('A','Standby',b'0',b'0',b'0',b'0'),
	('B','Premium Discounted',b'0',b'0',b'0',b'0'),
	('C','Discounted',b'0',b'0',b'0',b'1'),
	('D','Standard',b'0',b'0',b'0',b'1'),
	('E','Premium',b'1',b'0',b'1',b'1'),
	('F','ld',b'1',b'1',b'1',b'1'),
	('G','Platinum',b'1',b'1',b'1',b'1');

/*!40000 ALTER TABLE `TicketType` ENABLE KEYS */;
UNLOCK TABLES;