# CS611-Assignment 4
## Monster Legends
---------------------------------------------------------------------------
Name: Junsun (Lucas) Yoon
Email: lyoon02@bu.edu
Student ID: U24331773



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
    Players (player objects)
        Heros.java -> object for heros
        Party.java -> collection of heros, can have up to 4 heros
    
Inventory (directory containing inventory objects such as inventory and items)
    Items (directory for items)
        Items.java -> interface defining item behaviors
        Armors.java
        Potions.java
        Spells.java
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

World (directory for creating the world)

    Spaces (directory for each tiles of the world)
        Space.java -> interface defining space behaviors
        Common.java -> common area
        Inaccessible.java -> inaccessible space
        MarketSpace.java -> market place
    Coordinate.java -> coordinate for tiles
    World.java -> collection of spaces

Main.java -> Start here!



## Notes
---------------------------------------------------------------------------
Please explain the cool features of your program. Anything that you feel like you did a good job at or were creative about, explain it in bullets here. Additionally, any design decisions should be made here.

I believe I have created several features that are hard to explain in texts. I can come to office hours for full descriptions:

1. Can change world size
1. World display is beautiful
1. fully abstracted game! Every object has its own purpose
1. Strategy pattern, I use each object for its purpose, I use combinations of each objects to build off the game
1. Integrates its own database

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

