package ua.edu.chmnu.fks.oop.design.users.dao.impl;

import lombok.extern.slf4j.Slf4j;
import ua.edu.chmnu.fks.oop.design.users.dao.AuthUserRepository;
import ua.edu.chmnu.fks.oop.design.users.exceptions.IllegalUserFormatException;
import ua.edu.chmnu.fks.oop.design.users.exceptions.UserIOException;
import ua.edu.chmnu.fks.oop.design.users.exceptions.UserNotFoundException;
import ua.edu.chmnu.fks.oop.design.users.model.AuthUser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AuthUserRepositoryFileImpl implements AuthUserRepository {
    private final String fileName;

    public AuthUserRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
    }

    private void writeAll(List<AuthUser> listAutUsers) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(this.fileName))) {
            for (AuthUser authUser: listAutUsers) {
                os.writeObject(authUser);
            }
        } catch (IOException e) {
            throw new UserIOException(e);
        }
    }

    @Override
    public AuthUser create(AuthUser authUser) {
        if (!new File(this.fileName).exists()) {
            try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(this.fileName))) {
                authUser.setId(1L);
                os.writeObject(authUser);
                return authUser;
            } catch (IOException e) {
                throw new UserIOException(e);
            }
        }

        List<AuthUser> authUsers = getAll();
        Long maxId = authUsers.stream().mapToLong(AuthUser::getId).max().orElse(0L);
        authUser.setId(maxId + 1);
        authUsers.add(authUser);
        writeAll(authUsers);
        return authUser;
    }

    @Override
    public AuthUser update(AuthUser authUser) {
        List<AuthUser> authUsers = getAll();
        AuthUser oldAuthUser = authUsers.stream()
                .filter(au -> au.getId().equals(authUser.getId()))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(authUser.getId()));
        oldAuthUser.setLogin(authUser.getLogin());
        oldAuthUser.setHashPassword(authUser.getHashPassword());
        oldAuthUser.setPersonalInformation(authUser.getPersonalInformation());

        writeAll(authUsers);

        return oldAuthUser;
    }

    @Override
    public void delete(AuthUser authUser) {
        List<AuthUser> authUsers = getAll();
        AuthUser existedAuthUser = authUsers.stream()
                .filter(au -> au.getId().equals(authUser.getId()))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(authUser.getId()));

        authUsers.remove(existedAuthUser);

        writeAll(authUsers);
    }

    @Override
    public void deleteAll() {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(this.fileName))) {
        } catch (IOException e) {
            throw new UserIOException(e);
        }
    }

    @Override
    public AuthUser getById(Long id) {
        return getAll().stream()
                .filter(au -> au.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public AuthUser getByLogin(String login) {
        return getAll().stream()
                .filter(au -> au.getLogin().equals(login))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(login));
    }

    @Override
    public List<AuthUser> getAll() {
        if (!new File(this.fileName).exists()) {
            throw new UserIOException("Repository file " + this.fileName + " was not found");
        }

        List<AuthUser> listAll = new ArrayList<>();
        try (ObjectInputStream os = new ObjectInputStream(new FileInputStream(this.fileName))) {
            while (true) {
                AuthUser authUser = (AuthUser)os.readObject();
                listAll.add(authUser);
                log.debug("Read auth user ---{}---" , authUser.getId());
            }
        } catch (EOFException e) {
          log.debug("Readed {} auth user records", listAll.size());
        } catch (IOException e) {
            throw new UserIOException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalUserFormatException(e);
        }

        return listAll;
    }
}
