package customenchants.customenchants;

import customenchants.customenchants.enchants.Firsthit;
import customenchants.customenchants.enchants.Freeze;
import customenchants.customenchants.enchants.Humore;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public final class Main extends JavaPlugin implements Listener {

    public ArrayList<Enchantment> custom_enchants = new ArrayList<>();
    public Freeze freeze;
    public Humore humore;
    public Firsthit firsthit;


    @Override
    public void onEnable() {

        freeze = new Freeze("freeze");
        humore = new Humore("humore");
        firsthit = new Firsthit("firsthit");

        custom_enchants.add(freeze);
        custom_enchants.add(humore);
        custom_enchants.add(firsthit);

        registerEnchantment(freeze);
        registerEnchantment(humore);
        registerEnchantment(firsthit);

        this.getServer().getPluginManager().registerEvents(freeze, this);
        this.getServer().getPluginManager().registerEvents(humore, this);
        this.getServer().getPluginManager().registerEvents(firsthit, this);
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");

            keyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);

            for (Enchantment enchantment : custom_enchants){
                if(byKey.containsKey(enchantment.getKey())) {
                    byKey.remove(enchantment.getKey());
                }
            }

            Field nameField = Enchantment.class.getDeclaredField("byName");

            nameField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

            for (Enchantment enchantment : custom_enchants){
                if(byName.containsKey(enchantment.getName())) {
                    byName.remove(enchantment.getName());
                }
            }
        } catch (Exception ignored) { }
    }

    public static void registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
        if(registered){
            // It's been registered!
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        ItemStack Freeze = new ItemStack(Material.IRON_AXE);
        ItemMeta Freezemeta = Freeze.getItemMeta();
        Freezemeta.addEnchant(freeze,1,false);
        Freezemeta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "Freeze I", ""));
        Freeze.setItemMeta(Freezemeta);
        player.getInventory().addItem(Freeze);

        ItemStack FreezeBook = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta FreezeBookmeta = FreezeBook.getItemMeta();
        FreezeBookmeta.addEnchant(freeze,1,false);
        FreezeBookmeta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "Freeze I", ""));
        FreezeBook.setItemMeta(FreezeBookmeta);
        player.getInventory().addItem(FreezeBook);

        ItemStack Humore = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta Humoremeta = Humore.getItemMeta();
        Humoremeta.addEnchant(humore,1,false);
        Humoremeta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "Humore I", ""));
        Humore.setItemMeta(Humoremeta);
        player.getInventory().addItem(Humore);

        ItemStack HumoreBook = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta HumoreBookmeta = HumoreBook.getItemMeta();
        HumoreBookmeta.addEnchant(humore,1,false);
        HumoreBookmeta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "Humore I", ""));
        HumoreBook.setItemMeta(HumoreBookmeta);
        player.getInventory().addItem(HumoreBook);

        ItemStack FirstHit = new ItemStack(Material.IRON_SWORD);
        ItemMeta FirstHitmeta = FirstHit.getItemMeta();
        FirstHitmeta.addEnchant(firsthit,1,false);
        FirstHitmeta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "FirstHit I", ""));
        FirstHit.setItemMeta(FirstHitmeta);
        player.getInventory().addItem(FirstHit);

        ItemStack FirstHitBook = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta FirstHitBookmeta = FirstHitBook.getItemMeta();
        FirstHitBookmeta.addEnchant(firsthit,1,false);
        FirstHitBookmeta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "FirstHit I", ""));
        FirstHitBook.setItemMeta(FirstHitmeta);
        player.getInventory().addItem(FreezeBook);
    }
}