0. Exit
1. Begin game
2. Change world size
Enter a number. Enter q to quit
1
How many players in the party would you like to have? (1 ~ 4)
Enter a number. Enter q to quit
2
======================
Choose your hero name
Enter input. Enter 'q' to quit :( 
a
Hello a! Choose your hero role (Warrior, Paladin, Sorcerer)
Warrior
You chose Warrior. Enjoy the game.
======================
======================
Choose your hero name
Enter input. Enter 'q' to quit :( 
b
Hello b! Choose your hero role (Warrior, Paladin, Sorcerer)
Paladin
You chose Paladin. Enjoy the game.
======================
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|        O      ||              ||              ||        /\    ||              ||        /\    ||              ||              |
|       /|\     ||              ||              ||       /XX\   ||              ||       /XX\   ||              ||              |
|       / \     ||              ||              ||      /XXXX\  ||              ||      /XXXX\  ||              ||              |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|               ||              ||              ||              ||              ||              ||              ||        /\    |
|               ||              ||              ||              ||              ||              ||              ||       /XX\   |
|               ||              ||              ||              ||              ||              ||              ||      /XXXX\  |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|         /\    ||        /\    ||              ||              ||              ||        /\    ||              ||        /\    |
|        /XX\   ||       /XX\   ||              ||              ||              ||       /XX\   ||              ||       /XX\   |
|       /XXXX\  ||      /XXXX\  ||              ||              ||              ||      /XXXX\  ||              ||      /XXXX\  |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|               ||              ||       /\     ||        /\    ||       /\     ||        /\    ||              ||              |
|               ||              ||      /  \    ||       /XX\   ||      /  \    ||       /XX\   ||              ||              |
|               ||              ||      |_M_|   ||      /XXXX\  ||      |_M_|   ||      /XXXX\  ||              ||              |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|               ||              ||              ||        /\    ||              ||              ||              ||              |
|               ||              ||              ||       /XX\   ||              ||              ||              ||              |
|               ||              ||              ||      /XXXX\  ||              ||              ||              ||              |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|               ||              ||              ||              ||              ||              ||        /\    ||              |
|               ||              ||              ||              ||              ||              ||       /XX\   ||              |
|               ||              ||              ||              ||              ||              ||      /XXXX\  ||              |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|               ||              ||              ||        /\    ||              ||              ||              ||              |
|               ||              ||              ||       /XX\   ||              ||              ||              ||              |
|               ||              ||              ||      /XXXX\  ||              ||              ||              ||              |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|        /\     ||              ||        /\    ||              ||              ||              ||              ||       /\     |
|       /  \    ||              ||       /XX\   ||              ||              ||              ||              ||      /  \    |
|       |_M_|   ||              ||      /XXXX\  ||              ||              ||              ||              ||      |_M_|   |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+

0. Quit game
1. Move party
2. Open inventory
3. View party
Enter a number. Enter q to quit
1
Enter 'w' (up), 'a' (left), 's' (down), 'd' (right) to move. Enter 'q' to quit.
d
===============
This is common space
Monster encounter!!!
You met enemies
Warrior a(lvl 1)'s turn. Choose an action
0. Attack
1. Inventory
Enter a number. Enter q to quit
0
Which enemy should a attack?
0: Fire Dragon(lvl: 3) (HP: 20)
1: Fire Dragon(lvl: 3) (HP: 20)
Which option would you like to choose?
Enter a number. Enter q to quit
1
You are attacking Fire Dragon(lvl: 3) (HP: 20) with normal attack damage of 150
Fire Dragon(lvl: 3) (HP: 20) recieved 217 damage.
You have defeated Fire Dragon
a gained 69 exp
a gained 333 gold
Paladin b(lvl 1)'s turn. Choose an action
0. Attack
1. Inventory
Enter a number. Enter q to quit
0
Which enemy should b attack?
0: Fire Dragon(lvl: 3) (HP: 20)
Which option would you like to choose?
Enter a number. Enter q to quit
0
You are attacking Fire Dragon(lvl: 3) (HP: 20) with normal attack damage of 110
Fire Dragon(lvl: 3) (HP: 20) recieved 159 damage.
You have defeated Fire Dragon
b gained 81 exp
b gained 300 gold
false
Congrats you defeated the monsters
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|               ||       O      ||              ||        /\    ||              ||        /\    ||              ||              |
|               ||      /|\     ||              ||       /XX\   ||              ||       /XX\   ||              ||              |
|               ||      / \     ||              ||      /XXXX\  ||              ||      /XXXX\  ||              ||              |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|               ||              ||              ||              ||              ||              ||              ||        /\    |
|               ||              ||              ||              ||              ||              ||              ||       /XX\   |
|               ||              ||              ||              ||              ||              ||              ||      /XXXX\  |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|         /\    ||        /\    ||              ||              ||              ||        /\    ||              ||        /\    |
|        /XX\   ||       /XX\   ||              ||              ||              ||       /XX\   ||              ||       /XX\   |
|       /XXXX\  ||      /XXXX\  ||              ||              ||              ||      /XXXX\  ||              ||      /XXXX\  |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|               ||              ||       /\     ||        /\    ||       /\     ||        /\    ||              ||              |
|               ||              ||      /  \    ||       /XX\   ||      /  \    ||       /XX\   ||              ||              |
|               ||              ||      |_M_|   ||      /XXXX\  ||      |_M_|   ||      /XXXX\  ||              ||              |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|               ||              ||              ||        /\    ||              ||              ||              ||              |
|               ||              ||              ||       /XX\   ||              ||              ||              ||              |
|               ||              ||              ||      /XXXX\  ||              ||              ||              ||              |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|               ||              ||              ||              ||              ||              ||        /\    ||              |
|               ||              ||              ||              ||              ||              ||       /XX\   ||              |
|               ||              ||              ||              ||              ||              ||      /XXXX\  ||              |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|               ||              ||              ||        /\    ||              ||              ||              ||              |
|               ||              ||              ||       /XX\   ||              ||              ||              ||              |
|               ||              ||              ||      /XXXX\  ||              ||              ||              ||              |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|        /\     ||              ||        /\    ||              ||              ||              ||              ||       /\     |
|       /  \    ||              ||       /XX\   ||              ||              ||              ||              ||      /  \    |
|       |_M_|   ||              ||      /XXXX\  ||              ||              ||              ||              ||      |_M_|   |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+

0. Quit game
1. Move party
2. Open inventory
3. View party
Enter a number. Enter q to quit
1
Enter 'w' (up), 'a' (left), 's' (down), 'd' (right) to move. Enter 'q' to quit.
s
===============
This is common space
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|               ||              ||              ||        /\    ||              ||        /\    ||              ||              |
|               ||              ||              ||       /XX\   ||              ||       /XX\   ||              ||              |
|               ||              ||              ||      /XXXX\  ||              ||      /XXXX\  ||              ||              |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|               ||       O      ||              ||              ||              ||              ||              ||        /\    |
|               ||      /|\     ||              ||              ||              ||              ||              ||       /XX\   |
|               ||      / \     ||              ||              ||              ||              ||              ||      /XXXX\  |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|         /\    ||        /\    ||              ||              ||              ||        /\    ||              ||        /\    |
|        /XX\   ||       /XX\   ||              ||              ||              ||       /XX\   ||              ||       /XX\   |
|       /XXXX\  ||      /XXXX\  ||              ||              ||              ||      /XXXX\  ||              ||      /XXXX\  |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|               ||              ||       /\     ||        /\    ||       /\     ||        /\    ||              ||              |
|               ||              ||      /  \    ||       /XX\   ||      /  \    ||       /XX\   ||              ||              |
|               ||              ||      |_M_|   ||      /XXXX\  ||      |_M_|   ||      /XXXX\  ||              ||              |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|               ||              ||              ||        /\    ||              ||              ||              ||              |
|               ||              ||              ||       /XX\   ||              ||              ||              ||              |
|               ||              ||              ||      /XXXX\  ||              ||              ||              ||              |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|               ||              ||              ||              ||              ||              ||        /\    ||              |
|               ||              ||              ||              ||              ||              ||       /XX\   ||              |
|               ||              ||              ||              ||              ||              ||      /XXXX\  ||              |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|               ||              ||              ||        /\    ||              ||              ||              ||              |
|               ||              ||              ||       /XX\   ||              ||              ||              ||              |
|               ||              ||              ||      /XXXX\  ||              ||              ||              ||              |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|        /\     ||              ||        /\    ||              ||              ||              ||              ||       /\     |
|       /  \    ||              ||       /XX\   ||              ||              ||              ||              ||      /  \    |
|       |_M_|   ||              ||      /XXXX\  ||              ||              ||              ||              ||      |_M_|   |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+

0. Quit game
1. Move party
2. Open inventory
3. View party
Enter a number. Enter q to quit
3
=======================
Hero Stats {
Name: 'a'
Level: 1
Role: 'Warrior'
HP: 100
MP: 100
Strength: 8
Defence: 8
Dexterity: 8
Agility: 5
Armor: Begginer Armor
Weapon: Begginer Weapon

Gold: 333
% to next level: 69.0
=======================
=======================
Hero Stats {
Name: 'b'
Level: 1
Role: 'Paladin'
HP: 100
MP: 100
Strength: 8
Defence: 8
Dexterity: 8
Agility: 5
Armor: Begginer Armor
Weapon: Begginer Weapon

Gold: 300
% to next level: 81.0
=======================
Enter any key to continue
d
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|               ||              ||              ||        /\    ||              ||        /\    ||              ||              |
|               ||              ||              ||       /XX\   ||              ||       /XX\   ||              ||              |
|               ||              ||              ||      /XXXX\  ||              ||      /XXXX\  ||              ||              |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|               ||       O      ||              ||              ||              ||              ||              ||        /\    |
|               ||      /|\     ||              ||              ||              ||              ||              ||       /XX\   |
|               ||      / \     ||              ||              ||              ||              ||              ||      /XXXX\  |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|         /\    ||        /\    ||              ||              ||              ||        /\    ||              ||        /\    |
|        /XX\   ||       /XX\   ||              ||              ||              ||       /XX\   ||              ||       /XX\   |
|       /XXXX\  ||      /XXXX\  ||              ||              ||              ||      /XXXX\  ||              ||      /XXXX\  |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|               ||              ||       /\     ||        /\    ||       /\     ||        /\    ||              ||              |
|               ||              ||      /  \    ||       /XX\   ||      /  \    ||       /XX\   ||              ||              |
|               ||              ||      |_M_|   ||      /XXXX\  ||      |_M_|   ||      /XXXX\  ||              ||              |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|               ||              ||              ||        /\    ||              ||              ||              ||              |
|               ||              ||              ||       /XX\   ||              ||              ||              ||              |
|               ||              ||              ||      /XXXX\  ||              ||              ||              ||              |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|               ||              ||              ||              ||              ||              ||        /\    ||              |
|               ||              ||              ||              ||              ||              ||       /XX\   ||              |
|               ||              ||              ||              ||              ||              ||      /XXXX\  ||              |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|               ||              ||              ||        /\    ||              ||              ||              ||              |
|               ||              ||              ||       /XX\   ||              ||              ||              ||              |
|               ||              ||              ||      /XXXX\  ||              ||              ||              ||              |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+
|        /\     ||              ||        /\    ||              ||              ||              ||              ||       /\     |
|       /  \    ||              ||       /XX\   ||              ||              ||              ||              ||      /  \    |
|       |_M_|   ||              ||      /XXXX\  ||              ||              ||              ||              ||      |_M_|   |
 +--------------++--------------++--------------++--------------++--------------++--------------++--------------++--------------+

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
(base) lucas@crc-dot1x-nat-10-239-52-35 Monster_legends % 
