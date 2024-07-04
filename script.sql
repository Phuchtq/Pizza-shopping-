CREATE TABLE Customers (
    CustomerID VARCHAR(10) PRIMARY KEY,
    [Password] VARCHAR(20),
    ContactName VARCHAR(50),
    [Address] VARCHAR(250),
    Phone VARCHAR(20)
);

CREATE TABLE Categories (
    CategoryID VARCHAR(10) PRIMARY KEY,
    CategoryName VARCHAR(20),
    [Description] VARCHAR(250)
);

CREATE TABLE Suppliers (
    SupplierID VARCHAR(10) PRIMARY KEY,
    CompanyName VARCHAR(50),
    [Address] VARCHAR(250),
    Phone VARCHAR(20)
);

CREATE TABLE Account (
   AccountID VARCHAR(10) PRIMARY KEY,
   UserName VARCHAR(20),
   [Password] VARCHAR(20),
   FullName VARCHAR(20),
   [Type] bit
);

CREATE TABLE Products (
   ProductID VARCHAR(10) PRIMARY KEY,
   ProductName VARCHAR(20),
   SupplierID VARCHAR(10) FOREIGN KEY REFERENCES Suppliers(SupplierID), 
   CategoryID VARCHAR(10) FOREIGN KEY REFERENCES Categories(CategoryID), 
   QuantityPerUnit int, 
   UnitPrice DECIMAL(10,2),
   ProductImage VARCHAR(250)
);

CREATE TABLE Orders (
  OrderID VARCHAR(10) PRIMARY KEY, 
  CustomerID VARCHAR(10) FOREIGN KEY REFERENCES Customers(CustomerId), 
  OrderDate DATETIME, 
  RequiredDate DATETIME, 
  ShippedDate DATETIME, 
  Freight DECIMAL(10,2),  
  ShipAddress VARCHAR(250)
); 

CREATE TABLE OrderDetails (  
  OrderId VARCHAR(10) FOREIGN KEY REFERENCES Orders(OrderId),  
  ProductId VARCHAR(10) FOREIGN KEY REFERENCES Products(ProductId),  
  UnitPrice DECIMAL(10,2),   
  Quantity INT,
  PRIMARY KEY (OrderId, ProductId)
);
INSERT INTO Customers (CustomerID, [Password], ContactName, [Address], Phone)
VALUES ('C001', '123', 'John Doe', '123 Main St, City, Country', '1234567890'),
       ('C002', '123', 'Jane Smith', '456 Oak St, Town, Country', '9876543210'),
	   ('C003', '123', 'Mark', '123 Main St, City, Country', '1234567890'),
	   ('C004', '123', 'Michael Johnson', '789 Pine St, City, Country', '3216540987'),
       ('C005', '123', 'Emily Davis', '1010 Cedar St, Town, Country', '5551234567');

INSERT INTO Account(AccountID, UserName, [Password], FullName, [Type])
VALUES ('C000', 'A000', '123', 'Admin', 1),
		('C001', 'A001', '123', 'John Doe', 0),
       ('C002', 'A002', '123', 'Jane Smith', 0),
	   ('C003', 'A003', '123', 'Mark', 0),
	   ('C004', 'A004', '123', 'Michael Johnson', 0),
       ('C005', 'A005', '123', 'Emily Davis', 0);

-- Categories
INSERT INTO Categories (CategoryID, CategoryName, [Description])
VALUES ('CAT001', 'Vegetarian', 'Pizzas with no meat toppings'),
       ('CAT002', 'Beef', 'Pizzas loaded with meat toppings'),
       ('CAT003', 'Seafood', 'Pizzas featuring seafood toppings'),
	   ('CAT004', 'Chicken', 'Pizzas loaded with chicken toppings'),
	   ('CAT005', 'Pork', 'Pizzas loaded with pork toppings');

