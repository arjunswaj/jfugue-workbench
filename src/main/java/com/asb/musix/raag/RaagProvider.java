package com.asb.musix.raag;

import com.asb.musix.ds.Note;

import java.util.function.Supplier;

/**
 * RaagProvider.
 * Created by bhaarjun on 4/4/18.
 */
public interface RaagProvider extends Supplier<Note> {
    Note get();
}
