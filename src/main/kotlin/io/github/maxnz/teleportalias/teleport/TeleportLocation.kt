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

import org.bukkit.Location
import org.bukkit.configuration.serialization.ConfigurationSerializable

data class TeleportLocation(
    val player: String,
    val x: Double,
    val y: Double,
    val z: Double
): ConfigurationSerializable {

    constructor(map: Map<String, Any?>) :
            this(
                map["player"].toString(),
                map["x"].toString().toDouble(),
                map["y"].toString().toDouble(),
                map["z"].toString().toDouble()
            )

    constructor(player: String, location: Location) :
            this(
                player,
                location.x,
                location.y,
                location.z
            )

    override fun serialize(): MutableMap<String, Any> {
        return mutableMapOf(
            "player" to player,
            "x" to x,
            "y" to y,
            "z" to z
        )
    }
}
