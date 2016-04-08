# SetGame

---------------------------------------------------------------------------------------------

## Development Info.

The program was written using JDK1.7 in Intellij IDEA 2016.1.1.

The GitHub Repository is located at https://github.com/yatesm/SetGame.  There are four branches in this repository.
The master branch, containing the final version of this project.  The Part1 branch includes the source files only for a
implementation that utilizes enums to encode the descriptors for each class.  The Data-driven branch is the working branch
 for Task and Task 3.  The deployment branch is a working branch for ensuring the project deploys properly.

## How It Runs

 When run, the program will prompt the user for a file that contains the names of files, each encoding a descriptor.
 Example:

descriptor.txt
>shading.txt  
>color.txt  
>number.txt  
>symbol.txt  

symbol.txt
>red  
>purple  
>green  

The program will prompt the user to enter the number of cards (N) they would like to play with and how many cards form a set (M).
Once entered, the program will determine how many M-card sets can be generated from the N-cards dealt.
Each valid set is outputted, along with the total number of valid sets out of total possible valid sets.  The program
will also output some timing information.

## Executing SetGame.jar

**Clone the master branch and run the SetGame.jar file like so:**

>java -jar SetGame.jar

The program should handle loading files with paths relative to the execution directory, as well
as absolute paths to files *provided that the files named in the initial file have absolute paths as well.*

## Building from Source

**Pull the master branch into the Intellij IDEA2016.1.1 IDE.  There are a few steps to
 ensure the code itself works correctly within the IDE.**

1) **Open IntelliJ IDEA.**  File->Project From Version Control->Github.  Enter the URl of the branch
you would like to pull.

2) **Configure the JDK / Compiler settings.**  File->Project Structure->Project.  Ensure that the JDK is the Java 1.7 JDK.
The Java language level is 5.0.

3) **Configure the Source Settings** File-Project Structure->Modules->Sources Tab
Mark the SetGame/src directory as a source directory.  Mark the SetGame/test directory as a test directory

3) **Configure the Module Settings** File->Project Structure->Modules->Paths Tab.
The "Use Module Compile Output Path" raido button should be checked.  Ensure that the **absolute path** to the output
folders for the production and test outputs are set. The  and his should be /absolute/path/to/project/out/production/SetGame
and  /absolute/path/to/project/test/production/SetGame .  Without this, the classloader will not know where the .class
files are.

 4) **Configure the Library Settings**  File->Project Structure->Libraries.
 Ensure that the following three libraries are set as dependencies: hamcrest_core_1.3, junit, junit4.12.
 These modules are necessary for ensuring the unit tests run.  They can be located in the IntelliJ IDEA
 installation directory (for me, it is C:\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition 2016.1.1\lib)

 5) **Configure the Dependencies** File->Project Structure->Libraries
 Ensure that, at least, the JUnit and JUnit4.12 libraries are within the library dependencies.  If they are not
 there, then left-click the green + icon, select add library and add the JUnit and JUnit4.12 libraries.

Once this is completed, you should be able to run the Driver.java file, containing the main method,
or any of the Unit tests.

## Implementation Notes

### General Implementation Notes

I approached this project as a simulation project.  We're essentially simulating a game of set,
but there's one player, and his goal is to find all sets.  An interesting twist would be to
remove cards after they've been used in a set.

The program itself is essentially two algorithms:

**1) Deal N cards and determine all size M sets (Hands) of cards from the N cards dealt.**

This part was completed by generating the power set of the set of N-cards.  The power set is
the set containing all subsets of a set.  Thus, if your set is {a, b, c}, the power set is
{ {}, a, b, c, ab, ac, abc}.  I used an iterative algorithm that looks at cards as binary
indicators {000, 001, 010, 011, 100, 011, 101, 111}.  This works well, iteratively, because we
know that the number of sets in the power set of a set N can be easily computed*.

After generating the powerset, we go back through the resulting power set and prune out all Hands that
do not have more or less than the desired number of cards to form a set (i.e., 3).  Unfortunately,
due to the generation of the powerset, this is an exponential time algorithm with an asymptotic runtime
of O(2^N * N).  There are probably more efficient algorithms out there for generating all subsets with
with M members, but that would require some digging to find.  However, the program runs reasonably well
for N=1...16 (~1 seconds).

2) **Determine which and how many M-Card Hands form match our criteria for a Set.**

This algorithm involves iterating through each of the descriptors, determining if the cards in the
Hand qualify for our criteria as a set.  By default, this is an O(N^2 * M) computation, where N
 is the number of cards in Hand, and M is the number of descriptors on a card, as it
necessitates comparing each card to each other card for each descriptor.  This can be reduced in practice through
several small optimizations.  The first is avoiding comparing a card to itself.  Not only does
this reduce the runtime of this algorithm, but, logically, comparing a card to itself would
result in the Hand not being a set if there were any other differing cards.

