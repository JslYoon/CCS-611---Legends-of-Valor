# CS611-Assignment 5
## Legends of Valor
---------------------------------------------------------------------------
Name: Junsun (Lucas) Yoon
Email: lyoon02@bu.edu
Student ID: U24331773

Name: Ziping (Peter) Wang
Email: pwang78@bu.edu
Student ID: U34449628

## Files
---------------------------------------------------------------------------
db (directory containing database entries):
    items.txt -> db containing item list
    monsters.txt -> db containing monsters list

Behavaiors (directory containing entity behaviors (mainly battles at this point))
    Behaviors.java -> static methods that takes care of battles

Entities (directory containing entities. Entities can vary from Heros to monsters to neutral entity like the market)
    Entities.java -> java interface that defines entity behaviors

    Enemies (directory for enemy entity)
        Monsters.java -> object for monsters
    Neutral (neutral entity)
        Market.java -> object for neutral market
        NexusEntity.java -> for nexus
    Players (player objects)
        Heros.java -> object for heros
        Party.java -> collection of heros, can have up to 4 heros
    
Inventory (directory containing inventory objects such as inventory and items)
    Items (directory for items)
        Items.java -> interface defining item behaviors
        Armors.java
        Potions.java
        Spells.java 
        Consumeables.java -> interface for consumeables, eg. spells, potions
        Weapons.java -> these objects define the prospective names of the objects
    Inventory.java -> Inventory objects that takes care of the items

Messages (directory of files that have static methods that help with displaying text)
    ErrorMessage.java -> error messages
    printStatement.java -> commonly used print statements
    QuestionMessage.java -> print statements for questions

Misc (miscellaneous stuff)
    dbOperation.java -> takes care of Read operations for txt files
    Input.java -> for scanner inputs
    Interface.java -> for game interface
    Randomeselection.java -> for randomly selecting usage in the game
    Color.java -> strings for chaning the color in our terminal
    Music.java -> for playing music when playing 
    
World (directory for creating the world)

    Spaces (directory for each tiles of the world)
        Space.java -> interface defining space behaviors
        Common.java -> common area
        Inaccessible.java -> inaccessible space
        MarketSpace.java -> market place
        Koulou.java -> koulou space representation
        Dungeon.java -> dummy dungeon space representation never used
        Cave.java -> cave space representation
        Bush.java -> bush space representation
        StatSpace.java -> interface for all other stat buffing spaces to implement

    MonsterWorld.java -> default world class for Monsters and heroes game
    ValorWorld.java -> default world class for Legends of Valor game
    Coordinate.java -> coordinate for tiles
    World.java -> collection of spaces

Main.java -> Start here!



## Notes
---------------------------------------------------------------------------
Please explain the cool features of your program. Anything that you feel like you did a good job at or were creative about, explain it in bullets here. Additionally, any design decisions should be made here.

I believe I have created several features that are hard to explain in texts. I can come to office hours for full descriptions:

1. Can change number of lanes and how wide the lanes are
2. World display is beautiful and colorful
3. fully abstracted game! Every object has its own purpose
4. Strategy pattern, I use each object for its purpose, I use combinations of each objects to build off the game
5. Integrates its own database
6. Music while you play!

Notes: 
Representation of each space in terminal: 
Unfortunatly colors do not show up in txt.

|       /\     |
|      -[N]-   |       --->    Nexus 
|       \/     |

|        /\    |
|       /XX\   |       --->    Inaccessible 
|      /XXXX\  |

|      _/ \_   |
|      / C \   |       --->    Cave
|      \___/   |

|       ~~~    |
|      ~[B]~   |       --->    Bush
|       ~~~    |

|      v v v   |
|      v[K]v   |       --->    Koulou
|      v v v   |

|       O      |
|      /|\     |       --->    Party location
|      / \     |



