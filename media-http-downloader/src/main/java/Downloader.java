import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.google.api.client.googleapis.media.MediaHttpDownloaderProgressListener;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Sergey.Bespalko@x5.ru
 */
public class Downloader {
  private final URL from;
  private final File to;
  private double rateOfSpeed;

  private MediaHttpDownloader downloader;

  private Downloader(URL from, File to) {
    this.from = from;
    this.to = to;
  }

  public void download() throws IOException {
    long bytesToDownload = getUrlFileSize();
    if (to.exists()) {
      if (to.length() == bytesToDownload) {
        return;
      }
      downloader.setBytesDownloaded(to.length());
    }
    OutputStream outputStream = null;
    try {
      outputStream = new FileOutputStream(to, true);
      downloader.download(new GenericUrl(from), outputStream);
    } finally {
      if (outputStream != null) {
        outputStream.close();
      }
    }
  }

  public long getUrlFileSize() throws IOException {
    URLConnection conn = null;
    try {
      conn = from.openConnection();
      return conn.getContentLength();
    } finally {
      if (conn != null) {
        ((HttpURLConnection) conn).disconnect();
      }
    }
  }

  public static class Builder {
    private final URL from;
    private final File to;
    private int chunkSizeBytes = 10 * 1024;
    private double rateOfSpeed = 1;
    private MediaHttpDownloaderProgressListener progressListener;
    private HttpTransport transport = new NetHttpTransport();
    private HttpRequestInitializer initializer = null;

    public Builder(URL from, File to) {
      this.from = from;
      this.to = to;
    }

    public Builder withChunkSizeBytes(int chunkSizeBytes) {
      this.chunkSizeBytes = chunkSizeBytes;
      return this;
    }

    public Builder withRateOfSpeed(double rateOfSpeed) {
      this.rateOfSpeed = rateOfSpeed;
      return this;
    }

    public Builder setProgressListener(MediaHttpDownloaderProgressListener progressListener) {
      this.progressListener = progressListener;
      return this;
    }

    public Builder setTransport(HttpTransport transport) {
      this.transport = transport;
      return this;
    }

    public Builder setHttpRequestInitializer(HttpRequestInitializer initializer) {
      this.initializer = initializer;
      return this;
    }

    public Downloader build() {
      Downloader downloader = new Downloader(from, to);
      downloader.rateOfSpeed = rateOfSpeed;
      downloader.downloader = new MediaHttpDownloader(transport, initializer);
      downloader.downloader.setProgressListener(progressListener);
      downloader.downloader.setChunkSize(chunkSizeBytes);
      return downloader;
    }
  }

  MediaHttpDownloader getDownloader() {
    return downloader;
  }
}
