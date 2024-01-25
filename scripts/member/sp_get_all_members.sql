USE gym_membership;

DROP PROCEDURE IF EXISTS get_all_members;

DELIMITER $$
CREATE PROCEDURE get_all_members()
BEGIN
	SELECT member_id, first_name, last_name, phone, email FROM `MEMBER`;
END $$
DELIMITER ;