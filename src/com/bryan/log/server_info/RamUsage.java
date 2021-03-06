package com.bryan.log.server_info;

import com.bryan.log.ServerLog;
import com.bryan.log.server_log_api.ServerLogEvent;
import com.bryan.log.utils.Methods;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.text.DecimalFormat;

public class RamUsage {

    private Methods methods;
    public RamUsage(ServerLog serverLog) {
        this.methods = new Methods(serverLog);
    }

    public void appendRamUsage() throws IOException {

        long maxMemory = Runtime.getRuntime().maxMemory() / 1048576L;
        long freeMemory = Runtime.getRuntime().freeMemory() / 1048576L;
        long usedMemory = maxMemory - freeMemory;
        double percentUsed = ((usedMemory * 100.0) / maxMemory);

        DecimalFormat twoDPlaces = new DecimalFormat("###,###,###.##");
        String percent = twoDPlaces.format(percentUsed);

        ServerLogEvent logEvent = new ServerLogEvent(methods.getConfigFile().getString("ram-usage").replace("[ram]", usedMemory + "MB / " + maxMemory + "MB (" + percent + "%)"), methods.getTime(), methods.getDate(), "plugins/ServerLog/Server Information/Ram Usage/", "");
        Bukkit.getPluginManager().callEvent(logEvent);

        methods.appendString("/Server Information/Ram Usage/", methods.getConfigFile().getString("ram-usage").replace("[ram]", usedMemory + "MB / " + maxMemory + "MB (" + percent + "%)"));
        methods.appendString("/Compiled Log/", methods.getConfigFile().getString("ram-usage").replace("[ram]", usedMemory + "MB / " + maxMemory + "MB (" + percent + "%)"));
    }

}
