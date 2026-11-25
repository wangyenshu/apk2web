package a2w.xml.transform;

public interface Templates {
  Transformer newTransformer() throws TransformerConfigurationException;
  java.util.Properties getOutputProperties();
}
