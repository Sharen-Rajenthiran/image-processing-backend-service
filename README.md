# Image Processing Service 
A Spring Boot + local-dev directory REST API to perform
various transformations, and save images in different
formats. The system has user authentication, image retrieval,
and saving images in different formats. Currently using
local directory for saving and retrieving.

---

# Features
1. Perform transformation on a selected image.
   - Resize
   - Crop
   - Rotate
   - Apply filters (grayscale available for now)
   - Convert image format
2. Retrieve image.
3. User authentication.
   - Login
   - Register

---

# Tech Stack
- Java 17
- Spring Boot 3.5.5
- Spring Web
- Spring Security
- OpenCV
- JsonWebToken

---

# Setup
You will need these installed:
- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- An IDE of your choice

### Clone and Build
```
git clone https://github.com/Sharen-Rajenthiran/image-processing-backend-service.git
cd image-processing-backend-service
```

### Create ```images/dev``` folder if haven't
```
image-processing-service
    - src/main/java
        - com.example.image_processing_service
            - images
                - dev
```

### Run
```
mvn spring-boot:run
```
API will start at ```http://localhost:8080```


---
# API Documentation
- POST ```/login``` -> login user
- POST ```/register``` -> register user
- GET ```/profile``` -> get username 
- GET ```/images/{filename}``` -> get image based on filename
- GET ```/images?page=&limit=``` -> get all images with pagination and limit
- GET ```/``` -> show welcome message
- GET ```/health``` -> get API health
- GET ```/opencv-version``` -> get opencv version
- POST ```/images/{filename}/transform``` -> apply transformations to selected image

For these endpoints, you will need ```Authorization: Bearer <token>``` header:
- GET ```/profile``` -> get username
- GET ```/images/{filename}``` -> get image based on filename
- GET ```/images?page=&limit=``` -> get all images with pagination and limit
- GET ```/opencv-version``` -> get opencv version
- POST ```/images/{filename}/transform``` -> apply transformations to selected image


### Request body to login
```
{
  "username": "test",
  "password": "test"
}
```

### Response body after successful login
```
{
    "user": {
        "id": 1,
        "username": "test"
    },
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNzU3NjUzMzEyLCJleHAiOjE3NTc2NTM2MTJ9.4C5RrVdm208nBGYVZJOurCjPQw0dvT6GUC743Hkx3hI"
}
```

### Request body to register
```
{
  "username": "test",
  "password": "test"
}
```

### Response body after successful registration
```
{
    "user": {
        "id": 1,
        "username": "test"
    },
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNzU3NjUzMjc3LCJleHAiOjE3NTc2NTM1Nzd9.G4OT-F60IVlsBkE6bJt6PtKuUrVo3tchbu-295Icppg"
}
```

### Request body to get profile
```
NONE
```

### Response body after successful profile retrieval
```
{
    "username": "test2"
}
```


### Request body to get image based on id
```
NONE
```

### Response body after successful image retrieval
```
{
    "message": "Image successfully retrieved",
    "outputPath": "//image-processing-service//src//main//java//com//example//image_processing_service//images//dev//test.jpg",
    "imageName": "test.jpg",
    "imageFormat": "jpg"
}
```

### Request body to get images with pagination and limit
```
NONE
```

### Response body after successful images retrieval
```
{
    "page": 1,
    "limit": 1,
    "totalItems": 2,
    "images": [
        {
            "message": "OK",
            "outputPath": "\\image-processing-service\\src\\main\\java\\com\\example\\image_processing_service\\images\\dev\\test.jpg",
            "imageName": "test.jpg",
            "imageFormat": "jpg"
        }
    ],
    "totalPages": 2,
    "nextPage": "http://localhost:8080/images?page=2&limit=1",
    "prevPage": null
}
```

### Request body to root /
```
NONE
```

### Response body after successful root /
```
Welcome to Image Processing Service!
```

### Request body to get API health
```
NONE
```

### Response body after successful health retrieval
```
{
    "status": "UP",
    "message": "Service is running"
}
```

### Request body to get opencv version
```
NONE
```

### Response body after successful opencv version retrieval
```
OpenCV version: 4.10.0
```

### Request body to perform transformations
```
{
  "resize": { "width": 100, "height": 120 },
  "crop": { "width": 100, "height": 100, "x": 10, "y": 10 },
  "rotate": 90,
  "format": "png",
  "filters": { "grayscale": true }
}
```

### Response body after successful transformations
```
{
    "message": "Transformation successful",
    "inputPath": "//image-processing-service//src//main//java//com//example//image_processing_service//images//dev//test.jpg",
    "outputPath": "//image-processing-service//src//main//java//com//example//image_processing_service//images//dev//user_test.png",
    "imageName": "user_test",
    "imageFormat": "png",
    "transformations": {
        "resize": {
            "width": 100,
            "height": 120
        },
        "crop": {
            "width": 100,
            "height": 100,
            "x": 10,
            "y": 10
        },
        "rotate": 90,
        "format": "png",
        "filters": {
            "grayscale": true
        }
    }
}
```


---
# Improvements
- Swagger/OpenAPI documentation.
- Improve testing as testing was not implemented.
- Use cloud image storage next time.

---
# Author: Sharen Rajenthiran
