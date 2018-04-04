package com.asb.musix.raag.impl;

import com.asb.musix.ds.Edge;
import com.asb.musix.ds.Note;
import com.asb.musix.raag.RaagProvider;
import lombok.val;

import java.util.Arrays;
import java.util.Collections;

import static com.asb.musix.constants.MusicConstants.*;

/**
 * MeghMalhar.
 * Created by bhaarjun on 4/4/18.
 */
public class MeghMalharWithStructure implements RaagProvider {

    private final Note firstNote;

    public MeghMalharWithStructure() {
        val s = new Note(SHADAJ);
        val m = new Note(MADHYAM);
        val r = new Note(RISHABH);
        val m2 = new Note(MADHYAM);
        val p = new Note(PANCHAM);
        val nk = new Note(KOMAL_NISHAD);
        val n = new Note(NISHAD);
        val S = new Note(SHADAJ);
        val nl = new Note(NISHAD);
        val r2 = new Note(RISHABH);

        s.setEdges(Collections.singletonList(new Edge(m, 0)));

        m.setEdges(Arrays.asList(
                new Edge(r, 0),
                new Edge(nl, -1)
                                ));

        r.setEdges(Arrays.asList(
                new Edge(m, 0),
                new Edge(m2, 0)
                                ));
        m2.setEdges(Arrays.asList(
                new Edge(r, 0),
                new Edge(p, 0)
                                 ));

        p.setEdges(Arrays.asList(
                new Edge(nk, 0),
                new Edge(m, 0)
                                ));

        nk.setEdges(Arrays.asList(
                new Edge(n, 0),
                new Edge(p, 0)
                                 ));

        n.setEdges(Collections.singletonList(new Edge(S, 1)));

        S.setEdges(Collections.singletonList(new Edge(nk, -1)));

        nl.setEdges(Collections.singletonList(new Edge(r2, 1)));

        r2.setEdges(Collections.singletonList(new Edge(s, 0)));

        firstNote = s;
    }

    @Override
    public Note get() {
        return firstNote;
    }
}
