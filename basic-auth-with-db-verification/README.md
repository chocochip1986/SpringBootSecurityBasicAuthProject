1. Create a user via API call. The `POST` call is not guarded by Spring Security
```
localhost:8080/api/v1/user
```
Note the username. The password is hardcoded to be `password` for all users

2. Fire a get call with http basic authentication `username` and `password`
```
localhost:8080/api/v1/user/<username of the fellow you just created>
```
Only the `GET` call is secured by spring.