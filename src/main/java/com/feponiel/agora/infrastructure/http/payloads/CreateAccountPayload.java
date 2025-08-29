package com.feponiel.agora.infrastructure.http.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateAccountPayload {
  
  @NotBlank(message = "The name is required!")
  @Size(min = 1, max = 50, message = "Name must have between 1 and 50 characters!")
  private String name;

  @NotBlank(message = "The username is required!")
  @Size(min = 1, max = 30, message = "Username must have between 1 and 30 characters!")
  private String username;
  
  @NotBlank(message = "The email is required!")
  @Email(message = "Invalid email!")
  private String email;

  @NotBlank(message = "The password is required!")
  @Size(min = 8, message = "Password must have at least 8 characters!")
  private String password;

  public CreateAccountPayload() {}

  public CreateAccountPayload(String name, String username, String email, String password) {
    this.name = name;
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public String getName() {
    return this.name;
  }

  public String getUsername() {
    return this.username;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }
}
