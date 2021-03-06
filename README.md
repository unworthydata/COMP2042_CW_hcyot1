# Brick Breaker Game
### Omar Ismail (Student ID: 20311657)  
### COMP2042 Software Maintenance Fall 2021

* GitHub link: https://github.com/unworthydata/COMP2042_CW_hcyot1
* Javadoc in javadoc/index.html

## To run this game:
Clone the repo, then navigate to it in your CLI
  
For Windows use: ```gradlew run```  
For UNIX-based systems use: ```./gradlew run```

## Example
![image](https://user-images.githubusercontent.com/65664371/145704882-e5f64d35-734f-4ad8-993e-26a5bed7509a.png)

## Tested on 
* Windows 10
* Java SDK version 17
* JavaFX version 17
* Gradle version 7.3

## Changelog

#### 03/12/2021:
* Extracted related classes into separate packages.
  * Extracted "Ball" and "RubberBall" into package "ball"
  * Extracted "Brick", "CementBrick", "ClayBrick", and "SteelBrick" into package "brick"
  * Extracted "DebugConsole" and "DebugPanel" into package "debug"
---
#### 05/12/2021:
* Fixed all access modifiers. Set all fields to private, added getters and setters accordingly, and set to private all those helper methods only used internally
* Ordered all methods and variables in the order public -> protected -> private
* Removed unused variable "name" in class Brick constructor and all related unused variables
* Pushed up the repeated method "makeBrickFace()" in Brick subclasses
* Replaced radiusA and radiusB in constructor of class "Ball" (a circle cannot have two different radii)
---
#### 07/12/2021:
* Added src/test directory
* Removed duplicate code in Ball constructor (used setPoints(radius, radius) instead)
* Extracted Crack class from Brick class
* Removed unnecessary spaces in classes
* Moved as many declarations as possible from class constructors to inline
* Introduced an enum "BrickType" to represent Brick types
  * Brick types were set as "CLAY = 1" "STEEL = 2" "CEMENT = 3" in class "Wall"
  * This is not flexible and can cause unintended side effects
  * Introduced the enum "BrickType" and changed method signatures in the class "Wall" accordingly
* Extracted initializeTimer() method in GameBoard constructor
* Extracted repeated code into a method in class Wall (randomSpeedX() and randomSpeedY())
* Branched into refactor/fix-bricks-coupling
* Added BrickFactory to produce Bricks instead of directly in the Wall class
* removed repeated method setImpact() in class SteelBrick
* Merged branch "refactor/fix-bricks-coupling" into branch "main" and resolved merge conflict
---
**08/12/2021:** Fix the Wall mess. **_The Plan???_** is as follows:  
##### CLASSES BEFORE
  * Wall
##### CLASSES AFTER
  * Wall --> _(Abstract class)_
  * SingleWall --> _(Makes a single-brick-type wall)_
  * CheckerboardWall --> _(Makes a checkerboard-style wall)_
  * WallHandler --> _(Records the state of the multiple walls that make up the game)_
  * GameModel --> _(Contains the ball-related aspects)_

Pushed down much functionality to the Wall class in a psuedo-chain-of-command pattern.
Any function call regarding low-level details of a wall go through this chain: `GameModel -> WallHandler -> Wall`

---
#### 09/12/2021:
* Wrote tests for the newly implemented Wall subclasses and the WallHandler classes
* Extract drawing methods from GameBoard to a Painter class
---
#### 10/12/2021:
* Fix the coupling in GameFrame, HomeMenu and GameBoard. **_The Plan???_** is as follows:
Recreate HomeMenu and PauseMenu in JavaFX. Then recreate the game viewing in JavaFX

* Recreated HomeMenu in JavaFX. Added empty info screen.
* Recreate Debug console in JavaFX
* Game works using a SwingNode. Basically a wrapper around the Swing code to make the game run in a JavaFX context.
* GameBoard, GameFrame, and GameLogic converted to an MVC pattern such that we now have GameView, GameController, and GameModel with separation of concerns. ALL state is stored in the Model classes unless necessary.
* Finalize pause menu functionality
* Finalize debug console functionality
* Get the status message back
---
#### 11/12/2021:
* Removed old classes and moved to the new MVC and JavaFX classes
* Made windows not resizable, it messed with the look of the game
* Restructured classes into logical packages
  * Moved DebugConsole and PauseMenu related files into the same package as game, as they only make sense in a game context.
* Filled in info screen
* Styled main menu
* Added Leaderboard which contains high scores
  * High scores are stored in highscores.csv
  * Permanently stored in the file, so if you reload the game, you still retain the high scores
  * Reads and writes
  * If you get a new high score in game, you are shown a new high score message
* Game now shows a big pause image when the game is paused (hit space bar)
* Ball changes color when it hits bricks or the player
* Made the ball bigger so it is clearer
* Added new wall type (Hollow walls)
* Added new Brick type (Moss Brick)
* Made it so levels are made using lists, which is much more flexible when adding or removing levels
* Added Javadocs to all classes
* Added tests for BrickFactory and BallFactory classes
