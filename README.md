# Brick Breaker Game

## To run this game:
Clone the repo, then navigate to it in your CLI
  
For CMD use: ```gradlew run```  
For Powershell/Linux-based systems use: ```./gradlew run```

## Example
  ![image](https://user-images.githubusercontent.com/65664371/144083391-603a3772-0867-4623-9b77-c221503059f9.png)

### Tested on 
* Windows 10
* Java SDK version 17
* JavaFX version 17
* Gradle version 7.3

#### Changes
03/12/2021:
* Extracted related classes into separate packages.
  * Extracted "Ball" and "RubberBall" into package "ball"
  * Extracted "Brick", "CementBrick", "ClayBrick", and "SteelBrick" into package "brick"
  * Extracted "DebugConsole" and "DebugPanel" into package "debug"

05/12/2021:
* Fixed all access modifiers. Set all fields to private, added getters and setters accordingly, and set to private all those helper methods only used internally
* Ordered all methods and variables in the order public -> protected -> private
* Removed unused variable "name" in class Brick constructor and all related unused variables
* Pushed up the repeated method "makeBrickFace()" in Brick subclasses
* Replaced radiusA and radiusB in constructor of class "Ball" (a circle cannot have two different radii)