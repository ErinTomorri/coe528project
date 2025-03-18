# BookStore Application

This is a bookstore application for COE528 Project.

## Description

The BookStore Application is a Java-based GUI application that simulates a bookstore with the following features:
- Owner can manage books and customers
- Customers can buy books and earn/redeem points
- Points system that changes customer status based on points earned

## Requirements

- Java 8 or higher
- JavaFX

## How to Run

1. Make sure Java is installed on your system
2. Open the project in NetBeans IDE
3. Build the project
4. Run the main class `BookStoreApplication`

## Login Information

- Owner: 
  - Username: admin
  - Password: admin
  
- Sample Customer:
  - Username: jane
  - Password: password

## Features

- Owner Features:
  - Add/remove books
  - Add/remove customers
  - Manage book inventory

- Customer Features:
  - View books
  - Buy books
  - Earn points
  - Redeem points for discounts
  - Track status (Silver/Gold)

## State Pattern Implementation

The application uses the State pattern to manage customer status:
- Silver status (less than 1000 points)
- Gold status (1000 or more points)

The status of a customer changes automatically based on their points. 