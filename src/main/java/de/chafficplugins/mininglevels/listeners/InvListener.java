package de.chafficplugins.mininglevels.listeners;

import io.github.chafficui.CrucialAPI.Utils.player.inventory.InventoryItem;
import io.github.chafficui.CrucialAPI.Utils.player.inventory.Page;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InvListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Page page = Page.get(event.getInventory());
        if (page != null) {
            page.click(event);
            InventoryItem item = page.getInventoryItem(event.getCurrentItem());
            if (item != null && !item.isMovable) {
                event.setCancelled(true);
                page.reloadInventory();
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onDrop(PlayerDropItemEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItemDrop().getItemStack();
        Inventory inventory = player.getInventory();
        Page page = Page.get(inventory);
        if(page != null){
            InventoryItem invItem = page.getInventoryItem(item);
            if(invItem != null && !invItem.isMovable){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryMove(InventoryMoveItemEvent event) {
        Page page = Page.get(event.getSource());
        if (page != null) {
            InventoryItem item = page.getInventoryItem(event.getItem());
            if (item != null && !item.isMovable) {
                event.setCancelled(true);
            }
        }
    }
}
