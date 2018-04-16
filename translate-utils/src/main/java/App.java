import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sbespalko.TranslateUtil;

/**
 * @author Sergey.Bespalko@x5.ru
 */
public class App {
  private static final Logger log = LoggerFactory.getLogger(App.class);

  private static final String PATH = App.class.getClass().getResource("/").getPath();
  private static final String CONFIGS = "/configs/";
  private static final String TRANSLATES = "/configs/translation/";
  private static final String[] configTransl = { "config-translation.default",
                                                 "config-translation.en",
                                                 "config-translation.ru",
                                                 "config-translation.us" };
  private static final String[] internalTransl = { "internal.default", "internal.en", "internal.us" };
  private static final String[] transl = { "translation.default",
                                           "translation.en",
                                           "translation.ru",
                                           "translation.us" };
  private static final String[] editorTransl = { "translation.editor.default",
                                                 "translation.editor.en",
                                                 "translation.editor.us" };

  public static void main(String[] args) throws IOException {
    List<File> files = getFilesList(CONFIGS, configTransl);
    generateDiffFiles(files);

    files = getFilesList(TRANSLATES, internalTransl);
    generateDiffFiles(files);

    files = getFilesList(TRANSLATES, transl);
    generateDiffFiles(files);

    files = getFilesList(TRANSLATES, editorTransl);
    generateDiffFiles(files);
  }

  private static void generateDiffFiles(List<File> files) throws IOException {
    for (File file : files) {
      List<String> doubles = TranslateUtil.getAllDoubles(file);
      if (!doubles.isEmpty()) {
        Files.write(Paths.get(file.toPath() + ".doubles"), doubles, StandardCharsets.UTF_8);
      } else {
        log.info("{} without doubles", file);
      }
      for (File comparedFile : files) {
        if (file.equals(comparedFile)) {
          continue;
        }
        Map<String, String> diffMap = TranslateUtil.getDiff(file, comparedFile);
        Properties props = new Properties() {
          public Enumeration keys() {
            Enumeration keysEnum = super.keys();
            Vector<String> keyList = new Vector<String>();
            while (keysEnum.hasMoreElements()) {
              keyList.add((String) keysEnum.nextElement());
            }
            Collections.sort(keyList);
            return keyList.elements();
          }
        };
        for (Map.Entry<String, String> entry : diffMap.entrySet()) {
          props.setProperty(entry.getKey(), entry.getValue());
        }
        String commend = "\n## Compare "
                         + file.getName()
                         + "(1) => "
                         + comparedFile.getName()
                         + "(2) ## Exist in (1), but not in (2)\n";
        try (OutputStream out = new FileOutputStream(getDiffPath(file).toFile(), true)) {
          if (!props.isEmpty()) {
            props.store(out, commend);
          }
        }
      }
    }
  }

  private static Path getDiffPath(File file) throws IOException {
    Path path = Paths.get(file.toPath() + ".diff");
    if (Files.notExists(path)) {
      Files.createFile(path);
    }
    return path;
  }

  private static List<File> getFilesList(String prefix, String[] fileNames) {
    List<File> files = new ArrayList<>(fileNames.length);
    for (String fileName : fileNames) {
      File file = new File(PATH + File.separator + prefix + File.separator + fileName);
      if (file.exists()) {
        files.add(file);
      } else {
        log.error("File not exist: {}", file.getAbsolutePath());
      }
    }
    return files;
  }
}
