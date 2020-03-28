package io.github.maxnz.teleportalias.editalias

import io.github.maxnz.teleportalias.aliases
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class EditAliasTabCompleter : TabCompleter {
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String> {
        return if (args.size <= 1) aliases.map { it.name }.toMutableList()
        else {
            val foundAlias = aliases.find { it.name == args[0] } ?: return mutableListOf()
            when (args.size) {
                2 -> mutableListOf(foundAlias.x.toString())
                3 -> mutableListOf(foundAlias.y.toString())
                4 -> mutableListOf(foundAlias.z.toString())
                else -> mutableListOf()
            }
        }
    }
}