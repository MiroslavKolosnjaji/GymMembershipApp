USE gym_membership;

DROP PROCEDURE IF EXISTS update_member;

DELIMITER $$
CREATE PROCEDURE update_member(id BIGINT, first_name VARCHAR(50), last_name VARCHAR(50), phone VARCHAR(9), email VARCHAR(255))
BEGIN
	UPDATE `MEMBER` SET first_name = first_name, last_name = last_name, phone = phone, email = email WHERE member_id = id;
END $$
DELIMITER ;