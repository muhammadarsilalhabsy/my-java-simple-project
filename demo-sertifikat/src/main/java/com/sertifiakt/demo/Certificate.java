package com.sertifiakt.demo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "certificate")
@Entity
public class Certificate {

  @Id
  private String id;

  @Column(name = "i_date")
  private LocalDate islamicDate;

  @Column(name = "m_date")
  private LocalDate masehiDate;


}
