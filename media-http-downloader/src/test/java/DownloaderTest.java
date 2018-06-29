import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.google.api.client.googleapis.media.MediaHttpDownloaderProgressListener;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Sergey.Bespalko@x5.ru
 */
@RunWith(MockitoJUnitRunner.class)
public class DownloaderTest {
  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  private Downloader subj;

  private Map<URL, File> urlToFile;

  @Before
  public void setUp() throws Exception {
    urlToFile = new LinkedHashMap<>();
    urlToFile.put(new URL("http://speedtest.ftp.otenet.gr/files/test100k.db"),
                  new File(/*folder.getRoot(), */"testFile1"));
    urlToFile.put(new URL("http://speedtest.ftp.otenet.gr/files/test1Mb.db"),
                  new File(/*folder.getRoot(),*/ "testFile2"));
  }

  @Test
  public void when_DownloadFiles_Then_FilesExist() throws Exception {
    checkFilesNotExist();
    for (Map.Entry<URL, File> entry : urlToFile.entrySet()) {
      URL from = entry.getKey();
      File to = entry.getValue();
      subj = createDownloader(from, to);
      long expectedLength = subj.getUrlFileSize();
      subj.download();
      assertThat(to.exists(), is(true));
      assertThat(to.length(), is(expectedLength));
    }
  }

  private void checkFilesNotExist() {
    for (File file : urlToFile.values()) {
      if (file.exists()) {
        fail("File \"" + file.getAbsolutePath() + "\" exist before test.");
      }
    }
  }

  private Downloader createDownloader(URL from, File to) {
    return new Downloader.Builder(from, to).setProgressListener(downloader -> showToConsole(downloader, from, to)).build();
  }

  private void showToConsole(MediaHttpDownloader downloader, URL from, File to) {
    switch (downloader.getDownloadState()) {
    case MEDIA_IN_PROGRESS:
      System.out.println(String.format("%s in progress: %.1f%%", from, downloader.getProgress() * 100));
      break;
    case MEDIA_COMPLETE:
      System.out.println(to + " complete. Size: " + downloader.getNumBytesDownloaded() / 1024 + " kB");
      break;
    }
  }

  @Test
  public void when_BreakDownloadFiles_Then_Resume() throws IOException {
    checkFilesNotExist();
    for (Map.Entry<URL, File> entry : urlToFile.entrySet()) {
      URL from = entry.getKey();
      File to = entry.getValue();
      subj = createDownloader(from, to);
      int fileSize = (int) subj.getUrlFileSize();
      //subj.getDownloader().setContentRange(0, fileSize / 3);

      //TODO выкачать файлы полностью и разрезать их джавой

      // assertThat(to.exists(), is(true));
      // assertThat(to.length(), is(fileSize / 3 - 1));

      Downloader newSubj = createDownloader(from, to);
      newSubj.download();
      assertThat((int) to.length(), is(fileSize));
    }
  }

  @Test
  public void when_getUrlFileSize_Then_GetRealSize() throws IOException {
    for (Map.Entry<URL, File> entry : urlToFile.entrySet()) {
      URL from = entry.getKey();
      File to = entry.getValue();
      subj = createDownloader(from, to);
      long size = subj.getUrlFileSize();
      subj.download();
      if (!to.exists()) {
        fail("File \"" + to.getAbsolutePath() + "\" not downloaded from: \"" + from + "\"");
      }
      assertThat(size, is(to.length()));
    }
  }

  @Test
  public void downloadPartialNeverEnds() throws IOException {
    MediaHttpDownloader downloader = new MediaHttpDownloader(new NetHttpTransport(), null);
    //downloader.setContentRange(0, 1024);
    OutputStream out = new FileOutputStream(new File("test-file1"));
    downloader.download(new GenericUrl("http://speedtest.tele2.net/100MB.zip"), out);
  }
}