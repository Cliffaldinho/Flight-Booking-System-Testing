USE flightpub_group1;

CREATE TABLE `FlightInformation`
	AS  (SELECT flights.AirlineCode, 
                flights.FlightNumber, flights.DepartureTime, flights.ArrivalTimeStopOver, 
                flights.DepartureTimeStopOver, flights.ArrivalTime, flights.PlaneCode, flights.Duration, 
                flights.DurationSecondLeg, price.Price, price.PriceLeg1, price.PriceLeg2, price.ClassCode,
                d1.DestinationCode as "Departure", d2.DestinationCode as "Stopover", d3.DestinationCode as 
                "Arrival"
                FROM flights
                INNER JOIN price ON price.AirlineCode = flights.AirlineCode AND price.FlightNumber = flights.FlightNumber
                AND price.StartDate < flights.DepartureTime AND price.EndDate > flights.ArrivalTime

                INNER JOIN tickettype ON tickettype.TicketCode = price.TicketCode

                INNER JOIN availability ON availability.ClassCode = price.ClassCode AND
                availability.FlightNumber = flights.FlightNumber AND availability.DepartureTime = flights.DepartureTime
                AND availability.AirlineCode = flights.AirlineCode

                INNER JOIN destinations d1 ON flights.DepartureCode = d1.DestinationCode
                LEFT JOIN destinations d2 ON flights.StopOverCode = d2.DestinationCode

                INNER JOIN destinations d3 ON flights.DestinationCode = d3.DestinationCode

                INNER JOIN airlines ON price.AirlineCode = airlines.AirlineCode);
                
ALTER TABLE FlightInformation
ADD `flightInformationId` INT NOT NULL AUTO_INCREMENT PRIMARY KEY;
ALTER TABLE `FlightInformation` ADD FOREIGN KEY (`AirlineCode`) REFERENCES `Airlines`(AirlineCode);
ALTER TABLE `FlightInformation` ADD FOREIGN KEY (`Departure`) REFERENCES `Destinations`(DestinationCode);
ALTER TABLE `FlightInformation` ADD FOREIGN KEY (`Stopover`) REFERENCES `Destinations`(DestinationCode);
ALTER TABLE `FlightInformation` ADD FOREIGN KEY (`Arrival`) REFERENCES `Destinations`(DestinationCode);

CREATE INDEX `IX_FlightInformation_DepartureTime` ON `FlightInformation` (`DepartureTime`);

CREATE TABLE `userBookings` (
	`userBookingId` INT PRIMARY KEY AUTO_INCREMENT,
	`userId` INT NOT NULL,
	CONSTRAINT `user_booking` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `bookings` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `userBookingId` INT,
    `flightInformationId` INT,
    `legNo` INT NOT NULL,
	`reviewed` BOOLEAN DEFAULT FALSE,
	CONSTRAINT `user_booking_id` FOREIGN KEY (`userBookingId`) REFERENCES `userBookings` (`userBookingId`) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT `flight_booking` FOREIGN KEY (`flightInformationId`) REFERENCES `flightinformation` (`flightInformationId`) ON UPDATE NO ACTION ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;