# DCEL (Doubly Connected Edge List)

A type of planar subdivsion. Most of geometric objects in this repository will be implemented using DCEL. 

* Mainly used to store geometric and topological information. 
* Complexity is #vertices + #edges + #faces.


# Features:
* Every edge has a twin edge that has the opposite origin and destination.
* Every face has an Outercomponent edge and an Innercomponent edge.
  * Face are represented by edges arranged in counter-clockwise order.
  * Outercomponent edge is the edge that bounds the face.
  * To traverse the face, simply visit the Outercomponent edge's next edge until it repeats.
  * Innercomponent edge is the edge that bounds holes inside the face (which a face can have none).
  * A hole is a face inside a face which is represented by edges arranged in clockwise order to distinguish with a "normal face".
