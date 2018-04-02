package com.asb.musix.raag;

import com.asb.musix.ds.Note;

import java.util.function.Supplier;

/**
 * Raag.
 * Created by bhaarjun on 3/30/18.
 */
public interface Raag extends Supplier<Note>{
    Note get();
}
