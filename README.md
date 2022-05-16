# UserApi
Recruiting Task Backend - Java

Curl:

Post:
```
curl --location --request POST 'localhost:8000/user' \
 --header 'Content-Type: application/json' \
 --data-raw '{
     "firstName":"Bob",
     "lastName":"Smith",
     "email":"testMail@xyz.de"
 }'
```

Put:
```
curl --location --request PUT 'localhost:8000/user/3' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName":"Alice",
    "lastName":"Smith",
    "email":"testMail@xyz.de"
}'
```

GetOne:
```
curl --location --request GET 'localhost:8000/user/1'
```

GetAll:
```
curl --location --request GET 'localhost:8000/user'
```

Delete:
```
curl --location --request DELETE 'localhost:8000/user/4'
```