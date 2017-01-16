JavaMineSweeper
------------------
Java implementation of Mine Sweeper. Getting some practice with JUnit and test 
driven development. Sprites were ripped from a Google images search.

Class Structure
 - MineSweeper (Main method): is a KeyListener and Runnable
    - Minefield: the entire minefield
        - Square: an individual square on the minefield
    - MineSweeperGraphics: draws on JFrame graphics
        - SpriteCropper: has hardcoded sprite positions on the sprite sheet
    - JFrame

Test Driven Development and JUnit
-----------------------------------
For the classes Minefield and Square I wrote the Tests before the code. It 
definitely helped speed up the development process. Once I went on to writing 
graphics classes I started to get sloppy and stopped writing tests. Debugging
and implementing functions took longer and more thought. Next time the goal is
to try to keep up writing tests before actual classes.
