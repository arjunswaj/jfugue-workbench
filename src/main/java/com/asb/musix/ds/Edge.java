package com.asb.musix.ds;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Edge.
 * Created by bhaarjun on 4/4/18.
 */
@Data
@AllArgsConstructor
public class Edge {
    private Note end;
    private int scaleDiff;
}
