package com.asb.music.builder;

import com.asb.music.eos.Note;
import com.asb.music.eos.NoteWithContext;
import com.asb.music.eos.Raag;
import fj.data.Option;

import javax.annotation.Nonnull;

public interface NoteFinder {

    @Nonnull
    NoteWithContext findNext(@Nonnull final Raag raag, @Nonnull final NoteWithContext currentNote);

    @Nonnull
    Option<Note> getNote(@Nonnull final Raag raag, @Nonnull final String noteName);

}