The next two optimizations relied on understanding some hints that indicate setness or non-setness early on.
If, when checking a given card, all cards have the same value for a given descriptor, we can exit the check early.
If the first card has the same symbol as all subsequent cards, all subsequent cards will have the same
symbol as the first card, which implies that the cards form a set for that descriptor.  If, when checking a given card,
 there are more than 0 cards with the same value for a given descriptor, and there are more than 0 cards with
 a different value for a given descriptor, this implies the Hand does not form a set.

  The final optimization involves quitting the check for setness if we know setness cannot be obtained.  I.e.,
   if, for a given hand, we find that for one or more descriptors the hand does not form a set, it cannot
   form a set at all and the computation should be stopped.

 These latter optimizations improve the runtime to some degree; however, I did not time my tests to
 determine the actual runtime gains in practice.  However, these optimizations scale well with the number
 of cards in a Hand (i.e., we receive more runtime gains from these optimizations when Hands have 5 cards than 3 cards).

### Branch Specific Implementation Notes

#### Data Driven Implementation

The data driven implementation was simple and straight forward because this is a dixie cup
program.  With additional requirements, there would be additional changes I would make, which I'll detail after the
description.

Card generation was delegated to a CardFactory class.  This was a simple class that relied on
static methods.  It is initialized at the beginning of execution with a filename inputted by the user.
This file contains the names and/or paths to any number of different files, each encoding a descriptor.
Each subsequent file contains a set of values for the descriptor being inputted.

These files are read in.  For each descriptor file, an ArrayList< String > of its possible values is stored
in a HashMap, keyed on the name of the file which was read (i.e. <"symbol", {diamond, oval, squiggle}>).

 To generate a card, the CardFactory pulls the keyset from the hashmap, iterates through the keyset to obtain
 each ArrayList< String > containing the possible values and pulls one at random.

 If I were to improve this implementation, I would probably utilize a single XML file to contain
 all of the descriptors.  This would provide the ability to perform V&V on the input file, at the cost
 of having to use XML (being hard to write, read, being big).  However, that would probably necessitate
 an additional class that performs the XML reading, as that is too much functionality for the Factory itself.
 In this case, the class reading the XML would provide the CardFactory a set of ArrayLists with keys at the
 start of execution.

 There are some additional polish/robustness improvements that could be made regarding the data
 driven aspect of this program as well.  One would be reprompting the user for input if the file
 they enter is not found or is not accessible, as well as disabling the output of exception messages,
 adding in logging to the software, etc.

 There's also the question of where certain data comes from.  In order to evaluate a hand, the
 hand has to know all of the descriptors of a Card.  Because we're using hashmaps, we can't necessarily
 iterate through the keyset of each card, as there is no guarantee that the order in the keyset of one
 card is the same order for the keyset of another card.  At the moment, construction of a Hand
 object requires the caller to pass the keyset (Set< String >)of the cards that will be stored at
 construction time.  Alternatively, if an empty hand is constructed, as cards are added through the
 Hand.addCard(Card c) method, the keyset of that cards descriptor hashmap is added to the keyset contained
 within the hand.  The obvious fault point here is that if the two cards have two different sets of descriptors
 (i.e., one with symbol, shape, color and another with symbol, shading and color), the program will
 crash and burn when trying to compare a descriptor that is not common to all cards.

 There are construction/instantiation time checks that could be added to prevent this from occurring, but
 I felt that bordered on over-engineering since the program should be engineered to not allow that
 to happen in the first place.

 ### Enum Implementation

I was quite pleased with the enum-based implementation.  It was fairly clean, though suffers from the limitations of enums.
Namely, if the game is to be extended, we would have to create a new enum for each different descriptor.  Likewise, engineering
the program so that it used different sets of desriptors at runtime would be work intensive at the least.

I implemented the methods to determine whether a Hand (set of cards) was a set by checking to see
if there was a set formed from each of the descriptors.  That is to say, we determined whether or not
the cards formed a set for color, symbol, shading and number.  If all four descriptors were a set, then
the cards formed a set.

There's a pitfall in that method of checking given this implementation.  Because each Card has a member variable
for each descriptor, the naive approach to checking settness for each descriptor is to have a method for each descriptor
(i.e., isColorSet(), isSymbolSet, etc).  I circumvented this egregious code duplication by using a little meta-data from
the Card class itself.  Within the Hand class is a method called methodCaller(Card card, String methodName), which
attempts to call the Card.methodName method for the passed Card object.  Given a hardcoded list of methodName which
function as getters for the different descriptors, this allows us to iterate through that list, checking the setness for
each descriptor.
