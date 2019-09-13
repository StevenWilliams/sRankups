package org.stevenw.prison.rankup.economy;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public interface Economy {
    public boolean deposit(Player player, long amount);

    public boolean withdraw(Player player, long amount);

    public boolean has(Player player, long amount);

    public boolean deposit(Player player, long amount, String message);

    public boolean withdraw(Player player, long amount, String message);

    public boolean withdraw(Player player, String currency, long amount, String message);

    public boolean withdraw(Player player, String currency, long amount);

    public boolean has(Player player, String currency, long amount);

    public long getBalance(OfflinePlayer player);
}
