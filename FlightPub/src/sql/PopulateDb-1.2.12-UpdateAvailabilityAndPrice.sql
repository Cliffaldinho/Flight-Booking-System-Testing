USE flightpub_group1;

DELETE FROM Availability
WHERE TicketCode <> 'A';

DELETE FROM Price
WHERE TicketCode <> 'A';

SET SQL_SAFE_UPDATES = 0;
UPDATE availability
SET NumberAvailableSeatsLeg2 = 0
WHERE NumberAvailableSeatsLeg2 IS NULL;

UPDATE Price
SET priceLeg1 = price
WHERE priceLeg1 IS NULL;

UPDATE Price
SET priceLeg2 = 0
WHERE priceLeg2 IS NULL;