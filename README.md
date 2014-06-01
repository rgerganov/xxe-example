Simple web application which demonstrates XXE vulnerabitlity.

Start web app:

    $ mvn jetty:run

Retrieve all books:

    $ curl http://localhost:8080/rest/books

Retrieve book by id:

    $ curl http://localhost:8080/rest/books/1

Delete book by id:

    $ curl -v -X DELETE http://localhost:8080/rest/books/4

Create new book:

    $ curl -v -H "Content-Type:application/xml" --upload-file book.xml http://localhost:8080/rest/books