# PirateHunt-Java
Uni project-Game in Java-Pirate Hunt-Tarik Kreso
*This README preassume that you are using Eclipse(IDE), but steps described below should be fairly simple for the others as well.*

This GitHub repository contains source code for game "Pirate Hunt" and provides the user with the ability of playing through console,
as well as via graphical user interfaces (GUI).

**RULES**
 1)Your main aim is to eliminate all the Pirates on the board,
 2)The Pirate will be eliminated if it collides with Barrier, or another Pirate (in this case, new Barrier will be created),
 3)If Player collides with one or more Pirates, the game ends and Player has lost,
 4)If all the Pirates have been destroyed, Player will be directed to a new game level,
 5)You can also change game difficulty from game menu. You can choose between "Easy" and "Hard",
 6)If you think your score can compete for highest overall, you should save it through game menu.

***For performing moves and generally, playing the game, we use Numpad(1-9) keys***

In order to run Game in console, you need to navigate to Main class inside this project and simply perform steps:
  Click on Run submenu -> Run as -> Java application. (my keyboard binding is Alt+Shift+X)

Playing the game through the console is fairly one-sided: After user enters Numpad number (1-9) inside the console, game will render board 
to console as it is. If(by the rules of the game) it is gameover, the process will terminate. On the other hand, game will wait for new 
input from user.

Without further ado, let's describe how to run the "real" game (read GUI).Firstly, navigate to GUI class inside project and do:
  Click on Run submenu -> Run as -> Java application. (keyboard binding Alt+Shift+X)
  P.S At first sight, Eclipse has rather odd way of telling which main method it should run. So be sure to run the right one.

When you successfully run our Game, the rest of it should be fairly straightforward.

****Project documentation and APIs are generated using JavaDoc and it is added to project (see 'doc' folder)****
  Apart from the obvious (viewing JavaDoc as html), I highly recommend using Eclipse (or other IDE) for that. From Project Menu navigate 
  to 'doc' folder inside our Project.When you open it, you can click to any of the <classname>.html file and you will get an actual webpage
  of my documentation(Also, to perform easier navigation through documentation, you can use links inside webpage).
 
 If everything works fine, you should see:
 ![Screenshot](https://github.com/kr3s0/PirateHunt-Java/blob/master/GUI-Pirate%20Hunt.png)
  
  Tarik Kreso
