package org.stevenw.prison.rankup;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.stevenw.prison.rankup.requirements.RequirementMoney;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Rank {
    private final String rank;
    private final HashMap<Integer, RankupRequirement> requirements = new HashMap<>();
    private final ConfigurationSection section;
    private final sRankup plugin;
    public Rank(sRankup plugin, String rank) {
        this.plugin = plugin;
        this.rank = rank;
        this.section = plugin.getConfig().getConfigurationSection("rankups." + rank);
        Set<String> keys = plugin.getConfig().getConfigurationSection("rankups." + rank + ".requirements").getKeys(false);
        for(String key : keys) {
            try {
                int id = Integer.valueOf(key);
                String type = plugin.getConfig().getString("rankups." + rank + ".requirements." + key + ".type");
                Class<?> clazz = Class.forName(type);
                Constructor<?> constructor = clazz.getConstructor(sRankup.class, ConfigurationSection.class);
                Object instance = constructor.newInstance(plugin, plugin.getConfig().getConfigurationSection("rankups." + rank + ".requirements." + key));
                requirements.put(id, (RankupRequirement) instance);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch(NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
    public String getName() {
        return this.rank;
    }

    public ConfigurationSection getConfigSection() {
        return this.section;
    }
    public Collection<RankupRequirement> getRequirements() {
        return this.requirements.values();
    }

    public sRankup getPlugin() {
        return this.plugin;
    }

    public boolean canRankup(Player player) {
        for(RankupRequirement requirement : requirements.values()) {
            if(!requirement.isFulfilled(player)) {
                return false;
            }
        }
        return true;
    }

    public void rankup(Player player) {
        if(!canRankup(player)) return;
        for(RankupRequirement requirement : requirements.values()) {
            requirement.rankup(player);
        }
        plugin.getPermissions().playerAddGroup(player, rank);
    }

    public boolean has(Player player) {
        return plugin.getPermissions().playerInGroup(player, rank);
    }

}
