package de.dafuqs.dontburnmystuff.mixin;

import de.dafuqs.dontburnmystuff.DontBurnMyStuff;
import de.dafuqs.dontburnmystuff.config.DontBurnMyStuffConfig;
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

    @Inject(method="Lnet/minecraft/entity/ItemEntity;isFireImmune()Z", at=@At("HEAD"), cancellable = true)
    private void isFireProof(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        ItemStack itemStack = ((ItemEntity)(Object) this).getStack();

        if(itemStack != ItemStack.EMPTY) {
            int minFireProtectionLevelRequired = DontBurnMyStuff.getMinFireProtectionLevelRequired();
            if(minFireProtectionLevelRequired == 0) {
                callbackInfoReturnable.setReturnValue(true);
            } else {
                int fireProtectionLevel = EnchantmentHelper.getLevel(Enchantments.FIRE_PROTECTION, itemStack);
                if(fireProtectionLevel >= minFireProtectionLevelRequired) {
                    callbackInfoReturnable.setReturnValue(true);
                }
            }
        }
    }

}
