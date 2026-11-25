package a2w.xml;

import a2w.xml.dom.*;
import a2w.xml.transform.*;
import a2w.xml.transform.dom.DOMResult;
import a2w.xml.transform.dom.DOMSource;
import a2w.xml.transform.stream.StreamResult;
import a2w.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.HashMap;
import java.util.Properties;

/** The identity Transformer: copies a DOM or a stream to a DOM or a stream (serializing XML). */
public final class Identity extends Transformer {
  final Properties out = new Properties();
  final HashMap<String, Object> params = new HashMap<>();
  URIResolver resolver;
  ErrorListener listener;

  @Override public void transform(Source src, Result res) throws TransformerException {
    try {
      Node node;
      if (src instanceof DOMSource) {
        node = ((DOMSource) src).getNode();
        if (node == null) node = new Dom.DocumentImpl(Dom.Impl.INSTANCE);
      } else if (src instanceof StreamSource) {
        StreamSource s = (StreamSource) src;
        a2w.xml.sax.InputSource in = s.getReader() != null ? new a2w.xml.sax.InputSource(s.getReader())
            : s.getInputStream() != null ? new a2w.xml.sax.InputSource(s.getInputStream()) : new a2w.xml.sax.InputSource(s.getSystemId());
        node = a2w.xml.parsers.DocumentBuilderFactory.newNSInstance().newDocumentBuilder().parse(in);
      } else throw new TransformerException("unsupported Source " + (src == null ? null : src.getClass().getName()));

      if (res instanceof DOMResult) {
        DOMResult r = (DOMResult) res;
        Node target = r.getNode();
        if (target == null) {
          Document d = new Dom.DocumentImpl(Dom.Impl.INSTANCE);
          if (node instanceof Document) { for (Node k = node.getFirstChild(); k != null; k = k.getNextSibling()) d.appendChild(d.importNode(k, true)); }
          else d.appendChild(d.importNode(node, true));
          r.setNode(d);
        } else {
          Document d = target instanceof Document ? (Document) target : target.getOwnerDocument();
          if (node instanceof Document) { for (Node k = node.getFirstChild(); k != null; k = k.getNextSibling()) target.insertBefore(d.importNode(k, true), r.getNextSibling()); }
          else target.insertBefore(d.importNode(node, true), r.getNextSibling());
        }
        return;
      }
      if (!(res instanceof StreamResult)) throw new TransformerException("unsupported Result " + (res == null ? null : res.getClass().getName()));
      StreamResult r = (StreamResult) res;
      String enc = out.getProperty(OutputKeys.ENCODING, "UTF-8");
      Writer w = r.getWriter();
      boolean close = false;
      if (w == null) {
        OutputStream os = r.getOutputStream();
        if (os == null) {
          String id = r.getSystemId();
          if (id == null) throw new TransformerException("StreamResult has no target");
          if (id.startsWith("file:")) { id = id.substring(5); while (id.startsWith("//")) id = id.substring(1); }
          os = new FileOutputStream(id);
          close = true;
        }
        w = new OutputStreamWriter(os, enc);
      }
      Xml x = new Xml(w, "yes".equals(out.getProperty(OutputKeys.INDENT)), parseIndent());
      boolean html = "html".equalsIgnoreCase(out.getProperty(OutputKeys.METHOD));
      boolean text = "text".equalsIgnoreCase(out.getProperty(OutputKeys.METHOD));
      if (text) { w.write(node.getTextContent() == null ? "" : node.getTextContent()); }
      else {
        if (!html && !"yes".equals(out.getProperty(OutputKeys.OMIT_XML_DECLARATION))) {
          w.write("<?xml version=\"" + out.getProperty(OutputKeys.VERSION, "1.0") + "\" encoding=\"" + enc + "\"");
          if (out.getProperty(OutputKeys.STANDALONE) != null) w.write(" standalone=\"" + out.getProperty(OutputKeys.STANDALONE) + "\"");
          else if (node instanceof Document && ((Document) node).getXmlStandalone()) w.write(" standalone=\"yes\"");
          w.write("?>");
          if (x.indent) w.write("\n");
        }
        String sys = out.getProperty(OutputKeys.DOCTYPE_SYSTEM), pub = out.getProperty(OutputKeys.DOCTYPE_PUBLIC);
        Element root = node instanceof Document ? ((Document) node).getDocumentElement() : node instanceof Element ? (Element) node : null;
        if (sys != null && root != null) {
          w.write("<!DOCTYPE " + root.getTagName() + (pub != null ? " PUBLIC \"" + pub + "\" \"" + sys + "\"" : " SYSTEM \"" + sys + "\"") + ">");
          if (x.indent) w.write("\n");
        }
        x.node(node, 0);
      }
      w.flush();
      if (close) w.close();
    } catch (IOException | a2w.xml.sax.SAXException | a2w.xml.parsers.ParserConfigurationException e) {
      TransformerException t = new TransformerException(e);
      if (listener != null) listener.fatalError(t);
      throw t;
    }
  }
  int parseIndent() {
    String n = out.getProperty("{http://xml.apache.org/xslt}indent-amount", out.getProperty("{http://xml.apache.org/xalan}indent-amount", "0"));
    try { return Integer.parseInt(n.trim()); } catch (NumberFormatException e) { return 0; }
  }

