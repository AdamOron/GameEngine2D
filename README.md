# GameEngine2D
Bare-bones 2D Game Engine written in Java. Uses OpenGL as a rendering pipeline.  
I created this engine sometime in 2020, so I don't recall most of the specifics, but it's an interesting project nonetheless.  

## Usage
Inherit from the Engine class, defined in `src/engine/core/Engine.java`. Then, create an instance of your inheriting class and call the `run` function, which starts the engine.

### Features
* The engine contains behavior scripts, similar to Unity. These scripts are defined under `src/engine/core/scripts`.
* The engine contains some physics components, under `src/engine/core/physics`. Within those components is a simple collision system and some physics scripts.
* The engine contains some math utilities, under `src/engine/core/math`.
* All entities and their behaviors are defined under `src/engine/core/entities`.
* While working on this engine, I also started a 'layer' system, where you can assign entities to different layers so they can interact/not interact with specific components.

### To understand how to properly use the engine, check out the simple `src/test` package which contains an example implementation of the engine.
