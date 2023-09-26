package com.sertifiakt.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;


@RestController
@RequestMapping("api/v1/certificate")
public class CertificateController {

  private final CertificateService service;
  public static final String DIRECTORY = System.getenv("PWD").concat("/assets/");


  @Autowired
  public CertificateController(CertificateService service) {
    this.service = service;
  }

  @GetMapping
  public String get(){
    return "Hallo Gaes";
  }
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<byte[]> getCertificate(@RequestBody CreateCertificateRequest request) throws IOException {

    byte[] data = service.getCertificate(request);
    System.out.println("Jalan");
    return ResponseEntity.status(HttpStatus.OK).body(data);

  }

  // Define a method to download files
  @GetMapping("download")
  public ResponseEntity<Resource> downloadFiles() throws IOException {
    Path filePath = Paths.get(DIRECTORY).toAbsolutePath().normalize().resolve("sertifikat-out.pdf");
    if(!Files.exists(filePath)) {
      throw new FileNotFoundException("sertifikat-out.pdf was not found on the server");
    }
    Resource resource = new UrlResource(filePath.toUri());
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("File-Name", "sertifikat-out.pdf");
    httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
    return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
            .headers(httpHeaders).body(resource);
  }
}
