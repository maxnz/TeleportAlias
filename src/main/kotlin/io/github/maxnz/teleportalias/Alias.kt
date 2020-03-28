package io.github.maxnz.teleportalias

import org.bukkit.command.CommandSender
import org.bukkit.configuration.serialization.ConfigurationSerializable

data class Alias(
    val name: String,
    val x: Int,
    val y: Int,
    val z: Int
) : ConfigurationSerializable {

    constructor(map: Map<String, Any?>) :
            this(
                map["name"].toString(),
                map["x"].toString().toInt(),
                map["y"].toString().toInt(),
                map["z"].toString().toInt()
            )

    override fun serialize(): MutableMap<String, Any> {
        return mutableMapOf(
            "name" to name,
            "x" to x,
            "y" to y,
            "z" to z
        )
    }

    fun coordinates(): String = "$x $y $z"

    companion object {
        fun newAlias(sender: CommandSender, args: Array<out String>): Alias? {
            var validFormat = true
            val name = calculateAliasName(args[0])

            val x = args[1].toIntOrNull() ?: run {
                sender.sendMessage("x should be Int")
                validFormat = false
                0
            }
            val y = args[2].toIntOrNull() ?: run {
                sender.sendMessage("y should be Int")
                validFormat = false
                0
            }
            val z = args[3].toIntOrNull() ?: run {
                sender.sendMessage("z should be Int")
                validFormat = false
                0
            }

            return if (!validFormat) null
            else Alias(name, x, y, z)
        }
    }
}

fun calculateAliasName(name: String) = if (name.startsWith('$')) name else "$$name"
