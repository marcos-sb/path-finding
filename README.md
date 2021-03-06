PATH FINDING
============
Path Finding is a small demo application to understand the behaviour of some classical graph traversal algorithms.  
The goal is to show the path, if there existed, from an origin 3-digit number to a destination number.  
Impossible numbers could be added to the path. Those represent numbers that cannot be visited anywhere in the path.  

Supported Algorithms
--------------------
* A*
* Hill Climbing
* BFS

Supported Heuristics
--------------------
* Maximum Manhattan Distance
* Manhattan Distance


INSTALLATION
------------
__REQUIREMENTS__
* maven 3+
* java 1.5+
* git

__QUICK GUIDE__
* Choose your desired folder location and clone the repo issuing  
`git clone https://github.com/marcos-sb/path-finding.git`  
The folder `path-finding` should have been created  

* Change directory to the one just created and maven install:  
`cd path-finding && mvn install`  

* Finally, run it typing  
`mvn exec:exec`  


CONFIGURE EXECUTION
-------------------
Once the application is running, the user interface allows for defining a path to traverse and a method to visit intermediate nodes. The following screenshots show the basic GUI properties.
![Configure Path-Finding](docs/img1.png)
![Traversing Path](docs/img2.png)
