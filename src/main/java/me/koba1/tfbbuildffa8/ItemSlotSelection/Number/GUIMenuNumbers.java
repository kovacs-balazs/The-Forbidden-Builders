package me.koba1.tfbbuildffa8.ItemSlotSelection.Number;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUIMenuNumbers {

    public static Inventory inv(ItemStack pickedItem) {
        Inventory inv = Bukkit.createInventory(null, 5*9, "§8Helyválasztó");

        for(int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, NumberEvent.glass());
        }

        inv.setItem(27, Heads.createCustomSkull(1, "§7Slot: §e§n1", null,
                "71bc2bcfb2bd3759e6b1e86fc7a79585e1127dd357fc202893f9de241bc9e530"));

        inv.setItem(28, Heads.createCustomSkull(1, "§7Slot: §e§n2", null,
                "4cd9eeee883468881d83848a46bf3012485c23f75753b8fbe8487341419847"));

        inv.setItem(29, Heads.createCustomSkull(1, "§7Slot: §e§n3", null,
                "1d4eae13933860a6df5e8e955693b95a8c3b15c36b8b587532ac0996bc37e5"));

        inv.setItem(30, Heads.createCustomSkull(1, "§7Slot: §e§n4", null,
                "d2e78fb22424232dc27b81fbcb47fd24c1acf76098753f2d9c28598287db5"));

        inv.setItem(31, Heads.createCustomSkull(1, "§7Slot: §e§n5", null,
                "6d57e3bc88a65730e31a14e3f41e038a5ecf0891a6c243643b8e5476ae2"));

        inv.setItem(32, Heads.createCustomSkull(1, "§7Slot: §e§n6", null,
                "334b36de7d679b8bbc725499adaef24dc518f5ae23e716981e1dcc6b2720ab"));

        inv.setItem(33, Heads.createCustomSkull(1, "§7Slot: §e§n7", null,
                "6db6eb25d1faabe30cf444dc633b5832475e38096b7e2402a3ec476dd7b9"));

        inv.setItem(34, Heads.createCustomSkull(1, "§7Slot: §e§n8", null,
                "59194973a3f17bda9978ed6273383997222774b454386c8319c04f1f4f74c2b5"));

        inv.setItem(35, Heads.createCustomSkull(1, "§7Slot: §e§n9", null,
                "e67caf7591b38e125a8017d58cfc6433bfaf84cd499d794f41d10bff2e5b840"));
        //inv.setItem(27, Heads.firstHead());
//        inv.setItem(28, Heads.secondHead());
//        inv.setItem(29, Heads.thirdHead());
//        inv.setItem(30, Heads.negyHead());
//        inv.setItem(31, Heads.otHead());
//        inv.setItem(32, Heads.hatHead());
//        inv.setItem(33, Heads.hetHead());
//        inv.setItem(34, Heads.nyolcHead());
//        inv.setItem(35, Heads.kilencHead());
        inv.setItem(13, pickedItem);
        inv.setItem(40, NumberEvent.backButton());
        return inv;
    }
}
