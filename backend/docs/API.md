## Principaux endpoints API

Vous pouvez utiliser [Postman](https://www.postman.com/) ou `curl` pour tester ces API.

### Authentification

- `POST /api/auth/register`  
  Inscription d’un utilisateur  
  **Body JSON attendu :**
  ```json
  {
    "username": "integrationUser",
    "password": "password"
  }
  ```

- `POST /api/auth/login`  
  Connexion d’un utilisateur  
  **Body JSON attendu :**
  ```json
  {
    "username": "integrationUser",
    "password": "password"
  }
  ```

