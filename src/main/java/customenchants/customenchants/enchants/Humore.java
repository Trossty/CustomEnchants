package customenchants.customenchants.enchants;

import customenchants.customenchants.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Humore extends Enchantment implements Listener {

    public Humore(String namespace){
        super(new NamespacedKey(Main.getPlugin(Main.class), namespace));
    }

    @Override
    public String getName() {
        return "Humore";
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return true;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        Entity entity = event.getEntity();

        if(entity instanceof Player) return;
        Player player = (Player) entity;

        for(ItemStack armor : player.getInventory().getArmorContents()){
            if(armor.getEnchantments().containsKey(Enchantment.getByKey(getKey()))){
                if(player.getHealth()<10){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,999999,2));
                }
            }
        }
    }

    @EventHandler
    public void humore(EntityRegainHealthEvent event){
        if(!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        for(ItemStack armor : player.getInventory().getArmorContents()){
            if(armor.getEnchantments().containsKey(Enchantment.getByKey(getKey()))){
                if(player.getHealth()>=10){
                    player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                }
            }
        }
    }

}
