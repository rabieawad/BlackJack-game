## Name
javalinServer

## Description

Server providing Trading serves that allows signed up Users to buy various goods
## Authors

Rabie Alawad
Linus Remy
## Used liberaries and Frameworks
Javalin : Javalin is a very lightweight web framework for Kotlin and Java which supports WebSockets, HTTP2 and async requests. Javalin’s main goals are simplicity, a great developer experience, and first class interoperability between Kotlin and Java.
jckson : Jackson has been known as "the Java JSON library" or "the best JSON parser for Java". Or simply as "JSON for Java".
 
java.util.ArrayList :Resizable-array implementation of the List interface. Implements all optional list operations, and permits all elements, including null. In addition to implementing the List interface, this class provides methods to manipulate the size of the array that is used internally to store the list.
java.util.List: An ordered collection (also known as a sequence). The user of this interface has precise control over where in the list each element is inserted. The user can access elements by their integer index (position in the list), and search for elements in the list.
java.util.objects:This class consists of static utility methods for operating on objects. These utilities include null-safe or null-tolerant methods for computing the hash code of an object, returning a string for an object, and comparing two objects.
## BlackJackGame 

## Card
a class that can represent any card by its name and value 
## Deck
 a calss containing the Deck of cards used for Black Jack
## BlackJackGame 
the Class can have as many instances as desired 
it represents a Black jack game of tow players and save between states of the game and an instance of it  
## HitRequest 
it represent a hit of a player

## StandRequest
it represents a stand request of a player
## StartGameRequest
it represents a start game request of a player
## Status
calculates and represents the current state of the ongoing game
 ## Rest Interface
The Rest interface opens multiple endpoints to allow the Client to send Requests to be processed by the Server
\start 
a path over which the client sends start Requests
\hit
a path over which the client sends hit Requests
\stand
a path over which the client sends hit Requests  
\status
a path over which the client can get the most recent request
 


