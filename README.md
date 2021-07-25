# expense-tracker
A simple expense tracker using Spring boot
Application is available at http://localhost:8080

# To run the application
Start the postgres database using
```
docker-compose up
```
Start the spring application.

To access the REST end points, use basic auth with default user and take password from console.

# Available rest end points
1. Get all categories. GET http://localhost:8080/api/v1/category
2. Add new category. POST http://localhost:8080/api/v1/category, request body: {"name":"...", "description":"..."}
3. Delete existing categories by names. DELETE http://localhost:8080/api/v1/category, request body: {"category":[name(s)]}
4. Delete an existing category by Id. DELETE http://localhost:8080/api/v1/category/{categoryId}
5. Update an existing category. PUT http://localhost:8080/api/v1/category/{oldcategoryname}, request body: {"name":"...", "description":"..."}
