package me.koba1.tfbbuildffa8.API;

import me.koba1.tfbbuildffa8.Items.ItemList;
import me.koba1.tfbbuildffa8.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FFAApi {

    private static Main m = Main.getPlugin(Main.class);

    public static boolean successfullyChange(ItemStack is, int slot, Player p) {
        try {
            if(p.getInventory().getItem(slot).equals(is)) {
                return true;
            }
            if (p.getInventory().getItem(slot) == null || p.getInventory().getItem(slot).getType() == Material.AIR) {
                return false;
            } else {

                int currentSlot = m.getConfig().getInt("ItemSlot." + p.getUniqueId() + "." + is.getType().name());
                ItemStack currentOnSlot = p.getInventory().getItem(slot);

                p.getInventory().remove(is);
                p.getInventory().setItem(slot, is);
                p.getInventory().setItem(currentSlot, currentOnSlot);

                m.getConfig().set("ItemSlot." + p.getUniqueId() + "." + is.getType().name(), slot);
                m.getConfig().set("ItemSlot." + p.getUniqueId() + "." + currentOnSlot.getType().name(), currentSlot);
                m.saveConfig();
                return true;
            }
        } catch (NullPointerException ex) {
            return false;
        }
    }

    public static boolean isFully(Player p, ItemStack is) {
        for(ItemStack itemS : p.getInventory().getContents()) {
            if(itemS != null) {
                if (itemS.getType().equals(is.getType())) {
                    if (itemS.getAmount() >= 64) {
                        return true;
                    }
                    if (itemS.getType() != null) {
                        if (itemS.getType() == Material.ENDER_PEARL) {
                            if (itemS.getAmount() >= 16) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void addItem(Player p, ItemStack is) {
        if (p.getInventory().contains(is.getType())) {
            if(!isFully(p, is)) {
                p.getInventory().addItem(is);
            }
        } else {
            p.getInventory().setItem(m.getConfig().getInt("ItemSlot." + p.getUniqueId() + "." + is.getType().name()), is);
        }
    }

    public static void change(Player p, int slot, ItemStack is) {
        String name = is.getType().name();
        if(m.getConfig().getInt("ItemSlot." + p.getUniqueId() + "." + name) == slot) {
            //p.sendMessage("§cMár ezen van!");
        }
        //
        if(m.getConfig().getInt("ItemSlot." + p.getUniqueId() + "." + name) != slot) {
            for(String str : m.getConfig().getConfigurationSection("ItemSlot." + p.getUniqueId()).getKeys(false)) {
                if(m.getConfig().getInt("ItemSlot." + p.getUniqueId() + "." + str) == slot) {
                    int currentSlot = m.getConfig().getInt("ItemSlot." + p.getUniqueId() + "." + name);
                    m.getConfig().set("ItemSlot." + p.getUniqueId() + "." + name, slot);
                    m.getConfig().set("ItemSlot." + p.getUniqueId() + "." + str, currentSlot);
                    m.saveConfig();
                }
            }
        }
    }

    public static void removeItems(Player p) {

        p.getInventory().remove(ItemList.goldenapple().getType());
        p.getInventory().remove(ItemList.platform().getType());
        p.getInventory().remove(ItemList.slime().getType());
    }

    public static void transferDeadItems(Player fromP, Player toP) {
        if (fromP.getInventory().contains(ItemList.slime().getType())) {
            if (!isFully(toP, ItemList.slime())) {
                if (toP.getInventory().contains(ItemList.slime().getType()))
                    toP.getInventory().addItem(ItemList.slime());
                else
                    toP.getInventory().setItem(m.getConfig().getInt("ItemSlot." + toP.getUniqueId() + "." + ItemList.slime().getType().name()), ItemList.slime());
            }
        }

        if (fromP.getInventory().contains(ItemList.platform().getType())) {
            if (!isFully(toP, ItemList.platform())) {
                if (toP.getInventory().contains(ItemList.platform().getType())) {
                    toP.getInventory().addItem(ItemList.platform());
                } else
                    toP.getInventory().setItem(m.getConfig().getInt("ItemSlot." + toP.getUniqueId() + "." + ItemList.platform().getType().name()), ItemList.platform());
            }
        }

        if(fromP.getInventory().contains(ItemList.enderpearl().getType())) {
            if(!isFully(toP, ItemList.enderpearl())) {
                if (toP.getInventory().contains(ItemList.enderpearl().getType())) {
                    toP.getInventory().addItem(ItemList.enderpearl());
                } else
                    toP.getInventory().setItem(m.getConfig().getInt("ItemSlot." + toP.getUniqueId() + "." + ItemList.enderpearl().getType().name()), ItemList.enderpearl());
            }
        }

        if(fromP.getInventory().contains(ItemList.goldenapple().getType())) {
            if(!isFully(toP, ItemList.goldenapple())) {
                if (toP.getInventory().contains(ItemList.goldenapple().getType())) {
                    toP.getInventory().addItem(ItemList.goldenapple());
                } else
                    toP.getInventory().setItem(m.getConfig().getInt("ItemSlot." + toP.getUniqueId() + "." + ItemList.goldenapple().getType().name()), ItemList.goldenapple());
            }
        }
    }

}
