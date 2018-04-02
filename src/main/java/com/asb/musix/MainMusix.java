package com.asb.musix;

import com.asb.musix.raag.impl.Bhupali;
import com.asb.musix.raag.impl.Yaman;
import com.asb.musix.tune.Composer;
import lombok.val;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

/**
 * MainMusix.
 * Created by bhaarjun on 3/30/18.
 */
public class MainMusix {

    public static void main(String[] args) {
        val bhupali = new Bhupali();
        val yaman = new Yaman();
        val composer = new Composer();
        val musix = composer.getTune(yaman.get(), 4, 80);
        System.out.println(musix);

        val pattern = new Pattern(musix)
                .setInstrument("Violin")
                .setTempo(100);
        val player = new Player();
        player.play(pattern);
    }

}
