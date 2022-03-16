# TCP Socket Server

![example workflow](https://github.com/Thomas-Mildner/VV_SS22_Exercise01-Server/actions/workflows/docker-image.yml/badge.svg)


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
  exercise01-vv-server:
    container_name: exercise01-vv-server
    image: vvthromildner/ss22_exercise01_server:latest
    ports:
      - "8080:8080"
    environment:
      - JSON_STORAGE_PATH=/var/exercise01/invoices/
      - SOCKET_HOST=exercise01-vv-client
      - SOCKET_PORT=${CLIENT_PORT}
    volumes:
      - json-volume:/var/exercise01/invoices/
    depends_on:
      - exercise01_vv_client
```
## Log Output

The container generates output logs. If you see messages like this, your container was started successfully.

### Startup
- exercise01-vv-server    | 2022-03-16 10:40:48.772  INFO bc41da9b2613 -- [           main] d.t.v.e.Exercise01ServerApplication      : Reading Environment Variables...
- exercise01-vv-server    | 2022-03-16 10:40:48.777  INFO bc41da9b2613 -- [           main] d.t.v.e.Exercise01ServerApplication      : JsonStoragePath: /var/exercise01/invoices/
- exercise01-vv-server    | 2022-03-16 10:40:48.777  INFO bc41da9b2613 -- [           main] d.t.v.e.Exercise01ServerApplication      : SocketURL: exercise01-vv-client
- exercise01-vv-server    | 2022-03-16 10:40:48.778  INFO bc41da9b2613 -- [           main] d.t.v.e.Exercise01ServerApplication      : SocketPort: 9020
- exercise01-vv-server    | 2022-03-16 10:40:48.778  INFO bc41da9b2613 -- [           main] d.t.v.e.Exercise01ServerApplication      : Check Startup Parameters for valid Values
- exercise01-vv-server    | 2022-03-16 10:40:48.778  INFO bc41da9b2613 -- [           main] d.t.v.e.Exercise01ServerApplication      : Startup Parameter are valid -- Starting APP
- ///other output -- spring boot

### Runtime Log Output
- exercise01-vv-server    | 2022-03-16 10:41:09.195  INFO bc41da9b2613 -- [       Thread-0] d.t.v.e.s.InvoiceCreationService         : Writing to Socket: {"firstName":"Lennard","lastName":"Spöttel","invoiceAmount":"44.48","invoiceDate":"2022-03-16 10:40:59"}
- 10 sec later
- exercise01-vv-server    | 2022-03-16 10:41:29.247  INFO bc41da9b2613 -- [       Thread-0] d.t.v.e.s.InvoiceCreationService         : Writing to Socket: {"firstName":"Valentino","lastName":"Dienel","invoiceAmount":"15.67","invoiceDate":"2022-03-16 10:41:19"}
