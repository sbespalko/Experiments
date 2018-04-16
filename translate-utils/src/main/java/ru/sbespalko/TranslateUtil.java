package ru.sbespalko;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sergey.Bespalko@x5.ru
 */
public class TranslateUtil {
  private static final Logger log = LoggerFactory.getLogger(TranslateUtil.class);

  public static List<String> getAllDoubles(File file) {
    List<String> strings = new ArrayList<>();
    try {
      strings = Files.readAllLines(file.toPath());
    } catch (IOException e) {
      e.printStackTrace();
    }
    Set<String> uniqueStrings = new HashSet<>();
    List<String> notUniqueStrings = new ArrayList<>();
    for (String string : strings) {
      if (StringUtils.isEmpty(string)) {
        continue;
      }
      String key = string.split("=")[0].trim();
      if (uniqueStrings.contains(key)) {
        notUniqueStrings.add(key);
      } else {
        uniqueStrings.add(key);
      }
    }
    return notUniqueStrings;
  }

  public static Map<String, String> getDiff(File file1, File file2) {
    Properties props1 = new Properties();
    Properties props2 = new Properties();
    try (InputStream in1 = new FileInputStream(file1); InputStream in2 = new FileInputStream(file2)) {
      props1.load(in1);
      props2.load(in2);
    } catch (IOException e) {
      e.printStackTrace();
    }
    Map<String, String> diffMap = new LinkedHashMap<>();
    diffMap.putAll(getDiffValues(props1, props2));
    diffMap.putAll(getDiffValues(props2, props1));
    return diffMap;
  }

  private static Map<String, String> getDiffValues(Properties props1, Properties props2) {
    Map<String, String> diffValues = new LinkedHashMap<>();
    for (Map.Entry<Object, Object> entry1 : props1.entrySet()) {
      String key1 = (String) entry1.getKey();
      if (!props2.contains(key1)) {
        diffValues.put(key1, (String) entry1.getValue());
      }
    }
    return diffValues;
  }
}
