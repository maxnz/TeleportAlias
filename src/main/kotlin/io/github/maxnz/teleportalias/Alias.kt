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

import org.bukkit.command.CommandSender
import org.bukkit.configuration.serialization.ConfigurationSerializable

data class Alias(
    val name: String,
    val x: Int,
    val y: Int,
    val z: Int
) : ConfigurationSerializable {

    constructor(map: Map<String, Any?>) :
            this(
                map["name"].toString(),
                map["x"].toString().toInt(),
                map["y"].toString().toInt(),
                map["z"].toString().toInt()
            )

    override fun serialize(): MutableMap<String, Any> {
        return mutableMapOf(
            "name" to name,
            "x" to x,
            "y" to y,
            "z" to z
        )
    }

    fun coordinates(): String = "$x $y $z"

    companion object {
        fun newAlias(sender: CommandSender, args: Array<out String>): Alias? {
            var validFormat = true
            val name = calculateAliasName(args[0])

            val x = args[1].toIntOrNull() ?: run {
                sender.sendMessage("x should be Int")
                validFormat = false
                0
            }
            val y = args[2].toIntOrNull() ?: run {
                sender.sendMessage("y should be Int")
                validFormat = false
                0
            }
            val z = args[3].toIntOrNull() ?: run {
                sender.sendMessage("z should be Int")
                validFormat = false
                0
            }

            return if (!validFormat) null
            else Alias(name, x, y, z)
        }
    }
}