## Citaions
---------------------------------------------------------------------------
https://stackoverflow.com/questions/210567/package-structure-for-a-java-project. I used this source to better learn the java project structure
https://en.wikipedia.org/wiki/Sliding_puzzle. I used this source to better write the slide puzzle rules
https://www.digitalocean.com/community/tutorials/factory-design-pattern-in-java. I used this source to better understand the coding structure
https://ioflood.com/blog/java-color/#:~:text=new%20Color%20object%3A-,import%20java.,that%20represents%20the%20color%20red.&text=In%20this%20example%2C%20we%20import%20the%20Color%20class%20from%20the%20java. for java colors
https://www.digitalocean.com/community/tutorials/java-equals-hashcode implementing hashcode override
https://www.geeksforgeeks.org/different-ways-reading-text-file-java/ read txt file
https://stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it file configuration/write


## How to compile and run
---------------------------------------------------------------------------
1. Navigate to the directory "Monster_legends" after unzipping the files
2. Run the following instructions:
javac *.java -d bin
java -cp ./bin Main




## Input/Output Example
---------------------------------------------------------------------------
Please give us a full execution of what we should see on the screen. Label each text with input and output. For example:

Welcome to the main page, pick an option! :)

0. Monster and Legends
1. Legends of Valor
2. Change world size
Enter a number. Enter q to quit
1

Welcome to Legends of Valor!

Welcome to the main page, pick an option! :)

