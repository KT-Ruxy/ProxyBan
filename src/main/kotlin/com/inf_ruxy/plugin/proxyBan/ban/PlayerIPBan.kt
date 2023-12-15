package com.inf_ruxy.plugin.proxyBan.ban

import com.velocitypowered.api.command.CommandSource
import com.velocitypowered.api.proxy.ProxyServer
import net.kyori.adventure.text.Component

object PlayerIPBan {

    fun banPlayerIP(playerName: String, source: CommandSource, server: ProxyServer) {
        // IP Ban処理を実装
        // IP Ban情報をJSONファイルに書き込む
        // 例: IPBanInfo(playerIP) を JSON に書き込む
        // この部分は実際のIP Ban処理のロジックに応じて実装が必要
        source.sendMessage(Component.text("IP of $playerName has been banned."))
    }
}
