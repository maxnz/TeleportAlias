package io.github.maxnz.teleportalias.teleport

import io.github.maxnz.teleportalias.aliases
import io.github.maxnz.teleportalias.playerLastPositions
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class TeleportCommandExecutor : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        Bukkit.getServer().dispatchCommand(sender, constructCommand(sender, args) ?: return false)
        return true
    }

    private fun constructCommand(sender: CommandSender, args: Array<out String>): String? {
        val builder = StringBuilder()
        builder.append("minecraft:tp")
        for (arg in args) {
            builder.append(' ')
            when {
                arg.isAlias() -> builder.append(arg.replaceAlias(sender) ?: return null)
                arg.isBack() -> builder.append(arg.replaceBack(sender) ?: return null)
                else -> builder.append(arg)
            }
        }
        return builder.toString()
    }

    private fun String.isAlias() = this.startsWith("$")

    private fun String.isBack() = this == "-"


    private fun String.replaceAlias(sender: CommandSender): String? {
        val alias = aliases.find { it.name == this } ?: run {
            sender.sendMessage("Could not find alias for $this")
            return null
        }
        return alias.coordinates()
    }

    private fun String.replaceBack(sender: CommandSender): String? {
        val lastLocation = playerLastPositions[sender.name] ?: run {
            sender.sendMessage("No last location for player ${sender.name}")
            return null
        }
        return "${lastLocation.x} ${lastLocation.y} ${lastLocation.z}"
    }
}