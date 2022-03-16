# TCP Socket Server

## Description

Sends a message to the connected TCP socket every 10 seconds. 

## Endpoints
- "http:localhost:{PORT}" --> Status Message to check which "invoice folder" the server is listening on
- "http:localhost:{PORT}/result" --> Read Json Data from listening "invoice folder"

## Environment Variables
  - JSON_STORAGE_PATH=/var/exercise01/invoices/    <-- (for example)
  - SOCKET_HOST=${CLIENT_HOST}  
  - SOCKET_PORT=${CLIENT_PORT}

## Docker Compose
```yml
  exercise01_vv_server:
    container_name: exercise01_vv_server
    image: vvthromildner/ss22_exercise01_server:latest
    ports:
      - "8080:8080"
    environment:
      - JSON_STORAGE_PATH=/var/exercise01/invoices/
      - SOCKET_HOST=exercise01_vv_client
      - SOCKET_PORT=${CLIENT_PORT}
    volumes:
      - json-volume:/var/exercise01/invoices/
    depends_on:
      - exercise01_vv_client
```
## Log Output

The container generates output logs. If you see messages like this, your container was started successfully.

### Startup
- exercise01_vv_server    | Reading Environment Variables...
- exercise01_vv_server    | JsonStoragePath: /var/exercise01/invoices/
- exercise01_vv_server    | SocketURL: exercise01_vv_client
- exercise01_vv_server    | SocketPort: 9020
- ///other output -- spring boot

### Runtime Log Output
- exercise01_vv_server    | Writing to Socket: {"firstName":"Peter","lastName":"Fricke","invoiceAmount":"11.65","invoiceDate":"2022-03-15 06:11:46"}
- 10 sec later
- exercise01_vv_server    | Writing to Socket: {"firstName":"Friedrich","lastName":"Freisen","invoiceAmount":"7.28","invoiceDate":"2022-03-15 06:12:06"}