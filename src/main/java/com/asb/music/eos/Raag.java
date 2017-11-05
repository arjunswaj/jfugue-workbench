package com.asb.music.eos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Raag {
    private String name;
    private Note list;
    private Note firstAaroha;
    private Note firstAvaroha;
}
