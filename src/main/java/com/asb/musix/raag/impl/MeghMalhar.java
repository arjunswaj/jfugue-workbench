package com.asb.musix.raag.impl;

import com.asb.musix.ds.Edge;
import com.asb.musix.ds.Note;
import com.asb.musix.raag.RaagProvider;
import lombok.val;

import java.util.Arrays;

import static com.asb.musix.constants.MusicConstants.*;

/**
 * MeghMalhar.
 * Created by bhaarjun on 4/4/18.
 */
public class MeghMalhar implements RaagProvider {

    private final Note firstNote;

    public MeghMalhar() {
        val s = new Note(SHADAJ);
        val m = new Note(MADHYAM);
        val r = new Note(RISHABH);
        val p = new Note(PANCHAM);
        val nk = new Note(KOMAL_NISHAD);
        val n = new Note(NISHAD);

        s.setEdges(Arrays.asList(
                new Edge(m, 0),
                new Edge(nk, -1)
        ));

        m.setEdges(Arrays.asList(
                new Edge(p, 0),
                new Edge(r, 0),
                new Edge(n, -1)
        ));

        r.setEdges(Arrays.asList(
                new Edge(s, 0),
                new Edge(m, 0)
        ));

        p.setEdges(Arrays.asList(
                new Edge(nk, 0),
                new Edge(m, 0)
        ));

        nk.setEdges(Arrays.asList(
                new Edge(p, 0),
                new Edge(n, 0)
        ));

        n.setEdges(Arrays.asList(
                new Edge(s, 1),
                new Edge(r, 1)
        ));

        firstNote = s;
    }

    @Override
    public Note get() {
        return firstNote;
    }
}
