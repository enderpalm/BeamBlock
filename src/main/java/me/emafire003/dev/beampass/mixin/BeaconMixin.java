package me.emafire003.dev.beampass.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.emafire003.dev.beampass.BeamBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BeaconBlockEntity.class)
public class BeaconMixin {

	private static BlockState state;

	@ModifyExpressionValue(
			method = "tick",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getOpacity(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)I")
	)
	private static int onlyIfNotBeamPassable(int original) {
		return state != null && BeamBlock.beamDisablingBlocks.contains(state.getBlock()) ? original : 0;
	}

	//Gets the block
	@Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getBlock()Lnet/minecraft/block/Block;"), locals = LocalCapture.CAPTURE_FAILSOFT)
	private static void getBlockState(World world, BlockPos pos, BlockState state, BeaconBlockEntity blockEntity, CallbackInfo ci, int i, int j, int k, BlockPos blockPos, BeaconBlockEntity.BeamSegment beamSegment, int l, int m, BlockState blockState){
		BeaconMixin.state = blockState;
	}
}


