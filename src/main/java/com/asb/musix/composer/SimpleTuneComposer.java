package com.asb.musix.composer;

import com.asb.musix.converters.IndianToLetterNotation;
import com.asb.musix.ds.Note;
import lombok.val;

import java.util.Random;
import java.util.function.BiFunction;

/**
 * SimpleTuneComposer.
 * Created by bhaarjun on 4/4/18.
 */
public class SimpleTuneComposer implements BiFunction<Note, Integer, String> {

    private IndianToLetterNotation notationConverter = new
            IndianToLetterNotation();

    private Random random = new Random();

    private static final Integer INIT_SCALE = 5;

    @Override
    public String apply(final Note note, final Integer limit) {
        int currScale = INIT_SCALE;
        Note currNote = note;
        val sb = new StringBuilder();
        for (int i = 0; i < limit; i += 1) {
            System.out.print(currNote.getName() + currScale + " ");
            val noteName = notationConverter.apply(currNote.getName());
            sb.append(noteName)
                    .append(currScale)
                    .append(" ");
            val edge = currNote.getEdges()
                    .get(random.nextInt(currNote.getEdges()
                            .size()));
            currNote = edge.getEnd();
            currScale += edge.getScaleDiff();
        }
        System.out.println();
        return sb.toString();
    }
}
