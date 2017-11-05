package com.asb.music.builder.impl;

import com.asb.music.builder.NoteFinder;
import com.asb.music.eos.Note;
import com.asb.music.eos.NoteWithContext;
import com.asb.music.eos.Raag;
import fj.data.Option;
import lombok.AllArgsConstructor;
import lombok.experimental.var;
import lombok.val;

import javax.annotation.Nonnull;
import java.util.Random;

import static com.asb.music.eos.NoteWithContext.ProgressionType.AAROHA;
import static com.asb.music.eos.NoteWithContext.ProgressionType.AVAROHA;

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

    @Nonnull
    @Override
    public NoteWithContext findNext(@Nonnull final Raag raag, @Nonnull final NoteWithContext currentNote) {
        switch (currentNote.getProgressionType()) {
            case AAROHA:
            default:
                return findNextInAaroha(raag, currentNote);
            case AVAROHA:
                return findNextInAvaroha(raag, currentNote);
        }
    }

    @Nonnull
    private NoteWithContext findNextInAaroha(@Nonnull final Raag raag, @Nonnull final NoteWithContext currentNote) {
        Note note = currentNote.getNote();
        var nextScale = currentNote.getScale();
        var hasCrossedOverLast = currentNote.isCrossedOverLast();
        var hasCrossedOverFirst = currentNote.isCrossedOverFirst();
        if (null != note.getNext() && random.nextBoolean()) {
            val hops = random.nextInt(maxPositive);
            var ctr = 0;
            while (ctr < hops) {
                note = note.getNext();
                ctr += 1;
                if (note.isScaleHop()) {
                    nextScale += 1;
                }
                hasCrossedOverLast = hasCrossedOverLast | (note == raag.getFirstAaroha());
            }
        } else if (null != note.getPrevious()) {
            val hops = random.nextInt(maxNegative);
            var ctr = 0;
            while (ctr < hops) {
                hasCrossedOverLast = hasCrossedOverLast | (note == raag.getFirstAaroha());
                if (note.isScaleHop()) {
                    nextScale -= 1;
                }
                note = note.getPrevious();
                ctr += 1;
            }
        }
        NoteWithContext.ProgressionType progressionType;
        if (hasCrossedOverLast && (note.getAarohaNumber() >= maxDeviation)) {
            progressionType = nextScale < currentNote.getScale() ? AAROHA : AVAROHA;
        } else {
            progressionType = (null == note.getNext()) ? AVAROHA : AAROHA;
        }

        return new NoteWithContext(note, nextScale, hasCrossedOverLast, hasCrossedOverFirst, progressionType);
    }

    @Nonnull
    private NoteWithContext findNextInAvaroha(@Nonnull final Raag raag, @Nonnull final NoteWithContext currentNote) {
        Note note = currentNote.getNote();
        var nextScale = currentNote.getScale();
        var hasCrossedOverLast = currentNote.isCrossedOverLast();
        var hasCrossedOverFirst = currentNote.isCrossedOverFirst();
        if (null != note.getPrevious() && random.nextBoolean()) {
            val hops = random.nextInt(maxPositive);
            var ctr = 0;
            while (ctr < hops) {
                if (note.isScaleHop()) {
                    nextScale -= 1;
                }
                note = note.getPrevious();
                ctr += 1;
                hasCrossedOverFirst = hasCrossedOverFirst | (note == raag.getFirstAvaroha());
            }
        } else if (null != note.getNext()) {
            val hops = random.nextInt(maxNegative);
            var ctr = 0;
            while (ctr < hops) {
                hasCrossedOverFirst = hasCrossedOverFirst | (note == raag.getFirstAvaroha());
                note = note.getNext();
                if (note.isScaleHop()) {
                    nextScale += 1;
                }
                ctr += 1;
            }
        }
        NoteWithContext.ProgressionType progressionType;
        if (hasCrossedOverFirst && (note.getAvarohaNumber() >= maxDeviation)) {
            progressionType = nextScale < currentNote.getScale() ? AAROHA : AVAROHA;
        } else {
            progressionType = (null == note.getPrevious()) ? AAROHA : AVAROHA;
        }

        return new NoteWithContext(note, nextScale, hasCrossedOverLast, hasCrossedOverFirst, progressionType);
    }
}
