package com.sertifiakt.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  @Autowired
  public UserServiceImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public User getUser(String id) {
    return repository.findById(id)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User tidak ditemukan!"));
  }

  @Override
  public void addUser(CreateUserRequest request) {

    User user = new User();
    user.setId(request.getId());
    user.setName(request.getName());
    user.setJurusan(request.getJurusan());
    user.setRole(request.getRole());

    repository.save(user);

  }

  @Override
  public void getCertificate(String userId) {

  }
}
