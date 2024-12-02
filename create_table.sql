
CREATE TABLE IF NOT EXISTS users(
	username VARCHAR(50) PRIMARY KEY,
	avatar VARCHAR(50),
	creationdate TIMESTAMP,
	lang VARCHAR(3)
);

CREATE TABLE IF NOT EXISTS games(
	id SERIAL PRIMARY KEY,
	username VARCHAR(50) REFERENCES users(username) ON DELETE CASCADE,
	score INT,
	moves INT,
	besttile INT,
	win BOOLEAN
);

INSERT INTO users (username, avatar, creationdate, lang)
VALUES ('TestUser', 'src/main/ressources/avatar/3.png', '2024-11-27 12:00:00', 'EN'),
		('Arthur', 'src/main/ressources/avatar/1.png', '2024-11-27 13:00:00', 'EN'),
		('Yoan', 'src/main/ressources/avatar/5.png', '2024-11-27 14:00:00', 'EN');

INSERT INTO games (username, score, moves, besttile, win)
VALUES ('TestUser', 2500, 150, 2048, TRUE),
		('TestUser', 1800, 120, 1024, FALSE),
		('TestUser', 3200, 180, 4096, TRUE),
		('TestUser', 900, 80, 512, FALSE),
		('TestUser', 4000, 200, 8192, TRUE),
		('Arthur', 1500, 110, 1024, FALSE),
	    ('Arthur', 5000, 210, 8192, TRUE),
	    ('Arthur', 2200, 140, 2048, TRUE),
	    ('Yoan', 750, 70, 256, FALSE),
	    ('Yoan', 3100, 175, 4096, TRUE);


