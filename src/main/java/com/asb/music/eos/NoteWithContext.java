package com.asb.music.eos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteWithContext {
    public enum ProgressionType {
        AAROHA, AVAROHA
    }

    private Note note;
    private int scale;
    private boolean crossedOverLast;
    private boolean crossedOverFirst;
    private ProgressionType progressionType;
}
