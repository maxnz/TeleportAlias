package io.github.maxnz.teleportalias.teleport

import org.bukkit.Location
import org.bukkit.configuration.serialization.ConfigurationSerializable

data class TeleportLocation(
    val player: String,
    val x: Double,
    val y: Double,
    val z: Double
): ConfigurationSerializable {

    constructor(map: Map<String, Any?>) :
            this(
                map["player"].toString(),
                map["x"].toString().toDouble(),
                map["y"].toString().toDouble(),
                map["z"].toString().toDouble()
            )

    constructor(player: String, location: Location) :
            this(
                player,
                location.x,
                location.y,
                location.z
            )

    override fun serialize(): MutableMap<String, Any> {
        return mutableMapOf(
            "player" to player,
            "x" to x,
            "y" to y,
            "z" to z
        )
    }
}
