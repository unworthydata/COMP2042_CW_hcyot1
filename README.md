# Brick Breaker Game

## To run this game:
Clone the repo, then navigate to it in your CLI
  
For CMD use: ```gradlew run```  
For Powershell/Linux-based systems use: ```./gradlew run```

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

* Pushed down much functionality to the Wall class in a psuedo-chain-of-command pattern
  
