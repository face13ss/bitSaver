import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import io.github.anhdungxd21.Translator;

public class Test {
    void test() {
        File file = new File("");
        byte[] bFile = new byte[(int) file.length()];

        try (FileInputStream inputStream = new FileInputStream(file)) {
            inputStream.read(bFile);
        } catch (IOException e ){
            e.printStackTrace();
        }

        List<Byte> b = new ArrayList<>();
        int i = 0;
        while (i < bFile.length){
            if(i + 8 < bFile.length) {
                byte[] shrink = Translator.shrink(new byte[]{bFile[i], bFile[i + 1], bFile[i + 2], bFile[i + 3], bFile[i + 4], bFile[i + 5], bFile[i + 6], bFile[i + 7]});
                b.add(shrink[0]);
                b.add(shrink[1]);
                b.add(shrink[2]);
                b.add(shrink[3]);
                b.add(shrink[4]);
                b.add(shrink[5]);
                b.add(shrink[6]);
                i+=8;
            } else {
                b.add(bFile[i]);
                i++;
            }
        }
    }
}
