package a2w.xml;

import a2w.xml.dom.*;
import a2w.xml.sax.*;
import a2w.xml.sax.ext.LexicalHandler;
import java.util.ArrayList;

/** Builds a DOM tree from SAX events (DocumentBuilder.parse). */
final class DomBuilder implements ContentHandler, LexicalHandler {
  final Dom.DocumentImpl doc;
  final boolean nsAware, ignoreComments, coalescing;
  Node current;
  boolean inCdata;
  final ArrayList<String[]> pendingNs = new ArrayList<>();
  DomBuilder(boolean nsAware, boolean ignoreComments, boolean coalescing) {
    doc = new Dom.DocumentImpl(Dom.Impl.INSTANCE);
    doc.setStrictErrorChecking(false);
    this.nsAware = nsAware; this.ignoreComments = ignoreComments; this.coalescing = coalescing;
    current = doc;
  }
  @Override public void setDocumentLocator(Locator l) {}
  @Override public void startDocument() {}
  @Override public void endDocument() { doc.setStrictErrorChecking(true); }
  @Override public void startPrefixMapping(String prefix, String uri) { pendingNs.add(new String[] {prefix, uri}); }
  @Override public void endPrefixMapping(String prefix) {}
  @Override public void startElement(String uri, String local, String qName, Attributes atts) {
    Element e = nsAware ? doc.createElementNS(uri == null || uri.isEmpty() ? null : uri, qName) : doc.createElement(qName);
    if (nsAware) {
      for (String[] ns : pendingNs) e.setAttributeNS("http://www.w3.org/2000/xmlns/", ns[0].isEmpty() ? "xmlns" : "xmlns:" + ns[0], ns[1]);
      pendingNs.clear();
    }
    for (int i = 0; i < atts.getLength(); i++) {
      String q = atts.getQName(i);
      if (nsAware) {
        if (q.equals("xmlns") || q.startsWith("xmlns:")) { if (!e.hasAttribute(q)) e.setAttributeNS("http://www.w3.org/2000/xmlns/", q, atts.getValue(i)); }
        else e.setAttributeNS(atts.getURI(i).isEmpty() ? null : atts.getURI(i), q, atts.getValue(i));
      } else e.setAttribute(q, atts.getValue(i));
    }
    current.appendChild(e);
    current = e;
  }
  @Override public void endElement(String uri, String local, String qName) { current = current.getParentNode(); }
  @Override public void characters(char[] ch, int start, int length) {
    if (current == doc) return;
    String s = new String(ch, start, length);
    Node last = current.getLastChild();
    if (inCdata && !coalescing) {
      if (last instanceof CDATASection) ((CDATASection) last).appendData(s);
      else current.appendChild(doc.createCDATASection(s));
      return;
    }
    if (last != null && last.getNodeType() == Node.TEXT_NODE) ((Text) last).appendData(s);
    else current.appendChild(doc.createTextNode(s));
  }
  @Override public void ignorableWhitespace(char[] ch, int start, int length) { characters(ch, start, length); }
  @Override public void processingInstruction(String target, String data) { current.appendChild(doc.createProcessingInstruction(target, data)); }
  @Override public void skippedEntity(String name) { current.appendChild(doc.createEntityReference(name)); }
  @Override public void startDTD(String name, String publicId, String systemId) { doc.appendChild(new Dom.DocTypeImpl(doc, name, publicId, systemId, null)); }
  @Override public void endDTD() {}
  @Override public void startEntity(String name) {}
  @Override public void endEntity(String name) {}
  @Override public void startCDATA() { inCdata = true; if (!coalescing) current.appendChild(doc.createCDATASection("")); }
  @Override public void endCDATA() { inCdata = false; }
  @Override public void comment(char[] ch, int start, int length) { if (!ignoreComments && current != null) current.appendChild(doc.createComment(new String(ch, start, length))); }
}
