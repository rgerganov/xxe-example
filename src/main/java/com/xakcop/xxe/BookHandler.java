package com.xakcop.xxe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/books")
public class BookHandler {

    static final Map<Integer, Book> books = new ConcurrentHashMap<Integer, Book>();
    static volatile int currentId = 0;
    
    static {
        Book book = new Book();
        book.setId(currentId);
        book.setTitle("Java puzzlers");
        book.setAuthor("Joshua Bloch");
        book.setIsbn("032133678X");
        books.put(currentId++, book);
        book = new Book();
        book.setId(currentId);
        book.setTitle("Java concurrency in practice");
        book.setAuthor("Brian Goetz");
        book.setIsbn("0321349601");
        books.put(currentId++, book);        
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void createBook(Book book) {
        book.setId(currentId);
        System.out.println(book.title);
        books.put(currentId++, book);
        System.out.println(books.size());
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Book> retrieveAllBooks() {
        List<Book> result = new ArrayList<Book>();
        for (Map.Entry<Integer, Book> entry : books.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }    
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Book retrieveBook(@PathParam("id") int id) {
        return books.get(id);
    }

    @DELETE
    @Path("{id}")
    public void deleteBook(@PathParam("id") int id) {
        books.remove(id);
    }

}