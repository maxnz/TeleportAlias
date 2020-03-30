package io.github.maxnz.teleportalias

import io.github.maxnz.teleportalias.teleport.TeleportLocation

val aliases = mutableListOf<Alias>()
val playerLastPositions = mutableMapOf<String, TeleportLocation>()


fun calculateAliasName(name: String) = if (name.startsWith('$')) name else "$$name"
