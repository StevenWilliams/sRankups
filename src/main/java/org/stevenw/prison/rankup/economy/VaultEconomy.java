package org.stevenw.prison.rankup.economy;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.stevenw.prison.rankup.sRankup;

public class VaultEconomy implements Economy {
    private net.milkbowl.vault.economy.Economy econ;
    private sRankup plugin;
    public VaultEconomy(sRankup plugin) {
        this.plugin =plugin;
        RegisteredServiceProvider<net.milkbowl.vault.economy.Economy> rsp = plugin.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        econ = rsp.getProvider();
    }
    @Override
    public boolean deposit(Player player, long amount) {
        return econ.depositPlayer(player, amount).transactionSuccess();
    }

    @Override
    public boolean withdraw(Player player, long amount) {
        plugin.getLogger().info("withdraw amount " + amount);
        return econ.withdrawPlayer(player.getName(), amount).transactionSuccess();
    }

    @Override
    public boolean has(Player player, long amount) {
        return econ.has(player, amount);
    }

    @Override
    public boolean deposit(Player player, long amount, String message) {
        return deposit(player, amount);
    }

    @Override
    public boolean withdraw(Player player, long amount, String message) {
        return withdraw(player, amount);
    }

    @Override
    public boolean withdraw(Player player, String currency, long amount, String message) {
        return false;
    }

    @Override
    public boolean withdraw(Player player, String currency, long amount) {
        return false;
    }

    @Override
    public boolean has(Player player, String currency, long amount) {
        return false;
    }

    @Override
    public long getBalance(OfflinePlayer player) {
        return Math.round((econ.getBalance(player)));
    }


}
