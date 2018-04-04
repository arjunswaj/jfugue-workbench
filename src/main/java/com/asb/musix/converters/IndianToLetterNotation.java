package com.asb.musix.converters;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.asb.musix.constants.MusicConstants.*;

/**
 * IndianToLetterNotation.
 * Created by bhaarjun on 4/4/18.
 */
public class IndianToLetterNotation implements Function<String, String> {

    private static final Map<String, String> NOTATION_MAP = new HashMap<String, String>() {{
        put(SHADAJ, C);
        put(KOMAL_RISHAB, C_SHARP);
        put(RISHABH, D);
        put(KOMAL_GANDHAR, D_SHARP);
        put(GANDHAR, E);
        put(MADHYAM, F);
        put(TIVRA_MADHYAM, F_SHARP);
        put(PANCHAM, G);
        put(KOMAL_DHAIWAT, G_SHARP);
        put(DHAIWAT, A);
        put(KOMAL_NISHAD, A_SHARP);
        put(NISHAD, B);
    }};

    @Override
    public String apply(final String key) {
        return NOTATION_MAP.get(key);
    }
}