-- Suppliers
INSERT INTO Suppliers (SupplierID, CompanyName, [Address], Phone)
VALUES ('S001', 'Fresh Ingredients Inc.', '789 Elm St, City, Country', '4567890123'),
       ('S002', 'Farm to Table Foods', '321 Maple St, Town, Country', '7890123456'),
	   ('S003', 'Local Farms Co-op', '222 Orchard Ave, City, Country', '8885551234'),
       ('S004', 'Artisan Bakers LLC', '333 Baker St, Town, Country', '9997774567');

-- Products
INSERT INTO Products (ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, ProductImage)
VALUES ('P001', 'OCEAN MANIA',			 'S001', 'CAT003', 1, 9.99,  'image/OCEAN_MANIA.jpg'),
       ('P002', 'PIZZAMIN SEA',			 'S004', 'CAT003', 1, 11.99, 'image/PIZZAMIN_SEA.jpg'),
       ('P003', 'SURF & TURF',			 'S001', 'CAT003', 1, 12.99, 'image/SURF&TURF.jpg'),
       ('P004', 'SEAFOOD DELIGHT',		 'S002', 'CAT003', 1, 14.99, 'image/SEAFOOD_DELIGHT.jpg'),
	   ('P005', 'SEOUL BEEF BULGOGI',	 'S002', 'CAT002', 1, 13.99, 'image/SEOUL_BEEF_BULGOGI.jpg'),
	   ('P006', 'NEW YORK CHEESESTEAK',	 'S003', 'CAT002', 1, 14.99, 'image/NEW_YORK_CHEESESTEAK.jpg'),
	   ('P007', 'EXTRAVAGANZA',			 'S003', 'CAT002', 1, 17.99, 'image/EXTRAVAGANZA.jpg'),
	   ('P008', 'MEAT LOVERS',			 'S003', 'CAT002', 1, 14.99, 'image/MEAT_LOVERS.jpg'),
	   ('P009', 'CHEESY CHICKEN BACON',	 'S004', 'CAT004', 1, 20.99, 'image/CHEESY_CHICKEN_BACON.jpg'),
	   ('P010', 'TERIYAKI CHICKEN',		 'S002', 'CAT004', 1, 17.99, 'image/TERIYAKI_CHICKEN.jpg'),
	   ('P011', 'PEPPERONI FEAST',		 'S004', 'CAT005', 1, 17.99, 'image/PEPPERONI_FEAST.jpg'),
	   ('P012', 'HAWAIIAN',				 'S003', 'CAT005', 1, 13.99, 'image/HAWAIIAN.jpg'),
	   ('P013', 'VEGGIE MANIA',			 'S001', 'CAT001', 1, 14.99, 'image/VEGGIE_MANIA.jpg'),
	   ('P014', 'CHEESE MANIA',			 'S002', 'CAT001', 1, 22.99, 'image/CHEESE_MANIA.jpg');

-- Orders
INSERT INTO Orders (OrderID, CustomerID, OrderDate, RequiredDate, ShippedDate, Freight, ShipAddress)
VALUES ('O001', 'C001', '2024-03-08 09:00:00', '2024-03-15 10:00:00', '2024-03-16 15:30:00', 5.00, '123 Main St, City, Country'),
       ('O002', 'C002', '2024-04-08 11:00:00', '2024-04-15 12:00:00', '2024-04-16 14:00:00', 7.50, '456 Oak St, Town, Country'),
	   ('O003', 'C003', '2024-05-08 13:00:00', '2024-05-15 16:00:00', '2024-05-16 18:30:00', 6.50, '789 Pine St, City, Country'),
       ('O004', 'C004', '2024-06-01 09:30:00', '2024-06-08 17:00:00', '2024-06-09 20:45:00', 8.00, '1010 Cedar St, Town, Country');

-- OrderDetails
INSERT INTO OrderDetails (OrderID, ProductID, UnitPrice, Quantity)
VALUES ('O001', 'P001', 9.99, 2),
       ('O001', 'P002', 11.99, 1),
       ('O002', 'P004', 14.99, 3),
	   ('O003', 'P006', 15.99, 2),
       ('O004', 'P007', 10.99, 1);
