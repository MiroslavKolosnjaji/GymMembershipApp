package backend.repository.impl;

import backend.database.Database;
import backend.domain.Member;
import backend.exception.database.DatabaseException;
import backend.exception.repository.RepositoryException;
import backend.repository.MemberRepository;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Miroslav Kološnjaji
 */
public class MemberRepositoryImpl implements MemberRepository {

    private final Database db = Database.getInstance();

    @Override
    public void add(Member member) throws RepositoryException {
        try {
            String call = "CALL insert_member(?,?,?,?)";
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            callableStatement.setString(1, member.getFirstName());
            callableStatement.setString(2, member.getLastName());
            callableStatement.setString(3, member.getPhone());
            callableStatement.setString(4, member.getEmail());
            callableStatement.executeUpdate();

            callableStatement.close();
            db.confirmTransaction();
        } catch (SQLException | DatabaseException exception) {
            try {
                db.cancelTransaction();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
            exception.printStackTrace();
            throw new RepositoryException("An error occured while adding member to the database");

        }
    }

    @Override
    public void update(Member member) throws RepositoryException {
        try {
            String call = "CALL update_member(?,?,?,?,?)";
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            callableStatement.setLong(1, member.getMemberId());
            callableStatement.setString(1, member.getFirstName());
            callableStatement.setString(2, member.getLastName());
            callableStatement.setString(3, member.getPhone());
            callableStatement.setString(4, member.getEmail());
            callableStatement.executeUpdate();

            callableStatement.close();
            db.confirmTransaction();
        } catch (SQLException | DatabaseException exception) {
            try {
                db.cancelTransaction();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
            exception.printStackTrace();
            throw new RepositoryException("An error occured while updating member to the database");

        }
    }

    @Override
    public void delete(Member member) throws RepositoryException {
        try {
            String call = "CALL delete_member(?)";
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            callableStatement.setLong(1, member.getMemberId());
            callableStatement.executeUpdate();

            callableStatement.close();
            db.confirmTransaction();
        } catch (SQLException | DatabaseException exception) {
            try {
                db.cancelTransaction();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
            exception.printStackTrace();
            throw new RepositoryException("An error occured while deleting member from the database");
        }
    }

    @Override
    public List<Member> getAll() throws RepositoryException {
        try {
            List<Member> memberList = new ArrayList<>();

            String call = "CALL get_all_members()";
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("member_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");

                memberList.add(new Member(id, firstName, lastName, email, phone, null));
            }

            resultSet.close();
            callableStatement.close();
            db.confirmTransaction();
            return memberList;
        } catch (SQLException | DatabaseException exception) {
            try {
                db.cancelTransaction();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
            exception.printStackTrace();
            throw new RepositoryException("An error occured while loading members from database");

        }
    }

    @Override
    public Optional<Member> findById(Long aLong) throws RepositoryException {
        return Optional.empty();
    }
}
