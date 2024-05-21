package de.dafuqs.dontburnmystuff.mixin;

import de.dafuqs.dontburnmystuff.DontBurnMyStuff;
import net.minecraft.component.*;
import net.minecraft.component.type.*;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {

    @Inject(method= "isFireImmune()Z", at=@At("HEAD"), cancellable = true)
    private void dontburnmystuff$isFireImmune(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        ItemStack stack = ((ItemEntity)(Object) this).getStack();
        
        ItemEnchantmentsComponent component = stack.get(DataComponentTypes.ENCHANTMENTS);
        if(component != null) {
            int minFireProtectionLevelRequired = DontBurnMyStuff.getMinFireProtectionLevelRequired();
            if(minFireProtectionLevelRequired == 0) {
                callbackInfoReturnable.setReturnValue(true);
            } else {
                int fireProtectionLevel = component.getLevel(Enchantments.FIRE_PROTECTION);
                if(fireProtectionLevel >= minFireProtectionLevelRequired) {
                    callbackInfoReturnable.setReturnValue(true);
                }
            }
        }
    }

}
