package com.inf_ruxy.plugin.proxyBan.ban

import com.velocitypowered.api.command.SimpleCommand
import com.velocitypowered.api.proxy.ProxyServer
import net.kyori.adventure.text.Component
import java.nio.file.Path
import javax.inject.Inject

class BanManager @Inject constructor(private val server: ProxyServer, private val dataFolderPath: Path) : SimpleCommand {

    fun registerCommands() {
        server.commandManager.register(server.commandManager.metaBuilder("pban").build(), this)
        server.commandManager.register(server.commandManager.metaBuilder("pban-ip").build(), this)
    }

    override fun execute(invocation: SimpleCommand.Invocation) {
        val source = invocation.source()
        val args = invocation.arguments()

        if (args.size < 2) {
            source.sendMessage(Component.text("Usage: /pban <player> <reason>"))
            return
        }

        val playerName = args[0]
        val reason = args.drop(1).joinToString(" ")
        val commandLabel = invocation.alias()

        when (commandLabel) {
            "pban" -> PlayerBan.banPlayer(playerName, reason, source, server, dataFolderPath)
            "pban-ip" -> PlayerIPBan.banPlayerIP(playerName, source, server)
            else -> source.sendMessage(Component.text("Unknown command"))
        }

    }

}
