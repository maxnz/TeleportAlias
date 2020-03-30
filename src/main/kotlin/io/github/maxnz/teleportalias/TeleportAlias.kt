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