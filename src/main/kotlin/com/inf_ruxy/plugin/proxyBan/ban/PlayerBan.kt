package com.inf_ruxy.plugin.proxyBan.ban

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.inf_ruxy.plugin.proxyBan.data.BanInfo
import com.velocitypowered.api.command.CommandSource
import com.velocitypowered.api.proxy.ProxyServer
import net.kyori.adventure.text.Component
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

object PlayerBan {
    private val objectMapper = jacksonObjectMapper()

    fun banPlayer(playerName: String, reason: String, source: CommandSource, server: ProxyServer, dataFolderPath: Path) {
        val banListFile: Path = dataFolderPath.resolve("BanList.json")
        val player = server.getPlayer(playerName).orElse(null)

        if (player != null) {
            val banInfo = BanInfo(player.username, player.uniqueId.toString(), reason)
            val banList = readBanList(banListFile).toMutableList()
            banList.add(banInfo)
            writeBanList(banList, banListFile)
            source.sendMessage(Component.text("$playerName has been banned for $reason."))
        } else {
            source.sendMessage(Component.text("Player $playerName not found or not online."))
        }
    }

    private fun readBanList(banListFile: Path): List<BanInfo> {
        return if (Files.exists(banListFile)) {
            val content = Files.readString(banListFile)
            objectMapper.readValue(content)
        } else {
            emptyList()
        }
    }

    private fun writeBanList(banList: List<BanInfo>, banListFile: Path) {
        Files.writeString(banListFile, objectMapper.writeValueAsString(banList), StandardOpenOption.CREATE)
        reload()
    }

    private fun reload() {
        BanFileReload.playerBanReload()
    }

}
