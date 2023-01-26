# Library Management System 📚
This is a library management system restful api that I created to use as an example of how to create rest api using string boot and deploy it to the cloud server using docker. This document will walk you through what this application is and how to use this api.

# Tech Stack ⚙
1. Spring Boot
2. Postgresql
3. Docker

# Live Site 🌐
[https://libraray-management-system.com/ap1/v1/student](https://library-management-system-g6oa.onrender.com/api/v1/student)

# About
This system helps library manager to manage, organize, and keep constant track of all the books available in the library and also manage students that have issued them.

# How to use it

## Add new book category

### Request
`POST`
```
https://library-management-system-g6oa.onrender.com/api/v1/category
```
`body`
```
{
	"categoryName": "computer science",
	"shortName": "CS"
}
```
### Response
```
{
	"id": 1,
	"categoryName": "computer science",
	"shortName": "CS"
}
```

## Get book category by id
### Request
`GET`
```
https://library-management-system-g6oa.onrender.com/api/v1/category/1
```
### Response
```
{
	"id": 1,
	"categoryName": "computer science",
	"shortName": "CS"
}
```

## Update book category
### Request
`PUT`
```
https://library-management-system-g6oa.onrender.com/api/v1/category/1
```
`body`
```
{
	"categoryName": "computer science",
	"shortName": "CSE"
}
```
### Response
```
{
	"id": 1,
	"categoryName": "computer science",
	"shortName": "CSE"
}
```

## Get all book categories
### Request
`GET`
```
https://library-management-system-g6oa.onrender.com/api/v1/category
```
### Response
```
[
	{
		"id": 1,
		"categoryName": "computer science",
		"shortName": "CSE"
	}
]
```

## Add new book

### Request
`POST`
```
https://library-management-system-g6oa.onrender.com/api/v1/book/category/1
```
`body`
```
{
	"title": "Head first java",
	"author": "Kathy Sierra"
}
```
### Response
```
{
	"id": 1,
	"title": "Head first java",
	"author": "Kathy Sierra",
	"description": null,
	"imageUrl": null,
	"category": {
		"id": 1,
		"categoryName": "computer science",
		"shortName": "CSE"
	}
}
```

## Get book by id

### Request
`GET`
```
https://library-management-system-g6oa.onrender.com/api/v1/book/1
```
### Response
```
{
	"id": 1,
	"title": "Head first java",
	"author": "Kathy Sierra",
	"description": null,
	"imageUrl": null,
	"category": {
		"id": 1,
		"categoryName": "computer science",
		"shortName": "CSE"
	}
}
```

## Update book

### Request
`PUT`
```
https://library-management-system-g6oa.onrender.com/api/v1/book/1/category/1
```
`body`
```
{
	"title": "Head first java",
	"author": "Kathy Sierra",
	"description": "This book is good for beginners..."
}
```
### Response
```
{
	"id": 1,
	"title": "Head first java",
	"author": "Kathy Sierra",
	"description": "This book is good for beginners...",
	"imageUrl": null,
	"category": {
		"id": 1,
		"categoryName": "computer science",
		"shortName": "CSE"
	}
}
```

## Get all books

### Request
`GET`
```
https://library-management-system-g6oa.onrender.com/api/v1/book
```
### Response
```
[
	{
		"id": 1,
		"title": "Head first java",
		"author": "Kathy Sierra",
		"description": "This book is good for beginners...",
		"imageUrl": null,
		"category": {
			"id": 1,
			"categoryName": "computer science",
			"shortName": "CSE"
		}
	}
]
```

## Add new student

### Request
`POST`
```
https://library-management-system-g6oa.onrender.com/api/v1/student
```
### Response
```
{
	"id": 3,
	"stream": "btech",
	"name": "aryan",
	"gender": "male",
	"dob": "02-10-2000",
	"contact": "7041452458",
	"email": "aryan@gmail.com",
	"imageUrl": null
}
```

## Get student by id

## Request
`GET`
```
https://library-management-system-g6oa.onrender.com/api/v1/student/1
```
## Response
```
{
	"id": 1,
	"stream": "bsc",
	"name": "akash",
	"gender": "male",
	"dob": "20-02-1999",
	"contact": "9857478977",
	"email": "akash@gmail.com",
	"imageUrl": "https://library-management-system-g6oa.onrender.com/api/v1/uploads/f9297031-a781-46dc-8a18-feabe6539290.jpg"
}
```

## Update student

## Request
`PUT`
```
https://library-management-system-g6oa.onrender.com/api/v1/student/1
```
`body`
```
{
	"stream": "bsc",
	"name": "akash sathi",
	"gender": "male",
	"dob": "20-02-1999",
	"contact": "9857478977",
	"email": "akash@gmail.com"
}
```
## Response
```
{
	"id": 1,
	"stream": "bsc",
	"name": "akash sathi",
	"gender": "male",
	"dob": "20-02-1999",
	"contact": "9857478977",
	"email": "akash@gmail.com",
	"imageUrl": "https://library-management-system-g6oa.onrender.com/api/v1/uploads/f9297031-a781-46dc-8a18-feabe6539290.jpg"
}
```

## Get all students

## Request
`GET`
```
https://library-management-system-g6oa.onrender.com/api/v1/student
```
## Response
```
[
	{
		"id": 2,
		"stream": "b pharmacy",
		"name": "rahul",
		"gender": "male",
		"dob": "15-05-2000",
		"contact": "7854784785",
		"email": "rahul@gmail.com",
		"imageUrl": null
	},
	{
		"id": 3,
		"stream": "btech",
		"name": "aryan",
		"gender": "male",
		"dob": "02-10-2000",
		"contact": "7041452458",
		"email": "aryan@gmail.com",
		"imageUrl": null
	},
	{
		"id": 1,
		"stream": "bsc",
		"name": "akash sathi",
		"gender": "male",
		"dob": "20-02-1999",
		"contact": "9857478977",
		"email": "akash@gmail.com",
		"imageUrl": "https://library-management-system-g6oa.onrender.com/api/v1/uploads/f9297031-a781-46dc-8a18-feabe6539290.jpg"
	}
]
```

## Upload student image

### Request
`POST`
```
https://library-management-system-g6oa.onrender.com/api/v1/student/1/uploadimage
```
### Response
```
image uploaded successfully!
```

## Upload book image

### Request
`POST`
```
https://library-management-system-g6oa.onrender.com/api/v1/book/1/uploadimage
```
### Response
```
image uploaded successfully!
```
