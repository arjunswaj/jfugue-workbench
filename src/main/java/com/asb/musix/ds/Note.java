package com.asb.musix.ds;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Note.
 * Created by bhaarjun on 3/30/18.
 */
@Data
@RequiredArgsConstructor
public class Note {
    private final String name;
    private Note next;
    private Note previous;
    private boolean start;
}
