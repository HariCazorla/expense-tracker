# expense-tracker
A small project to understand spring boot, spring boot data JPA, and spring boot security. The expense-tracker is a service which comes with default set of categories. APIs are available to add/edit/delete categories and expenses. Swagger UI is integrated and it can be used to refer API documentation.

Application is available at http://localhost:8080

Swagger documentation is available at http://localhost:8080/swagger-ui.html#/

# To run the application
Start the postgres database using
```
docker-compose up
```
Start the spring application.

To access the REST end points, use basic authentication.

# Available rest end points
1. Get all categories. GET http://localhost:8080/api/v1/category
2. Get category by id. GET http://localhost:8080/api/v1/category?id={categoryId}
3. Get category by name. GET http://localhost:8080/api/v1/category?name={categoryName}
4. Add new category. POST http://localhost:8080/api/v1/category, request body: {"name":"...", "description":"..."}
5. Delete existing categories by names. DELETE http://localhost:8080/api/v1/category, request body: {"category":[name(s)]}
6. Delete an existing category by Id. DELETE http://localhost:8080/api/v1/category/{categoryId}
7. Update an existing category. PUT http://localhost:8080/api/v1/category/{oldcategoryname}, request body: {"name":"...", "description":"..."}
8. Get all expenses. GET http://localhost:8080/api/v1/expenses
9. Get expense by id. GET http://localhost:8080/api/v1/expenses?id={expenseId}
10. Get expense by name. GET http://localhost:8080/api/v1/expenses?name={expenseName}
