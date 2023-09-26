package com.sertifiakt.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {


  private String id;

  private String name;

  private String jurusan;

  private String role;


}
