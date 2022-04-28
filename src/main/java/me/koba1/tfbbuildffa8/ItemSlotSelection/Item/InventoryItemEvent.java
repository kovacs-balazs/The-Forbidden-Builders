package me.koba1.tfbbuildffa8.ItemSlotSelection.Item;

import me.koba1.tfbbuildffa8.Events.JoinWorldEvent;
import me.koba1.tfbbuildffa8.ItemSlotSelection.Number.GUIMenuNumbers;
import me.koba1.tfbbuildffa8.Items.ItemList;
import me.koba1.tfbbuildffa8.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryItemEvent implements Listener {

    private Main m = Main.getPlugin(Main.class);

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase("§8Tárgyválasztó")) {
            e.setCancelled(true);
            if (e.getWhoClicked().getWorld().getName().equalsIgnoreCase("buildffa")) {
                if(e.getClickedInventory() != null) {
                    if (e.getClickedInventory().equals(e.getView().getTopInventory())) {
                        try {
                            if ((e.getCurrentItem().getType() != Material.AIR
                                    && e.getCurrentItem().getType() != Material.DARK_OAK_DOOR_ITEM
                                    && e.getCurrentItem().getType() != Material.BARRIER
                                    && e.getCurrentItem().getType() != Material.STAINED_GLASS_PANE)) {

                                if (e.getCurrentItem().hasItemMeta()) {
                                    e.getWhoClicked().openInventory(GUIMenuNumbers.inv(e.getCurrentItem()));
                                } else if (e.getCurrentItem().getType() == Material.WOOL) {
                                    e.getWhoClicked().openInventory(GUIMenuNumbers.inv(e.getCurrentItem()));
                                } else if (e.getCurrentItem().getType() == Material.GOLDEN_APPLE) {
                                    e.getWhoClicked().openInventory(GUIMenuNumbers.inv(e.getCurrentItem()));
                                } else if (e.getCurrentItem().getType() == Material.ENDER_PEARL) {
                                    e.getWhoClicked().openInventory(GUIMenuNumbers.inv(e.getCurrentItem()));
                                } else if (e.getCurrentItem().getType() == Material.WOOD_SWORD) {
                                    e.getWhoClicked().openInventory(GUIMenuNumbers.inv(e.getCurrentItem()));
                                }
                            } else if(e.getCurrentItem().getType() == Material.BARRIER) {
                                m.getConfig().set("ItemSlot." + e.getWhoClicked().getUniqueId() + "." + "STICK", 0);
                                m.getConfig().set("ItemSlot." + e.getWhoClicked().getUniqueId() + "." + "WOOL", 2);
                                m.getConfig().set("ItemSlot." + e.getWhoClicked().getUniqueId() + "." + "GOLDEN_APPLE", 6);
                                m.getConfig().set("ItemSlot." + e.getWhoClicked().getUniqueId() + "." + "STONE_PLATE", 4);
                                m.getConfig().set("ItemSlot." + e.getWhoClicked().getUniqueId() + "." + "ENDER_PEARL", 5);
                                m.getConfig().set("ItemSlot." + e.getWhoClicked().getUniqueId() + "." + "SLIME_BLOCK", 3);
                                m.getConfig().set("ItemSlot." + e.getWhoClicked().getUniqueId() + "." + "WOOD_SWORD", 1);
                                m.saveConfig();

                                if(e.getWhoClicked().getInventory().contains(ItemList.slime())) {
                                    ItemList.slime().setAmount(
                                            e.getWhoClicked().getInventory().getItem(
                                                    e.getWhoClicked().getInventory().first(
                                                            ItemList.slime().getType())).getAmount());
                                    e.getWhoClicked().getInventory().setItem(3, ItemList.slime());
                                }
                                if(e.getWhoClicked().getInventory().contains(ItemList.platform())) {
                                    ItemList.platform().setAmount(
                                            e.getWhoClicked().getInventory().getItem(
                                            e.getWhoClicked().getInventory().first(
                                                    ItemList.platform().getType())).getAmount());
                                    e.getWhoClicked().getInventory().setItem(4, ItemList.platform());
                                }
                                if(e.getWhoClicked().getInventory().contains(ItemList.goldenapple())) {
                                    ItemList.goldenapple().setAmount(
                                            e.getWhoClicked().getInventory().getItem(
                                                    e.getWhoClicked().getInventory().first(
                                                            ItemList.goldenapple().getType())).getAmount());
                                    e.getWhoClicked().getInventory().setItem(6, ItemList.goldenapple());
                                }
                                JoinWorldEvent.addItems((Player) e.getWhoClicked());
                                return;
                            } else if(e.getCurrentItem().getType() == Material.DARK_OAK_DOOR_ITEM) {
                                e.getWhoClicked().closeInventory();
                            }
                        } catch (NullPointerException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static int getAmount(Player p, ItemStack is) {
        for(ItemStack is2 : p.getInventory().getContents()) {
            if(is2.hasItemMeta() && is.hasItemMeta()) {
                if(is2.getItemMeta().equals(is.getItemMeta())) {
                    if((is2.getAmount() + is.getAmount()) > 64) {
                        return -1;
                    }
                    return is2.getAmount() + is.getAmount();
                }
            }
        }
        return -1;
    }
}
