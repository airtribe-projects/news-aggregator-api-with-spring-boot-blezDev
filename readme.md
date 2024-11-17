# News API Service

This repository contains a RESTful API service for user authentication, preferences management, and fetching news. The API supports operations like user login, registration, updating preferences, retrieving news, and managing news history.

---

## Table of Contents

- [Features](#features)
- [Endpoints](#endpoints)
    - [Authentication](#authentication)
    - [Preferences](#preferences)
    - [News](#news)
    - [News History](#news-history)
- [Getting Started](#getting-started)
- [Technologies Used](#technologies-used)
- [Contributing](#contributing)

---

## Features

- User login and registration
- Fetch and update user preferences
- Fetch latest news and search news by keywords
- Manage and retrieve news reading history
- Robust exception handling for improved reliability

---

## Endpoints

### Authentication

#### 1. **Login**
- **Endpoint**: `/api/login`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "username": "string",
    "password": "string"
  }

- **Response**:
  ```json
  {
    token : "string"
  }
  
#### 2. **Register**
- **Endpoint**: `/api/register`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "username": "string",
    "password": "string",
    "email": "string"
  }

- **Response**:
  ```json
  {
   "User registered successfully"
  } 

### Preferences
- **Endpoint**: `/api/preferences`
- **Method**: `GET`
- **Request Body**:
  ```json
  {
    "username": "string",
    "password": "string",
    "email": "string"
  }

- **Response**:
  ```json
  {
   "User registered successfully"
  } 

### Preferences
- **Endpoint**: `/api/preferences`
- **Method**: `PUT`
- **Request Body**:
  ```json
  {
    "username": "string",
    "password": "string",
    "email": "string"
  }

- **Response**:
  ```json
  {
   "User registered successfully"
  } 


### News
- **Endpoint**: `/api/news/{userId}`
- **Method**: `GET`

- **Response**:
  ```json
  {
    "totalResults": Int,
    "articles": [
      {
        "source": {
          "id": "string",
          "name": "string"
        },
        "author": "string",
        "title": "string",
        "description": "string",
        "url": "string",
        "urlToImage": "string",
        "publishedAt": "string",
        "content": "string"
      }
    ],
    "status": "string"
  } 

### News Read
- **Endpoint**: `/api/news/read/{userId}`
- **Method**: `GET`
- **Response**:
  ```json
  {
    
    "totalResults": Int,
    "articles": [
      {
        "source": {
          "id": "string",
          "name": "string"
        },
        "author": "string",
        "title": "string",
        "description": "string",
        "url": "string",
        "urlToImage": "string",
        "publishedAt": "string",
        "content": "string"
      }
    ],
    "status": "string"
  }

### News Search
- **Endpoint**: `/api/news/search/{search}/{userId}`
- **Method**: `GET`
- **Response**:
  ```json
  {
    "totalResults": Int,
    "articles": [
      {
        "source": {
          "id": "string",
          "name": "string"
        },
        "author": "string",
        "title": "string",
        "description": "string",
        "url": "string",
        "urlToImage": "string",
        "publishedAt": "string",
        "content": "string"
      }
    ],
    "status": "string"
  }

---
