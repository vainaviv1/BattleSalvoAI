Changes made to PA03 Files:
- GameResult Enumeration: Changed the GameResult enum to have "DRAW" instead of "TIE".
  - Justification: This change was made to match the server's expectation of when a game ends with no winner.

- Ship class: Added a String direction field to Ship to keep track of the ship's direction even after it has been 
initialized.
  - Justification: This change was made so when a FleetRecord is made to respond to the server's setup request, we can 
  get the Ship's direction to create the Ship to make the Fleet in the format the server wants. 

- Board Class: Changed the method, updateShipCoordinates, to call the updateDirection method and specify the direction 
of the ship when it is placed on the board.
  - Justification: This change was made to tell the ship to update the direction field to the correct direction it was 
  placed in. This is to save the direction so that it can be accessed later when we want the Ship's direction to create a ShipRecord to make the FleetRecord in the format the server wants.

- Ship class: Added a method called updateDirection(boolean horizontal) in the Ship class, that updates the direction 
field of a ship based on how it was placed.
  - Justification: This change was made so that the Ship's direction field is updated after it has been placed. This is 
  to save the direction so that it can be accessed later when we want the Ship's direction to create a ShipRecord to 
  make the FleetRecord in the format the server wants.

- Ship class: Added a method called getHeadCoordinate in the Ship class, that gets the first coordinate (either the 
topmost if the ship is placed vertically or the leftmost in the ship is placed horizontally).
    - Justification: This change was made so that the head coordinate can be accessed in the ProxyController when we 
  want the Ship's head coordinate to create a ShipRecord to make the FleetRecord in the format the server wants.

- Ship class: Added a method called getDirection in the Ship class, that gets the direction of the ship (either
  "HORIZONTAL" or "VERTICAL").
    - Justification: This change was made so that the direction of the ship can be accessed in the ProxyController when 
  we want the Ship's direction to create a ShipRecord to make the FleetRecord in the format the server wants.