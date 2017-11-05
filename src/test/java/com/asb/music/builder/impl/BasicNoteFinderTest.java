package com.asb.music.builder.impl;

import com.asb.music.builder.NoteFinder;
import com.asb.music.factory.RaagGenerator;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

import static com.asb.music.factory.RaagGenerator.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BasicNoteFinderTest {

    @Spy
    private Random random;

    private NoteFinder noteFinder;

    @Before
    public void setUp() throws Exception {
        noteFinder = new BasicNoteFinder(3, 3, 1, random);
    }

    @Test
    public void getNote_ValidNotePassed_ReturnsNote() throws Exception {
        val raag = RaagGenerator.getRaag(YAMAN);
        String noteName = G + b;
        val note = noteFinder.getNote(raag, noteName);
        note.forEach(n -> assertEquals(noteName, n.toString()));
    }

    @Test
    public void getNote_InvalidNotePassed_ReturnsNone() throws Exception {
        val raag = RaagGenerator.getRaag(YAMAN);
        String noteName = G + Sh;
        val note = noteFinder.getNote(raag, noteName);
        assertTrue(note.isNone());
    }

}
