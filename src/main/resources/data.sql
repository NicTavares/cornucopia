INSERT INTO Equipment (name)
VALUES  ('knife'),
		('deboning knife'),
		('microwave'),
		('non-stick pan'),
		('electric kettle');

INSERT INTO Technique (requirementName, difficulty)
VALUES  ('knife skills', 4),
		('deboning', 3),
		('steaming a fish', 2),
		('sous-vide', 2),
		('tea brewing', 1);

INSERT INTO Tag (name)
VALUES  ('easy'),
		('japanese'),
		('healthy'),
		('desserts'),
		('vegan');
		
INSERT INTO Ingredient (name) 
VALUES  ('beef'),
		('chicken'),
		('egg'),
		('potato'),
		('fish');

INSERT INTO Usr (UUID, birthday, email, username, name, password,city,postalCode)
VALUES  (11111, '1994-03-01','nic.tavares22@gmail.com', 'nic_tavares', 'Nic Tavares', '12345','Van','V5R1A1'),
		(11112, '1994-02-01', 'gloria@gmail.com', 'gloria', 'Gloria Zhang', '12345','Van','V5R1A1'),
		(11113, '1994-01-01', 'yuan@gmail.com', 'yuan', 'Yuan Gao', '12345','Van','V5R1A1'),
		(11114, '1993-12-01', 'polly@gmail.com', 'polly', 'Polly', '12345','Van','V5R1A1'),
		(11115, '1993-11-01', 'phoebe@gmail.com', 'phoebe', 'Phoebe', '12345','Van','V5R1A1'),
		(11116, '1967-5-23', 'markus@gmail.com', 'markkk', 'Markus Naslund', '12345','Van','V5R1A1'),
		(11117, '1989-7-05', 'hedy@gmail.com', 'hedy', 'Hedy Lamarr', 'e544785f-??','Van','V5R1A1');
		
INSERT INTO Recipe(UUID, name, text, averageScore, estimatedTime, uploaderUUID) 
VALUES  (00001, 'Parmesan chicken', "Cut the chicken...", 4.5, 13, 11111),
		(00002, 'Steamed fish', "Wash the fish...", 5.0, 14, 11111),
		(00003, 'Beef wellington', "Prepare eggs and beef...", 4.3, 20, 11111),
		(00004, 'French fries', "Cut the potato...", 2.5, 16, 11112),
		(00005, 'Quiche', "Wash the spinach...", 4.9, 30, 11112);

INSERT INTO Administrator(UUID, adminLevel, password,email)
VALUES  (11111, 1, '12345','admin+1@gmail.com'),
		(11112, 1, '12345', 'admin+2@gmail.com'),
		(11113, 1, '12345', 'admin+3@gmail.com'),
		(11114, 2, '12345','admin+4@gmail.com'),
		(11115, 3, '12345', 'admin+5@gmail.com');
		
INSERT INTO Message(UUID, text, senderUUID, sentTime, receiverUUID)
VALUES  (00001, 'Hello', 11111, '2021-05-04 23:31:00', 11112),
		(00002, 'Hi', 11112, '2021-05-04 23:33:00', 11111),
		(00003, 'What''s up', 11111, '2021-05-04 23:34:00', 11112),
		(00004, 'Not much bro', 11112, '2021-05-04 23:35:00', 11111),
		(00005, 'Check out this recipe', 11111, '2021-05-04 23:36:00', 11112);

INSERT INTO Course(UUID, text, length, name, requirementName, creatorUUID)
VALUES  (10011, 'Knives are great, hold them with your hand...', 10, 'How to use a knife', 'knife', 11111),
		(10012, 'Fishes, blah blah...', 10, 'How to debone a fish', 'deboning Knife', 11111),
		(10013, 'Press the buttons...', 15, 'How to operate a microwave', 'microwave', 11113),
		(10014, 'Don''t use metal spatula...', 5, 'How to use a non-stick pan', 'non-stick pan', 11111),
		(10015, 'Water at 80 degrees...', 5, 'How to brew green tea', 'electric kettle', 11112);
