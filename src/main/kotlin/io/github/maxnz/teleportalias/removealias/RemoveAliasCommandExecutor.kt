package io.github.maxnz.teleportalias.removealias

import io.github.maxnz.teleportalias.aliases
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class RemoveAliasCommandExecutor : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) return false
        for (arg in args) {
            val aliasName = if (args[0].startsWith('$')) args[0] else "$${args[0]}"

            val foundAlias = aliases.find { it.name == aliasName }
            if (foundAlias != null) {
                aliases.remove(foundAlias)
                sender.sendMessage("Removed alias $aliasName")
                if (aliases.find { it.name == aliasName } != null) {
                    sender.sendMessage("Somehow there is another alias with name $aliasName. You may want to remove it too.")
                }
            } else {
                sender.sendMessage("Alias $aliasName does not exist")
            }
        }
        return true
    }
}