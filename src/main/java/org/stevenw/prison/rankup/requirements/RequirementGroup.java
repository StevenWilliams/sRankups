package org.stevenw.prison.rankup.requirements;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.stevenw.prison.rankup.RankupRequirement;
import org.stevenw.prison.rankup.sRankup;

import java.util.List;

public class RequirementGroup extends RankupRequirement {
    private final String group;
    private final sRankup plugin;
    private final ConfigurationSection section;
    public RequirementGroup(sRankup plugin, ConfigurationSection section) {
        super(plugin, section);
        this.section = section;
        this.plugin = plugin;
        group = section.getString("value");
    }

    @Override
    public String getType() {
        return this.getClass().getName();
    }

    @Override
    public boolean isFulfilled(Player player) {
        return plugin.getPermissions().playerInGroup(player, group);
    }

    @Override
    public String getName(Player player) {
        return "Must be in: " + group;
    }

    @Override
    public Material getDefaultMaterial() {
        return Material.BOOK;
    }


}
