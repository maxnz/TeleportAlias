package io.github.maxnz.teleportalias.listalias

import io.github.maxnz.teleportalias.aliases
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class ListAliasCommandExecutor : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (aliases.size == 0)
            sender.sendMessage("No aliases defined")
        else
            aliases.sortedBy { it.name }.forEach { alias ->
                sender.sendMessage("Alias ${alias.name} -> ${alias.coordinates()}")
            }
        return true
    }
}