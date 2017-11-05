package com.asb.music.main;

import com.asb.music.builder.impl.BasicNoteFinder;
import com.asb.music.eos.NoteWithContext;
import com.asb.music.eos.Raag;
import com.asb.music.factory.RaagGenerator;
import fj.data.Stream;
import lombok.val;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

import java.util.Random;

import static com.asb.music.eos.NoteWithContext.ProgressionType.AAROHA;
import static com.asb.music.factory.RaagGenerator.D;
import static com.asb.music.factory.RaagGenerator.YAMAN;

public class PlayerApp {


    public static void main(String[] args) {
        int scale = 5;
        val yaman = RaagGenerator.getRaag(YAMAN);
        val miyan = RaagGenerator.getRaag(YAMAN);
        val noteFinder = new BasicNoteFinder(3, 3, 2, new Random(5));
        val firstNoteWithContext = NoteWithContext.builder()
                .note(noteFinder.getNote(yaman, D)
                              .orSome(yaman.getFirstAaroha()))
                .progressionType(AAROHA)
                .scale(scale)
                .build();
        val firstNoteOfMiyaWithContext = NoteWithContext.builder()
                .note(noteFinder.getNote(miyan, D)
                              .orSome(miyan.getFirstAaroha()))
                .progressionType(AAROHA)
                .scale(scale)
                .build();

        val yamanNotes = getNotes(yaman, noteFinder, firstNoteWithContext);
        val miyanNotes = getNotes(miyan, noteFinder, firstNoteOfMiyaWithContext);

        System.out.println(yamanNotes);
        System.out.println(miyanNotes);

        val yamanPattern = new Pattern(yamanNotes)
                .setInstrument("Violin")
                .setTempo(100);
        val mkmPattern = new Pattern(miyanNotes)
                .setInstrument("Piano")
                .setTempo(100);

        val player = new Player();
        player.play(yamanPattern, mkmPattern);
    }

    private static String getNotes(Raag raag, BasicNoteFinder noteFinder, NoteWithContext firstNoteWithContext) {
        return Stream.range(1)
                .take(100)
                .foldLeft((b, a) -> b.cons(noteFinder.findNext(raag, b.head())),
                          Stream.arrayStream(firstNoteWithContext))
                .map(t -> t.getNote()
                        .toString() + t.getScale())
                .reverse()
                .foldLeft((b, a) -> b.append(a)
                        .append(" "), new StringBuilder())
                .toString();
    }
}
