# TeleportAlias
Spigot plugin adding aliases to teleport commands in Minecraft.
Using the bash variable syntax, an alias can be used as a replacement for the actual coordinates.
The plugin will then replace the alias with the coordinates before calling the `minecraft:tp` command.

## Usage
Use the teleport command just like you would normally.
To use an alias, type `$name` instead of coordinates.

### Example
![Example1-1](https://github.com/maxnz/TeleportAlias/blob/master/images/TeleportEx1-1.png)
![Example1-2](https://github.com/maxnz/TeleportAlias/blob/master/images/TeleportEx1-2.png)

### `/tp -`
Anyone who has used a terminal like bash long enough may have heard of the `cd -` command, which brings you back to the directory you just ran `cd` from.
`tp -` works the same way, taking you back to the last place you teleported from.
Only saves teleport data when you are teleported by a command - teleportation caused by ender pearls, nether portals, etc. won't affect the location that `/tp -` will take you back to.

![Example2-1](https://github.com/maxnz/TeleportAlias/blob/master/images/TeleportEx2-1.png)
![Example2-2](https://github.com/maxnz/TeleportAlias/blob/master/images/TeleportEx2-2.png)

## Modifying Aliases
### Commands
- `/addalias alias x y z` - Add a new alias
  - Requires `teleportalias.addalias` permission
- `/editalias alias x y z` - Edit an alias
  - Requires `teleportalias.editalias` permission
- `/listalias` - List all aliases
  - No permissions required
- `/removealias alias [alias...]` - Remove an alias
  - Requires `teleportalias.removealias` permission

## Permissions
- `teleportalias.*` - Grant access to all teleportalias commands
- `teleportalias.addalias` - Grant ability to add new aliases
- `teleportalias.editalias` - Grant ability to edit an existing alias
- `teleportalias.removealias` - Grant ability to remove existing aliases
