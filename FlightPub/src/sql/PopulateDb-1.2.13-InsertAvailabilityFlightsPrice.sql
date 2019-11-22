USE flightpub_group1;

INSERT INTO `Availability` (`AirlineCode`, `FlightNumber`, `DepartureTime`, `ClassCode`, `TicketCode`, `NumberAvailableSeatsLeg1`, `NumberAvailableSeatsLeg2`)
VALUES
	('AA','AA1736','2017-09-23 07:50:00','BUS','A',18,18),
	('AA','AA1736','2017-09-23 09:50:00','BUS','A',18,18),
	('AA','AA1736','2017-09-23 12:50:00','BUS','A',18,18),
	('AA','AA1736','2017-09-23 16:50:00','BUS','A',18,18),
    
    
    ('KL','KL11','2017-09-23 09:55:00','BUS','A',15,15),
    ('KL','KL11','2017-09-23 09:45:00','BUS','A',14,14),
    ('KL','KL11','2017-09-23 09:35:00','BUS','A',17,17),
    ('KL','KL11','2017-09-23 09:25:00','BUS','A',11,11),
    
    
	('EK','EK001','2017-09-23 10:35:00','ECO','A',10,10),
	('EK','EK001','2017-09-23 09:35:00','ECO','A',10,10),
	('EK','EK001','2017-09-23 08:35:00','ECO','A',10,10),
	('EK','EK001','2017-09-23 07:35:00','ECO','A',10,10),
    
    ('EI','EI6969','2017-09-23 11:50:00','ECO','A',10,10),
    ('EI','EI6969','2017-09-23 12:50:00','ECO','A',10,10),
    ('EI','EI6969','2017-09-23 13:50:00','ECO','A',10,10),
    ('EI','EI6969','2017-09-23 14:50:00','ECO','A',10,10);
    
    
	INSERT INTO `Flights` (`AirlineCode`, `FlightNumber`, `DepartureCode`, `StopOverCode`, `DestinationCode`, `DepartureTime`, `ArrivalTimeStopOver`, `DepartureTimeStopOver`, `ArrivalTime`, `PlaneCode`, `Duration`, `DurationSecondLeg`)
VALUES
	('AA','AA1736','ATL','MIA','GIG','2017-09-23 07:50:00','2017-09-23 13:50:00','2017-09-23 14:50:00','2017-09-23 19:50:00','A380',360,300),
    ('AA','AA1736','ATL','MIA','GIG','2017-09-23 09:50:00','2017-09-23 15:50:00','2017-09-23 16:50:00','2017-09-23 21:50:00','A380',360,300),
    ('AA','AA1736','ATL','MIA','GIG','2017-09-23 12:50:00','2017-09-23 18:50:00','2017-09-23 19:50:00','2017-09-24 00:50:00','A380',360,300),
    ('AA','AA1736','ATL','MIA','GIG','2017-09-23 16:50:00','2017-09-23 22:50:00','2017-09-23 23:50:00','2017-09-24 04:50:00','A380',360,300),
        
        
	('KL','KL11','ATL','MIA','GIG','2017-09-23 09:55:00','2017-09-23 15:50:00','2017-09-23 16:50:00','2017-09-23 21:30:00','A380',355,280),
	('KL','KL11','ATL','MIA','GIG','2017-09-23 09:45:00','2017-09-23 15:40:00','2017-09-23 16:40:00','2017-09-23 21:20:00','A380',355,280),
	('KL','KL11','ATL','MIA','GIG','2017-09-23 09:35:00','2017-09-23 15:30:00','2017-09-23 16:30:00','2017-09-23 21:10:00','A380',355,280),
	('KL','KL11','ATL','MIA','GIG','2017-09-23 09:25:00','2017-09-23 15:20:00','2017-09-23 16:20:00','2017-09-23 21:00:00','A380',355,280),
    
	('EK','EK001','ATL','MIA','GIG','2017-09-23 10:35:00','2017-09-23 15:50:00','2017-09-23 16:50:00','2017-09-23 21:50:00','A380',315,300),
	('EK','EK001','ATL','MIA','GIG','2017-09-23 09:35:00','2017-09-23 14:50:00','2017-09-23 15:50:00','2017-09-23 20:50:00','A380',315,300),
	('EK','EK001','ATL','MIA','GIG','2017-09-23 08:35:00','2017-09-23 13:50:00','2017-09-23 14:50:00','2017-09-23 19:50:00','A380',315,300),
	('EK','EK001','ATL','MIA','GIG','2017-09-23 07:35:00','2017-09-23 12:50:00','2017-09-23 13:50:00','2017-09-23 18:50:00','A380',315,300),
    
	('EI','EI6969','ATL','MIA','GIG','2017-09-23 11:50:00','2017-09-23 15:50:00','2017-09-23 16:50:00','2017-09-23 20:50:00','A380',240,240),
	('EI','EI6969','ATL','MIA','GIG','2017-09-23 12:50:00','2017-09-23 16:50:00','2017-09-23 17:50:00','2017-09-23 21:50:00','A380',240,240),
	('EI','EI6969','ATL','MIA','GIG','2017-09-23 13:50:00','2017-09-23 17:50:00','2017-09-23 18:50:00','2017-09-23 22:50:00','A380',240,240),
	('EI','EI6969','ATL','MIA','GIG','2017-09-23 14:50:00','2017-09-23 18:50:00','2017-09-23 19:50:00','2017-09-23 23:50:00','A380',240,240);
    
 INSERT INTO `Price` (`AirlineCode`, `FlightNumber`, `ClassCode`, `TicketCode`, `StartDate`, `EndDate`, `Price`, `PriceLeg1`, `PriceLeg2`)
