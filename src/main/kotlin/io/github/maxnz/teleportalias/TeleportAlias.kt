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

package io.github.maxnz.teleportalias

import io.github.maxnz.teleportalias.addalias.AddAliasCommandExecutor
import io.github.maxnz.teleportalias.editalias.EditAliasCommandExecutor
import io.github.maxnz.teleportalias.editalias.EditAliasTabCompleter
import io.github.maxnz.teleportalias.listalias.ListAliasCommandExecutor
import io.github.maxnz.teleportalias.removealias.RemoveAliasCommandExecutor
import io.github.maxnz.teleportalias.removealias.RemoveAliasTabCompleter
import io.github.maxnz.teleportalias.teleport.TeleportCommandExecutor
import io.github.maxnz.teleportalias.teleport.TeleportListener
import io.github.maxnz.teleportalias.teleport.TeleportLocation
import io.github.maxnz.teleportalias.teleport.TeleportTabCompleter
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin

class TeleportAlias : JavaPlugin() {

    init {
        ConfigurationSerialization.registerClass(Alias::class.java)
        ConfigurationSerialization.registerClass(TeleportLocation::class.java)
        this.saveDefaultConfig()
        this.config.getList("aliases")?.forEach {
            aliases += it as Alias
        }
        this.config.getList("playerLastPositions")?.forEach {
            val location = it as TeleportLocation
            playerLastPositions += location.player to location
        }
    }

    override fun onEnable() {
        server.pluginManager.registerEvents(TeleportListener(), this)
        this.getCommand("addalias")?.setExecutor(AddAliasCommandExecutor())
        this.getCommand("editalias")?.apply {
            setExecutor(EditAliasCommandExecutor())
            tabCompleter = EditAliasTabCompleter()
        }
        this.getCommand("listalias")?.setExecutor(ListAliasCommandExecutor())
        this.getCommand("removealias")?.apply {
            setExecutor(RemoveAliasCommandExecutor())
            tabCompleter = RemoveAliasTabCompleter()
        }
        this.getCommand("teleport")?.apply {
            setExecutor(TeleportCommandExecutor())
            tabCompleter = TeleportTabCompleter()
        }
        this.getCommand("tp")?.apply {
            setExecutor(TeleportCommandExecutor())
            tabCompleter = TeleportTabCompleter()
        }
        super.onEnable()
    }

    override fun onDisable() {
        this.config.set("aliases", aliases)
        this.config.set("playerLastPositions", playerLastPositions.values.toMutableList())
        this.saveConfig()
        super.onDisable()
    }
}