package com.inf_ruxy.plugin.proxyBan.util

import java.nio.file.Files
import java.nio.file.Path
import org.slf4j.Logger

class DataFolderManager(private val dataFolderPath: Path, private val logger: Logger) {

    fun manageDataFolder() {
        if (!Files.exists(dataFolderPath)) {
            try {
                Files.createDirectories(dataFolderPath)
                logger.info("Data folder created at: ${dataFolderPath.toAbsolutePath()}")
            } catch (e: Exception) {
                logger.error("Failed to create data folder: ${e.message}")
            }
        } else {
            logger.info("Data folder already exists.")
        }
    }

}
