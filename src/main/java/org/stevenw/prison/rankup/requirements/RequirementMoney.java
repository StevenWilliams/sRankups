package org.stevenw.prison.rankup.requirements;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.stevenw.prison.rankup.RankupRequirement;
import org.stevenw.prison.rankup.sRankup;

import java.util.ArrayList;
import java.util.List;

public class RequirementMoney extends RankupRequirement{
    private final ConfigurationSection section;
    private final sRankup plugin;
    private final long amount;
    public RequirementMoney(sRankup plugin, ConfigurationSection section) {
        super(plugin, section);
        this.section = section;
        this.plugin = plugin;
        this.amount = section.getLong("value");
    }
    @Override
    public String getType() {
        return this.getClass().getName();
    }

    @Override
    public boolean isFulfilled(Player player) {
        return plugin.getEconomy().has(player, amount);
    }

    @Override
    public String getName(Player player) {
        return "$" + Math.round(amount);
    }

    @Override
    public List<String> getDescription(Player player) {
        List<String> desc = new ArrayList<>();
        desc.add(ChatColor.GOLD + "You have $" + plugin.getEconomy().getBalance(player) + "/" + Math.round(amount) + " needed to rankup");
        return desc;
    }

    @Override
    public void rankup(Player player) {
        plugin.getEconomy().withdraw(player, amount);
    }

    @Override
    public Material getDefaultMaterial() {
        return Material.GOLD_INGOT;
    }

}
