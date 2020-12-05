# JPaint Application
Class: SE 450 – Object Oriented Software Development 

## Application Features:

Draw rectangles, ellipses, and triangles onto the canvas.  
Supports the use of various primary and secondary colors.  
The application supports multiple types of shading (filled in, filled in and outlined, outlined).  
Shape manipulation commands (copy,paste,undo,redo,delete,group,ungroup).

## Implemented Design 

Command Design Pattern
The command design pattern was used in my project in order to encapsulate the information required for the execution of given commands. This design pattern helped increase code reusability, decoupling, and encapsulation. This pattern was used by the ICommand and IUndoable interfaces, and it was implemented in the CopyCommand, PasteCommand, UndoCommand, RedoCommand, MoveCommand, DrawCommand, SelectCommand, DeleteCommand, GroupCommand, and UngroupCommand classes.

Strategy Design Pattern
The strategy design pattern was used in my program because it allowed for the creation of different variants of an algorithm, and switching between them at runtime. Ultimately, this design pattern helped solve the problem of having duplicate code. This pattern was used in the static factory DrawableShapeFactory and implemented at runtime based on the appropriate request.  

Singleton Design Pattern
The singleton design pattern was used in my project in order to have a single object coordinate multiple actions. In particular, the singleton pattern was used to create the object which was used to store the current selected shapes on the canvas. This design pattern helped me solve the problem of having duplicate code, and allowed for a seamless way to extend code whenever necessary through an interface. This pattern was implemented by the IBoundingBox interface and used within the SelectBoundingBox and ShapeBoundingBox classes.  

Composite Design Pattern
The purpose of the composite design pattern in my program was to apply the same command across grouped shapes instead of individual shapes. This pattern solved the problem where the difference between individual shapes and grouped shapes needed to be treated as one. The composite design pattern was implemented across the IShape interface, and ShapeGroup and ShapeDraw classes.

Observer Design Pattern
The observer design pattern was used in my project to notify the paint canvas when shapes were added to the shapes list. Afterwards, the shapes could either be removed or drawn onto the canvas. This design pattern allowed for loose coupling between objects, meaning data could be transferred without having to make changes to multiple classes. The observer design pattern was implemented in the IObserver, IObservable, IShape interfaces and used in the PaintCanvas and ShapeList classes.
