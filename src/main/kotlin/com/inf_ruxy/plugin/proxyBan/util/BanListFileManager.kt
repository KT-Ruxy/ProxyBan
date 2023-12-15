package com.inf_ruxy.plugin.proxyBan.util

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.inf_ruxy.plugin.proxyBan.ProxyBanApi.logger
import com.inf_ruxy.plugin.proxyBan.data.BanInfo
import com.inf_ruxy.plugin.proxyBan.data.IPBanInfo
import java.nio.file.Files
import java.nio.file.Path

class BanListFileManager(dataFolderPath: Path) {
    private val banListFile: Path = dataFolderPath.resolve("BanList.json")
    private val ipBanListFile: Path = dataFolderPath.resolve("IP-BanList.json")
    private val objectMapper = jacksonObjectMapper()

    fun checkOrCreateFile() {
        createFileIfNotExists(banListFile, "BanList.json")
        createFileIfNotExists(ipBanListFile, "IP-BanList.json")
    }

    private inline fun <reified T> loadListFromFile(file: Path): List<T> {
        if (Files.exists(file)) {
            val content = Files.readString(file)
            return objectMapper.readValue(content)
        }
        return emptyList()
    }

    fun loadBanList(): List<BanInfo> = loadListFromFile(banListFile)

    fun loadIPBanList(): List<IPBanInfo> = loadListFromFile(ipBanListFile)

    private fun createFileIfNotExists(file: Path, fileName: String) {
        try {
            if (!Files.exists(file)) {
                Files.createFile(file)
                logger.info("$fileName created.")
                return
            } else {
                logger.info("$fileName is already created.")
                return
            }
        } catch (e: Exception) {
            logger.error("Error managing $fileName: ${e.message}")
        }
    }
}

