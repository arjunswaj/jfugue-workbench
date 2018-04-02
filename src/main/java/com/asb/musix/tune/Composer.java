package com.asb.musix.tune;

import com.asb.musix.ds.Note;
import lombok.val;

import java.util.Random;

/**
 * Composer.
 * Created by bhaarjun on 3/30/18.
 */
public class Composer {

    private static final Random RANDOM = new Random(2);

    public String getTune(Note note, int scale, int limit) {
        Note s = note;
        val sb = new StringBuilder();
        int curScale = scale;
        for (int i = 0; i < limit; i += 1) {
            sb.append(s.getName())
                    .append(curScale)
                    .append(" ");

            if (RANDOM.nextInt() % 3 != 0) {
                if (null != s.getNext()) {
                    s = s.getNext();
                    if (s.isStart()) {
                        curScale += 1;
                    }
                }
            } else {
                if (null != s.getPrevious()) {
                    if (s.isStart()) {
                        curScale -= 1;
                    }
                    s = s.getPrevious();
                }
            }
        }

        return sb.toString();
    }

}
