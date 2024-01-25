USE gym_membership;

DROP PROCEDURE IF EXISTS insert_member;

DELIMITER $$
CREATE PROCEDURE insert_member(first_name VARCHAR(50), last_name VARCHAR(50), phone VARCHAR(9), email VARCHAR(255))
BEGIN
	INSERT INTO `MEMBER` (first_name, last_name, phone, email) VALUES(first_name, last_name, phone, email);
END $$
DELIMITER ;