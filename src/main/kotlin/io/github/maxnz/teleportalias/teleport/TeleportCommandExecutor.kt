/*
 *  Copyright (c) 2020 Max Narvaez
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

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