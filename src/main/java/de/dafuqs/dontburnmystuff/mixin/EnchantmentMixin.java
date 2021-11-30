package de.dafuqs.dontburnmystuff.mixin;

import de.dafuqs.dontburnmystuff.DontBurnMyStuff;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {

    @Inject(method= "isAcceptableItem(Lnet/minecraft/item/ItemStack;)Z", at=@At("HEAD"), cancellable = true)
    private void isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        int minFireProtectionLevelRequired = DontBurnMyStuff.getMinFireProtectionLevelRequired();
        if(minFireProtectionLevelRequired > 0) {
            if ((Object) this instanceof ProtectionEnchantment protectionEnchantment) {
                if (protectionEnchantment.protectionType == ProtectionEnchantment.Type.FIRE) {
                    callbackInfoReturnable.setReturnValue(true);
                }
            }
        }
    }

}
