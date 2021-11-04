# expense-tracker
A small project to understand spring boot, spring boot data JPA, and spring boot security. The expense-tracker is a service which comes with default set of categories. APIs are available to add/edit/delete categories and expenses. Swagger UI is integrated and it can be used to refer API documentation.

## To run the application
Start the expense-tracker.
```
docker-compose up
```

## To deploy on K8s
Follow steps described in [url](https://github.com/HariCazorla/Kubernetes/tree/master/expense-tracker) to deploy expense tracker on K8s.

## To access swagger ui
Application is available at http://localhost:8080
Swagger documentation is available at http://localhost:8080/swagger-ui.html#/
To access the REST end points, use basic authentication.

# Available rest end points
Refer swagger documentation for more information related to headers, parameters, and body information.
1. Get all categories. GET http://localhost:8080/api/v1/category
2. Get category by id. GET http://localhost:8080/api/v1/category?id={categoryId}
3. Get category by name. GET http://localhost:8080/api/v1/category?name={categoryName}
4. Add new category. POST http://localhost:8080/api/v1/category
5. Delete existing categories by names. DELETE http://localhost:8080/api/v1/category
6. Delete an existing category by Id. DELETE http://localhost:8080/api/v1/category/{categoryId}
7. Update an existing category. PUT http://localhost:8080/api/v1/category/{oldcategoryname}
8. Get all expenses. GET http://localhost:8080/api/v1/expenses
9. Get expense by id. GET http://localhost:8080/api/v1/expenses?id={expenseId}
10. Get expense by name. GET http://localhost:8080/api/v1/expenses?name={expenseName}
11. Add new expense. POST http://localhost:8080/api/v1/expenses
12. Delete existing expense. DELETE http://localhost:8080/api/v1/expenses/{expenseId}
13. Update an existing expense. PUT http://localhost:8080/api/v1/expenses/{oldExpenseId}
