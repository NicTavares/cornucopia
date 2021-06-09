INSERT INTO Equipment (name)
VALUES  ('knife'),
		('deboning knife'),
		('microwave'),
		('non-stick pan'),
		('electric kettle');

INSERT INTO Usr (UUID, birthday, email, username, name, password,city,postalCode)
VALUES  (11111, '1994-03-01','nic.tavares22@gmail.com', 'nic_tavares', 'Nic Tavares', '12345','Van','V5R1A1'),
		(11112, '1994-02-01', 'gloria@gmail.com', 'gloria', 'Gloria Zhang', '12345','Van','V5R1A1'),
		(11113, '1994-01-01', 'yuan@gmail.com', 'yuan', 'Yuan Gao', '12345','Van','V5R1A1'),
		(11114, '1993-12-01', 'polly@gmail.com', 'polly', 'Polly', '12345','Van','V5R1A1'),
		(11115, '1993-11-01', 'phoebe@gmail.com', 'phoebe', 'Phoebe', '12345','Van','V5R1A1'),
		(11116, '1967-5-23', 'markus@gmail.com', 'markkk', 'Markus Naslund', '12345','Van','V5R1A1'),
		(11117, '1989-7-05', 'hedy@gmail.com', 'hedy', 'Hedy Lamarr', 'e544785f-??','Van','V5R1A1');

INSERT INTO Administrator(UUID, adminLevel,password,email)
VALUES  (11111, 1,'admin+1@gmail.com','12345'),
		(11112, 1,'admin+2@gmail.com','12345'),
		(11113, 1,'admin+3@gmail.com','12345'),
		(11114, 2,'admin+4@gmail.com','12345'),
		(11115, 3,'admin+5@gmail.com','12345');

INSERT INTO Course(UUID, text, length, name, requirementName, creatorUUID)
VALUES  (10011, 'Knives are great, hold them with your hand...', 10, 'How to use a knife', 'knife', 11111),
		(10012, 'Fishes, blah blah...', 10, 'How to debone a fish', 'deboning Knife', 11111),
		(10013, 'Press the buttons...', 15, 'How to operate a microwave', 'microwave', 11113),
		(10014, 'Don''t use metal spatula...', 5, 'How to use a non-stick pan', 'non-stick pan', 11111),
		(10015, 'Water at 80 degrees...', 5, 'How to brew green tea', 'electric kettle', 11112);
