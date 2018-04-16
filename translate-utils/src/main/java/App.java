import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sbespalko.TranslateUtil;

/**
 * @author Sergey.Bespalko@x5.ru
 */
public class App {
  private static final Logger log = LoggerFactory.getLogger(App.class);

  private static final String PATH = App.class.getClass().getResource("/").getPath();
  private static final String[] configTransl = { "config-translation.default",
                                                 "config-translation.en",
                                                 "config-translation.ru",
                                                 "config-translation.us" };
  private static final String[] internalTransl = { "internal.default", "internal.en", "internal.us" };
  private static final String[] transl = { "translation.default", "translation.en", "translation.us" };
  private static final String[] editorTransl = { "translation.editor.default",
                                                 "translation.editor.en",
                                                 "translation.editor.us" };

  public static void main(String[] args) throws IOException {
    List<File> files = getFilesList(configTransl);

    for (File file : files) {
      List<String> doubles = TranslateUtil.getAllDoubles(file);
      if (doubles.isEmpty()) {
        log.info("{} without doubles", file);
      } else {
        Files.write(Paths.get(file.toPath() + ".doubles"), doubles, StandardCharsets.UTF_16);
      }
    }
  }

  private static List<File> getFilesList(String[] fileNames) {
    List<File> files = new ArrayList<>(fileNames.length);
    for (String fileName : fileNames) {
      File file = new File(PATH + File.separator + fileName);
      if (file.exists()) {
        files.add(new File(PATH + File.separator + fileName));
      } else {
        log.error("File not exist: {}", file.getAbsolutePath());
      }
    }
    return files;
  }
}
