USE gym_membership;

DROP PROCEDURE IF EXISTS get_all_users;

DELIMITER $$
CREATE PROCEDURE get_all_users()
BEGIN
	SELECT user_id, first_name, last_name, phone, email, `password`, city_id FROM `USER`;
END $$
DELIMITER ;