package com.asb.music.factory;

import com.asb.music.eos.Note;
import com.asb.music.eos.Raag;
import lombok.val;

public class RaagGenerator {

    public static final String MIYAN_KI_MALHAR = "Miyan ki Malhar";
    public static final String YAMAN = "Yaman";
    public static final String BHUPALI = "Bhupali";
    public static final String C = "C";
    public static final String D = "D";
    public static final String E = "E";
    public static final String F = "F";
    public static final String G = "G";
    public static final String A = "A";
    public static final String B = "B";
    public static final String b = "b";
    public static final String Sh = "#";

    public static Raag getRaag(String raag) {
        switch (raag) {
            case BHUPALI:
                return getBhupaliRaag();
            case YAMAN:
                return getYamanRaag();
            case MIYAN_KI_MALHAR:
                return getMiyanKiMalhar();
            default:
                throw new RuntimeException("Not Available man");
        }
    }

    private static Raag getMiyanKiMalhar() {
        val a1 = Note.builder()
                .name(C)
                .modifier("")
                .scaleHop(true)
                .aarohaNumber(1)
                .avarohaNumber(8)
                .build();
        val a2 = Note.builder()
                .name(D)
                .modifier("")
                .scaleHop(false)
                .aarohaNumber(2)
                .avarohaNumber(7)
                .build();
        val a3 = Note.builder()
                .name(E)
                .modifier(b)
                .scaleHop(false)
                .aarohaNumber(3)
                .avarohaNumber(6)
                .build();
        val a4 = Note.builder()
                .name(F)
                .modifier("")
                .scaleHop(false)
                .aarohaNumber(4)
                .avarohaNumber(5)
                .build();
        val a5 = Note.builder()
                .name(G)
                .modifier("")
                .scaleHop(false)
                .aarohaNumber(5)
                .avarohaNumber(4)
                .build();
        val a6 = Note.builder()
                .name(A)
                .modifier("")
                .scaleHop(false)
                .aarohaNumber(6)
                .avarohaNumber(3)
                .build();
        val a7 = Note.builder()
                .name(B)
                .modifier(b)
                .scaleHop(false)
                .aarohaNumber(7)
                .avarohaNumber(2)
                .build();
        val a8 = Note.builder()
                .name(B)
                .modifier("")
                .scaleHop(false)
                .aarohaNumber(8)
                .avarohaNumber(1)
                .build();

        a1.setNext(a2);
        a1.setPrevious(a8);

        a2.setNext(a3);
        a2.setPrevious(a1);

        a3.setNext(a4);
        a3.setPrevious(a2);

        a4.setNext(a5);
        a4.setPrevious(a3);

        a5.setNext(a6);
        a5.setPrevious(a4);

        a6.setNext(a7);
        a6.setPrevious(a5);

        a7.setNext(a8);
        a7.setPrevious(a6);

        a8.setNext(a1);
        a8.setPrevious(a7);

        return Raag.builder()
                .list(a1)
                .firstAaroha(a1)
                .firstAvaroha(a8)
                .name(MIYAN_KI_MALHAR)
                .build();
    }

    private static Raag getYamanRaag() {
        val a1 = Note.builder()
                .name(D)
                .modifier("")
                .scaleHop(true)
                .aarohaNumber(1)
                .avarohaNumber(7)
                .build();
        val a2 = Note.builder()
                .name(E)
                .modifier("")
                .scaleHop(false)
                .aarohaNumber(2)
                .avarohaNumber(6)
                .build();
        val a3 = Note.builder()
                .name(F)
                .modifier("")
                .scaleHop(false)
                .aarohaNumber(3)
                .avarohaNumber(5)
                .build();
        val a4 = Note.builder()
                .name(G)
                .modifier(b)
                .scaleHop(false)
                .aarohaNumber(4)
                .avarohaNumber(4)
                .build();
        val a5 = Note.builder()
                .name(G)
                .modifier("")
                .scaleHop(false)
                .aarohaNumber(5)
                .avarohaNumber(3)
                .build();
        val a6 = Note.builder()
                .name(A)
                .modifier(Sh)
                .scaleHop(false)
                .aarohaNumber(6)
                .avarohaNumber(2)
                .build();
        val a7 = Note.builder()
                .name(B)
                .modifier(Sh)
                .scaleHop(false)
                .aarohaNumber(7)
                .avarohaNumber(1)
                .build();

        a1.setNext(a2);
        a1.setPrevious(a7);

        a2.setNext(a3);
        a2.setPrevious(a1);

        a3.setNext(a4);
        a3.setPrevious(a2);

        a4.setNext(a5);
        a4.setPrevious(a3);

        a5.setNext(a6);
        a5.setPrevious(a4);

        a6.setNext(a7);
        a6.setPrevious(a5);

        a7.setNext(a1);
        a7.setPrevious(a6);

        return Raag.builder()
                .list(a1)
                .firstAaroha(a1)
                .firstAvaroha(a7)
                .name(YAMAN)
                .build();
    }

    private static Raag getBhupaliRaag() {
        val a1 = Note.builder()
                .name(C)
                .modifier(Sh)
                .scaleHop(true)
                .aarohaNumber(1)
                .avarohaNumber(1)
                .build();
        val a2 = Note.builder()
                .name(D)
                .modifier(Sh)
                .scaleHop(false)
                .aarohaNumber(2)
                .avarohaNumber(5)
                .build();
        val a3 = Note.builder()
                .name(F)
                .modifier(Sh)
                .scaleHop(false)
                .aarohaNumber(3)
                .avarohaNumber(4)
                .build();
        val a4 = Note.builder()
                .name(G)
                .modifier(Sh)
                .scaleHop(false)
                .aarohaNumber(4)
                .avarohaNumber(3)
                .build();
        val a5 = Note.builder()
                .name(A)
                .modifier(Sh)
                .scaleHop(false)
                .aarohaNumber(5)
                .avarohaNumber(2)
                .build();

        a1.setNext(a2);
        a1.setPrevious(a5);

        a2.setNext(a3);
        a2.setPrevious(a1);

        a3.setNext(a4);
        a3.setPrevious(a2);

        a4.setNext(a5);
        a4.setPrevious(a3);

        a5.setNext(a1);
        a5.setPrevious(a4);

        return Raag.builder()
                .list(a1)
                .firstAaroha(a1)
                .firstAvaroha(a1)
                .name(BHUPALI)
                .build();
    }
}
