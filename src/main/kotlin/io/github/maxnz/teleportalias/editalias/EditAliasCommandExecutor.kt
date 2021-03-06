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