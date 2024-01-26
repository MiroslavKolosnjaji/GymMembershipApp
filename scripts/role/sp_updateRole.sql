USE gym_membership;

DROP PROCEDURE IF EXISTS update_role;

DELIMITER $$
CREATE PROCEDURE update_role(id BIGINT, `role` VARCHAR(50))
BEGIN
	UPDATE `ROLE` SET `role` = `role` WHERE role_id = id;
END $$
DELIMITER ;