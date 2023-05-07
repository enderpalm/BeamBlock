package me.emafire003.dev.beampass;

import com.llamalad7.mixinextras.MixinExtrasBootstrap;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class BeamBlockPreLaunch implements PreLaunchEntrypoint {

	/**
	 * Runs the entrypoint.
	 */
	@Override
	public void onPreLaunch() {
		BeamBlock.LOGGER.info("Starting BeamPass mod...");
		MixinExtrasBootstrap.init();
		BeamBlock.LOGGER.info("Mixin loaded!");
	}
}
