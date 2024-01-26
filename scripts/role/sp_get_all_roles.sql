USE gym_membership;

DROP PROCEDURE IF EXISTS get_all_roles;

DELIMITER $$
CREATE PROCEDURE get_all_roles()
BEGIN
	SELECT role_id, `role` FROM `ROLE`;
END $$
DELIMITER ;