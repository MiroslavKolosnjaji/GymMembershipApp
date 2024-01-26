USE gym_membership;

DROP PROCEDURE IF EXISTS insert_role;

DELIMITER $$
CREATE PROCEDURE insert_role(`role` VARCHAR(50))
BEGIN
	INSERT INTO `ROLE` (`role`) VALUES(`role`);
END $$
DELIMITER ;