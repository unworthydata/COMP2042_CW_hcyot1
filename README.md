# Brick Breaker Game

## To run this game:
Clone the repo, then navigate to it in your CLI
  
For Windows use: ```gradlew run```  
For UNIX-based systems use: ```./gradlew run```

## Example
  ![image](https://user-images.githubusercontent.com/65664371/144083391-603a3772-0867-4623-9b77-c221503059f9.png)

## Tested on 
* Windows 10
* Java SDK version 17
* JavaFX version 17
* Gradle version 7.3

## Changes

---
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
**08/12/2021:** Fix the Wall mess. **_The Plan™_** is as follows:  
##### CLASSES BEFORE
  * Wall
##### CLASSES AFTER
  * Wall --> _(Abstract class)_
  * SingleWall --> _(Makes a single-brick-type wall)_
  * CheckerboardWall --> _(Makes a checkerboard-style wall)_
  * WallHandler --> _(Records the state of the multiple walls that make up the game)_
  * GameLogic --> _(Contains the ball-related aspects)_

Pushed down much functionality to the Wall class in a psuedo-chain-of-command pattern.
Any function call regarding low-level details of a wall go through this chain: `GameModel -> WallHandler -> Wall`
---
#### 09/12/2021:
* Wrote tests for the newly implemented Wall subclasses and the WallHandler classes
* Extract drawing methods from GameBoard to a Painter class
---
#### 10/12/2021:
* Fix the coupling in GameFrame, HomeMenu and GameBoard. **_The Plan™_** is as follows:
Recreate HomeMenu and PauseMenu in JavaFX. Then recreate the game viewing in JavaFX

* Recreated HomeMenu in JavaFX. Added empty info screen.
* Recreate Game in JavaFX  
Use the mediator pattern to handle collaboration between the new classes. Use interfaces to reduce
coupling and increase extensibility. In the future, we might want to create a new menu or a game with a new look, and using interfaces means we just make a new class with those required features.  
  
First we find the functionality that can be extracted (method signatures), then write tests for any testable class, then implement the new methods, and inject the new classes into the old code.

ALL state is stored in the Model classes unless necessary.
  
##### CLASSES BEFORE
* GameFrame --> _(Handling switching between HomeMenu and GameBoard)_
* HomeMenu --> _(Drawing Main menu and handling mouse events)_
* GameBoard --> _()_
##### CLASSES AFTER
* _package painter_
  * Painter --> _(Interface)_
  * BasicPainter
* _package mediator_
  * Mediator --> _(Interface)_
  * BasicMediator
* _package mainMenu_
  * MainMenuModel --> _(Interface)_
  * MainMenuView --> _(Interface)_
  * MainMenuController --> _(Interface)_
  * BasicMainMenuModel
  * BasicMainMenuView
  * BasicMainMenuController
* _package gameBoard_
  * GameBoardModel --> _(Interface)_
  * GameBoardView --> _(Interface)_
  * GameBoardController --> _(Interface)_
  * BasicGameBoardModel
  * BasicGameBoardView
  * BasicGameBoardController
* _package pauseMenu_
  * PauseModel --> _(Interface)_
  * PauseView --> _(Interface)_
  * PauseController --> _(Interface)_
  * BasicPauseModel
  * BasicPauseView
  * BasicPauseController