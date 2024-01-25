USE gym_membership;

DROP PROCEDURE IF EXISTS delete_member;

DELIMITER $$
CREATE PROCEDURE delete_member(id BIGINT)
BEGIN
	DELETE FROM `MEMBER` WHERE member_id = id;
END $$
DELIMITER ;