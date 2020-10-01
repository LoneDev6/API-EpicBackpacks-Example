package dev.lone.Tests;

import dev.lone.EpicBackpacks.API.EpicBackpackCloseEvent;
import dev.lone.EpicBackpacks.API.EpicBackpackOpenEvent;
import dev.lone.EpicBackpacks.API.EpicBackpackPersonalCloseEvent;
import dev.lone.EpicBackpacks.API.EpicBackpackPersonalOpenEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.WeakHashMap;

public final class Main extends JavaPlugin implements Listener
{
    WeakHashMap<Player, Boolean> handled = new WeakHashMap<>();
    WeakHashMap<Player, Boolean> handledPersonal = new WeakHashMap<>();

    @Override
    public void onEnable()
    {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    void onEpicBackpackOpenEvent(EpicBackpackOpenEvent e)
    {
        if(e.getBackpackID().contains("red"))
        {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "Hey! you're trying to open a red backpack, don't do that!");
            return;
        }


        if(handled.containsKey(e.getPlayer()))
            return;
        e.getGui().addItem(new ItemStack(Material.DIAMOND_BLOCK));
        e.getPlayer().sendMessage("You opened " + e.getBackpackID() + " backpack! I added a DIAMOND_BLOCK inside");
    }


    @EventHandler
    void onEpicBackpackCloseEvent(EpicBackpackCloseEvent e)
    {
        if(handled.containsKey(e.getPlayer()))
            return;
        e.getGui().remove(new ItemStack(Material.DIAMOND_BLOCK));
        e.getPlayer().sendMessage("You closed " + e.getBackpackID() + " backpack! I was just joking, I removed the DIAMOND_BLOCK");

        handled.put(e.getPlayer(), true);
    }

    @EventHandler
    void onEpicBackpackPersonalOpenEvent(EpicBackpackPersonalOpenEvent e)
    {
        if(handledPersonal.containsKey(e.getPlayer()))
            return;
        e.getGui().addItem(new ItemStack(Material.EMERALD_BLOCK));
        e.getPlayer().sendMessage("You opened " + e.getOwner().getName() + "'s backpack! I added a EMERALD_BLOCK inside");
    }


    @EventHandler
    void onEpicBackpackPersonalCloseEvent(EpicBackpackPersonalCloseEvent e)
    {
        if(handledPersonal.containsKey(e.getPlayer()))
            return;
        e.getGui().remove(new ItemStack(Material.EMERALD_BLOCK));
        e.getPlayer().sendMessage("You closed " + e.getOwner().getName() + "'s backpack! I was just joking, I removed the EMERALD_BLOCK");

        handledPersonal.put(e.getPlayer(), true);
    }
}
