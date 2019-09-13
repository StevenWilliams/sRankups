package org.stevenw.prison.rankup;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.stevenw.prison.rankup.commands.RankupCommand;
import org.stevenw.prison.rankup.economy.Economy;
import org.stevenw.prison.rankup.economy.VulcanEconomyConnector;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class sRankup extends JavaPlugin {
    private Economy econ = null;
    private Permission perms = null;
    private final List<Rank> ranks = new ArrayList<>();


    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        if (!setupEconomy() ) {
            this.getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        Set<String> ranknames = this.getConfig().getConfigurationSection("rankups").getKeys(false);
        for(String rank : ranknames) {
            ranks.add(new Rank(this, rank));
        }
        getCommand("rankup").setExecutor(new RankupCommand(this));

    }

    public List<Rank> getRanks() {
        return this.ranks;
    }

    private boolean setupEconomy() {
        /*
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;*/
        if (getServer().getPluginManager().getPlugin("VulcanEconomy") == null) {
            return false;
        } else {
            this.econ = new VulcanEconomyConnector(this); //new VaultEconomy(this);
            return true;
        }
    }
    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
    public Economy getEconomy() {
        return this.econ;
    }
    public Permission getPermissions() {
        return this.perms;
    }
}
