package jaxb.xml.promotion;

import entity.promotion152.Promotion;
import entity.promotion152.PromotionRoot;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

class PromotionSerializer {
  public static void main(String[] args) throws IOException, JAXBException, DatatypeConfigurationException,
                                                SAXException {
    PromotionRoot root = new PromotionRoot();
    Promotion promotion = new Promotion();
    Promotion.GlobalData globalData = new Promotion.GlobalData();
    globalData.setID("100");
    globalData.setDescription("testDesc");
    GregorianCalendar from = new GregorianCalendar();
    from.setTime(new Date(1504990800000L));
    globalData.setEffectiveDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(from));
    GregorianCalendar to = new GregorianCalendar();
    to.setTime(new Date(1505595600000L));
    globalData.setExpiryDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(to));
    promotion.setGlobalData(globalData);
    root.setPromotion(promotion);

    root = PromotionDeserializer.getPromotionRoot(Paths.get("promo.xml"));
    File file = new File("promo.xml");
    if (file.exists()) {
      file.delete();
    }
    file.createNewFile();
    JAXBContext jaxbContext = JAXBContext.newInstance(PromotionRoot.class);
    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

    // output pretty printed
    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

    jaxbMarshaller.marshal(root, file);
    jaxbMarshaller.marshal(root, System.out);
  }
}

class PromotionDeserializer {
  public static void main(String[] args) throws IOException, JAXBException, SAXException {
    Path path = Paths.get("promo.xml");
    String content = new String (Files.readAllBytes(path), Charset.defaultCharset());

    JAXBContext jaxbContext = JAXBContext.newInstance(PromotionRoot.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

    InputSource inputSource = new InputSource(new StringReader(content));
    final SAXParserFactory sax = SAXParserFactory.newInstance();
    sax.setNamespaceAware(false);
    final XMLReader reader;
    try {
      reader = sax.newSAXParser().getXMLReader();
    } catch (SAXException | ParserConfigurationException e) {
      throw new RuntimeException(e);
    }
    SAXSource saxSource = new SAXSource(reader, inputSource);

    PromotionRoot root = (PromotionRoot) jaxbUnmarshaller.unmarshal(saxSource);
    System.out.println(root);
  }

  static PromotionRoot getPromotionRoot(Path path) throws IOException, JAXBException {
    String content = new String (Files.readAllBytes(path), Charset.defaultCharset());

    JAXBContext jaxbContext = JAXBContext.newInstance(PromotionRoot.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

    InputSource inputSource = new InputSource(new StringReader(content));
    final SAXParserFactory sax = SAXParserFactory.newInstance();
    sax.setNamespaceAware(false);
    final XMLReader reader;
    try {
      reader = sax.newSAXParser().getXMLReader();
    } catch (SAXException | ParserConfigurationException e) {
      throw new RuntimeException(e);
    }
    SAXSource saxSource = new SAXSource(reader, inputSource);

    return (PromotionRoot) jaxbUnmarshaller.unmarshal(saxSource);
  }
}