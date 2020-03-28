package io.github.maxnz.teleportalias.teleport

import io.github.maxnz.teleportalias.aliases
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class TeleportTabCompleter : TabCompleter {
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String> {
        var list = mutableListOf(
            "-",
            "@a",
            "@e",
            "@p",
            "@r",
            "@s",
            "~",
            "~ ~",
            "~ ~ ~"
        )
        list.addAll(Bukkit.getServer().onlinePlayers.map { it -> it.name })
        list.addAll(aliases.map { it -> it.name })
        if (args.last().isNotEmpty()) list =
            list.filter { it.startsWith(args.last(), ignoreCase = true) }.toMutableList()
        return list
    }
}