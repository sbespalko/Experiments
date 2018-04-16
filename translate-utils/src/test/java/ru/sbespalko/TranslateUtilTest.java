package ru.sbespalko;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 * @author Sergey.Bespalko@x5.ru
 */
public class TranslateUtilTest {
  private static final String PATH = TranslateUtilTest.class.getResource("translations").getPath();

  @Test
  public void when_CheckUnics_Then_ReturnDublesList() {
    List<String> result = TranslateUtil.getAllDoubles(new File(PATH, "unique.properties"));
    assertTrue(result.isEmpty());
  }

  @Test
  public void when_HanvtDiffKeys_Then_ReturnEmptyList() {
    List<String> result = TranslateUtil.getAllDoubles(new File(PATH, "not-unique.properties"));
    assertFalse(result.isEmpty());
    assertThat(result, hasItems("uniq1"));
  }

  @Test
  public void when_Compare2Trans_Then_ReturnDiffMap() {
    File file1 = new File(PATH, "file1.properties");
    File file2 = new File(PATH, "file3not-equals1.properties");
    Map<String, String> result = TranslateUtil.getDiff( file1, file2);
    assertFalse(result.isEmpty());
  }

  @Test
  public void when_EqualsTrans_Then_ReturnEmpyMap() {
    File file1 = new File(PATH, "file1.properties");
    File file2 = new File(PATH, "file2equals1.properties");
    Map<String, String> result = TranslateUtil.getDiff( file1, file2);
    assertTrue(result.isEmpty());
  }
}
