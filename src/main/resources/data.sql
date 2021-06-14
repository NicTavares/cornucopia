INSERT INTO Equipment (name)
VALUES  ('knife'),
		('deboning knife'),
		('microwave'),
		('non-stick pan'),
		('electric kettle');

INSERT INTO Technique (name, difficulty)
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

INSERT INTO Usr (UUID, email, username, name, password,city,postalCode)
VALUES  (11111,'nic.tavares22@gmail.com', 'nic_tavares', 'Nic Tavares', '12345','Van','V5R1A1'),
		(11112, 'gloria@gmail.com', 'gloria', 'Gloria Zhang', '12345','Van','V5R1A1'),
		(11113, 'yuan@gmail.com', 'yuan', 'Yuan Gao', '12345','Van','V5R1A1'),
		(11114, 'polly@gmail.com', 'polly', 'Polly', '12345','Van','V5R1A1'),
		(11115, 'phoebe@gmail.com', 'phoebe', 'Phoebe', '12345','Van','V5R1A1'),
		(11116,'markus@gmail.com', 'markkk', 'Markus Naslund', '12345','Van','V5R1A1'),
		(11117,'hedy@gmail.com', 'hedy', 'Hedy Lamarr', 'e544785f-??','Van','V5R1A1');

		
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
		
INSERT INTO UsrFavouriteRecipe(usrUUID,recipeUUID)
VALUES  ( 11111,1),
		( 11111,2),
		( 11113,3),
		( 11114,4),
		( 11115,5);

INSERT INTO RecipeHasTag(UUID, name)
VALUES  (00001, 'easy'),
        (00002, 'healthy'),
        (00004, 'vegan'),
        (00004, 'desserts'),
        (00005, 'japanese');

INSERT INTO RecipeHasIngredient(UUID, name)
VALUES  (00001, 'chicken'),
        (00002, 'fish'),
        (00003, 'egg'),
        (00003, 'beef'),
        (00004, 'potato'),
        (00005, 'beef');

INSERT INTO RecipeHasEquipment(UUID, name)
VALUES  (00001,'knife'),
        (00002,'deboning knife'),
        (00005,'microwave'),
        (00002,'non-stick pan'),
        (00004,'non-stick pan'),
        (00003,'electric kettle');

INSERT INTO RecipeHasTechnique(UUID, name)
VALUES  (00001,'knife skills'),
        (00001,'deboning'),
        (00001,'steaming a fish'),
        (00002,'sous-vide'),
        (00002,'tea brewing');

-- INSERT INTO Picture(recipeUUID, pictureTitle, userUUID, filepath)
-- VALUES  (00001, 'Cover', 11111, './Pictures/cover11111.png'),
-- 		(00001, 'fig1', 11111, './Pictures/fig11111.png'),
-- 		(00002, 'Cover', 11112, './Pictures/cover11112.png'),
-- 		(00003, 'my results', 11115, './Pictures/results11113.png'),
-- 		(00004, 'Cover', 11115, './Pictures/cover_1213.png');

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

INSERT INTO UsrHasIngredient(usrUUID, name, quantity)
VALUES  (11114, "fish", 1),
        (11113, "egg", 8),
        (11115, "potato", 3),
        (11112, "chicken", 2),
        (11112, "potato", 4);

INSERT INTO UsrRateRecipe (usrUUID, recipeUUID, score)
VALUES  (11111, 1,7),
        (11114, 2,5),
        (11114, 3,7),
        (11112, 4,9),
        (11113, 5,8);

INSERT INTO Comment(recipeUUID, commentNumber, text, authorUUID)
VALUES  (1, 1, 'nice', 11111),
		(3, 2, 'Noice', 11114),
		(1, 3, 'bad', 11114),
		(4, 4, 'nice recipe :)', 11115),
		(4, 1, 'not good', 11115);