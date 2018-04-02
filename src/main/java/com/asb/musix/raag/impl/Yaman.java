package com.asb.musix.raag.impl;

import com.asb.musix.ds.Note;
import com.asb.musix.raag.Raag;
import lombok.val;

public class Yaman implements Raag {

    private final Note ni;

    public Yaman() {
        this.ni = new Note("C");
        val re = new Note("D#");
        val ga = new Note("F");
        val ma = new Note("G");
        val pa = new Note("G#");
        val dha = new Note("A#");
        val sa = new Note("C#");

        ni.setNext(re);
        ni.setStart(true);

        re.setNext(ga);

        ga.setNext(ma);
        ma.setNext(dha);
        dha.setNext(ni);

        sa.setPrevious(ni);
        ni.setPrevious(dha);
        dha.setPrevious(pa);
        pa.setPrevious(ma);
        ma.setPrevious(ga);
        ga.setPrevious(re);
        re.setPrevious(sa);
    }

    @Override
    public Note get() {
        return ni;
    }
}