VALUES   
    ('AA','AA1736','BUS','G','2017-09-23 07:50:00','2017-09-23 19:50:00',3557.60,2500.00,1057.60),
    ('AA','AA1736','BUS','G','2017-09-23 09:50:00','2017-09-23 21:50:00',3657.60,2600.00,1057.60),
    ('AA','AA1736','BUS','G','2017-09-23 12:50:00','2017-09-24 00:50:00',3957.60,2900.00,1057.60),
    ('AA','AA1736','BUS','G','2017-09-23 16:50:00','2017-09-24 04:50:00',3457.60,2400.00,1057.60),
    
	('KL','KL11','BUS','G','2017-09-23 09:55:00','2017-09-23 21:30:00',8039.14,4000,4039.14),
	('KL','KL11','BUS','G','2017-09-23 09:45:00','2017-09-23 21:20:00',10039.14,6000,4039.14),
	('KL','KL11','BUS','G','2017-09-23 09:35:00','2017-09-23 21:10:00',9039.14,5000,4039.14),
	('KL','KL11','BUS','G','2017-09-23 09:25:00','2017-09-23 21:00:00',7039.14,3000,4039.14),
    
	('EK','EK001','ECO','A','2017-09-23 10:35:00','2017-09-23 21:50:00', 1485.23,774.17,711.06), 
	('EK','EK001','ECO','A','2017-09-23 09:35:00','2017-09-23 20:50:00', 1490.23,779.17,711.06), 
	('EK','EK001','ECO','A','2017-09-23 08:35:00','2017-09-23 19:50:00', 1285.23,1100,185.23), 
	('EK','EK001','ECO','A','2017-09-23 07:35:00','2017-09-23 18:50:00', 1185.23,1000,185.23), 
    
	('EI','EI6969','ECO','A','2017-09-23 11:50:00','2017-09-23 20:50:00',1258.27,627.23,630.64),
	('EI','EI6969','ECO','A','2017-09-23 12:50:00','2017-09-23 21:50:00',1358.27,727.23,630.64),
	('EI','EI6969','ECO','A','2017-09-23 13:50:00','2017-09-23 22:50:00',1458.27,827.23,630.64),
	('EI','EI6969','ECO','A','2017-09-23 14:50:00','2017-09-23 23:50:00',1558.27,927.23,630.64);
	