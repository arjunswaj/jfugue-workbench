package com.asb.music.eos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    private String name;
    private String modifier;
    private boolean scaleHop;
    private int aarohaNumber;
    private int avarohaNumber;
    private Note next;
    private Note previous;


    @Nonnull
    @Override
    public String toString() {
        return name + modifier;
    }
}
