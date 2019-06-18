# frostywarp
somewhat tiny warp plugin for FC creative.

## Features
- Fast, I think
- Saves to YAML + caches warps for read access
- /warps command with interactive menu and tooltips

## Usage
```
/warp <warp>
    warp to <warp>
/warps [page]
    list warps. Click a warp to warp, or [<-] [->] for prev/next page of warps.
/setwarp <warp> [description]
    create a new warp with the given name <warp> and multi-word [description].
/delwarp <warp>
    delete the specified warp with name <warp>
```

## Notes
- All warps are immutable. To rename or redescribe warps, simply use the
  creation/deletion operations.

## Building
- Get a JDK and Maven.
- `mvn package` should probably put the jar in `target/frostywarp.jar`.
