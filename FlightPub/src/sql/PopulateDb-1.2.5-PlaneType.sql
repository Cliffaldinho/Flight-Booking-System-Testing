USE flightpub_group1;

LOCK TABLES `PlaneType` WRITE;
/*!40000 ALTER TABLE `PlaneType` DISABLE KEYS */;

INSERT INTO `PlaneType` (`PlaneCode`, `Details`, `NumFirstClass`, `NumBusiness`, `NumPremiumEconomy`, `Economy`)
VALUES
	('747-100','Boeing 747-100',55,58,100,210),
	('757-200','Boeing 757-200',44,26,106,197),
	('757-300','Boeing 757-300',44,28,106,197),
	('767-200','Boeing 767-200',40,48,115,189),
	('767-300','Boeing 767-300',42,33,132,211),
	('767-400','Boeing 767-400',42,50,121,220),
	('A330-200','Airbus A330-200',42,40,120,177),
	('A330-300','Airbus A330-300',44,46,91,210),
	('A340-200','Airbus A340-200',42,50,124,208),
	('A340-300','Airbus A340-300',41,36,112,196),
	('A340-500','Airbus A340-500',39,62,131,187),
	('A340-600','Airbus A340-600',35,55,98,200),
	('A380','Airbus A380',46,47,111,203);

/*!40000 ALTER TABLE `PlaneType` ENABLE KEYS */;
UNLOCK TABLES;
