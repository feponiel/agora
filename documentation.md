# AGORA: Documentation
This is a provisional documentation for AGORA. Currently, it only includes the available API routes. It is being shared while the first official release of the system is not yet launched. A complete, official documentation will be published once the system reaches its first stable release.

## Routes
<details>
  <summary><code>[POST] /accounts</code>: Create a new account.</summary>

  **Input (JSON Body)**:
  ```json
  {
    "name": "Felipe Elias",
    "username": "feponiel",
    "email": "felipedaniel.me@gmail.com",
    "password": "12345678"
  }
  ```

  **Output**:
  > Response: 201 (Created)
</details>

---