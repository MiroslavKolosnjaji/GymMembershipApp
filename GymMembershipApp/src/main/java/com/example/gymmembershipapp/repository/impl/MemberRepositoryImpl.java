package com.example.gymmembershipapp.repository.impl;

import com.example.gymmembershipapp.database.Database;
import com.example.gymmembershipapp.domain.Contact;
import com.example.gymmembershipapp.domain.Member;
import com.example.gymmembershipapp.domain.Person;
import com.example.gymmembershipapp.exception.DatabaseException;
import com.example.gymmembershipapp.exception.RepositoryException;
import com.example.gymmembershipapp.repository.Repository;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Miroslav Kološnjaji
 */
public class MemberRepositoryImpl implements Repository<Member, Long> {

    private final Database db = Database.getInstance();

    @Override
    public void add(Member member) throws RepositoryException {
        try {
            String call = "CALL insert_member(?,?,?,?)";
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            callableStatement.setString(1, member.getMember().getFirstName());
            callableStatement.setString(2, member.getMember().getLastName());
            callableStatement.setString(3, member.getMember().getContact().getPhone());
            callableStatement.setString(4, member.getMember().getContact().getEmail());
            callableStatement.execute();

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
            callableStatement.setString(1, member.getMember().getFirstName());
            callableStatement.setString(2, member.getMember().getLastName());
            callableStatement.setString(3, member.getMember().getContact().getPhone());
            callableStatement.setString(4, member.getMember().getContact().getEmail());
            callableStatement.execute();

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
            callableStatement.execute();

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

                memberList.add(new Member(id, new Person(firstName, lastName, new Contact(phone, email)), null));
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
    public Member findById(Long aLong) throws RepositoryException {
        return null;
    }
}
