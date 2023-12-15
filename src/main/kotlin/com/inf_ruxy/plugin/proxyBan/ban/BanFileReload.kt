package com.inf_ruxy.plugin.proxyBan.ban

import com.inf_ruxy.plugin.proxyBan.ProxyBanApi.banListFileManager

object BanFileReload {

    fun playerBanReload() {
        banListFileManager.loadBanList()

    }

    fun playerIPBanReload() {
        banListFileManager.loadIPBanList()

    }

}