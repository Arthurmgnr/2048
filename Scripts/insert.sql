INSERT INTO users (username, avatar, creationdate, lang)
VALUES ('TestUser', 'src/main/ressources/avatar/3.png', '2024-11-27 12:00:00', 'EN'),
		('Arthur', 'src/main/ressources/avatar/1.png', '2024-11-27 13:00:00', 'FR');


INSERT INTO games (username, score, moves, besttile, win)
VALUES ('TestUser', 1500, 50, 2048, TRUE),
		('TestUser', 1200, 45, 1024, FALSE),
		('TestUser', 1800, 60, 2048, TRUE),
		('TestUser', 1100, 40, 512, FALSE),
		('TestUser', 2000, 70, 4096, TRUE),
		('Arthur', 2000, 60, 4096, TRUE),
		('Arthur', 800, 30, 512, FALSE),
		('Arthur', 2500, 80, 8192, TRUE),
		('Arthur', 900, 35, 1024, FALSE),
		('Arthur', 1700, 55, 2048, TRUE),
		('Arthur', 1400, 45, 1024, FALSE),
		('Arthur', 2200, 65, 4096, TRUE),
		('Arthur', 1000, 40, 512, FALSE),
		('Arthur', 2600, 75, 8192, TRUE),
		('Arthur', 950, 37, 1024, FALSE),
		('Arthur', 1800, 50, 2048, TRUE),
		('Arthur', 1950, 58, 4096, TRUE),
		('Arthur', 72000, 120, 65536, TRUE);