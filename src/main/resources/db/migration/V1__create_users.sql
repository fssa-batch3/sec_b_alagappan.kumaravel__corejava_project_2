USE product ;

CREATE TABLE IF NOT EXISTS players(
id int PRIMARY KEY AUTO_INCREMENT,
phone_number VARCHAR(20) UNIQUE NOT NULL,
user_name VARCHAR(50) NOT NULL,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50),
image text,
password VARCHAR(50) NOT NULL,
gender TINYINT,
address_id int NOT NULL,
date_of_birth DATE NOT NULL,
about VARCHAR(200),
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
is_active boolean DEFAULT true,
FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE TABLE IF NOT EXISTS address(
id INT PRIMARY KEY AUTO_INCREMENT,
area VARCHAR(50) NOT NULL,
district VARCHAR(50) NOT NULL
);

CREATE INDEX user_index
ON players(user_name);
CREATE TABLE IF NOT EXISTS teams(
id INT PRIMARY KEY AUTO_INCREMENT,
team_name VARCHAR(50) UNIQUE NOT NULL,
url text,
address_id int NOT NULL,
about VARCHAR(200) NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
created_by int NOT NULL,
modified_by int NOT NULL,
is_active boolean DEFAULT true,
FOREIGN KEY (address_id) REFERENCES address(id),
FOREIGN KEY (created_by) REFERENCES players(id),
FOREIGN KEY (modified_by) REFERENCES players(id)
);

CREATE TABLE IF NOT EXISTS team_member (
id INT PRIMARY KEY AUTO_INCREMENT,
team_id int NOT NULL,
user_id int NOT NULL,
is_captain boolean DEFAULT true,
is_active boolean DEFAULT true,
FOREIGN KEY (user_id) REFERENCES players(id),
FOREIGN KEY (team_id) REFERENCES teams(id)
);

CREATE TABLE IF NOT EXISTS match_request(
id INT PRIMARY KEY AUTO_INCREMENT,
created_by int NOT NULL,
to_team int,
address_id int,
status boolean DEFAULT true,
type_of_match TINYINT NOT NULL,
members INT NOT NULL,
members_age_from INT NOT NULL,
members_age_to INT NOT NULL,
match_time DATE NOT NULL,
location VARCHAR(200) NOT NULL,
information VARCHAR(200),
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY (created_by) REFERENCES team_member(id),
FOREIGN KEY (address_id) REFERENCES address(id),
FOREIGN KEY (to_team) REFERENCES teams(id)
);


CREATE TABLE IF NOT EXISTS request_response (
id INT PRIMARY KEY AUTO_INCREMENT,
request_id int NOT NULL,
from_team_id int NOT NULL,
status_of_response boolean NOT NULL,
FOREIGN KEY (request_id) REFERENCES match_request(id),
FOREIGN KEY (from_team_id) REFERENCES teams(id)
);





