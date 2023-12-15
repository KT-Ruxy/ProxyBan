package com.inf_ruxy.plugin.proxyBan

import com.inf_ruxy.plugin.proxyBan.ban.BanManager
import com.inf_ruxy.plugin.proxyBan.util.BanListFileManager
import com.inf_ruxy.plugin.proxyBan.util.DataFolderManager
import com.velocitypowered.api.proxy.ProxyServer
import org.slf4j.Logger
import java.nio.file.Path

object ProxyBanApi {

    private lateinit var server: ProxyServer
    lateinit var logger: Logger
    private lateinit var dataFolderManager: DataFolderManager
    lateinit var banListFileManager: BanListFileManager
    private lateinit var banManager: BanManager

    fun start(server: ProxyServer, logger: Logger, dataDirectory: Path) {
        this.server = server
        this.logger = logger
        dataFolderManager = DataFolderManager(dataDirectory, logger)
        banListFileManager = BanListFileManager(dataDirectory)
        banManager = BanManager(server, dataDirectory)

        dataFolderManager.manageDataFolder()
        banListFileManager.checkOrCreateFile()
        banListFileManager.loadBanList()
        banListFileManager.loadIPBanList()

        banManager.registerCommands()
    }

    fun stop() {

    }

}