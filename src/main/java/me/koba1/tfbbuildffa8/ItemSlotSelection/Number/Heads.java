package me.koba1.tfbbuildffa8.ItemSlotSelection.Number;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

public class Heads {

    public static ItemStack firstHead() {
        ItemStack is = firstHeadPrivate();
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§7Hely: §e§n1");
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack secondHead() {
        ItemStack is = secondHeadPrivate();
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§7Hely: §e§n2");
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack thirdHead() {
        ItemStack is = thirdHeadPrivate();
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§7Hely: §e§n3");
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack negyHead() {
        ItemStack is = negyHeadPrivate();
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§7Hely: §e§n4");
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack otHead() {
        ItemStack is = otHeadPrivate();
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§7Hely: §e§n5");
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack hatHead() {
        ItemStack is = hatHeadPrivate();
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§7Hely: §e§n6");
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack hetHead() {
        ItemStack is = hetHeadPrivate();
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§7Hely: §e§n7");
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack nyolcHead() {
        ItemStack is = nyolcHeadPrivate();
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§7Hely: §e§n8");
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack kilencHead() {
        ItemStack is = kilencHeadPrivate();
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§7Hely: §e§n9");
        is.setItemMeta(im);
        return is;
    }

    private static ItemStack firstHeadPrivate() {
        ItemStack is = new ItemStack(Material.SKULL);
        String value = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzFiYzJiY2ZiMmJkMzc1OWU2YjFlODZmYzdhNzk1ODVlMTEyN2RkMzU3ZmMyMDI4OTNmOWRlMjQxYmM5ZTUzMCJ9fX0=";
        UUID hashAsId = new UUID(value.hashCode(), value.hashCode());
        return Bukkit.getUnsafe().modifyItemStack(is,
                "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + value + "\"}]}}}");
    }

    private static ItemStack secondHeadPrivate() {
        ItemStack is = new ItemStack(Material.SKULL);
        String value =
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGNkOWVlZWU4ODM0Njg4ODFkODM4NDhhNDZiZjMwMTI0ODVjMjNmNzU3NTNiOGZiZTg0ODczNDE0MTk4NDcifX19";
        UUID hashAsId = new UUID(value.hashCode(), value.hashCode());
        return Bukkit.getUnsafe().modifyItemStack(is,
                "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + value + "\"}]}}}");
    }

    private static ItemStack thirdHeadPrivate() {
        ItemStack is = new ItemStack(Material.SKULL);
        String value =
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWQ0ZWFlMTM5MzM4NjBhNmRmNWU4ZTk1NTY5M2I5NWE4YzNiMTVjMzZiOGI1ODc1MzJhYzA5OTZiYzM3ZTUifX19";
        UUID hashAsId = new UUID(value.hashCode(), value.hashCode());
        return Bukkit.getUnsafe().modifyItemStack(is,
                "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + value + "\"}]}}}");
    }

    private static ItemStack negyHeadPrivate() {
        ItemStack is = new ItemStack(Material.SKULL);
        String value =
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDJlNzhmYjIyNDI0MjMyZGMyN2I4MWZiY2I0N2ZkMjRjMWFjZjc2MDk4NzUzZjJkOWMyODU5ODI4N2RiNSJ9fX0=";
        UUID hashAsId = new UUID(value.hashCode(), value.hashCode());
        return Bukkit.getUnsafe().modifyItemStack(is,
                "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + value + "\"}]}}}");
    }

    private static ItemStack otHeadPrivate() {
        ItemStack is = new ItemStack(Material.SKULL);
        String value =
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmQ1N2UzYmM4OGE2NTczMGUzMWExNGUzZjQxZTAzOGE1ZWNmMDg5MWE2YzI0MzY0M2I4ZTU0NzZhZTIifX19";
        UUID hashAsId = new UUID(value.hashCode(), value.hashCode());
        return Bukkit.getUnsafe().modifyItemStack(is,
                "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + value + "\"}]}}}");
    }

    private static ItemStack hatHeadPrivate() {
        ItemStack is = new ItemStack(Material.SKULL);
        String value =
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzM0YjM2ZGU3ZDY3OWI4YmJjNzI1NDk5YWRhZWYyNGRjNTE4ZjVhZTIzZTcxNjk4MWUxZGNjNmIyNzIwYWIifX19";
        UUID hashAsId = new UUID(value.hashCode(), value.hashCode());
        return Bukkit.getUnsafe().modifyItemStack(is,
                "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + value + "\"}]}}}");
    }

    private static ItemStack hetHeadPrivate() {
        ItemStack is = new ItemStack(Material.SKULL);
        String value =
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmRiNmViMjVkMWZhYWJlMzBjZjQ0NGRjNjMzYjU4MzI0NzVlMzgwOTZiN2UyNDAyYTNlYzQ3NmRkN2I5In19fQ==";
        UUID hashAsId = new UUID(value.hashCode(), value.hashCode());
        return Bukkit.getUnsafe().modifyItemStack(is,
                "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + value + "\"}]}}}");
    }

    private static ItemStack nyolcHeadPrivate() {
        ItemStack is = new ItemStack(Material.SKULL);
        String value =
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTkxOTQ5NzNhM2YxN2JkYTk5NzhlZDYyNzMzODM5OTcyMjI3NzRiNDU0Mzg2YzgzMTljMDRmMWY0Zjc0YzJiNSJ9fX0=";
        UUID hashAsId = new UUID(value.hashCode(), value.hashCode());
        return Bukkit.getUnsafe().modifyItemStack(is,
                "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + value + "\"}]}}}");
    }

    private static ItemStack kilencHeadPrivate() {
        ItemStack is = new ItemStack(Material.SKULL);
        String value =
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTY3Y2FmNzU5MWIzOGUxMjVhODAxN2Q1OGNmYzY0MzNiZmFmODRjZDQ5OWQ3OTRmNDFkMTBiZmYyZTViODQwIn19fQ==";
        UUID hashAsId = new UUID(value.hashCode(), value.hashCode());
        return Bukkit.getUnsafe().modifyItemStack(is,
                "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + value + "\"}]}}}");
    }

    public static ItemStack createCustomSkull(int amount, String displayName, List<String> lore, String texture) {
        texture = "http://textures.minecraft.net/texture/" + texture;

        ItemStack skull = new ItemStack(Material.SKULL_ITEM, amount, (short) 3);
        if (texture.isEmpty()) {
            return skull;
        }

        SkullMeta skullMeta = (SkullMeta)skull.getItemMeta();
        skullMeta.setDisplayName(displayName);
        skullMeta.setLore(lore);

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", texture).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        }
        catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        assert profileField != null;
        profileField.setAccessible(true);
        try {
            profileField.set(skullMeta, profile);
        }
        catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        skull.setItemMeta(skullMeta);
        return skull;
    }
}
