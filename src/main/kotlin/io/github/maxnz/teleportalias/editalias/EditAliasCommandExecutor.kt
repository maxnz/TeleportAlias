package io.github.maxnz.teleportalias.editalias

import io.github.maxnz.teleportalias.Alias
import io.github.maxnz.teleportalias.aliases
import io.github.maxnz.teleportalias.calculateAliasName
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class EditAliasCommandExecutor : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.size != 4) {
            sender.sendMessage("Incorrect number of arguments: ${args.size} (should be 4)")
            return false
        }

        val aliasName = calculateAliasName(args[0])

        val existingAlias = aliases.find { it.name == aliasName } ?: run {
            sender.sendMessage("Alias $aliasName not found")
            return false
        }

        val oldCoords = existingAlias.coordinates()

        val newAlias = Alias.newAlias(sender, args) ?: return false
        if (newAlias == existingAlias) {
            sender.sendMessage("Alias $aliasName already has those target coordinates")
            return false
        }


        aliases.remove(existingAlias)
        aliases += newAlias

        sender.sendMessage("Modified alias ${newAlias.name} (old target $oldCoords) to point to target ${newAlias.coordinates()}")

        return true
    }
}