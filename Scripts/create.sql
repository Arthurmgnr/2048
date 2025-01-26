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