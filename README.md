# COM212 | Spring 2024 | Final Project: Parker Films
## Derin Gezgin - Johnny Andreasen - Dimitris Seremetis - Nick Essery


**Name of the class that contains the main function: ParkerFilmsGUI** \
Data folder contains the single serialization file that stores the objects of our program.\
Docs folder contains our starting plan (scheme1.jpg) and the project prompt.\
mainStructures folder contains the Nodes, Data Structures, and an extra Manager class, which we’ll explain in detail below.\
seed folder includes 2 CSV files (for users and movies) that can be used as seed for our program. The list of the user and movies are at the end.\
\
We have two main types of Nodes: Movie and Customer.

### The Movie Node
The Movie Node has the following fields:
- String title → for the Movie title
- Int releaseDate → for the release date of the movie, ranging from 10000101 to
99999999
- Int ID → Special ID for the movies to identify them
- Boolean availability → A boolean to keep track of the movie is available
- A pointer nextMovie that points to a movie → We need this to link the movies. We use
this pointer to construct a Have Watched list for the customer.
- Left and Right ID pointers, leftIDMovie, and rightIDMovie → We need these pointers to
keep track of the movies in a binary search tree (BST) based on their ID numbers.
- Left and Right Date pointers, leftDateMovie, and rightDateMovie → We need these
pointers to keep track of the movies in a binary search tree (BST) based on their release
date.

### The Customer Node
The Customer Node has the following fields:
- String name → for storing the name of the customer
- String email → for storing the email address of the customer
- Int credit → is used to store the customer’s credit card number. In our program, we
constructed everything by assuming that this field only has the last four digits of the
customer’s credit card number.
- String password → for storing the customer’s password. We added this functionality to
our program’s login functionality.
- A wish list object wishlist→ for storing the wishlist of the customer
- A have watched list haveWatched →for storing the have watched list of the customer.
- A pointer nextCustomer → to keep track of the next customer. We need this pointer to
implement the customer storage, a dictionary with separate chaining.

### Data Structures
In our program, we used six different data structures:
- CustomerStorage → This is a dictionary that is implemented with the separate chaining
approach. This structure stores the customers based on their credit card numbers, part of
the Customer Node. We need this data structure as we have to access the customer
records on average less than O(n) time based on their credit card numbers.
- HaveWatched → This linked list stores the movies the customer watched. It uses the
nextMovie pointer of the Movie Node to link the Nodes together and create a linked list. As
it has to be infinitely sized, a linked list is the best option in this use case. We used this
structure for the project’s extra credit.
- MoviesByDate → This is a binary search tree based on the release dates of the Movies.
leftDateMovie and rightDateMovie pointers are used to construct this data structure.
- MoviesByID → This is a binary search tree based on the movie ID. LeftIDMovie and
rightIDMovie pointers are used to construct this data structure. We need this structure as
customers can access any movie by ID in less than O(n) time.
- MoviesScoresHeap → This min-value heap always keeps the lowest-scored movie at the
top. It’s an array of 255 Movie Nodes. We need this structure because the demanded
program needs the functionality of removing the least-rated movie from the system in less
than O(n) time and seeing the least-rated movie in constant time.
- Wishlist → This is a queue to store the wishlist of the individual Customers. In this case,
we have to use a queue as the program asks to access/delete/insert a movie in the list in
constant time.

We created an extra importManager Class, which we use to read our seed data from CSV files and
create objects. \
We used the Java Swing library to create our visual interface.

A new logged-in user has four options: \
**A.** Proceeding as a customer \
**B.** Proceeding as an admin \
**C.** Creating a new customer account \
**D.** Quitting the program.


### A. Proceeding as a Customer
A login page appears if the user decides to proceed as a customer. The user should write the last four digits of their credit card number and password to
log in to the system. When the user is logged in, they have six options:
1. _Access Movies By ID or Release Date:_ This window allows users to search for a movie by
their 5-digit movie ID or their exact release date. After this, they are presented with all the
available information for the movie and asked if they would like to add the movie to their
wishlist or their have-watched list.
2. _Access Wishlist:_ This will open a window with the user’s wishlist. It will start empty, and
users can add movies to it. The first movie they add to the wishlist will be the first to appear,
and the rest will show in order of addition as the user removes a movie from the wishlist. If
there are no movies on the wishlist, the user will receive a message on the screen that
states the wishlist is empty.
3. _Access the Watched Movies List:_ This function is similar to the wishlist, but instead of
showing the first movie added to the list, it shows the entire list of movies the user has
watched. If the user selects a movie from the list, they can remove it from the list or add it
to the wishlist to watch it again. This window also has a button that forwards the user to
another screen in which the user can search for the movies in the watched movies list by ID
or release date. Similarly to the previous screen, if the user does a successful search, they
can remove the movie from the watched list or add it to the wishlist.
4. _View the Movies In Order of Release Date:_ This screen lists all of the movies in the
system by release date. If the user selects a movie, they can add it to the wishlist or the
watched movies list.
5. _Edit Customer Information:_ This allows the customer to change their Name, Surname,
email address, and password. However, the customer cannot change their credit card
number, as it’s the user name used to enter the system. If the customer wants to change
their credit card number, they must contact the admin.
6. _Logout to the Main Menu:_ This option logs out the customer to the customer login menu.

### B. Proceeding as an Admin
A specific admin login page appears if the user decides to proceed as
an admin. The admin password and username are “admin.” When the admin login is successful,
the admin has six options.
1. _Add a new movie:_ The admin can add a new movie to the system on this screen. For a
successful movie creation, a movie name, a release date in YYYYMMDD format, a rating
from 0 to 100, and availability 0/1 (0 for not available and 1 for available) should be
entered. The program checks for this requirement, so it won’t create the movie if it is a
false input. The program will assign the ID in the background so that the admin doesn’t
have to be bothered with it.
2. _View the Least-Rated Movie:_ This screen shows the admin the system’s least-rated
movie. The admin can also choose to remove the movie completely. If the admin decides
to remove the movie, its availability in the background will be set to false.
3. _View movies in order of release date:_ This menu is very similar to the customers’ (4th
menu option). In this case, if an admin selects a movie from the list, it can set its
availability to false.
4. _Access Movies by ID or Release Date:_ On this screen, the admin can search for a
movie throughout the system by ID or Release Date. In the background, the program
checks if the admin entered a valid ID or a release date. If the input is valid, the program
searches for the movie and shows if it exists. After the movie is displayed, the admin has
the option to set the movie as unavailable.
5. _Return to the initial seed of the program:_ When you select this option, all changes
that have been made will be reset. The “initial seed” is the original information that shows
when you first run the program. This means all original movies, users, and information will
stay, but any changed information like new users, user’s wish lists or watched lists, and
new movies will be removed. If the admin clicks on this option, the admin has to confirm
that they’re sure to reset the program.
6. _Logout to the main menu:_ This option returns the admin to the admin login menu.

### C. Creating a New Customer
If the user decides to create a new customer account, it’ll be
forwarded to a page that’ll prompt the user to enter their name, surname, a valid email
address (that contains @, ends with a valid domain (.com, .org, .edu, etc.), and contains at
least one character long username and mail server name), credit card number (in this stage
last four digits), and a password. When the user presses submit, the program will check the
inputs and, if successful, create a new customer. The user will be forwarded to the customer
login.

### D. Quitting the Program
This option will simply end the program completely.

### The Default Seed
The default seed has 14 users including their emails, and last 4 digits of their credit card numbers. \
The default seed has 55 movies including their titles, release dates, scores and availability. 
