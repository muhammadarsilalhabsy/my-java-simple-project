package com.sertifiakt.demo;


public interface UserService{

  User getUser(String id);

  void addUser(CreateUserRequest request);

  void getCertificate(String userId);
}
