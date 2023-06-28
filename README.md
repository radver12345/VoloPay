# VoloPay
API Development and Data Transformation

## Objective:

The objective of this assignment is to develop APIs, using the attached dataset, of purchases of different 
softwares through out the year. 
( I choose to directly import the attached CSV)

## Setting Up Project Environment
To set up the project, you can follow these steps:

1.Ensure you have Java Development Kit (JDK) installed on your system. You can download and install the latest JDK from the Oracle website.

2.Open the project in your preferred Java IDE (e.g., IntelliJ, Eclipse).

3.Resolve any project dependencies by importing the required libraries or configuring the project's build tool (e.g., Maven, Gradle).

4.Place the data.csv file in the project's root directory or specify the correct file path in the readDatasetFromCSV() method.

5.Build the project to ensure there are no compilation errors.

6.Run the application.The application should now be running locally. Open your web browser and access the application at http://localhost:8080

7.We can use tools like Postman or URL commands to send requests to the API endpoints as demonstrated earlier.

## Solutions:

### API 1:
#### End point :  /api/total_items

Use Cases :
1. Total item (total seats) sold in Marketting for last in q3 of the year? 

Expected O/P: returns integer 
Parameters: {start_date: DATE, end_date: DATE, department: string}

• URL:- http://localhost:8080/api/total_items?start_date=2022-01-01&end_date=2022-12-31&department=Tech

### API 2:
#### End point :  api/nth_most_total_item

Use Cases :
1. What is the second most sold item in terms of quantity sold in Q4?
2. What is the fourth most sold item in terms of total price in Q2? 
Expected Output: Returns the name of the item as a string 
Parameters: {item_by: ("quantity" | "price"), start_date: DATE, end_date: DATE, n: integer}

• URL:- http://localhost:8080/api/nth_most_total_item?item_by=quantity&start_date=2022-01-01&end_date=2022-12-31&n=87

### API 3:
#### End point :   /api/percentage_of_department_wise_sold_items

Use Cases:
1. What is the percentage of sold items (seats) department-wise? 
Expected Output: Returns a dictionary with department names and their respective 
percentages. 
Parameters: {start_date: Date, end_date: Date}
Percentage of sold items (seats) department-wise:

• URL:- http://localhost:8080/api/percentage_of_department_wise_sold_items?start_date=2023-01-01&end_date=2023-12-31

### API 4:
#### End point :  /api/monthly sales

Use Cases:
1. How does the monthly sales for any product look? 
Expected Output: Provides information about the monthly sales performance of a specific 
product.
Parameters: {product_id: string}
Monthly sales for a specific product:

• URL:- http://localhost:8080/api/monthly_sales?product=Apple&year=2022

## Points To Understand
#### API 1: Total items sold in Marketing for the last quarter of the year

#### • Test Cases:

• Verify that the API returns the correct total number of items sold in Marketing for the 
specified date range.

• Test with different start and end dates to ensure the API handles different quarters and 
years correctly.

• Test with different departments to verify that only items sold in Marketing are 
considered.

#### • API Documentation:
• Endpoint: /api/total_items

• Method: GET

• Parameters:

• start_date: The start date of the date range (format: "YYYY-MM-DD").

• end_date: The end date of the date range (format: "YYYY-MM-DD").

• department (optional): The department to filter the results (default: "Tech").

#### • Response:
• Returns an integer representing the total number of items sold in 
Marketing for the specified date range.

#### API 2: Nth most sold item based on quantity or total price within a specified date range

#### • Test Cases:

• Verify that the API returns the correct nth most sold item based on quantity and price 
for the specified date range.

• Test with different values of n to validate the retrieval of the nth most sold item.

#### • API Documentation:
• Endpoint: /api/nth_most_total_item

• Method: GET

• Parameters:

• item_by: The attribute to sort the items by ("quantity" or "price").

• start_date: The start date of the date range (format: "YYYY-MM-DD").

• end_date: The end date of the date range (format: "YYYY-MM-DD").

• n: The nth most sold item to retrieve (default: 87).

#### • Response:
• Returns a string representing the name of the nth most sold item.

• Test with different start and end dates to ensure the API handles different date ranges 
correctly


#### API 3: Percentage of sold items (seats) department-wise
#### • Test Cases:
• Verify that the API returns the correct percentage of sold items for each department for 
the specified date range.

• Test with different start and end dates to ensure the API handles different date ranges 
correctly.

#### • API Documentation:
• Endpoint: /api/percentage_of_department_wise_sold_items

• Method: GET

• Parameters:

• start_date: The start date of the date range (format: "YYYY-MM-DD").

• end_date: The end date of the date range (format: "YYYY-MM-DD").

#### • Response:
• Returns a JSON object containing department-wise percentages of sold 
items (seats).

#### API 4: Monthly sales for a specific product

#### • Test Cases:
• Verify that the API returns the correct monthly sales for the specified product and year.

• Test with different product names and years to validate the retrieval of monthly sales.

#### • API Documentation:
• Endpoint: /api/monthly_sales

• Method: GET

• Parameters:

• product: The name of the product.

• year: The year for which to retrieve the monthly sales.

#### • Response:
• Returns a JSON array containing the monthly sales for the specified 
product and year.

## Validation

### API 1: Total items sold in Marketing for the last quarter of the year

#### • Validate start_date and end_date:
• Ensure that both start_date and end_date are provided in the correct format ("YYYYMM-DD").

• Validate that start_date is before or equal to end_date.

• Verify that the date range is within a reasonable range (e.g., not too far in the past or 
future).

#### • Validate department:
• Check that department is a valid department name or ID in your dataset.

• If department is optional, provide a default value if not provided.

### API 2: Nth most sold item based on quantity or total price within a specified date range

#### • Validate item_by:
• Ensure that item_by is either "quantity" or "price".

• Handle cases where an invalid value is provided (e.g., return an appropriate error 
response).

#### • Validate start_date and end_date:

• Follow the same validation steps as in API 1.
#### • Validate n:

• Ensure that n is a positive integer.

• Handle cases where an invalid value is provided (e.g., return an appropriate error 
response).

### API 3: Percentage of sold items (seats) department-wise
#### • Validate start_date and end_date:
• Follow the same validation steps as in API 1.
### API 4: Monthly sales for a specific product
#### • Validate product:
• Ensure that product is a valid product name or ID in your dataset.
• Handle cases where an invalid value is provided (e.g., return an appropriate error 
response).
#### • Validate year:
• Ensure that year is a valid year in the correct format.
• Handle cases where an invalid value is provided (e.g., return an appropriate error 
response).


