package com.asb.music.builder.impl;

import com.asb.music.builder.NoteFinder;
import com.asb.music.eos.NoteWithContext;
import com.asb.music.factory.RaagGenerator;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

import static com.asb.music.eos.NoteWithContext.ProgressionType.AAROHA;
import static com.asb.music.eos.NoteWithContext.ProgressionType.AVAROHA;
import static com.asb.music.factory.RaagGenerator.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BasicNoteFinderTest {

    public static final int MAX_DEVIATION = 3;
    public static final int MAX_POSITIVE = 3;
    public static final int MAX_NEGATIVE = 2;
    @Spy
    private Random random;

    private NoteFinder noteFinder;

    @Before
    public void setUp() throws Exception {
        noteFinder = new BasicNoteFinder(MAX_DEVIATION, MAX_POSITIVE, MAX_NEGATIVE, random);
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

    // Aaroha tests

    @Test
    public void findNext_ForFirstNoteAarohaRandomGivesPositive2_ReturnsNoteWithContext() throws Exception {
        when(random.nextBoolean()).thenReturn(true);
        when(random.nextInt(MAX_POSITIVE)).thenReturn(2);
        val raag = RaagGenerator.getRaag(MIYAN_KI_MALHAR);
        val scale = 5;
        val noteWithContext = new NoteWithContext(raag.getFirstAaroha(), scale, false, false, AAROHA);
        val result = noteFinder.findNext(raag, noteWithContext);
        assertEquals(E + b, result.getNote()
                .toString());
        assertEquals(scale, result.getScale());
        assertEquals(AAROHA, result.getProgressionType());
    }

    @Test
    public void findNext_ForSecondNoteAarohaRandomGivesPositive1_ReturnsNoteWithContext() throws Exception {
        when(random.nextBoolean()).thenReturn(true);
        when(random.nextInt(MAX_POSITIVE)).thenReturn(1);
        val raag = RaagGenerator.getRaag(MIYAN_KI_MALHAR);
        val scale = 5;
        val noteWithContext = new NoteWithContext(raag.getFirstAaroha()
                                                          .getNext(), scale, false, false, AAROHA);
        val result = noteFinder.findNext(raag, noteWithContext);
        assertEquals(E + b, result.getNote()
                .toString());
        assertEquals(scale, result.getScale());
        assertEquals(AAROHA, result.getProgressionType());
    }

    @Test
    public void findNext_ForLastNoteAarohaRandomGivesPositive2_ReturnsNextScaleNote() throws Exception {
        when(random.nextBoolean()).thenReturn(true);
        when(random.nextInt(MAX_POSITIVE)).thenReturn(2);
        val raag = RaagGenerator.getRaag(MIYAN_KI_MALHAR);
        val scale = 5;
        val noteWithContext = new NoteWithContext(raag.getFirstAvaroha(), scale, false, false, AAROHA);
        val result = noteFinder.findNext(raag, noteWithContext);
        assertEquals(D, result.getNote()
                .toString());
        assertEquals(scale + 1, result.getScale());
        assertEquals(AAROHA, result.getProgressionType());
    }

    @Test
    public void findNext_ForCrossedNoteAarohaRandomGivesMoreThanDeviation_ReturnsNextScaleNote() throws Exception {
        when(random.nextBoolean()).thenReturn(true);
        when(random.nextInt(MAX_POSITIVE)).thenReturn(MAX_DEVIATION);
        val raag = RaagGenerator.getRaag(MIYAN_KI_MALHAR);
        val scale = 5;
        val noteWithContext = new NoteWithContext(raag.getFirstAvaroha(), scale, false, false, AAROHA);
        val result = noteFinder.findNext(raag, noteWithContext);
        assertEquals(E + b, result.getNote()
                .toString());
        assertEquals(scale + 1, result.getScale());
        assertEquals(AVAROHA, result.getProgressionType());
    }

    @Test
    public void findNext_ForFirstNoteAarohaRandomGivesNegative1_ReturnsNoteWithContext() throws Exception {
        when(random.nextBoolean()).thenReturn(false);
        when(random.nextInt(MAX_NEGATIVE)).thenReturn(1);
        val raag = RaagGenerator.getRaag(MIYAN_KI_MALHAR);
        val scale = 5;
        val noteWithContext = new NoteWithContext(raag.getFirstAaroha(), scale, false, false, AAROHA);
        val result = noteFinder.findNext(raag, noteWithContext);
        assertEquals(B, result.getNote()
                .toString());
        assertEquals(scale - 1, result.getScale());
        assertEquals(AAROHA, result.getProgressionType());
    }

    @Test
    public void findNext_ForSecondNoteAarohaRandomGivesNegative1_ReturnsNoteWithContext() throws Exception {
        when(random.nextBoolean()).thenReturn(false);
        when(random.nextInt(MAX_NEGATIVE)).thenReturn(1);
        val raag = RaagGenerator.getRaag(MIYAN_KI_MALHAR);
        val scale = 5;
        val noteWithContext = new NoteWithContext(raag.getFirstAaroha()
                                                          .getNext(), scale, false, false, AAROHA);
        val result = noteFinder.findNext(raag, noteWithContext);
        assertEquals(C, result.getNote()
                .toString());
        assertEquals(scale, result.getScale());
        assertEquals(AAROHA, result.getProgressionType());
    }

    @Test
    public void findNext_ForSecondNoteAarohaRandomGivesNegative2_ReturnsNoteWithContext() throws Exception {
        when(random.nextBoolean()).thenReturn(false);
        when(random.nextInt(MAX_NEGATIVE)).thenReturn(2);
        val raag = RaagGenerator.getRaag(MIYAN_KI_MALHAR);
        val scale = 5;
        val noteWithContext = new NoteWithContext(raag.getFirstAaroha()
                                                          .getNext(), scale, false, false, AAROHA);
        val result = noteFinder.findNext(raag, noteWithContext);
        assertEquals(B, result.getNote()
                .toString());
        assertEquals(scale - 1, result.getScale());
        assertEquals(AAROHA, result.getProgressionType());
    }

    @Test
    public void findNext_ForLastNoteAarohaRandomGivesNegative1_ReturnsNoteWithContext() throws Exception {
        when(random.nextBoolean()).thenReturn(false);
        when(random.nextInt(MAX_NEGATIVE)).thenReturn(1);
        val raag = RaagGenerator.getRaag(MIYAN_KI_MALHAR);
        val scale = 5;
        val noteWithContext = new NoteWithContext(raag.getFirstAvaroha(), scale, false, false, AAROHA);
        val result = noteFinder.findNext(raag, noteWithContext);
        assertEquals(B + b, result.getNote()
                .toString());
        assertEquals(scale, result.getScale());
        assertEquals(AAROHA, result.getProgressionType());
    }

    // Avaroha tests

    @Test
    public void findNext_ForLastNoteAvarohaRandomGivesPositive2_ReturnsNoteWithContext() throws Exception {
        when(random.nextBoolean()).thenReturn(true);
        when(random.nextInt(MAX_POSITIVE)).thenReturn(2);
        val raag = RaagGenerator.getRaag(MIYAN_KI_MALHAR);
        val scale = 5;
        val noteWithContext = new NoteWithContext(raag.getFirstAvaroha(), scale, false, false, AVAROHA);
        val result = noteFinder.findNext(raag, noteWithContext);
        assertEquals(A, result.getNote()
                .toString());
        assertEquals(scale, result.getScale());
        assertEquals(AVAROHA, result.getProgressionType());
    }

    @Test
    public void findNext_ForSecondNoteAvarohaRandomGivesPositive1_ReturnsNoteWithContext() throws Exception {
        when(random.nextBoolean()).thenReturn(true);
        when(random.nextInt(MAX_POSITIVE)).thenReturn(1);
        val raag = RaagGenerator.getRaag(MIYAN_KI_MALHAR);
        val scale = 5;
        val noteWithContext = new NoteWithContext(raag.getFirstAvaroha()
                                                          .getPrevious(), scale, false, false, AVAROHA);
        val result = noteFinder.findNext(raag, noteWithContext);
        assertEquals(A, result.getNote()
                .toString());
        assertEquals(scale, result.getScale());
        assertEquals(AVAROHA, result.getProgressionType());
    }

    @Test
    public void findNext_ForFirstNoteAvarohaRandomGivesPositive2_ReturnsPreviousScaleNote() throws Exception {
        when(random.nextBoolean()).thenReturn(true);
        when(random.nextInt(MAX_POSITIVE)).thenReturn(2);
        val raag = RaagGenerator.getRaag(MIYAN_KI_MALHAR);
        val scale = 5;
        val noteWithContext = new NoteWithContext(raag.getFirstAaroha(), scale, false, false, AVAROHA);
        val result = noteFinder.findNext(raag, noteWithContext);
        assertEquals(B + b, result.getNote()
                .toString());
        assertEquals(scale - 1, result.getScale());
        assertEquals(AVAROHA, result.getProgressionType());
    }

    @Test
    public void findNext_ForCrossedNoteAvarohaRandomGivesMoreThanDeviation_ReturnsPreviousScaleNote() throws Exception {
        when(random.nextBoolean()).thenReturn(true);
        when(random.nextInt(MAX_POSITIVE)).thenReturn(MAX_DEVIATION);
        val raag = RaagGenerator.getRaag(MIYAN_KI_MALHAR);
        val scale = 5;
        val noteWithContext = new NoteWithContext(raag.getFirstAaroha(), scale, false, false, AVAROHA);
        val result = noteFinder.findNext(raag, noteWithContext);
        assertEquals(A, result.getNote()
                .toString());
        assertEquals(scale - 1, result.getScale());
        assertEquals(AAROHA, result.getProgressionType());
    }

    @Test
    public void findNext_ForFirstNoteAvarohaRandomGivesNegative1_ReturnsNoteWithContext() throws Exception {
        when(random.nextBoolean()).thenReturn(false);
        when(random.nextInt(MAX_NEGATIVE)).thenReturn(1);
        val raag = RaagGenerator.getRaag(MIYAN_KI_MALHAR);
        val scale = 5;
        val noteWithContext = new NoteWithContext(raag.getFirstAvaroha(), scale, false, false, AVAROHA);
        val result = noteFinder.findNext(raag, noteWithContext);
        assertEquals(C, result.getNote()
                .toString());
        assertEquals(scale + 1, result.getScale());
        assertEquals(AVAROHA, result.getProgressionType());
    }

    @Test
    public void findNext_ForSecondNoteAvarohaRandomGivesNegative1_ReturnsNoteWithContext() throws Exception {
        when(random.nextBoolean()).thenReturn(false);
        when(random.nextInt(MAX_NEGATIVE)).thenReturn(1);
        val raag = RaagGenerator.getRaag(MIYAN_KI_MALHAR);
        val scale = 5;
        val noteWithContext = new NoteWithContext(raag.getFirstAvaroha()
                                                          .getPrevious(), scale, false, false, AVAROHA);
        val result = noteFinder.findNext(raag, noteWithContext);
        assertEquals(B, result.getNote()
                .toString());
        assertEquals(scale, result.getScale());
        assertEquals(AVAROHA, result.getProgressionType());
    }

    @Test
    public void findNext_ForSecondNoteAvarohaRandomGivesNegative2_ReturnsNoteWithContext() throws Exception {
        when(random.nextBoolean()).thenReturn(false);
        when(random.nextInt(MAX_NEGATIVE)).thenReturn(2);
        val raag = RaagGenerator.getRaag(MIYAN_KI_MALHAR);
        val scale = 5;
        val noteWithContext = new NoteWithContext(raag.getFirstAvaroha()
                                                          .getPrevious(), scale, false, false, AVAROHA);
        val result = noteFinder.findNext(raag, noteWithContext);
        assertEquals(C, result.getNote()
                .toString());
        assertEquals(scale + 1, result.getScale());
        assertEquals(AVAROHA, result.getProgressionType());
    }

    @Test
    public void findNext_ForLastNoteAvarohaRandomGivesPositive1_ReturnsNoteWithContext() throws Exception {
        when(random.nextBoolean()).thenReturn(true);
        when(random.nextInt(MAX_POSITIVE)).thenReturn(1);
        val raag = RaagGenerator.getRaag(MIYAN_KI_MALHAR);
        val scale = 5;
        val noteWithContext = new NoteWithContext(raag.getFirstAaroha(), scale, false, false, AVAROHA);
        val result = noteFinder.findNext(raag, noteWithContext);
        assertEquals(B, result.getNote()
                .toString());
        assertEquals(scale - 1, result.getScale());
        assertEquals(AVAROHA, result.getProgressionType());
    }
}
