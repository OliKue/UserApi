# UserApi
Recruiting Task Backend - Java

## How to start
0. Clone the project

2. Build the project with maven:
`mvn package` 

2. Build docker image:
`docker build -t org/userapi .`

3. Run docker image:
`docker run -p 9000:9000 org/userapi`

## Endpoints
This api implements CRUD options for `/user`.

The following curl commands target localhost:9000. 
Make sure to change the address and port according to your environment.

### Create
* POST a new user
```
curl --location --request POST "localhost:9000/user" --header "Content-Type: application/json" --data-raw "{\"firstName\":\"Dave\",\"lastName\":\"Smith\",\"email\":\"testMail@xyz.de\"}"
```

### Read
* GET all users
 ```
 curl --location --request GET "localhost:9000/user"
 ```

* GET one user by id. Replace `{id}` with a number matching an id.
 ```
 curl --location --request GET "localhost:9000/user/{id}"
 ```

* GET all users with given first name. Replace `{firstName}` with a string to match the first names.
 ```
 curl --location --request GET "localhost:9000/user/{firstName}"
 ```

### Update
* PUT new user or update if existing. Replace `{id}` with a number matching an id.
```
curl --location --request PUT "localhost:9000/user/{id}" --header "Content-Type: application/json" --data-raw "{\"firstName\":\"Alice\",\"lastName\":\"Smith\",\"email\":\"testMail@xyz.de\"}"
```


### Delete
* DELETE user by id. Replace `{id}` with a number matching an id.
```
curl --location --request DELETE "localhost:9000/user/{id}"
```
