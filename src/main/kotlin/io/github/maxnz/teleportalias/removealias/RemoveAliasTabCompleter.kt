package io.github.maxnz.teleportalias.removealias

import io.github.maxnz.teleportalias.aliases
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class RemoveAliasTabCompleter : TabCompleter {
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String> {
        return aliases.map { it.name }.toMutableList()
    }

}