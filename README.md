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

1. [Add new book category](#Add-new-book-category)
2. [Get book category by id](#Get-book-category-by-id)
3. [Update book category](#Update-book-category)
4. [Get all book categories](#Get-all-book-categories)
5. [Add new book](#Add-new-book)
6. [Get book by id](#Get-book-by-id)
7. [Update book](#Update-book)
8. [Get all books](#Get-all-books)
9. [Add new student](#Add-new-student)
10. [Get student by id](#Get-student-by-id)
11. [Update student](#Update-student)
12. [Get all students](#Get-all-students)
13. [Upload student image](#Upload-student-image)
14. [Upload book image](#Upload-book-image)
15. [Issue new book](#Issue-new-book)
16. [Get all issued books by student id](#Get-all-issued-books-by-student-id)
17. [Get issued book by book id](#Get-issued-book-by-book-id)
18. [Return issued book](#Return-issued-book)
19. [Delete student](#Delete-student)
20. [Delete book](#Delete-book)
21. [Delete book category](#Delete-book-category)

## Add new book category

### Request
`POST`
```
https://library-management-system-g6oa.onrender.com/api/v1/category
```
`JSON`
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
`JSON`
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
`JSON`
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
`JSON`
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
`JSON`
```
{
	"stream": "btech",
	"name": "aryan",
	"gender": "male",
	"dob": "02-10-2000",
	"contact": "7041452458",
	"email": "aryan@gmail.com"
}
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
`JSON`
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
`Multipart`
```
attribute = studentImage    file = student.jpg
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
`Multipart`
```
attribute = bookImage    file = book.jpg
```
### Response
```
image uploaded successfully!
```

## Issue new book

### Request
`GET`
```
https://library-management-system-g6oa.onrender.com/api/v1/student/1/issuebook/1
```
### Response
```
{
	"id": 1,
	"book": {
		"id": 1,
		"title": "Head first java",
		"author": "Kathy Sierra",
		"description": "This book is good for beginners...",
		"imageUrl": "https://library-management-system-g6oa.onrender.com/api/v1/uploads/fea58567-eda9-4575-8e53-50ee0393c190.jpg",
		"category": {
			"id": 1,
			"categoryName": "computer science",
			"shortName": "CSE"
		}
	},
	"issueDate": "27-01-2023",
	"expiringDate": "01-02-2023",
	"fine": 0
}
```

## Get all issued books by student id

### Request
`GET`
```
https://library-management-system-g6oa.onrender.com/api/v1/student/1/issuebook
```
### Response
```
[
	{
		"id": 1,
		"book": {
			"id": 1,
			"title": "Head first java",
			"author": "Kathy Sierra",
			"description": "This book is good for beginners...",
			"imageUrl": "https://library-management-system-g6oa.onrender.com/api/v1/uploads/fea58567-eda9-4575-8e53-50ee0393c190.jpg",
			"category": {
				"id": 1,
				"categoryName": "computer science",
				"shortName": "CSE"
			}
		},
		"issueDate": "27-01-2023",
		"expiringDate": "01-02-2023",
		"fine": 0
	}
]
```

## Get issued book by book id

### Request
`GET`
```
https://library-management-system-g6oa.onrender.com/api/v1/book/1/issuedto
```
### Response
```
{
	"id": 1,
	"book": {
		"id": 1,
		"title": "Head first java",
		"author": "Kathy Sierra",
		"description": "This book is good for beginners...",
		"imageUrl": "https://library-management-system-g6oa.onrender.com/api/v1/uploads/fea58567-eda9-4575-8e53-50ee0393c190.jpg",
		"category": {
			"id": 1,
			"categoryName": "computer science",
			"shortName": "CSE"
		}
	},
	"issueDate": "27-01-2023",
	"expiringDate": "01-02-2023",
	"fine": 0
}
```

## Return issued book

### Request
`GET`
```
https://library-management-system-g6oa.onrender.com/api/v1/student/1/returnbook/1
```
### Response
`STATUS 200`

## Delete student

### Request
`DELETE`
```
https://library-management-system-g6oa.onrender.com/api/v1/student/1
```
### Response
`STATUS 204`

## Delete book

### Request
`DELETE`
```
https://library-management-system-g6oa.onrender.com/api/v1/book/1
```
### Response
`STATUS 204`

## Delete book category

### Request
`DELETE`
```
https://library-management-system-g6oa.onrender.com/api/v1/category/1
```
### Response
`STATUS 204`
