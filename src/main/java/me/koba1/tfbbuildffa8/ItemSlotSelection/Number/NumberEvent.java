package me.koba1.tfbbuildffa8.ItemSlotSelection.Number;

import me.koba1.tfbbuildffa8.API.FFAApi;
import me.koba1.tfbbuildffa8.ItemSlotSelection.Item.ItemInventory;
import me.koba1.tfbbuildffa8.Main;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NumberEvent implements Listener {

    private Main m = Main.getPlugin(Main.class);

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase("§8Helyválasztó")) {
            e.setCancelled(true);
            if (e.getWhoClicked().getWorld().getName().equalsIgnoreCase("buildffa")) {
                if (e.getClickedInventory().equals(e.getView().getTopInventory())) {
                    try {
                        if (e.getCurrentItem().getType() != Material.AIR) {
                            if (e.getCurrentItem().hasItemMeta()) {
                                if (e.getCurrentItem().isSimilar(backButton())) {
                                    e.getWhoClicked().openInventory(ItemInventory.inv());
                                    return;
                                }
                                if (e.getCurrentItem().getType() == Material.SKULL_ITEM) {

                                    int slotPicked = Integer.parseInt(e.getCurrentItem().getItemMeta().getDisplayName().substring(12));
                                    String type = e.getView().getTopInventory().getItem(13).getType().name();

                                    if(!e.getWhoClicked().getInventory().contains(e.getView().getItem(13))) {
                                        ItemStack is = e.getView().getBottomInventory().getItem(slotPicked - 1);
                                        ItemStack is2 = e.getView().getTopInventory().getItem(13);
                                        int asd = m.getConfig().getInt("ItemSlot." + e.getWhoClicked().getUniqueId() + "." + is2.getType().name());

                                        e.getWhoClicked().getInventory().remove(is);
                                        e.getWhoClicked().getInventory().setItem(asd, is);
                                        FFAApi.change((Player) e.getWhoClicked(), slotPicked - 1, e.getView().getTopInventory().getItem(13));
                                        e.getWhoClicked().sendMessage("§aNincs ilyen tárgy nálad, azonban sikeresen áthelyezted a tárgyat!");
                                        return;
                                    }

                                    if(!FFAApi.successfullyChange(e.getView().getTopInventory().getItem(13), slotPicked - 1, (Player) e.getWhoClicked())) {

                                        e.getWhoClicked().sendMessage("§aSikeresen áthelyezted a tárgyat!");

                                        int previousSlot = m.getConfig().getInt("ItemSlot." + e.getWhoClicked().getUniqueId() + "." + type);
                                        String item = null;

                                        for(String str : m.getConfig().getConfigurationSection("ItemSlot." + e.getWhoClicked().getUniqueId()).getKeys(false)) {
                                            if(m.getConfig().getInt("ItemSlot." + e.getWhoClicked().getUniqueId() + "." + str) == (slotPicked - 1)) {
                                                item = str;
                                                break;
                                            }
                                        }

                                        //FFAApi.change((Player) e.getWhoClicked(), slotPicked - 1, e.getView().getTopInventory().getItem(13));
                                        m.getConfig().set("ItemSlot." + e.getWhoClicked().getUniqueId() + "." + item, previousSlot);
                                        m.getConfig().set("ItemSlot." + e.getWhoClicked().getUniqueId() + "." + type, slotPicked - 1);
                                        m.saveConfig();

                                        e.getWhoClicked().getInventory().remove(e.getView().getTopInventory().getItem(13));
                                        e.getWhoClicked().getInventory().setItem(slotPicked - 1, e.getView().getTopInventory().getItem(13));
                                        e.getWhoClicked().closeInventory();
                                    } else {
                                        e.getWhoClicked().sendMessage("§aSikeresen áthelyezted a tárgyat!");
                                    }
                                }
                            }
                        }
                    } catch (NullPointerException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    public static ItemStack backButton() {
        ItemStack is = new ItemStack(Material.INK_SACK, 1, (short) 1);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§cVissza");
        im.addEnchant(Enchantment.DURABILITY, 2, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        is.setItemMeta(im);
        return is;
    }


    public static ItemStack quit() {
        ItemStack is = new ItemStack(Material.DARK_OAK_DOOR_ITEM);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§cKilépés");
        im.addEnchant(Enchantment.DURABILITY, 2, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack glass() {
        ItemStack is = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§5");
        is.setItemMeta(im);
        return is;
    }

    /*e.getWhoClicked().sendMessage("§aSikeresen áthelyezted a tárgyat, amikor hozzád kerül már az új helyre rakja!");

                                        int currentSlot = m.getConfig().getInt("ItemSlot." + e.getWhoClicked().getUniqueId() + "." + e.getCurrentItem().getType().name());
                                        try {
                                            ItemStack currentOnSlot = e.getWhoClicked().getInventory().getItem(slotPicked - 1);
                                            m.getConfig().set("ItemSlot." + e.getWhoClicked().getUniqueId() + "." + currentOnSlot.getType().name(), currentSlot);
                                            m.saveConfig();
                                        } catch (NullPointerException ex) {

                                        }

                                        m.getConfig().set("ItemSlot." + e.getWhoClicked().getUniqueId() + "." + e.getCurrentItem().getType().name(), slotPicked - 1);
                                        m.saveConfig();

                                        e.getWhoClicked().closeInventory();
                                        return;*/
}
