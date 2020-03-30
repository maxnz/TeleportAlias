package io.github.maxnz.teleportalias.teleport

import io.github.maxnz.teleportalias.playerLastPositions
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerTeleportEvent

class TeleportListener: Listener {

    @EventHandler(priority = EventPriority.HIGH)
    fun checkTeleportType(event: PlayerTeleportEvent) {
        if (event.cause == PlayerTeleportEvent.TeleportCause.COMMAND) {
            playerLastPositions[event.player.name] = TeleportLocation(event.player.name, event.player.location)
        }
    }

}
