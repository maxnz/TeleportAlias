package io.github.maxnz.teleportalias.addalias

import io.github.maxnz.teleportalias.Alias
import io.github.maxnz.teleportalias.aliases
import io.github.maxnz.teleportalias.calculateAliasName
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class AddAliasCommandExecutor : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.size != 4) {
            sender.sendMessage("Incorrect number of arguments: ${args.size} (should be 4)")
            return false
        }

        val aliasName = calculateAliasName(args[0])

        if (aliases.find { it.name == aliasName } != null) {
            sender.sendMessage("Alias $aliasName already exists")
            return false
        }

        val newAlias = Alias.newAlias(sender, args) ?: return false

        aliases += newAlias

        sender.sendMessage("Added alias ${newAlias.name} with target ${newAlias.coordinates()}")

        return true
    }
}