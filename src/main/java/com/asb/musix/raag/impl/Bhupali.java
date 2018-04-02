package com.asb.musix.raag.impl;

import com.asb.musix.ds.Note;
import com.asb.musix.raag.Raag;
import lombok.val;

/**
 * Bhupali.
 * Created by bhaarjun on 3/30/18.
 */
public class Bhupali implements Raag {

    private final Note startingNote;

    public Bhupali() {
        startingNote = new Note("C");
        startingNote.setIncForward(true);
        startingNote.setDecBackward(true);

        val d = new Note("D");
        val e = new Note("E");
        val g = new Note("G");
        val a = new Note("A");

        startingNote.setNext(d);
        d.setNext(e);
        e.setNext(g);
        g.setNext(a);
        a.setNext(startingNote);

        startingNote.setPrevious(a);
        a.setPrevious(g);
        g.setPrevious(e);
        e.setPrevious(d);
        d.setPrevious(startingNote);

    }

    @Override
    public Note get() {
        return startingNote;
    }

}