0. Exit
1. Begin game
2. Change world size
Enter a number. Enter q to quit
1
Creating hero for lane 1
======================
Choose your hero name
Enter input. Enter 'q' to quit :(
Peter
Hello Peter! Choose your hero role (Warrior, Paladin, Sorcerer)
Warrior
You chose Warrior. Enjoy the game.
======================
Creating hero for lane 2
======================
Choose your hero name
Enter input. Enter 'q' to quit :( 
Lucas
Hello Lucas! Choose your hero role (Warrior, Paladin, Sorcerer)
Paladin
You chose Paladin. Enjoy the game.
======================

[Warrior Peter(lvl: 1)(HP: 100)]
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|         /\    ||       /\     ||       /\     ||        /\    ||       /\     ||       /\     ||       /\   ||        /XX\   ||      -[N]-   ||      -[N]-   ||       /XX\   ||      -[N]-   ||      -[N]-   ||      /XX\  ||       /XXXX\  ||       \/     ||       \/     ||      /XXXX\  ||       \/     ||       \/     ||     /XXXX\ | +--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|         /\    ||              ||              ||        /\    ||              ||              ||       /\   ||        /XX\   ||              ||              ||       /XX\   ||              ||              ||      /XX\  ||       /XXXX\  ||              ||              ||      /XXXX\  ||              ||              ||     /XXXX\ | +--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|         /\    ||              ||              ||        /\    ||              ||              ||       /\   ||        /XX\   ||              ||              ||       /XX\   ||              ||              ||      /XX\  ||       /XXXX\  ||              ||              ||      /XXXX\  ||              ||              ||     /XXXX\ | +--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|         /\    ||              ||              ||        /\    ||      v v v   ||              ||       /\   ||        /XX\   ||              ||              ||       /XX\   ||      v[K]v   ||              ||      /XX\  ||       /XXXX\  ||              ||              ||      /XXXX\  ||      v v v   ||              ||     /XXXX\ | +--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|         /\    ||              ||              ||        /\    ||              ||              ||       /\   ||        /XX\   ||              ||              ||       /XX\   ||              ||              ||      /XX\  ||       /XXXX\  ||              ||              ||      /XXXX\  ||              ||              ||     /XXXX\ | +--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|         /\    ||              ||              ||        /\    ||              ||              ||       /\   ||        /XX\   ||              ||              ||       /XX\   ||              ||              ||      /XX\  ||       /XXXX\  ||              ||              ||      /XXXX\  ||              ||              ||     /XXXX\ | +--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|         /\    ||              ||       ~~~    ||        /\    ||              ||              ||       /\   ||        /XX\   ||              ||      ~[B]~   ||       /XX\   ||              ||              ||      /XX\  ||       /XXXX\  ||              ||       ~~~    ||      /XXXX\  ||              ||              ||     /XXXX\ | +--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|         /\    ||       O      ||       /\     ||        /\    ||       O      ||       /\     ||       /\   ||        /XX\   ||      /|\     ||      -[N]-   ||       /XX\   ||      /|\     ||      -[N]-   ||      /XX\  ||       /XXXX\  ||      / \     ||       \/     ||      /XXXX\  ||      / \     ||       \/     ||     /XXXX\ | +--------------++--------------++--------------++--------------++--------------++--------------++--------------+

Peter's turn
0. Quit game
1. Move party
2. Open inventory
3. View party
Enter a number. Enter q to quit
Peter's turn
0. Quit game
1. Move party
2. Open inventory
3. View party
Enter a number. Enter q to quit
1
Enter 'w' (up), 'a' (left), 's' (down), 'd' (right) to move. Enter 't' to teleport, 'r' to recall. Enter 'q' to quit.
d
===============
This is nexus space
Would you like to enter this space?
t/f for true or false. Enter q to quit
t
[Warrior Peter(lvl: 1)(HP: 100)]
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|         /\    ||       /\     ||       /\     ||        /\    ||       /\     ||       /\     ||       /\   ||        /XX\   ||      -[N]-   ||      -[N]-   ||       /XX\   ||      -[N]-   ||      -[N]-   ||      /XX\  ||       /XXXX\  ||       \/     ||       \/     ||      /XXXX\  ||       \/     ||       \/     ||     /XXXX\ | +--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|         /\    ||              ||              ||        /\    ||              ||              ||       /\   ||        /XX\   ||              ||              ||       /XX\   ||              ||              ||      /XX\  ||       /XXXX\  ||              ||              ||      /XXXX\  ||              ||              ||     /XXXX\ | +--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|         /\    ||              ||              ||        /\    ||              ||              ||       /\   ||        /XX\   ||              ||              ||       /XX\   ||              ||              ||      /XX\  ||       /XXXX\  ||              ||              ||      /XXXX\  ||              ||              ||     /XXXX\ | +--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|         /\    ||              ||              ||        /\    ||      v v v   ||              ||       /\   ||        /XX\   ||              ||              ||       /XX\   ||      v[K]v   ||              ||      /XX\  ||       /XXXX\  ||              ||              ||      /XXXX\  ||      v v v   ||              ||     /XXXX\ | +--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|         /\    ||              ||              ||        /\    ||              ||              ||       /\   ||        /XX\   ||              ||              ||       /XX\   ||              ||              ||      /XX\  ||       /XXXX\  ||              ||              ||      /XXXX\  ||              ||              ||     /XXXX\ | +--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|         /\    ||              ||              ||        /\    ||              ||              ||       /\   ||        /XX\   ||              ||              ||       /XX\   ||              ||              ||      /XX\  ||       /XXXX\  ||              ||              ||      /XXXX\  ||              ||              ||     /XXXX\ | +--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|         /\    ||              ||       ~~~    ||        /\    ||              ||              ||       /\   ||        /XX\   ||              ||      ~[B]~   ||       /XX\   ||              ||              ||      /XX\  ||       /XXXX\  ||              ||       ~~~    ||      /XXXX\  ||              ||              ||     /XXXX\ | +--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|         /\    ||       /\     ||       O      ||        /\    ||       O      ||       /\     ||       /\   ||        /XX\   ||      -[N]-   ||      /|\     ||       /XX\   ||      /|\     ||      -[N]-   ||      /XX\  ||       /XXXX\  ||       \/     ||      / \     ||      /XXXX\  ||      / \     ||       \/     ||     /XXXX\ | +--------------++--------------++--------------++--------------++--------------++--------------++--------------+

// Continue playing until end of game





// paste end of game here







Lucas's turn
0. Quit game
1. Move party
2. Open inventory
3. View party
Enter a number. Enter q to quit
0

Welcome to the main page, pick an option! :)

0. Exit
1. Begin game
2. Change world size
Enter a number. Enter q to quit
0
Goodbye!
