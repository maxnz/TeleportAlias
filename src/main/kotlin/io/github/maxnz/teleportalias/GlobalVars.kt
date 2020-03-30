package io.github.maxnz.teleportalias

import org.bukkit.Location

val aliases = mutableListOf<Alias>()
val playerLastPositions = mutableMapOf<String, Location>()


fun calculateAliasName(name: String) = if (name.startsWith('$')) name else "$$name"
