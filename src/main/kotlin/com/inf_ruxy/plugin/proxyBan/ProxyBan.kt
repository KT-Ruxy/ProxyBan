package com.inf_ruxy.plugin.proxyBan

import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.plugin.annotation.DataDirectory
import com.velocitypowered.api.proxy.ProxyServer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.file.Path
import javax.inject.Inject

@Suppress("unused")
@Plugin(id = "proxyban", name = "ProxyBan", version = "1.0.0", authors = ["Ruxy"])
class ProxyBan @Inject constructor(
    private val server: ProxyServer,
    private val logger: Logger = LoggerFactory.getLogger("ProxyBan"),
    @DataDirectory val dataDirectory: Path
) {

    @Inject
    fun onEnable() {
        ProxyBanApi.start(server, logger, dataDirectory)
    }

    @Inject
    fun onDisable() {
        ProxyBanApi.stop()
    }

}
