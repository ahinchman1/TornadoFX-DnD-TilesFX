# TornadoFX-DnD-TilesFX

Native app displaying TornadoFX's latest features and the ease of integrating existing JavaFX libraries with TornadoFX.
A basic drag-and-drop for a custom object without the use of Clipboard for custom grid creations using TilesFX.  

## Notable TornadoFX features:
 - Datagrid paginators: paginate cells with a set cell count per page
 - JSON Support: Lightweight support for reading JSON files and parsing them into files.  Define your grids and tiles using   JSON files for grid/tile construction
 
      ```
      {
               "title": "a_block",
               "width": 100.0,
               "height": 100.0,
               "color": "#00AEEF"
      }
      ```
## Models and Scopes:
 - TileBuilder: builds a single tile using TilesFX
 - TilePlacement and GridInfo: used to create custom grids with TilesFX
 - GridScope: used to pass information from a selected datagrid to generate a custom grid
    
![alttext](https://github.com/ahinchman1/TornadoFX-DnD-TilesFX/blob/master/readme.png)
