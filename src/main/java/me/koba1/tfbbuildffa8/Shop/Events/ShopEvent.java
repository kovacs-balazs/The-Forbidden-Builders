package me.koba1.tfbbuildffa8.Shop.Events;

import me.koba1.tfbbuildffa8.API.FFABuyAPI;
import me.koba1.tfbbuildffa8.Items.ItemList;
import me.koba1.tfbbuildffa8.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ShopEvent implements Listener {

    private Main m = Main.getPlugin(Main.class);

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase("§8Bolt")) {
            e.setCancelled(true);
            if (e.getWhoClicked().getWorld().getName().equalsIgnoreCase("buildffa")) {
                try {
                    if (e.getClickedInventory().equals(e.getView().getTopInventory())) {
                        if (e.getCurrentItem().getType() != Material.AIR) {
                            if (e.getCurrentItem().hasItemMeta()) {
                                Player p = (Player) e.getWhoClicked();
                                if (e.getCurrentItem().getType() == Material.SLIME_BLOCK) {
                                    FFABuyAPI.buyItem(p, ItemList.slime());
                                }
                                //
                                else if (e.getCurrentItem().getType() == Material.STONE_PLATE) {
                                    FFABuyAPI.buyItem(p, ItemList.platform());
                                }
                                //
                                else if (e.getCurrentItem().getType() == Material.ENDER_PEARL) {
                                    FFABuyAPI.buyItem(p, ItemList.enderpearl());
                                }
                                //
                                else if (e.getCurrentItem().getType() == Material.GOLDEN_APPLE) {
                                    FFABuyAPI.buyItem(p, ItemList.goldenapple());
                                }
                                //
                                else if (e.getCurrentItem().getType() == Material.DARK_OAK_DOOR_ITEM)
                                    e.getWhoClicked().closeInventory();
                            }
                        }
                    }
                } catch (NullPointerException ex) {

                }
            }
        }
    }
}