  /** XML text of a DOM node */
  public static String toXml(Node n) {
    StringWriter w = new StringWriter();
    try { new Xml(w, false, 0).node(n, 0); } catch (IOException e) { throw new RuntimeException(e); }
    return w.toString();
  }

  static final class Xml {
    final Writer w;
    final boolean indent;
    final int amount;
    Xml(Writer w, boolean indent, int amount) { this.w = w; this.indent = indent; this.amount = amount; }
    void pad(int depth) throws IOException { if (!indent) return; w.write('\n'); for (int i = 0; i < depth * amount; i++) w.write(' '); }
    void node(Node n, int depth) throws IOException {
      switch (n.getNodeType()) {
        case Node.DOCUMENT_NODE: case Node.DOCUMENT_FRAGMENT_NODE: {
          boolean first = true;
          for (Node k = n.getFirstChild(); k != null; k = k.getNextSibling()) {
            if (!first && indent && n.getNodeType() == Node.DOCUMENT_NODE) w.write('\n');
            first = false;
            node(k, depth);
          }
          break;
        }
        case Node.ELEMENT_NODE: {
          Element e = (Element) n;
          w.write('<'); w.write(e.getTagName());
          NamedNodeMap at = e.getAttributes();
          for (int i = 0; i < at.getLength(); i++) {
            Attr a = (Attr) at.item(i);
            w.write(' '); w.write(a.getName()); w.write("=\""); w.write(escape(a.getValue(), true)); w.write('"');
          }
          if (!e.hasChildNodes()) { w.write("/>"); break; }
          w.write('>');
          boolean onlyText = true;
          for (Node k = e.getFirstChild(); k != null; k = k.getNextSibling()) if (k.getNodeType() != Node.TEXT_NODE && k.getNodeType() != Node.CDATA_SECTION_NODE) onlyText = false;
          for (Node k = e.getFirstChild(); k != null; k = k.getNextSibling()) {
            if (indent && !onlyText) {
              if (k.getNodeType() == Node.TEXT_NODE && k.getNodeValue().trim().isEmpty()) continue;
              pad(depth + 1);
            }
            node(k, depth + 1);
          }
          if (indent && !onlyText) pad(depth);
          w.write("</"); w.write(e.getTagName()); w.write('>');
          break;
        }
        case Node.TEXT_NODE: w.write(escape(n.getNodeValue(), false)); break;
        case Node.CDATA_SECTION_NODE: w.write("<![CDATA["); w.write(n.getNodeValue().replace("]]>", "]]]]><![CDATA[>")); w.write("]]>"); break;
        case Node.COMMENT_NODE: w.write("<!--"); w.write(n.getNodeValue()); w.write("-->"); break;
        case Node.PROCESSING_INSTRUCTION_NODE: w.write("<?"); w.write(n.getNodeName()); if (!n.getNodeValue().isEmpty()) { w.write(' '); w.write(n.getNodeValue()); } w.write("?>"); break;
        case Node.ENTITY_REFERENCE_NODE: w.write('&'); w.write(n.getNodeName()); w.write(';'); break;
        case Node.DOCUMENT_TYPE_NODE: {
          DocumentType d = (DocumentType) n;
          w.write("<!DOCTYPE "); w.write(d.getName());
          if (d.getPublicId() != null) w.write(" PUBLIC \"" + d.getPublicId() + "\" \"" + d.getSystemId() + "\"");
          else if (d.getSystemId() != null) w.write(" SYSTEM \"" + d.getSystemId() + "\"");
          if (d.getInternalSubset() != null) w.write(" [" + d.getInternalSubset() + "]");
          w.write('>');
          break;
        }
        case Node.ATTRIBUTE_NODE: w.write(escape(n.getNodeValue(), true)); break;
        default:
      }
    }
    static String escape(String s, boolean attr) {
      if (s == null) return "";
      StringBuilder b = new StringBuilder(s.length());
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        switch (c) {
          case '<': b.append("&lt;"); break;
          case '>': b.append("&gt;"); break;
          case '&': b.append("&amp;"); break;
          case '"': if (attr) b.append("&quot;"); else b.append(c); break;
          case '\n': if (attr) b.append("&#10;"); else b.append(c); break;
          case '\r': b.append("&#13;"); break;
          case '\t': if (attr) b.append("&#9;"); else b.append(c); break;
          default: b.append(c);
        }
      }
      return b.toString();
    }
  }

  @Override public void setParameter(String name, Object value) { params.put(name, value); }
  @Override public Object getParameter(String name) { return params.get(name); }
  @Override public void clearParameters() { params.clear(); }
  @Override public void setURIResolver(URIResolver r) { resolver = r; }
  @Override public URIResolver getURIResolver() { return resolver; }
  @Override public void setOutputProperties(Properties p) { out.clear(); if (p != null) out.putAll(p); }
  @Override public Properties getOutputProperties() { Properties p = new Properties(); p.putAll(out); return p; }
  @Override public void setOutputProperty(String name, String value) { out.setProperty(name, value); }
  @Override public String getOutputProperty(String name) { return out.getProperty(name); }
  @Override public void setErrorListener(ErrorListener l) { if (l == null) throw new IllegalArgumentException(); listener = l; }
  @Override public ErrorListener getErrorListener() { return listener; }
}
