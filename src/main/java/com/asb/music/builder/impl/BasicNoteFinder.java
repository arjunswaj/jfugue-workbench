package com.asb.music.builder.impl;

import com.asb.music.builder.NoteFinder;
import com.asb.music.eos.Note;
import com.asb.music.eos.Raag;
import fj.data.Option;
import lombok.AllArgsConstructor;
import lombok.experimental.var;

import javax.annotation.Nonnull;
import java.util.Random;

@AllArgsConstructor
public class BasicNoteFinder implements NoteFinder {

    private final int maxDeviation;
    private final int maxPositive;
    private final int maxNegative;
    private final Random random;

    @Nonnull
    @Override
    public Option<Note> getNote(@Nonnull Raag raag, @Nonnull String noteName) {
        Note result = null;
        var start = raag.getFirstAaroha();
        var startName = start.toString();
        var found = false;
        do {
            if (start.toString()
                    .equals(noteName)) {
                found = true;
                result = start;
            }
            start = start.getNext();
        } while (!startName.equals(start.toString()) && !found);

        if (!found) {
            start = raag.getFirstAvaroha();
            startName = start.toString();
            do {
                if (start.toString()
                        .equals(noteName)) {
                    found = true;
                    result = start;
                }
                start = start.getPrevious();
            } while (!startName.equals(start.toString()) && !found);
        }

        return Option.fromNull(result);
    }

}
