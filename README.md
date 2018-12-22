# mini-Library
Realization of data structure

java

input: 
  4 Posts:
    1. Lending a book
    2. Returning a book
    3. New subscription
    4. Termination of subscription
  3 queries:
    1. Which books are in the possession of the subscriber whose ID number is registered in the query
    2. In which subscriber is the book whose code is listed in the query
    3. Who are all customers currently holding the largest number of books
  Number of books = m, number of readers = n.
  Each subscriber may lend up to 10 books at the same time.
  Workplace - There is only one copy of each book in the library.

Output:
  
  Any changes made to the library.
  Each query followed by the answer to it.
  
Description of the data structure and explanation:
  A tree in which the value according to which it is sorted is the customer's ID. Each node contains in addition to the main field of the ID, a field of the list of books that is in the same customer, which also updates the field containing the number of books currently in that customer.
  A tree whose value is sorted is the name of the books (according to ascii characters) that are currently loaned, each node contains in addition to the main field of the name of the book, a field of the name of the customer who borrowed it and its ID.
  The departments in the project:
    1. MMN18 - the main page in the project
    2. IdRBTree - A tree of customers
    3. BookRBTree - A tree of books
    4. IdNode - A node in the tree that represents a client
    5. BookNode - A node in the tree that represents a book
