package io.github.anhdungxd21;

public class Translator {

    public static byte[] shrink(byte[] arr) {
        if(arr.length != 8)
            throw new IllegalArgumentException("Byte[] must 8 element length");

        if((arr[7] & 0b0100_0000) != 0)
            arr[0] += (byte) 0b1000_0000;

        if((arr[7] & 0b0010_0000) != 0)
            arr[1] += (byte) 0b1000_0000;

        if((arr[7] & 0b0001_0000) != 0)
            arr[2] += (byte) 0b1000_0000;

        if((arr[7] & 0b0000_1000) != 0)
            arr[3] += (byte) 0b1000_0000;

        if((arr[7] & 0b0000_0100) != 0)
            arr[4] += (byte) 0b1000_0000;

        if((arr[7] & 0b0000_0010) != 0)
            arr[5] += (byte) 0b1000_0000;

        if((arr[7] & 0b0000_0001) != 0)
            arr[6] += (byte) 0b1000_0000;

        return new byte[]{
                arr[0],
                arr[1],
                arr[2],
                arr[3],
                arr[4],
                arr[5],
                arr[6]};
    }

    public static byte[] revert(byte[] arr) {
        if (arr.length != 7)
            throw new IllegalArgumentException("Byte[] must 7 element length");

        byte lastByte = 0;

        if ((arr[0] & 0b1000_0000) != 0) {
            lastByte += 64;
            arr[0] = (byte) (arr[0] & 0b0111_1111);
        }
        if ((arr[1] & 0b1000_0000) != 0) {
            lastByte += 32;
            arr[1] = (byte) (arr[1] & 0b0111_1111);
        }
        if ((arr[2] & 0b1000_0000) != 0) {
            lastByte += 16;
            arr[2] = (byte) (arr[2] & 0b0111_1111);
        }
        if ((arr[3] & 0b1000_0000) != 0) {
            lastByte += 8;
            arr[3] = (byte) (arr[3] & 0b0111_1111);
        }
        if ((arr[4] & 0b1000_0000) != 0) {
            lastByte += 4;
            arr[4] = (byte) (arr[4] & 0b0111_1111);
        }
        if ((arr[5] & 0b1000_0000) != 0) {
            lastByte += 2;
            arr[5] = (byte) (arr[5] & 0b0111_1111);
        }
        if ((arr[6] & 0b1000_0000) != 0) {
            lastByte += 1;
            arr[6] = (byte) (arr[6] & 0b0111_1111);
        }
        return new byte[]{
                arr[0],
                arr[1],
                arr[2],
                arr[3],
                arr[4],
                arr[5],
                arr[6],
                lastByte
        };
    }
}
