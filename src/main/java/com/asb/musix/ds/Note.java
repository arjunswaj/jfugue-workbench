package com.asb.musix.ds;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Note.
 * Created by bhaarjun on 4/4/18.
 */
@Data
@RequiredArgsConstructor
public class Note {
    private final String     name;
    private       List<Edge> edges;
}
