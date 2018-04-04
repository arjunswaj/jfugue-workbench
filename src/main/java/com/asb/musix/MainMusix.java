package com.asb.musix;

import com.asb.musix.composer.SimpleTuneComposer;
import com.asb.musix.raag.impl.MeghMalharWithStructure;
import lombok.val;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

/**
 * MainMusix.
 * Created by bhaarjun on 4/4/18.
 */
public class MainMusix {

    public static void main(String[] args) {
        val tuneComposer = new SimpleTuneComposer();
        val meghMalhar = new MeghMalharWithStructure();

        val musix = tuneComposer.apply(meghMalhar.get(), 500);
        System.out.println(musix);

        val pattern = new Pattern(musix)
                .setInstrument("Violin")
                .setTempo(64);
        val player = new Player();
        player.play(pattern);
    }

}
